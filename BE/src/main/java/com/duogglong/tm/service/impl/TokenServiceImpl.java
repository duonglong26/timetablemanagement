package com.duogglong.tm.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.duogglong.tm.dto.RoleDto;
import com.duogglong.tm.core.entity.SampleResponse;
import com.duogglong.tm.dto.TokenDto;
import com.duogglong.tm.dto.UserDto;
import com.duogglong.tm.entity.Token;
import com.duogglong.tm.repository.TokenRepository;
import com.duogglong.tm.service.TokenService;
import com.duogglong.tm.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class TokenServiceImpl implements TokenService {

    UserService userService;
    TokenRepository tokenRepository;

    @Autowired
    public TokenServiceImpl(UserService userService, TokenRepository tokenRepository) {
        this.userService = userService;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                UserDto user = userService.getUserByUsername(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoleList().stream().map(RoleDto::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), new SampleResponse<>(OK.value(), "Success", new TokenDto(access_token, refresh_token, user.getRoleList(), user.getUsername())));
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), new SampleResponse<>(FORBIDDEN.value(), "Access denied", exception.getMessage()));
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

    @Override
    public void save(Token token) {
        if (token != null) {
            deleteByUsername(token.getUsername());
            tokenRepository.save(token);
        }
    }

    @Override
    public boolean isExist(String username) {
        return tokenRepository.findByUsername(username) != null;
    }

    @Override
    public void deleteByAccessToken(String accessToken) {
        tokenRepository.deleteByAccessToken(accessToken);
    }

    @Override
    public void deleteByUsername(String username) {
        tokenRepository.deleteByUsername(username);
    }


}
