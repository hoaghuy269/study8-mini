package com.study8.mini.rest.v1.auth.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study8.mini.auth.dto.AuthAccountDto;
import com.study8.mini.auth.service.AuthAccountService;
import com.study8.mini.common.rest.CommonApiResponse;
import com.study8.mini.core.util.LanguageUtils;
import com.study8.mini.rest.v1.req.LoginReq;
import com.study8.mini.rest.v1.req.RegisterReq;
import com.study8.mini.rest.v1.res.LoginRes;
import com.study8.mini.rest.v1.res.RegisterRes;
import com.study8.mini.rest.v1.auth.AuthRest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public CommonApiResponse<LoginRes> login(LoginReq loginReq, BindingResult bindingResult,
            HttpServletRequest request, HttpServletResponse response) {
        return null;
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
}