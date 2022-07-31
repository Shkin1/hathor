package com.hathor.core.engine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>TableNode</p>
 * <p>Description:
 * 数据血缘解析时表节点
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/30 22:38
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@AllArgsConstructor
@Builder
public class TableNode extends Node {

    public TableNode() {

    }

    public TableNode(String tableType) {
        setType(tableType);
    }

    /**
     * schema
     */
    private String schemaName;
    /**
     * 表名
     */
    private String name;
    /**
     * 别名
     */
    private String alias;
    /**
     * 是否为虚拟表
     */
    @Builder.Default
    private Boolean isVirtualTemp = false;

    /**
     * 特殊类型节点的处理
     */
    private String queryType;
    /**
     * 字段列表
     */
    private final List<ColumnNode> columns = new ArrayList<>();
    /**
     * 表达式
     */
    private String expression;


    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }


    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Boolean getVirtualTemp() {
        return isVirtualTemp;
    }

    public void setVirtualTemp(Boolean virtualTemp) {
        isVirtualTemp = virtualTemp;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public List<ColumnNode> getColumns() {
        return columns;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.setPkName(name);
        this.name = name;
    }
}
