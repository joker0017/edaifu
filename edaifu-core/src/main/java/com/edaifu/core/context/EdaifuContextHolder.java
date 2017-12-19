package com.edaifu.core.context;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class EdaifuContextHolder implements ApplicationContextAware {
    private static Log log = LogFactory.getLog(EdaifuContextHolder.class);

    @Autowired
    private static ApplicationContext applicationContext;

    @Autowired
    private DataSource dataSource;


    public static Object getCurrentUser() {
        SecurityContext sc = SecurityContextHolder.getContext();
        if (sc.getAuthentication() != null) {
            Object obj = sc.getAuthentication().getPrincipal();
            if (obj != null)
                return obj;
        }
        return null;
    }

    public static String getLoginId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            return authentication.getName();
        }
        return "noneuser";
    }

    public static String getLoginIp() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object details = null;
        if (authentication != null)
            if ((details = authentication.getDetails()) != null) {
                return ((WebAuthenticationDetails) details).getRemoteAddress();
            }
        return "noneip";
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    public static <T> Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}