package com.hathor.core.engine.annotation;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * <p>SQLObjectType</p>
 * <p>Description:
 * SQL Object Type
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/27 19:37
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@Component
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SQLObjectType {
    @Nullable Class<?> clazz();

    Class<?>[] parent() default {};
}
