package com.hathor.core.engine.tracer;

import com.hathor.core.engine.model.ColumnNode;
import com.hathor.core.engine.model.TableNode;
import com.hathor.core.engine.model.TreeNode;

/**
 * <p>ColumnLineageTracer</p>
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
public interface ColumnLineageTracer {
    /**
     * 构建血缘关系
     *
     * @param dbType            数据库类型
     * @param currentColumnNode 当前的Column
     * @param tableNode         表血缘树
     */
    void traceColumnLineageTree(String dbType, TreeNode<ColumnNode> currentColumnNode,
                                TreeNode<TableNode> tableNode);
}
