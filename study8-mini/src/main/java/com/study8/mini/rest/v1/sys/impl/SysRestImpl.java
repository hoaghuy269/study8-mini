package com.study8.mini.rest.v1.sys.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study8.mini.common.rest.CommonApiResponse;
import com.study8.mini.core.constant.CoreExceptionConstant;
import com.study8.mini.core.util.ExceptionUtils;
import com.study8.mini.core.util.LanguageUtils;
import com.study8.mini.rest.res.SysConfigRes;
import com.study8.mini.rest.v1.sys.SysRest;
import com.study8.mini.sys.dto.SysConfigurationDto;
import com.study8.mini.sys.service.SysConfigurationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * SysRestImpl
 * @Date: 2024-12-17
 * @Author: HuyNH
 * @Desc: SysRestImpl
 */
@RestController
@Slf4j
public class SysRestImpl implements SysRest {
    @Autowired
    private SysConfigurationService sysConfigurationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public CommonApiResponse<SysConfigRes> getConfiguration(String groupCode, String code, HttpServletRequest request,
            HttpServletResponse response) {
        Locale locale = LanguageUtils.getLanguageFromHeader(request);
        try {
            SysConfigurationDto dto = sysConfigurationService.getPublicConfig(groupCode, code);

            if (ObjectUtils.isEmpty(dto)) {
                ExceptionUtils.throwApplicationException(
                        CoreExceptionConstant.EXCEPTION_DATA_NOT_FOUND, locale);
            }

            SysConfigRes result = objectMapper.convertValue(dto, SysConfigRes.class);

            return CommonApiResponse.handleSuccess(result, locale);
        } catch (Exception e) {
            log.error("SysRestImpl | getConfiguration", e);
            return CommonApiResponse.handleError(e.getMessage(), response);
        }
    }
}
