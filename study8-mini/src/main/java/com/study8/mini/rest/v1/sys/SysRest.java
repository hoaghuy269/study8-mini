package com.study8.mini.rest.v1.sys;

import com.study8.mini.common.constant.CommonApiConstant;
import com.study8.mini.common.constant.CommonSwaggerConstant;
import com.study8.mini.common.rest.CommonApiResponse;
import com.study8.mini.rest.constant.RestApiConstant;
import com.study8.mini.rest.constant.RestSwaggerConstant;
import com.study8.mini.rest.res.SysConfigRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * SysRest
 * @Date: 2024-12-17
 * @Author: HuyNH
 * @Desc: API System
 */
@RequestMapping(CommonApiConstant.API_V1 + RestApiConstant.API_SYS)
@Tag(name = RestSwaggerConstant.SYS_REST_TAG)
public interface SysRest {
    /**
     * @API: /api/v1/sys/configuration
     * @Date: 2024-12-17
     * @Author: HuyNH
     * @Desc: Api get system configuration
     */
    @Operation(summary = RestSwaggerConstant.API_CONFIGURATION_TAG)
    @ApiResponses(value = {
            @ApiResponse(responseCode = CommonSwaggerConstant.RESPONSE_200_CODE_TAG, description = CommonSwaggerConstant.RESPONSE_200_TAG),
            @ApiResponse(responseCode = CommonSwaggerConstant.RESPONSE_500_CODE_TAG, description = CommonSwaggerConstant.RESPONSE_500_TAG),
            @ApiResponse(responseCode = CommonSwaggerConstant.RESPONSE_400_CODE_TAG, description = CommonSwaggerConstant.RESPONSE_400_TAG),
            @ApiResponse(responseCode = CommonSwaggerConstant.RESPONSE_401_CODE_TAG, description = CommonSwaggerConstant.RESPONSE_401_TAG)
    })
    @GetMapping(RestApiConstant.API_CONFIGURATION)
    CommonApiResponse<SysConfigRes> getConfiguration(@RequestParam(name = "groupCode") @Parameter(description = "Group Code", example = "GROUP_CODE", required = true) String groupCode,
            @RequestParam(name = "code") @Parameter(description = "Code", example = "CODE", required = true) String code,
            HttpServletRequest request, HttpServletResponse response);
}
