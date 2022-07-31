package com.hathor.core.engine.process.statement;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.hathor.core.engine.model.Node;
import com.hathor.core.engine.model.TableNode;
import com.hathor.core.engine.model.TreeNode;
import com.hathor.core.engine.process.SqlExprContent;
import com.hathor.core.engine.register.DruidProcessorRegister;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>AbstractStatementProcessor</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 11:20
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@Slf4j
public abstract class AbstractStatementProcessor implements StatementProcessor {

    @Override
    public void process(String dbType, AtomicInteger sequence, TreeNode<TableNode> root, SQLStatement statement) {
        this.doProcess(dbType, sequence, root, statement);
        this.after(dbType, sequence, root, statement);
    }

    protected void doProcess(String dbType, AtomicInteger sequence, TreeNode<TableNode> root, SQLStatement statement) {
    }

    protected void constructRootNode(String dbType, TreeNode<TableNode> root, SQLStatement statement,
                                     SQLExprTableSource sqlExprTableSource) {
        SQLExpr sqlExpr = sqlExprTableSource.getExpr();
        SqlExprContent content = new SqlExprContent();
        DruidProcessorRegister.getSQLExprProcessor(sqlExpr.getClass()).process(dbType, sqlExpr, content);
        String tableName = content.getName();
        String schemaName = content.getOwner();
        TableNode tableNode = new TableNode();
        tableNode.setType(Node.ROOT_TABLE_TYPE);
        tableNode.setSchemaName(schemaName);
        tableNode.setName(tableName);
        tableNode.setVirtualTemp(false);
        tableNode.setProcessorName(statement.getClass().getName());
        root.setValue(tableNode);
        try {
            tableNode.setExpression(SQLUtils.toSQLString(statement));
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
    }


    protected void after(String dbType, AtomicInteger sequence, TreeNode<TableNode> root, SQLStatement statement) {
        //  处理完之后的操作 TODO 合并Union

        log.info("[todo] - StatementProcessor after handle...");
    }


}
