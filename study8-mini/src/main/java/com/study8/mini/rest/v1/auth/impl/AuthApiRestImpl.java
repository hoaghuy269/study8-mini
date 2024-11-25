package com.study8.mini.rest.v1.auth.impl;

import com.study8.mini.auth.dto.AuthAccountDto;
import com.study8.mini.auth.service.AuthAccountService;
import com.study8.mini.common.rest.CommonApiResponse;
import com.study8.mini.core.util.LanguageUtils;
import com.study8.mini.rest.req.LoginReq;
import com.study8.mini.rest.req.RegisterReq;
import com.study8.mini.rest.res.LoginRes;
import com.study8.mini.rest.res.RegisterRes;
import com.study8.mini.rest.v1.auth.AuthApiRest;
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
public class AuthApiRestImpl implements AuthApiRest {
    @Autowired
    private AuthAccountService authAccountService;

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

            AuthAccountDto registerDto = new AuthAccountDto();
            registerDto.setEmail(registerReq.getEmail());
            registerDto.setStep(registerReq.getStep());

            AuthAccountDto dto = authAccountService.register(registerDto, locale);

            RegisterRes result = new RegisterRes();
            result.setUserId(dto.getId());
            return CommonApiResponse.handleSuccess(result, locale);
        } catch (Exception e) {
            log.error("AuthApiRestImpl | register", e);
            return CommonApiResponse.handleError(e.getMessage());
        }
    }
}
