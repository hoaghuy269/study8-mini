package com.study8.mini.configuration.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.study8.mini.sys.constant.SysConstant;
import com.study8.mini.sys.service.SysConfigurationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Map;
import java.util.Properties;

/**
 * BeanConfiguration
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: BeanConfiguration
 */
@Configuration
@EnableCaching
public class BeanConfiguration {
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1))
                .disableCachingNullValues();
        return RedisCacheManager
                .builder(redisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }

    @Bean
    public JavaMailSender javaMailSender(
            @Value("${spring.mail.host}") String mailHost,
            @Value("${spring.mail.port}") int mailPort,
            @Value("${spring.mail.properties.mail.smtp.auth}") boolean mailSmtpAuth,
            @Value("${spring.mail.properties.mail.smtp.starttls.enable}") boolean mailSmtpStarttlsEnable,
            SysConfigurationService sysConfigurationService) {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setPort(mailPort);

        Map<String, String> emailConfigMap = sysConfigurationService
                .getMapConfig(SysConstant.EMAIL);
        mailSender.setUsername(emailConfigMap.get(SysConstant.EMAIL_USERNAME));
        mailSender.setPassword(emailConfigMap.get(SysConstant.EMAIL_PASSWORD));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", String.valueOf(mailSmtpAuth));
        props.put("mail.smtp.starttls.enable", String.valueOf(mailSmtpStarttlsEnable));
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofSeconds(5))
                .setReadTimeout(Duration.ofSeconds(10))
                .build();
    }
}
