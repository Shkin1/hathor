package com.hathor.core.engine.model;

import lombok.Builder;

/**
 * <p>Node</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/30 21:22
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
public class Node {


    /**
     * 根节点类型.
     */
    public static final String ROOT_TABLE_TYPE = "0_ROOT";

    /**
     * 表节点类型.
     */
    public static final String TABLE_TYPE = "1_TABLE";


    /**
     * 虚拟类型.
     */
    public static final String V_TABLE_TYPE = "2_V_TABLE";

    /**
     * 虚拟JOIN类型.
     */
    public static final String V_JOIN_TABLE_TYPE = "2_V_JOIN_TABLE";

    /**
     * 视图类型.
     */
    public static final String VIEW_TABLE_TYPE = "3_VIEW_TABLE";

    /**
     * 临时类型.
     */
    public static final String TMP_TABLE_TYPE = "4_TMP_TABLE";

    /**
     * 字段类型.
     */
    public static final String COLUMN_TYPE = "10_COLUMN";

    /**
     * EXPR类型.
     */
    public static final String EXPR_TYPE = "30_EXPR";



    private String processorName;

    private String pkName;
    private String type;

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    public String getProcessorName() {
        return processorName;
    }

    public void setProcessorName(String processorName) {
        this.processorName = processorName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
