package com.hathor.core.engine.tracer;

/**
 * <p>ColumnLineageTracerFactory</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 15:49
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
public class ColumnLineageTracerFactory {
    private ColumnLineageTracerFactory() {
    }

    /**
     * 获取默认的字段解析器
     *
     * @return ColumnLineageTracer
     */
    public static ColumnLineageTracer getDefaultTracer() {
        return new DefaultColumnLineageTracer();
    }
}
