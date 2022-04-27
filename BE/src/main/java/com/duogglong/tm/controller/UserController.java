package com.duogglong.tm.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.duogglong.tm.dto.RoleDto;
import com.duogglong.tm.dto.SampleResponse;
import com.duogglong.tm.dto.UserDto;
import com.duogglong.tm.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/user")
public class UserController implements ApiController<UserDto> {

    @Autowired
    private UserService service;

    @Override
    @PostMapping
    public ResponseEntity<SampleResponse<UserDto>> create(@RequestBody UserDto dto) {
        return new ResponseEntity<>(new SampleResponse<>(HttpStatus.OK.value(), "Success", service.saveOrUpdate(dto)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SampleResponse<UserDto>> update(UserDto dto) {
        return null;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<SampleResponse<UserDto>> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(new SampleResponse<>(HttpStatus.OK.value(), "Success", service.getUserById(Long.parseLong(id))), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SampleResponse<Boolean>> delete(String id) {
        return null;
    }
}
