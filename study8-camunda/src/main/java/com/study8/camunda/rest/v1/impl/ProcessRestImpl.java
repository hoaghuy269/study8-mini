package com.study8.camunda.rest.v1.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study8.camunda.common.rest.CommonApiResponse;
import com.study8.camunda.common.util.LanguageUtils;
import com.study8.camunda.dto.ProcessDto;
import com.study8.camunda.rest.req.NextStepProcessReq;
import com.study8.camunda.rest.req.StartProcessReq;
import com.study8.camunda.rest.res.NextStepProcessRes;
import com.study8.camunda.rest.res.StartProcessRes;
import com.study8.camunda.rest.v1.ProcessRest;
import com.study8.camunda.service.ProcessService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ProcessService processService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public CommonApiResponse<StartProcessRes> startProcess(StartProcessReq startProcessReq, BindingResult bindingResult,
            HttpServletRequest request, HttpServletResponse response) {
        Locale locale = LanguageUtils.getLanguageFromHeader(request);
        try {
            if (bindingResult.hasErrors()) {
                return CommonApiResponse.handleBindingResult(
                        bindingResult, locale);
            }

            ProcessDto dto = objectMapper.convertValue(startProcessReq, ProcessDto.class);

            //Return result
            ProcessDto resultDto = processService.startProcess(dto);
            StartProcessRes result = objectMapper.convertValue(resultDto, StartProcessRes.class);

            return CommonApiResponse.handleSuccess(result, locale);
        } catch (Exception e) {
            log.error("ProcessRestImpl | startProcess", e);
            return CommonApiResponse.handleError(e.getMessage(), response);
        }
    }

    @Override
    public CommonApiResponse<NextStepProcessRes> nextStepProcess(NextStepProcessReq nextStepProcessReq,
            BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        Locale locale = LanguageUtils.getLanguageFromHeader(request);
        try {
            if (bindingResult.hasErrors()) {
                return CommonApiResponse.handleBindingResult(
                        bindingResult, locale);
            }

            ProcessDto dto = objectMapper.convertValue(nextStepProcessReq, ProcessDto.class);

            //Return result
            ProcessDto resultDto = processService.nextStepProcess(dto);
            NextStepProcessRes result = objectMapper.convertValue(resultDto, NextStepProcessRes.class);

            return CommonApiResponse.handleSuccess(result, locale);
        } catch (Exception e) {
            log.error("ProcessRestImpl | nextStepProcess", e);
            return CommonApiResponse.handleError(e.getMessage(), response);
        }
    }
}
