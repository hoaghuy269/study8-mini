package com.study8.mini.core.util;

import com.study8.mini.core.constant.CoreConstant;
import com.study8.mini.core.exception.ApplicationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * ExceptionUtils
 * @Date: 2024-06-17
 * @Author: HuyNH
 * @Desc: Throw exception utils
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionUtils {
    public static void throwApplicationException(String messageKey, Locale locale)
            throws ApplicationException {
        ResourceBundle messages = ResourceBundle.getBundle(CoreConstant.MESSAGES_SOURCE, locale);
        String message = messages.getString(messageKey);
        throw new ApplicationException(message);
    }

    public static void throwApplicationException(String messageKey, Locale locale, Object[] parameters)
            throws ApplicationException {
        ResourceBundle messages = ResourceBundle.getBundle(CoreConstant.MESSAGES_SOURCE, locale);
        String messageTemplate = messages.getString(messageKey);
        String formattedMessage = MessageFormat.format(messageTemplate, parameters);
        throw new ApplicationException(formattedMessage);
    }
}
