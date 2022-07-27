package com.hathor.core.engine.annotation;

import java.lang.annotation.*;

import org.springframework.stereotype.Component;

/**
 * <p>DataSourceType</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/27 18:28
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@Component
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MsgType {
    String value();
}
