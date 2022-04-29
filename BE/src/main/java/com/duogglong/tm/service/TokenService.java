package com.duogglong.tm.service;

import com.duogglong.tm.entity.Token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface TokenService {
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void save(Token token);

    boolean isExist(String username);

    void deleteByAccessToken(String accessToken);

    void deleteByUsername(String username);
}
