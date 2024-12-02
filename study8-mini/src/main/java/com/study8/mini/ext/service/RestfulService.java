package com.study8.mini.ext.service;

import com.study8.mini.common.dto.CommonRestfulDto;
import org.springframework.core.ParameterizedTypeReference;

/**
 * RestfulService
 * @Date: 2024-12-02
 * @Author: HuyNH
 * @Desc: RestfulService
 */
public interface RestfulService {
    <T> CommonRestfulDto<T> post(String url, Object requestBody, ParameterizedTypeReference<CommonRestfulDto<T>> responseType);
}
