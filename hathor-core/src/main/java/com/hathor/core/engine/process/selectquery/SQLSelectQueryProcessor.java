package com.hathor.core.engine.process.selectquery;

import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.hathor.core.engine.model.TableNode;
import com.hathor.core.engine.model.TreeNode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>SQLSelectQueryProcessor</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 10:57
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
public interface SQLSelectQueryProcessor {
    /**
     * SQLSelectQuery 处理
     *
     * @param dbType         数据库类型
     * @param sequence       节点主键
     * @param parent         传入的节点
     * @param sqlSelectQuery SQLSelectQuery子类
     */
    void process(String dbType, AtomicInteger sequence, TreeNode<TableNode> parent, SQLSelectQuery sqlSelectQuery);
}
