package com.study8.mini.sys.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface JwtService {
    String parseJwt(HttpServletRequest request);

    String generateJwtToken(Authentication authentication);

    String getUserNameFormToken(String token);

    boolean validateToken(String authToken);

    void blackListToken(String token);

    String generateJwtTokenForgotPassword(Long accountId);

    boolean validateTokenResetPassword(String authToken);

    Long getIdForToken(String token);
}
