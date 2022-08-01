package com.hathor.core.engine.model.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>HathorTableFlowNode</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/08/2022/8/1 17:21
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
public class HathorTableFlowNode {
    /**
     * 表类型 -> 对应底表
     */
    public static final String TYPE_TABLE = "table";

    /**
     * 表类型 -> 临时表 中间表
     */
    public static final String TYPE_TMP = "tmp";

    /**
     * 表类型 -> 视图 中间表
     */
    public static final String TYPE_VIEW = "view";

    public static final String TYPE_PROCESS = "process";


    private String tableName;
    private String schema;
    private String type;
    private final List<HathorTableFlowNode> child;

    @Override
    public String toString() {
        return "HathorTableFlowNode{" +
                "tableName='" + tableName + '\'' +
                '}';
    }

    public HathorTableFlowNode(String tableName, String type) {
        this.child = new ArrayList<>();
        this.tableName = tableName;
        this.type = type;
    }

    public HathorTableFlowNode() {
        this.child = new ArrayList<>();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<HathorTableFlowNode> getChild() {
        return child;
    }

    public void addChild(HathorTableFlowNode node) {
        this.child.add(node);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HathorTableFlowNode node = (HathorTableFlowNode) o;
        return tableName.equals(node.tableName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableName);
    }
}
