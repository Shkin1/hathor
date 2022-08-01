package com.hathor.core.engine.model.v1;

import com.hathor.common.entity.HathorBloodExplain;

import java.util.HashMap;
import java.util.Set;

/**
 * <p>HathorTableLineage</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/24 00:06
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
public class HathorTableBlood extends HathorBloodExplain {
    public HathorTableBlood(String dbType, String originalSql) {
        super(dbType, originalSql);
    }

    private Set<String> sourceTable;
    private Set<String> targetTable;
    HashMap<String, HathorTableFlowNode> tableNodeLineageDag;
    NodeRelPool tableNodeRel;

    public Set<String> getSourceTable() {
        return sourceTable;
    }

    public void setSourceTable(Set<String> sourceTable) {
        this.sourceTable = sourceTable;
    }

    public Set<String> getTargetTable() {
        return targetTable;
    }

    public void setTargetTable(Set<String> targetTable) {
        this.targetTable = targetTable;
    }

    public HashMap<String, HathorTableFlowNode> getTableNodeLineageDag() {
        return tableNodeLineageDag;
    }

    public void setTableNodeLineageDag(HashMap<String, HathorTableFlowNode> tableNodeLineageDag) {
        this.tableNodeLineageDag = tableNodeLineageDag;
    }

    public NodeRelPool getTableNodeRel() {
        return tableNodeRel;
    }

    public void setTableNodeRel(NodeRelPool tableNodeRel) {
        this.tableNodeRel = tableNodeRel;
    }
}
