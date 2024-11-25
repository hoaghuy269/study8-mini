package com.study8.mini.pm.service.impl;

import com.study8.mini.pm.entity.PmProcess;
import com.study8.mini.pm.repository.PmProcessRepository;
import com.study8.mini.pm.service.PmProcessService;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public void saveProcess(Long businessId, String processInstanceId, Long userId) {
        PmProcess entity = new PmProcess();
        entity.setProcessId(processInstanceId);
        entity.setBusinessId(businessId);
        entity.setCreatedDate(LocalDateTime.now());

        Long createdId = Optional.ofNullable(userId).orElse(1L);
        entity.setCreatedId(createdId);

        //Do save
        pmProcessRepository.save(entity);
    }
}
