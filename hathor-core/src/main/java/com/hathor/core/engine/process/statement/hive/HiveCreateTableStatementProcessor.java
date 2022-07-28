package com.hathor.core.engine.process.statement.hive;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.alibaba.druid.sql.dialect.hive.stmt.HiveCreateTableStatement;
import com.alibaba.druid.sql.dialect.postgresql.ast.stmt.PGInsertStatement;
import com.hathor.core.engine.annotation.SQLObjectType;
import com.hathor.core.engine.model.TableNode;
import com.hathor.core.engine.model.TreeNode;
import com.hathor.core.engine.process.statement.AbstractStatementProcessor;
import com.hathor.core.engine.register.DruidProcessorRegister;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>HiveInsertStatementProcessor</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 11:19
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@SQLObjectType(clazz = HiveCreateTableStatement.class)
@Slf4j
public class HiveCreateTableStatementProcessor extends AbstractStatementProcessor {
    @Override
    public void doProcess(String dbType, AtomicInteger sequence, TreeNode<TableNode> root, SQLStatement statement) {
        HiveCreateTableStatement hiveCreateTableStatement = (HiveCreateTableStatement) statement;
        SQLExprTableSource sqlExprTableSource = hiveCreateTableStatement.getTableSource();
        // 构建根表
        this.constructRootNode(dbType, root, statement, sqlExprTableSource);
        // 获取SQLSelectQuery
        String schema = hiveCreateTableStatement.getSchema();
        log.info("[HiveCreateTableStatementProcessor ] - schema: {}",schema);
        SQLSelectQuery sqlSelectQuery = hiveCreateTableStatement.getSelect().getQuery();
//        SQLSelectQuery sqlSelectQuery = hiveCreateTableStatement.getQuery().getQuery();
        // 执行SQLSelectQuery 查询
        DruidProcessorRegister.getSQLSelectQueryProcessor(sqlSelectQuery.getClass())
                .process(dbType, sequence, root, sqlSelectQuery);
    }
}
