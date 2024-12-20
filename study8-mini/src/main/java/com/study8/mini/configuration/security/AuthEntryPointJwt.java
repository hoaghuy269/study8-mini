package com.study8.mini.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study8.mini.common.rest.CommonApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * AuthEntryPointJwt
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: AuthEntryPointJwt
 */
@Slf4j
@Service
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        log.error("AuthEntryPointJwt | Unauthorized error at URL: {}", request.getRequestURI(), authException);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        CommonApiResponse<Void> body = CommonApiResponse.handleAuthError(authException.getMessage());

        objectMapper.writeValue(response.getOutputStream(), body);
    }
}
