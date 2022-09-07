package com.hathor.core.engine.model;

import java.util.List;

/**
 * <p>HathorLineage</p>
 * <p>Description:
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
public class HathorLineage {
    /**
     * 根表 -> 对应产出表
     */
    private TableNode rootTableNode;

    /**
     * 表来源
     */
    private List<TableNode> sourceTableNode;

    /**
     * 表来源
     */
    private List<TableNode> allTableNode;

    /**
     * MidTableNode
     */
    private List<TableNode> midTableNode;

    /**
     * LeafTableNode
     */
    private List<TableNode> leafTableNode;

    public TableNode getRootTableNode() {
        return rootTableNode;
    }

    public void setRootTableNode(TableNode rootTableNode) {
        this.rootTableNode = rootTableNode;
    }

    public List<TableNode> getSourceTableNode() {
        return sourceTableNode;
    }

    public void setSourceTableNode(List<TableNode> sourceTableNode) {
        this.sourceTableNode = sourceTableNode;
    }

    public List<TableNode> getAllTableNode() {
        return allTableNode;
    }

    public void setAllTableNode(List<TableNode> allTableNode) {
        this.allTableNode = allTableNode;
    }

    public List<TableNode> getMidTableNode() {
        return midTableNode;
    }

    public void setMidTableNode(List<TableNode> midTableNode) {
        this.midTableNode = midTableNode;
    }

    public List<TableNode> getLeafTableNode() {
        return leafTableNode;
    }

    public void setLeafTableNode(List<TableNode> leafTableNode) {
        this.leafTableNode = leafTableNode;
    }
}
