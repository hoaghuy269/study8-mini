package com.study8.mini.rest.v1.auth;

import com.study8.mini.common.constant.CommonApiConstant;
import com.study8.mini.common.rest.CommonApiResponse;
import com.study8.mini.rest.v1.req.LoginReq;
import com.study8.mini.rest.v1.req.RegisterReq;
import com.study8.mini.rest.v1.res.LoginRes;
import com.study8.mini.rest.constant.RestApiConstant;
import com.study8.mini.rest.v1.res.RegisterRes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * AuthRest
 * @Date: 2024-11-19
 * @Author: HuyNH
 * @Desc: API Authentication
 */
@RequestMapping(CommonApiConstant.API_V1 + RestApiConstant.API_AUTH)
public interface AuthRest {
    /**
     * @API: /auth/api/v1/login
     * @Date: 2024-06-11
     * @Author: HuyNH
     * @Desc: Login API
     */
    @PostMapping(RestApiConstant.API_LOGIN)
    CommonApiResponse<LoginRes> login(@RequestBody @Valid LoginReq loginReq, BindingResult bindingResult,
            HttpServletRequest request, HttpServletResponse response);

    /**
     * @API: /auth/api/v1/register
     * @Date: 2024-06-11
     * @Author: HuyNH
     * @Desc: Register API
     */
    @PostMapping(RestApiConstant.API_REGISTER)
    CommonApiResponse<RegisterRes> register(@RequestBody @Valid RegisterReq registerReq, BindingResult bindingResult,
            HttpServletRequest request, HttpServletResponse response);
}
