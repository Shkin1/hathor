package com.hathor.core.engine.process.tablesource;

import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.alibaba.druid.sql.ast.statement.SQLSubqueryTableSource;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.hathor.core.engine.annotation.SQLObjectType;
import com.hathor.core.engine.model.TableNode;
import com.hathor.core.engine.model.TreeNode;
import com.hathor.core.engine.register.DruidProcessorRegister;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>SQLSubqueryTableSourceProcessor</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 15:08
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@SQLObjectType(clazz = SQLSubqueryTableSource.class)
public class SQLSubqueryTableSourceProcessor implements TableSourceProcessor{
    @Override
    public void process(String dbType, AtomicInteger sequence, TreeNode<TableNode> parent,
                        SQLTableSource sqlTableSource) {
        SQLSelectQuery sqlSelectQuery = ((SQLSubqueryTableSource) sqlTableSource).getSelect().getQuery();
        DruidProcessorRegister.getSQLSelectQueryProcessor(sqlSelectQuery.getClass())
                .process(dbType, sequence, parent, sqlSelectQuery);
    }
}
