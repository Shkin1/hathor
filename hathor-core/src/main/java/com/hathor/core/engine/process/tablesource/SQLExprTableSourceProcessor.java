package com.hathor.core.engine.process.tablesource;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.hathor.core.engine.annotation.SQLObjectType;
import com.hathor.core.engine.model.TableNode;
import com.hathor.core.engine.model.TreeNode;
import com.hathor.core.engine.process.SqlExprContent;
import com.hathor.core.engine.register.DruidProcessorRegister;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>SQLExprTableSourceProcessor</p>
 * <p>Description:
 * select * from emp where i = 3
 * 这里的from emp是一个SQLExprTableSource
 *
 * select * from emp e inner join org o on e.org_id = o.id
 * 其中left 'emp e' 是一个SQLExprTableSource，right 'org o'也是一个SQLExprTableSource
 * condition 'e.org_id = o.id'是一个SQLBinaryOpExpr
 *
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 14:59
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@SQLObjectType(clazz = SQLExprTableSource.class)
public class SQLExprTableSourceProcessor implements TableSourceProcessor{
    @Override
    public void process(String dbType, AtomicInteger sequence, TreeNode<TableNode> parent, SQLTableSource sqlTableSource
    ) {
        // 建立TEMP节点 start
        TableNode proxyTable = TableNode.builder()
                .expression(SQLUtils.toSQLString(sqlTableSource))
                .alias(sqlTableSource.getAlias())
                .build();
        TreeNode<TableNode> proxyNode = TreeNode.of(proxyTable);
        parent.addChild(proxyNode);

        SQLExpr sqlExprTableSourceExpr = ((SQLExprTableSource) sqlTableSource).getExpr();
        SqlExprContent sqlExprContent = new SqlExprContent();
        DruidProcessorRegister.getSQLExprProcessor(sqlExprTableSourceExpr.getClass())
                .process(dbType, sqlExprTableSourceExpr, sqlExprContent);
        proxyTable.setName(sqlExprContent.getName());
        proxyTable.setSchemaName(sqlExprContent.getOwner());
    }
}
