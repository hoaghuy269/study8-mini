package com.study8.mini.rest.v1.auth;

import com.study8.mini.common.constant.CommonApiConstant;
import com.study8.mini.common.rest.CommonApiResponse;
import com.study8.mini.rest.req.LoginReq;
import com.study8.mini.rest.req.RegisterReq;
import com.study8.mini.rest.res.LoginRes;
import com.study8.mini.rest.constant.ApiConstant;
import com.study8.mini.rest.res.RegisterRes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * AuthRest
 *
 * @Date: 2024-11-19
 * @Author: HuyNH
 * @Desc: API Authentication
 */
@RequestMapping(ApiConstant.API_AUTH + CommonApiConstant.API_V1)
public interface AuthApiRest {
    /**
     * @API: /auth/api/v1/login
     * @Date: 2024-06-11
     * @Author: HuyNH
     * @Desc: Login API
     */
    @PostMapping(ApiConstant.API_LOGIN)
    CommonApiResponse<LoginRes> login(@RequestBody @Valid LoginReq loginReq, BindingResult bindingResult,
            HttpServletRequest request, HttpServletResponse response);

    /**
     * @API: /auth/api/v1/register
     * @Date: 2024-06-11
     * @Author: HuyNH
     * @Desc: Register API
     */
    @PostMapping(ApiConstant.API_REGISTER)
    CommonApiResponse<RegisterRes> register(@RequestBody @Valid RegisterReq registerReq, BindingResult bindingResult,
            HttpServletRequest request, HttpServletResponse response);
}
