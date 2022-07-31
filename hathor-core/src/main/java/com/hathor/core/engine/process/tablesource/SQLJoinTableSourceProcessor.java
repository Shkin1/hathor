package com.hathor.core.engine.process.tablesource;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.statement.SQLJoinTableSource;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.hathor.common.contants.ParserConstant;
import com.hathor.core.engine.annotation.SQLObjectType;
import com.hathor.core.engine.model.Node;
import com.hathor.core.engine.model.TableNode;
import com.hathor.core.engine.model.TreeNode;
import com.hathor.core.engine.register.DruidProcessorRegister;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>SQLJoinTableSourceProcessor</p>
 * <p>Description:
 * eg:
 * table1
 * LEFT JOIN
 * (SELECT t2.a1, t2.a2 FROM table2 t2) temp1
 * ON
 * t1.a1 = temp1.a1
 *
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 15:06
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@SQLObjectType(clazz = SQLJoinTableSource.class)
public class SQLJoinTableSourceProcessor implements TableSourceProcessor {
    @Override
    public void process(String dbType, AtomicInteger sequence, TreeNode<TableNode> parent,
                        SQLTableSource sqlTableSource) {
        // 建立中介节点 start
        TableNode proxyTable = new TableNode();
        proxyTable.setType(Node.V_JOIN_TABLE_TYPE);
        proxyTable.setProcessorName(sqlTableSource.getClass().getName());
        proxyTable.setName(ParserConstant.TEMP_TABLE_PREFIX + sequence.incrementAndGet());
        proxyTable.setAlias(sqlTableSource.getAlias());
        proxyTable.setExpression(SQLUtils.toSQLString(sqlTableSource));
        proxyTable.setVirtualTemp(true);

        TreeNode<TableNode> proxyNode = TreeNode.of(proxyTable);
        parent.addChild(proxyNode);

        SQLJoinTableSource sqlJoinTableSource = (SQLJoinTableSource) sqlTableSource;
        DruidProcessorRegister.getTableSourceProcessor(sqlJoinTableSource.getLeft().getClass())
                .process(dbType, sequence, proxyNode, sqlJoinTableSource.getLeft());
        DruidProcessorRegister.getTableSourceProcessor(sqlJoinTableSource.getRight().getClass())
                .process(dbType, sequence, proxyNode, sqlJoinTableSource.getRight());

    }
}
