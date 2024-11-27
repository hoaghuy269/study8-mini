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

                //Save Log
//                this.saveLog(mimeMessage,
//                        sendEmailDto.getTemplateCode(),
//                        content,
//                        currentDate,
//                        true,
//                        "SUCCESS"
//                );
            } catch (Exception e) {
                log.error("EmailServiceImpl | sendEmailSMTP", e);
                sendEmailResultDto.setIsSuccess(false);
                sendEmailResultDto.setErrorMessage(e.getMessage());
                sendEmailResultDto.setTime(currentDate);

                //Save Log
//                this.saveLog(mimeMessage,
//                        sendEmailDto.getTemplateCode(),
//                        null,
//                        currentDate,
//                        false,
//                        e.getMessage()
//                );

                return sendEmailResultDto;
            }
            //Success
            sendEmailResultDto.setIsSuccess(true);
            sendEmailResultDto.setTime(currentDate);
            return sendEmailResultDto;
        } else {
            sendEmailResultDto.setIsSuccess(false);
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

//    private void saveLog(MimeMessage mimeMessage,
//            String templateCode,
//            String content,
//            LocalDateTime currentDate,
//            boolean isSuccess,
//            String message) {
//        SystemEmailLogDto systemEmailLogDto = new SystemEmailLogDto();
//
//        try {
//            String ipAddress = InetAddress.getLocalHost().getHostAddress();
//            systemEmailLogDto.setEmailServerIp(StringUtils.isNotEmpty(ipAddress)
//                    ? ipAddress
//                    : null);
//        } catch (UnknownHostException e) {
//            log.error("EmailServiceImpl | saveLog", e);
//        }
//
//
//        //Get config
//        systemEmailLogDto.setEmailServer(mailConfig.getEmailServer());
//        systemEmailLogDto.setEmailHost(mailConfig.getMailHost());
//        systemEmailLogDto.setEmailPort(mailConfig.getMailPort());
//
//        try {
//            //To
//            Address[] recipientArrayTo = mimeMessage
//                    .getRecipients(MimeMessage.RecipientType.TO);
//            if (ArrayUtils.isNotEmpty(recipientArrayTo)) {
//                systemEmailLogDto.setEmailTo(this
//                        .convertAddressesToString(
//                                recipientArrayTo));
//            }
//
//            //Cc
//            Address[] recipientArrayCc = mimeMessage
//                    .getRecipients(MimeMessage.RecipientType.CC);
//            if (ArrayUtils.isNotEmpty(recipientArrayCc)) {
//                systemEmailLogDto.setEmailCc(this
//                        .convertAddressesToString(
//                                recipientArrayCc));
//            }
//
//            //Bcc
//            Address[] recipientArrayBcc = mimeMessage
//                    .getRecipients(MimeMessage.RecipientType.BCC);
//            if (ArrayUtils.isNotEmpty(recipientArrayBcc)) {
//                systemEmailLogDto.setEmailBcc(this
//                        .convertAddressesToString(
//                                recipientArrayBcc));
//            }
//
//            //Subject
//            systemEmailLogDto.setEmailSubject(mimeMessage.getSubject());
//        } catch (MessagingException e) {
//            log.error("EmailServiceImpl | saveLog", e);
//        }
//
//
//        systemEmailLogDto.setEmailTemplateCode(templateCode);
//        systemEmailLogDto.setEmailContent(content);
//        systemEmailLogDto.setSentDate(currentDate);
//        systemEmailLogDto.setSystemLog(message);
//
//        if (isSuccess) {
//            systemEmailLogDto.setSentStatus(200);
//        } else {
//            systemEmailLogDto.setSentStatus(400);
//        }
//
//        systemEmailLogService.saveLog(systemEmailLogDto, SettingVariable.SYSTEM_ADMIN_ID);
//    }
//
//    private String convertAddressesToString(Address[] addresses) {
//        return Arrays.stream(addresses)
//                .map(address -> ((InternetAddress) address).getAddress())
//                .collect(Collectors.joining(", "));
//    }
}
