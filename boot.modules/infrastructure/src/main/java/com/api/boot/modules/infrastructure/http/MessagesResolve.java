package com.api.boot.modules.infrastructure.http;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Component (value = "httpMessagesResolve")
@Lazy(value = false)
public class MessagesResolve implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext;

    private static LocaleResolver localeResolver;

    private MessagesResolve(){

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        MessagesResolve.applicationContext = applicationContext;

    }

    @Override
    public void destroy() {
        applicationContext = null;
    }

    public ApplicationContext get() {
        return  applicationContext;
    }

    private static Object getBean(String name,String message) {
        Assert.hasText(name,message);
        return applicationContext.getBean(name);
    }

    private static <T> T getBean(String name, String message,Class<T> type) {
        Assert.hasText(name,message);
        Assert.notNull(type,message);
        return applicationContext.getBean(name,type);
    }

    public static String getMessage(String key) {
        Locale locale = getLocaleResolver().resolveLocale(null);
        return applicationContext.getMessage(key,null,locale);
    }


    public static String getMessage(String key , Object[] arg) {
        Locale locale = getLocaleResolver().resolveLocale(null);
        return applicationContext.getMessage(key,arg,locale);
    }

    private static LocaleResolver getLocaleResolver() {
        if (localeResolver == null) {
            localeResolver = getBean("fixedLocaleResolver","not found bean name ",LocaleResolver.class);
        }
        return localeResolver;
    }

}
