package com.study8.mini.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * CommonRestfulDto
 * @Date: 2024-12-02
 * @Author: HuyNH
 * @Desc: CommonRestfulDto
 */
@Getter
@Setter
@NoArgsConstructor
public class CommonRestfulDto<T> {
    private int statusCode;
    private String title;
    private String message;
    private List<String> errorMessages;
    private String time;
    private T data;
}
