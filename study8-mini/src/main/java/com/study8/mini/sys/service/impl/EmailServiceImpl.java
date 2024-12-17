package com.study8.mini.sys.service.impl;

import com.study8.mini.common.constant.CommonFileConstant;
import com.study8.mini.common.constant.CommonSignConstant;
import com.study8.mini.common.enumf.CommonLanguageEnum;
import com.study8.mini.core.constant.CoreExceptionConstant;
import com.study8.mini.core.util.ResourceUtils;
import com.study8.mini.sys.constant.SysConstant;
import com.study8.mini.sys.dto.SendEmailDto;
import com.study8.mini.sys.dto.SendEmailResultDto;
import com.study8.mini.sys.enumf.EmailTemplateEnum;
import com.study8.mini.sys.service.EmailService;
import com.study8.mini.sys.validator.EmailValidator;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;

/**
 * EmailServiceImpl
 * @Date: 2024-11-26
 * @Author: HuyNH
 * @Desc: EmailServiceImpl
 */
@Service
@Slf4j
@Transactional
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailValidator emailValidator;

    @Override
    public SendEmailResultDto sendEmailSMTP(SendEmailDto sendEmailDto, Locale locale) {
        SendEmailResultDto sendEmailResultDto = new SendEmailResultDto();
        LocalDateTime currentDate = LocalDateTime.now();

        boolean isValidated = emailValidator.isDataValid(sendEmailDto); //Validate before send email
        if (isValidated) {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            try {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMessageHelper.setTo(sendEmailDto.getTo().toArray(new String[0]));
                if (CollectionUtils.isNotEmpty(sendEmailDto.getCc())) { //cc
                    mimeMessageHelper.setCc(sendEmailDto.getCc().toArray(new String[0]));
                }
                if (CollectionUtils.isNotEmpty(sendEmailDto.getBcc())) { //bcc
                    mimeMessageHelper.setBcc(sendEmailDto.getBcc().toArray(new String[0]));
                }
                mimeMessageHelper.setSubject(sendEmailDto.getSubject()); //Subject

                String content = this.getTemplateResource(sendEmailDto.getTemplateCode(),
                        locale.getLanguage(), sendEmailDto.getMapData());
                mimeMessageHelper.setText(content, true); //Content

                //Do send mail
                javaMailSender.send(mimeMessage);
            } catch (Exception e) {
                log.error("EmailServiceImpl | sendEmailSMTP", e);
                sendEmailResultDto.setSuccess(false);
                sendEmailResultDto.setErrorMessage(e.getMessage());
                sendEmailResultDto.setTime(currentDate);

                return sendEmailResultDto;
            }
            //Success
            sendEmailResultDto.setSuccess(true);
            sendEmailResultDto.setTime(currentDate);
            return sendEmailResultDto;
        } else {
            sendEmailResultDto.setSuccess(false);
            sendEmailResultDto.setErrorMessage(
                    ResourceUtils.getMessage(CoreExceptionConstant.EXCEPTION_DATA_PROCESSING,
                            locale));
            sendEmailResultDto.setTime(currentDate);
            return sendEmailResultDto;
        }
    }

    private String getTemplateResource(EmailTemplateEnum templateCode, String language, Map<String, Object> mapData) {
        String templateName = templateCode.getValue();
        String templateLanguage = CommonLanguageEnum.resolveByValue(language)
                .toString();
        StringBuilder templateFullNameBuilder = new StringBuilder();
        String htmlTemplate = ResourceUtils.getStringResource(
                SysConstant.RESOURCE_EMAIL_TEMPLATES + CommonSignConstant.SPLASH +
                        templateFullNameBuilder.append(templateName)
                                .append(CommonSignConstant.DASH)
                                .append(templateLanguage)
                                .append(CommonSignConstant.DOT)
                                .append(CommonFileConstant.HTML));
        StringSubstitutor stringSubstitutor = new StringSubstitutor(mapData);
        return stringSubstitutor
                .replace(htmlTemplate);
    }
}
