package com.study8.mini.sys.service.impl;

import com.study8.mini.sys.constant.SysConstant;
import com.study8.mini.sys.entity.SysConfiguration;
import com.study8.mini.sys.repository.SysConfigurationRepository;
import com.study8.mini.sys.service.SysConfigurationService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
