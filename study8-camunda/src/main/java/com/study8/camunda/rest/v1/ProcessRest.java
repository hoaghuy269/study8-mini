package com.study8.camunda.rest.v1;

import com.study8.camunda.common.constant.CommonApiConstant;
import com.study8.camunda.common.rest.CommonApiResponse;
import com.study8.camunda.constant.ApiConstant;
import com.study8.camunda.rest.req.CompleteTaskReq;
import com.study8.camunda.rest.req.StartProcessReq;
import com.study8.camunda.rest.res.CompleteTaskRes;
import com.study8.camunda.rest.res.StartProcessRes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ProcessRest
 * @Date: 2024-12-02
 * @Author: HuyNH
 * @Desc: ProcessRest
 */
@RequestMapping(CommonApiConstant.API_V1 + ApiConstant.API_PROCESS)
public interface ProcessRest {
    /**
     * @API: /api/v1/process/start
     * @Date: 2024-12-02
     * @Author: HuyNH
     * @Desc: API Start Process
     */
    @PostMapping(ApiConstant.API_START)
    CommonApiResponse<StartProcessRes> startProcess(@RequestBody @Valid StartProcessReq startProcessReq, BindingResult bindingResult,
            HttpServletRequest request, HttpServletResponse response);

    /**
     * @API: /api/v1/process/next
     * @Date: 2024-12-02
     * @Author: HuyNH
     * @Desc: API Complete Task
     */
    @PostMapping(ApiConstant.API_COMPLETE_TASK)
    CommonApiResponse<CompleteTaskRes> completeTask(@RequestBody @Valid CompleteTaskReq completeTaskReq, BindingResult bindingResult,
            HttpServletRequest request, HttpServletResponse response);
}
