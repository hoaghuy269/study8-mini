package com.study8.mini.sys.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study8.mini.sys.dto.SysConfigurationDto;
import com.study8.mini.sys.entity.SysConfiguration;
import com.study8.mini.sys.repository.SysConfigurationRepository;
import com.study8.mini.sys.service.SysConfigurationService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * SysConfigurationServiceImpl
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: SysConfigurationServiceImpl
 */
@Service
@Transactional
public class SysConfigurationServiceImpl
        implements SysConfigurationService {
    @Autowired
    private SysConfigurationRepository sysConfigurationRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String getStringConfig(String groupCode, String code) {
        return sysConfigurationRepository.findByGroupCodeAndCode(groupCode, code)
                .filter(ObjectUtils::isNotEmpty)
                .map(SysConfiguration::getValue)
                .orElse(null);
    }

    @Override
    public Integer getIntConfig(String groupCode, String code) {
        return sysConfigurationRepository.findByGroupCodeAndCode(groupCode, code)
                .filter(ObjectUtils::isNotEmpty)
                .map(SysConfiguration::getValue)
                .map(Integer::parseInt)
                .orElse(null);
    }

    @Override
    public Map<String, String> getMapConfig(String groupCode) {
        Optional<List<SysConfiguration>> data = sysConfigurationRepository
                .findByGroupCode(groupCode);
        return data.map(sysConfigurations -> sysConfigurations
                .stream()
                .collect(Collectors.toMap(SysConfiguration::getCode, SysConfiguration::getValue)))
                .orElse(null);
    }

    @Override
    public SysConfigurationDto getPublicConfig(String groupCode, String code) {
        Optional<SysConfiguration> data = sysConfigurationRepository.findByGroupCodeAndCodeAndIsPublic(groupCode, code);
        return data.map(sysConfiguration -> objectMapper.convertValue(sysConfiguration, SysConfigurationDto.class))
                .orElse(null);
    }
}
