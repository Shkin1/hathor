package com.hathor.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * <p>ApplicationContextHelper</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 16:30
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@Slf4j
public class ApplicationContextHelper implements ApplicationContextAware {
    public static DefaultListableBeanFactory springFactory;

    public static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHelper.setContext(applicationContext);
        if (applicationContext instanceof AbstractRefreshableApplicationContext) {
            AbstractRefreshableApplicationContext springContext =
                    (AbstractRefreshableApplicationContext) applicationContext;
            ApplicationContextHelper.setFactory((DefaultListableBeanFactory) springContext.getBeanFactory());
        } else if (applicationContext instanceof GenericApplicationContext) {
            GenericApplicationContext springContext = (GenericApplicationContext) applicationContext;
            ApplicationContextHelper.setFactory(springContext.getDefaultListableBeanFactory());
        }

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("------------------" + beanDefinitionName);
        }
    }


    private static void setContext(ApplicationContext applicationContext) {
        ApplicationContextHelper.context = applicationContext;
    }

    private static void setFactory(DefaultListableBeanFactory springFactory) {
        ApplicationContextHelper.springFactory = springFactory;
    }
    public static ApplicationContext getContext() {
        return context;
    }


}
