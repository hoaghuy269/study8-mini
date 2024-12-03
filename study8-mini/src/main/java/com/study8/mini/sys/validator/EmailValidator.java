package com.study8.mini.sys.validator;

import com.study8.mini.sys.dto.SendEmailDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * EmailValidator
 * @Date: 2024-06-28
 * @Author: HuyNH
 * @Desc: Email Validator
 */
@Component
public class EmailValidator {
    public boolean isDataValid(SendEmailDto sendEmailDto) {
        boolean result = true;
        if (CollectionUtils.isEmpty(sendEmailDto.getTo())
                || StringUtils.isEmpty(sendEmailDto.getSubject())) {
            result = false;
            return result;
        }
        return result;
    }
}
