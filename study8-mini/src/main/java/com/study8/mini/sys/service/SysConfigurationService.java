package com.study8.mini.sys.service;

import com.study8.mini.sys.dto.SysConfigurationDto;

import java.util.Map;

/**
 * SysConfigurationService
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: SysConfigurationService
 */
public interface SysConfigurationService {
    String getStringConfig(String groupCode, String code);

    Integer getIntConfig(String groupCode, String code);

    Map<String, String> getMapConfig(String groupCode);

    SysConfigurationDto getPublicConfig(String groupCode, String code);
}
