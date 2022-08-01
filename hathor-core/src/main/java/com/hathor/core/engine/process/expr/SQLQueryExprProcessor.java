package com.hathor.core.engine.process.expr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLQueryExpr;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.hathor.common.contants.ParserConstant;
import com.hathor.core.engine.annotation.SQLObjectType;
import com.hathor.core.engine.model.TableNode;
import com.hathor.core.engine.model.TreeNode;
import com.hathor.core.engine.process.SqlExprContent;
import com.hathor.core.engine.register.DruidProcessorRegister;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>SQLQueryExpr</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/08/2022/8/1 11:41
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@SQLObjectType(clazz = SQLQueryExpr.class)
public class SQLQueryExprProcessor implements SQLExprProcessor {

    @Override
    public void process(String dbType, SQLExpr expr, SqlExprContent content) {
        SQLQueryExpr sqlQueryExpr = (SQLQueryExpr) expr;
        SQLSelect sqlSelect = sqlQueryExpr.getSubQuery();
        SQLSelectQuery query = sqlSelect.getQuery();

        TreeNode<TableNode> subTreeNode = new TreeNode<>();

        TableNode virtualTempTableNode = new TableNode();
        virtualTempTableNode.setName("sub_column_query");
        // 构建根表
        virtualTempTableNode.setVirtualTemp(true);
        virtualTempTableNode.setName(ParserConstant.TEMP_TABLE_PREFIX + "column_sub_root");
        virtualTempTableNode.setVirtualTemp(false);
        virtualTempTableNode.setProcessorName(sqlQueryExpr.getClass().getName());
        virtualTempTableNode.setExpression(query.toString());
        subTreeNode.setValue(virtualTempTableNode);
        AtomicInteger sequence = new AtomicInteger();
        DruidProcessorRegister.getSQLSelectQueryProcessor(query.getClass())
                .process(dbType, sequence, subTreeNode, query);

        content.setTreeNode(subTreeNode);
    }
}
