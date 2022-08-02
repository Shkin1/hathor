package com.hathor.core.engine.gsqlparser;

import com.hathor.core.engine.model.v1.HathorTableBlood;
import com.hathor.core.engine.model.v1.HathorTableFlowNode;
import com.hathor.core.engine.model.v1.NodeRelPool;
import gudusoft.gsqlparser.EDbVendor;
import gudusoft.gsqlparser.dlineage.DataFlowAnalyzer;
import gudusoft.gsqlparser.dlineage.dataflow.model.json.DBObject;
import gudusoft.gsqlparser.dlineage.dataflow.model.json.Dataflow;
import gudusoft.gsqlparser.dlineage.dataflow.model.json.Relationship;
import gudusoft.gsqlparser.dlineage.dataflow.model.json.RelationshipElement;
import gudusoft.gsqlparser.dlineage.dataflow.model.xml.dataflow;
import gudusoft.gsqlparser.dlineage.util.RemoveDataflowFunction;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>GSqlDataFlowAnalyzer</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/08/2022/8/1 16:51
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@Slf4j
public class GSqlDataFlowAnalyzer {


    public HathorTableBlood parse(String sql, String dbType) {
        HathorTableBlood hathorTableBlood = new HathorTableBlood(dbType, sql);
        Dataflow sqlFlow = getSqlFlow(sql, dbType);
        handle(sqlFlow, hathorTableBlood);
        return hathorTableBlood;
    }


    private Dataflow getSqlFlow(String sql, String dbType) {
        DataFlowAnalyzer flow = new DataFlowAnalyzer(sql, EDbVendor.dbvhive, false);
        flow.setSqlEnv(null);
        flow.setShowJoin(false);
        flow.setIgnoreRecordSet(true);
        flow.setLinkOrphanColumnToFirstTable(false);
        flow.setTextFormat(false);
        flow.generateDataFlow();
        dataflow dataflow = new RemoveDataflowFunction().removeFunction(flow.getDataFlow());

        log.info("[dbType] - {}", dbType);
        return DataFlowAnalyzer.getSqlflowJSONModel(dataflow);
    }

    private void handle(Dataflow dataflow, HathorTableBlood hathorTableBlood) {
        DBObject[] dbObjList = dataflow.getDbobjs();
        HashMap<String, HathorTableFlowNode> nodePools = new HashMap<>(16);
        HashMap<HathorTableFlowNode, List<HathorTableFlowNode>> nodeRelPools = new HashMap<>(16);
        NodeRelPool nodeRelPool = new NodeRelPool();
        for (DBObject dbObject : dbObjList) {
            dbObjectToNode(dbObject, nodePools);
        }
        Relationship[] relationships = dataflow.getRelationships();
        nodeRelHandle(relationships, nodePools, nodeRelPool);
        hathorTableBlood.setTableNodeRel(nodeRelPool);
        hathorTableBlood.setTableNodeLineageDag(nodePools);

    }

    private void nodeRelHandle(Relationship[] relationships,
                               Map<String, HathorTableFlowNode> nodePools,
                               NodeRelPool nodeRelPool
    ) {
        for (Relationship relationship : relationships) {
            RelationshipElement[] sources = relationship.getSources();

            RelationshipElement target = relationship.getTarget();
            String targetTableId = target.getParentId();
            HathorTableFlowNode parent = nodePools.get(targetTableId);
            for (RelationshipElement sonObj : sources) {
                String id = sonObj.getParentId();
                HathorTableFlowNode sonNode = nodePools.get(id);
                // 目录树结构
                parent.addChild(sonNode);
                // 映射关系
                nodeRelPool.put(targetTableId, id);
            }
        }

    }


    private void dbObjectToNode(DBObject object, HashMap<String, HathorTableFlowNode> nodePools) {
        String id = object.getId();
        String type = object.getType();
        String name = object.getName();
        log.info("dbObjectToNode type : {}", type);
        HathorTableFlowNode hathorTableFlowNode = new HathorTableFlowNode(name, type);
        if (HathorTableFlowNode.TYPE_TABLE.equals(type)) {
            hathorTableFlowNode.setSchema(object.getSchema());
        }
        nodePools.put(id, hathorTableFlowNode);
    }


}
