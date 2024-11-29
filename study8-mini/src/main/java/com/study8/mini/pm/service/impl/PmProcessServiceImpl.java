package com.study8.mini.pm.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study8.mini.pm.dto.PmProcessDto;
import com.study8.mini.pm.entity.PmProcess;
import com.study8.mini.pm.enumf.ProcessCodeEnum;
import com.study8.mini.pm.repository.PmProcessRepository;
import com.study8.mini.pm.service.PmProcessService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

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

    @Override
    public void saveProcess(Long businessId, String processInstanceId, Long createdId) {
        PmProcess entity = new PmProcess();
        entity.setProcessId(processInstanceId);
        entity.setBusinessId(businessId);
        entity.setCreatedDate(LocalDateTime.now());
        entity.setCreatedId(Optional.ofNullable(createdId).orElse(1L));

        //Do save
        pmProcessRepository.save(entity);
    }

    @Override
    public PmProcessDto getProcess(Long businessId, ProcessCodeEnum process) {
        PmProcess data = pmProcessRepository.findByBusinessIdAndCode(businessId, process.getValue()).orElse(null);
        if (ObjectUtils.isNotEmpty(data)) {
            return objectMapper.convertValue(data, PmProcessDto.class);
        }
        return null;
    }
}
