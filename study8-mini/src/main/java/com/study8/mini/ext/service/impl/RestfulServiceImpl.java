package com.study8.mini.ext.service.impl;

import com.study8.mini.common.dto.CommonRestfulDto;
import com.study8.mini.ext.service.RestfulService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * RestfulServiceImpl
 * @Date: 2024-12-02
 * @Author: HuyNH
 * @Desc: RestfulServiceImpl
 */
@Service
@Transactional
@Slf4j
public class RestfulServiceImpl implements RestfulService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public <T> CommonRestfulDto<T> post(String url, Object requestBody, ParameterizedTypeReference<CommonRestfulDto<T>> responseType) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Object> requestEntity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<CommonRestfulDto<T>> result = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    responseType
            );

            return result.getBody();
        } catch (Exception e) {
            log.error("RestfulServiceImpl | post", e);
            return null;
        }
    }

}
