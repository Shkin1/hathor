package com.hathor.core.engine.process.expr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLAggregateExpr;
import com.hathor.core.engine.annotation.SQLObjectType;
import com.hathor.core.engine.process.SqlExprContent;
import com.hathor.core.engine.register.DruidProcessorRegister;

/**
 * <p>SQLAggregateExprProcessor</p>
 * <p>Description:
 * </p>
 * SQLAggregateExpr
 * use case:
 * max()
 * min()
 * ...
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 13:45
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@SQLObjectType(clazz = SQLAggregateExpr.class)
public class SQLAggregateExprProcessor implements SQLExprProcessor {
    @Override
    public void process(String dbType, SQLExpr expr, SqlExprContent content) {
        SQLAggregateExpr sqlAggregateExpr = (SQLAggregateExpr) expr;
        sqlAggregateExpr.getArguments()
                .forEach(sqlExpr -> DruidProcessorRegister.getSQLExprProcessor(sqlExpr.getClass())
                        .process(dbType, sqlExpr, content));
    }
}
