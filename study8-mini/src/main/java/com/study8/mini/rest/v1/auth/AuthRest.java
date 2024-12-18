package com.study8.mini.rest.v1.auth;

import com.study8.mini.common.constant.CommonApiConstant;
import com.study8.mini.common.constant.CommonSwaggerConstant;
import com.study8.mini.common.rest.CommonApiResponse;
import com.study8.mini.rest.constant.RestSwaggerConstant;
import com.study8.mini.rest.req.ForgotPasswordReq;
import com.study8.mini.rest.req.LoginReq;
import com.study8.mini.rest.req.RegisterReq;
import com.study8.mini.rest.res.ForgotPasswordRes;
import com.study8.mini.rest.res.LoginRes;
import com.study8.mini.rest.constant.RestApiConstant;
import com.study8.mini.rest.res.RegisterRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = RestSwaggerConstant.AUTH_REST_TAG)
public interface AuthRest {
    /**
     * @API: /api/v1/auth/login
     * @Date: 2024-06-11
     * @Author: HuyNH
     * @Desc: Login API
     */
    @Operation(summary = RestSwaggerConstant.API_LOGIN_TAG)
    @ApiResponses(value = {
            @ApiResponse(responseCode = CommonSwaggerConstant.RESPONSE_200_CODE_TAG, description = CommonSwaggerConstant.RESPONSE_200_TAG),
            @ApiResponse(responseCode = CommonSwaggerConstant.RESPONSE_500_CODE_TAG, description = CommonSwaggerConstant.RESPONSE_500_TAG),
            @ApiResponse(responseCode = CommonSwaggerConstant.RESPONSE_400_CODE_TAG, description = CommonSwaggerConstant.RESPONSE_400_TAG),
            @ApiResponse(responseCode = CommonSwaggerConstant.RESPONSE_401_CODE_TAG, description = CommonSwaggerConstant.RESPONSE_401_TAG)
    })
    @PostMapping(RestApiConstant.API_LOGIN)
    CommonApiResponse<LoginRes> login(@RequestBody @Valid LoginReq loginReq, BindingResult bindingResult,
            HttpServletRequest request, HttpServletResponse response);

    /**
     * @API: /api/v1/auth/register
     * @Date: 2024-06-11
     * @Author: HuyNH
     * @Desc: Register API
     */
    @Operation(summary = RestSwaggerConstant.API_REGISTER_TAG)
    @ApiResponses(value = {
            @ApiResponse(responseCode = CommonSwaggerConstant.RESPONSE_200_CODE_TAG, description = CommonSwaggerConstant.RESPONSE_200_TAG),
            @ApiResponse(responseCode = CommonSwaggerConstant.RESPONSE_500_CODE_TAG, description = CommonSwaggerConstant.RESPONSE_500_TAG),
            @ApiResponse(responseCode = CommonSwaggerConstant.RESPONSE_400_CODE_TAG, description = CommonSwaggerConstant.RESPONSE_400_TAG),
            @ApiResponse(responseCode = CommonSwaggerConstant.RESPONSE_401_CODE_TAG, description = CommonSwaggerConstant.RESPONSE_401_TAG)
    })
    @PostMapping(RestApiConstant.API_REGISTER)
    CommonApiResponse<RegisterRes> register(@RequestBody @Valid RegisterReq registerReq, BindingResult bindingResult,
            HttpServletRequest request, HttpServletResponse response);

    /**
     * @API: /auth/api/v1/logout
     * @Date: 2024-12-03
     * @Author: HuyNH
     * @Desc: Logout API
     */
    @Operation(summary = RestSwaggerConstant.API_LOGOUT_TAG)
    @ApiResponses(value = {
            @ApiResponse(responseCode = CommonSwaggerConstant.RESPONSE_200_CODE_TAG, description = CommonSwaggerConstant.RESPONSE_200_TAG),
            @ApiResponse(responseCode = CommonSwaggerConstant.RESPONSE_500_CODE_TAG, description = CommonSwaggerConstant.RESPONSE_500_TAG),
            @ApiResponse(responseCode = CommonSwaggerConstant.RESPONSE_400_CODE_TAG, description = CommonSwaggerConstant.RESPONSE_400_TAG),
            @ApiResponse(responseCode = CommonSwaggerConstant.RESPONSE_401_CODE_TAG, description = CommonSwaggerConstant.RESPONSE_401_TAG)
    })
    @PostMapping(RestApiConstant.API_LOGOUT)
    CommonApiResponse<Void> logout(HttpServletRequest request, HttpServletResponse response);

    /**
     * @API: /auth/api/v1/forgot-password
     * @Date: 2024-12-03
     * @Author: HuyNH
     * @Desc: Forgot password
     */
    @Operation(summary = RestSwaggerConstant.API_FORGOT_PASSWORD_TAG)
    @ApiResponses(value = {
            @ApiResponse(responseCode = CommonSwaggerConstant.RESPONSE_200_CODE_TAG, description = CommonSwaggerConstant.RESPONSE_200_TAG),
            @ApiResponse(responseCode = CommonSwaggerConstant.RESPONSE_500_CODE_TAG, description = CommonSwaggerConstant.RESPONSE_500_TAG),
            @ApiResponse(responseCode = CommonSwaggerConstant.RESPONSE_400_CODE_TAG, description = CommonSwaggerConstant.RESPONSE_400_TAG),
            @ApiResponse(responseCode = CommonSwaggerConstant.RESPONSE_401_CODE_TAG, description = CommonSwaggerConstant.RESPONSE_401_TAG)
    })
    @PostMapping(RestApiConstant.API_FORGOT_PASSWORD)
    CommonApiResponse<ForgotPasswordRes> forgotPassword(@RequestBody @Valid ForgotPasswordReq forgotPasswordReq,
            HttpServletRequest request, HttpServletResponse response);

}
