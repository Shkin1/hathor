package com.hathor.core.engine.process.statement.pg;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.alibaba.druid.sql.dialect.postgresql.ast.stmt.PGInsertStatement;
import com.alibaba.druid.sql.dialect.postgresql.ast.stmt.PGSelectStatement;
import com.hathor.core.engine.annotation.SQLObjectType;
import com.hathor.core.engine.model.TableNode;
import com.hathor.core.engine.model.TreeNode;
import com.hathor.core.engine.process.statement.AbstractStatementProcessor;
import com.hathor.core.engine.register.DruidProcessorRegister;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>PGSelectStatementProcessor</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/31 17:46
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@SQLObjectType(clazz = PGSelectStatement.class)
@Slf4j
public class PGSelectStatementProcessor extends AbstractStatementProcessor {

    @Override
    public void doProcess(String dbType, AtomicInteger sequence, TreeNode<TableNode> root, SQLStatement statement) {
        PGSelectStatement pgSelectStatement = (PGSelectStatement) statement;
        // 构建根表
        this.constructRootNode(dbType, root, statement, null);
        // 获取SQLSelectQuery
        SQLSelectQuery sqlSelectQuery = pgSelectStatement.getSelect().getQuery();
        // 执行SQLSelectQuery 查询
        DruidProcessorRegister.getSQLSelectQueryProcessor(sqlSelectQuery.getClass())
                .process(dbType, sequence, root, sqlSelectQuery);
    }


}
