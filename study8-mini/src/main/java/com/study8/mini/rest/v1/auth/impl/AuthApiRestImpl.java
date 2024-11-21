package com.study8.mini.rest.v1.auth.impl;

import com.study8.mini.common.rest.CommonApiResponse;
import com.study8.mini.rest.v1.auth.AuthApiRest;
import com.study8.mini.rest.v1.auth.req.LoginReq;
import com.study8.mini.rest.v1.auth.res.LoginRes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthApiRestImpl implements AuthApiRest {
    @Override
    public CommonApiResponse<LoginRes> login(LoginReq loginReq, BindingResult bindingResult,
            HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
