package com.hathor.core.engine.config;

import com.alibaba.druid.sql.ast.SQLObject;
import com.hathor.core.engine.annotation.SQLObjectType;
import com.hathor.core.engine.register.DruidProcessorRegister;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

/**
 * <p>SqlObjectRegisterProcessor</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 15:54
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@Component
@ConditionalOnClass(SQLObject.class)
@Slf4j
public class SqlObjectRegisterProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        Class<?> cla = bean.getClass();
        SQLObjectType sqlObjectType = cla.getAnnotation(SQLObjectType.class);
        if (sqlObjectType == null) {
            return bean;
        }
        Class<?> clazz = sqlObjectType.clazz();
        DruidProcessorRegister.register(clazz, bean);
        log.info("[init] - register - {}", clazz.getName());
        return bean;
    }
}
