package com.hathor.core.engine.process.statement;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLCreateViewStatement;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.hathor.core.engine.annotation.SQLObjectType;
import com.hathor.core.engine.model.TableNode;
import com.hathor.core.engine.model.TreeNode;
import com.hathor.core.engine.register.DruidProcessorRegister;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>SQLCreateViewStatementProcessor</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 11:50
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@SQLObjectType(clazz = SQLCreateViewStatement.class)
public class SQLCreateViewStatementProcessor extends AbstractStatementProcessor {

    @Override
    public void doProcess(String dbType, AtomicInteger sequence, TreeNode<TableNode> root, SQLStatement statement) {
        SQLCreateViewStatement createViewStatement = (SQLCreateViewStatement) statement;
        SQLExprTableSource sqlExprTableSource = createViewStatement.getTableSource();
        // 构建根表
        this.constructRootNode(dbType, root, statement, sqlExprTableSource);
        // 获取SQLSelectQuery
        SQLSelectQuery sqlSelectQuery = createViewStatement.getSubQuery().getQuery();
        DruidProcessorRegister.getSQLSelectQueryProcessor(sqlSelectQuery.getClass())
                .process(dbType, sequence, root, sqlSelectQuery);
    }
}
