package com.study8.camunda.rest.v1.impl;

import com.study8.camunda.common.rest.CommonApiResponse;
import com.study8.camunda.common.util.LanguageUtils;
import com.study8.camunda.rest.req.StartProcessReq;
import com.study8.camunda.rest.res.StartProcessRes;
import com.study8.camunda.rest.v1.ProcessRest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * ProcessRestImpl
 * @Date: 2024-12-02
 * @Author: HuyNH
 * @Desc: ProcessRestImpl
 */
@RestController
@Slf4j
public class ProcessRestImpl implements ProcessRest {
    @Override
    public CommonApiResponse<StartProcessRes> startProcess(StartProcessReq startProcessReq, BindingResult bindingResult,
            HttpServletRequest request, HttpServletResponse response) {
        Locale locale = LanguageUtils.getLanguageFromHeader(request);
        try {
            if (bindingResult.hasErrors()) {
                return CommonApiResponse.handleBindingResult(
                        bindingResult, locale);
            }

            StartProcessRes result = new StartProcessRes();

            return CommonApiResponse.handleSuccess(result, locale);
        } catch (Exception e) {
            log.error("AuthApiRestImpl | register", e);
            return CommonApiResponse.handleError(e.getMessage(), response);
        }
    }
}
