package com.study8.mini.pm.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study8.mini.common.dto.CommonRestfulDto;
import com.study8.mini.core.constant.CoreConstant;
import com.study8.mini.core.constant.CoreSystem;
import com.study8.mini.ext.service.RestfulService;
import com.study8.mini.pm.dto.PmProcessDto;
import com.study8.mini.pm.entity.PmProcess;
import com.study8.mini.pm.enumf.ProcessCodeEnum;
import com.study8.mini.pm.ext.req.StartProcessExtReqDto;
import com.study8.mini.pm.ext.res.StartProcessExtResDto;
import com.study8.mini.pm.repository.PmProcessRepository;
import com.study8.mini.pm.service.PmProcessService;
import com.study8.mini.sys.constant.SysConstant;
import com.study8.mini.sys.service.SysConfigurationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * PmProcessServiceImpl
 * @Date: 2024-11-25
 * @Author: HuyNH
 * @Desc: PmProcessServiceImpl
 */
@Service
@Transactional
@Slf4j
public class PmProcessServiceImpl implements PmProcessService {
    @Autowired
    private PmProcessRepository pmProcessRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestfulService restfulService;

    @Autowired
    private SysConfigurationService sysConfigurationService;

    @Override
    public void startProcess(ProcessCodeEnum processCode, Long businessId) {
        Map<String, String> camundaConfig = sysConfigurationService.getMapConfig(SysConstant.CAMUNDA);
        if (MapUtils.isEmpty(camundaConfig)) {
            log.error("PmProcessServiceImpl | startProcess | Not found configuration of Camunda");
            return;
        }
        if (StringUtils.equals(camundaConfig.get(SysConstant.CAMUNDA_ENABLE), CoreConstant.ENABLE_CONFIG)) {
            String startProcessUrl = camundaConfig.get(SysConstant.CAMUNDA_URL) + camundaConfig.get(SysConstant.CAMUNDA_START_PROCESS_URL);

            StartProcessExtReqDto requestDto = new StartProcessExtReqDto();
            requestDto.setProcessCode(processCode.getValue());
            requestDto.setBusinessId(businessId.toString());

            CommonRestfulDto<StartProcessExtResDto> response = restfulService.post(
                    startProcessUrl,
                    requestDto,
                    new ParameterizedTypeReference<>() {}
            );

            if (ObjectUtils.isNotEmpty(response)) {
                this.saveProcess(businessId, response.getData().getProcessInstanceId(), processCode);
            }
        } else {
            log.info("PmProcessServiceImpl | startProcess | Camunda not allow by configuration");
        }

    }

    @Override
    public void completeTask(String processInstanceId, String stepName) {

    }

    @Override
    public PmProcessDto getProcess(ProcessCodeEnum processCode, Long businessId) {
        PmProcess data = pmProcessRepository.findByBusinessIdAndCode(businessId, processCode.getValue()).orElse(null);
        if (ObjectUtils.isNotEmpty(data)) {
            return objectMapper.convertValue(data, PmProcessDto.class);
        }
        return null;
    }

    public void saveProcess(Long businessId, String processInstanceId, ProcessCodeEnum processCode) {
        PmProcess entity = new PmProcess();
        entity.setCode(processCode.getValue());
        entity.setProcessId(processInstanceId);
        entity.setBusinessId(businessId);
        entity.setCreatedDate(LocalDateTime.now());
        entity.setCreatedId(CoreSystem.SYSTEM_ID);

        //Do save
        pmProcessRepository.save(entity);
    }
}
