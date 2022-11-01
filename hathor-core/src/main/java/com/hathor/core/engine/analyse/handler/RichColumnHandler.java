package com.hathor.core.engine.analyse.handler;

import com.hathor.core.engine.analyse.SqlRequestContext;
import com.hathor.core.engine.analyse.SqlResponseContext;
import com.hathor.core.engine.contants.StartOrderConstants;
import com.hathor.core.engine.model.TableNode;
import com.hathor.core.engine.model.TreeNode;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <p>RichColumnHandler</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 15:40
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@Order(StartOrderConstants.ORDER_THIRD)
@Component
public class RichColumnHandler implements IHandler{
    @Override
    public void handleRequest(SqlRequestContext request, SqlResponseContext response) {
        fillingTableExpression(response.getLineageTableTree());
    }

    /**
     * Tree<Table> 填充Column的TableExpression 字段
     *
     * @param root 当前表关系树节点
     */
    public void fillingTableExpression(TreeNode<TableNode> root) {
        root.getValue().getColumns()
                .forEach(columnNode -> columnNode.setTableExpression(root.getValue().getExpression()));
        if (root.isLeaf()) {
            return;
        }
        root.getChildList().forEach(this::fillingTableExpression);
    }
}
