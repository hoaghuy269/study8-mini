package com.study8.mini.sys.service;

import com.study8.mini.sys.dto.SendEmailDto;
import com.study8.mini.sys.dto.SendEmailResultDto;

import java.util.Locale;

/**
 * EmailService
 * @Date: 2024-11-26
 * @Author: HuyNH
 * @Desc: EmailService
 */
public interface EmailService {
    SendEmailResultDto sendEmailSMTP(SendEmailDto sendEmailDto, Locale locale);
}
