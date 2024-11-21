package com.study8.mini.sys.service;

/**
 * SysConfigurationService
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: SysConfigurationService
 */
public interface SysConfigurationService {
    String getStringConfig(String groupCode, String code);

    Integer getIntConfig(String groupCode, String code);
}
