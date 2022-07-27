package com.hathor.core.engine.process.tablesource;

import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.hathor.core.engine.model.TableNode;
import com.hathor.core.engine.model.TreeNode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>TableSourceProcessor</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/27 20:06
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
public interface TableSourceProcessor {
    /**
     * TableSource 的处理
     *
     * @param dbType         数据库类型
     * @param sequence       序列
     * @param parent         父节点
     * @param sqlTableSource SQLTableSource 子类
     */
    void process(String dbType, AtomicInteger sequence, TreeNode<TableNode> parent, SQLTableSource sqlTableSource);
}
