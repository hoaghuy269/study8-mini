package com.study8.mini.sys.dto;

import com.study8.mini.sys.enumf.EmailTemplateEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * SendEmailDto
 * @Date: 2024-07-26
 * @Author: HuyNH
 * @Desc: SendEmailDto
 */
@Getter
@Setter
@NoArgsConstructor
public class SendEmailDto {
    private EmailTemplateEnum templateCode;
    private String subject;
    private List<String> to; //Not allow null
    private List<String> cc; //Allow null
    private List<String> bcc; //Allow null
    Map<String, Object> mapData;
}