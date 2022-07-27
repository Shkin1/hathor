package com.hathor.core.engine.process.statement;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.hathor.core.engine.model.TableNode;
import com.hathor.core.engine.model.TreeNode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>StatementProcessor</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/27 20:05
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
public interface StatementProcessor {
    /**
     * SQLStatement 处理
     *
     * @param dbType    数据库类型
     * @param sequence  序列
     * @param root      当前表节点
     * @param statement SQLStatement
     */
    void process(String dbType, AtomicInteger sequence, TreeNode<TableNode> root, SQLStatement statement);
}
