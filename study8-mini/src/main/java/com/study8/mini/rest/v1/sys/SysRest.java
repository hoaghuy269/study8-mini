package com.study8.mini.rest.v1.sys;

import com.study8.mini.common.constant.CommonApiConstant;
import com.study8.mini.common.rest.CommonApiResponse;
import com.study8.mini.rest.constant.RestApiConstant;
import com.study8.mini.rest.res.SysConfigRes;
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
public interface SysRest {
    /**
     * @API: /api/v1/sys/configuration
     * @Date: 2024-12-17
     * @Author: HuyNH
     * @Desc: Api get system configuration
     */
    @GetMapping(RestApiConstant.API_CONFIGURATION)
    CommonApiResponse<SysConfigRes> getConfiguration(@RequestParam(name = "groupCode") String groupCode, @RequestParam(name = "code") String code,
            HttpServletRequest request, HttpServletResponse response);
}
