package com.study8.mini.rest.v1.auth.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study8.mini.auth.dto.AuthAccountDto;
import com.study8.mini.auth.service.AuthAccountService;
import com.study8.mini.common.rest.CommonApiResponse;
import com.study8.mini.configuration.constant.SecurityConstant;
import com.study8.mini.core.dto.UserAuthenticationToken;
import com.study8.mini.core.util.LanguageUtils;
import com.study8.mini.rest.v1.auth.AuthRest;
import com.study8.mini.rest.v1.req.LoginReq;
import com.study8.mini.rest.v1.req.RegisterReq;
import com.study8.mini.rest.v1.res.LoginRes;
import com.study8.mini.rest.v1.res.RegisterRes;
import com.study8.mini.sys.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * AuthApiRestImpl
 * @Date: 2024-11-25
 * @Author: HuyNH
 * @Desc: AuthApiRestImpl
 */
@RestController
@Slf4j
public class AuthRestImpl implements AuthRest {
    @Autowired
    private AuthAccountService authAccountService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public CommonApiResponse<LoginRes> login(LoginReq loginReq, BindingResult bindingResult,
            HttpServletRequest request, HttpServletResponse response) {
        Locale locale = LanguageUtils.getLanguageFromHeader(request);
        try {
            if (bindingResult.hasErrors()) {
                return CommonApiResponse.handleBindingResult(
                        bindingResult, locale);
            }

            //Authentication
            UserAuthenticationToken userLogin = new UserAuthenticationToken(loginReq.getUsername(), loginReq.getPassword(), locale);
            Authentication authentication = authenticationManager.authenticate(userLogin);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            //Generate token
            String token = jwtService.generateJwtToken(authentication);

            LoginRes result = new LoginRes();
            result.setToken(token);

            return CommonApiResponse.handleSuccess(result, locale);
        } catch (BadCredentialsException e) {
            log.error("AuthService | login", e);
            return CommonApiResponse.handleError(e.getMessage(), response);
        } catch (Exception e) {
            log.error("AuthApiRestImpl | login", e);
            return CommonApiResponse.handleError(e.getMessage(), response);
        }
    }

    @Override
    public CommonApiResponse<RegisterRes> register(RegisterReq registerReq,
            BindingResult bindingResult, HttpServletRequest request,
            HttpServletResponse response) {
        Locale locale = LanguageUtils.getLanguageFromHeader(request);
        try {
            if (bindingResult.hasErrors()) {
                return CommonApiResponse.handleBindingResult(
                        bindingResult, locale);
            }

            AuthAccountDto registerDto = objectMapper.convertValue(registerReq, AuthAccountDto.class);
            AuthAccountDto dto = authAccountService.register(registerDto, locale);

            RegisterRes result = new RegisterRes();
            result.setUserId(dto.getId());
            result.setEmail(dto.getEmail());

            return CommonApiResponse.handleSuccess(result, locale);
        } catch (Exception e) {
            log.error("AuthApiRestImpl | register", e);
            return CommonApiResponse.handleError(e.getMessage(), response);
        }
    }

    @Override
    public CommonApiResponse<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        Locale locale = LanguageUtils.getLanguageFromHeader(request);
        try {
            String authHeader = request.getHeader(SecurityConstant.AUTHORIZATION);
            if (authHeader == null || !authHeader.startsWith(SecurityConstant.BEARER)) {
                return CommonApiResponse.handleError("Invalid token", response);
            }

            String token = authHeader.substring(7);
            jwtService.blackListToken(token);

            SecurityContextHolder.clearContext();

            return CommonApiResponse.handleSuccess(null, locale);
        } catch (Exception e) {
            log.error("AuthApiRestImpl | logout", e);
            return CommonApiResponse.handleError(e.getMessage(), response);
        }
    }
}

