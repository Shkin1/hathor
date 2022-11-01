package com.hathor.core.engine.analyse.handler;

import com.hathor.common.exception.ParserException;
import com.hathor.core.engine.analyse.SqlRequestContext;
import com.hathor.core.engine.analyse.SqlResponseContext;
import com.hathor.core.engine.contants.StartOrderConstants;
import com.hathor.core.engine.model.ColumnNode;
import com.hathor.core.engine.model.TableNode;
import com.hathor.core.engine.model.TreeNode;
import com.hathor.core.engine.tracer.ColumnLineageTracer;
import com.hathor.core.engine.tracer.ColumnLineageTracerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>ColumnHandler</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 15:43
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@Order(StartOrderConstants.ORDER_FOURTH)
@Component
public class ColumnLineageTreeHandler implements IHandler {
    @Override
    public void handleRequest(SqlRequestContext request, SqlResponseContext response) {
        System.out.println("TODO ---");
        handleColumnRelation(request, response);
    }

    private void handleColumnRelation(SqlRequestContext sqlContext, SqlResponseContext response) {
        TreeNode<TableNode> lineageTableTree = response.getLineageTableTree();
        TreeNode<TableNode> firstHaveColumnTableNode = this.findFirstHaveColumnTableNode(lineageTableTree);
        List<ColumnNode> rootColumns = firstHaveColumnTableNode.getValue().getColumns();
        if (CollectionUtils.isEmpty(rootColumns)) {
            throw new ParserException("node.not.found.effective");
        }
        ColumnLineageTracer columnLineageTracer = ColumnLineageTracerFactory.getDefaultTracer();
        // 获取到字段血缘树
        List<TreeNode<ColumnNode>> lineageColumnTreeList = new ArrayList<>();
        rootColumns.stream().map(TreeNode::of).forEach(nodeTreeNode -> { // 遍历字段
            lineageColumnTreeList.add(nodeTreeNode);
            columnLineageTracer.traceColumnLineageTree(
                    sqlContext.getDbType(),
                    nodeTreeNode,
                    firstHaveColumnTableNode);
        });
        // save
        response.setLineageColumnTreeList(lineageColumnTreeList);
    }

    /**
     * 找到第一个有字段的节点
     *
     * @param root TableNode
     * @return TreeNode<TableNode>
     */
    private TreeNode<TableNode> findFirstHaveColumnTableNode(TreeNode<TableNode> root) {
        if (!CollectionUtils.isEmpty(root.getValue().getColumns())) {
            return root;
        }
        if (CollectionUtils.isEmpty(root.getChildList()) || root.getChildList().size() != 1) {
            throw new ParserException("node.found.more");
        }
        // 拿到根的第一个子集，根一般只有一个虚拟的子集
        return root.getChildList().get(0);
    }
}
