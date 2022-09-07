package com.hathor.core.engine.process.expr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.hathor.core.engine.annotation.SQLObjectType;
import com.hathor.core.engine.process.SqlExprContent;
import com.hathor.core.engine.register.DruidProcessorRegister;

/**
 * <p>SQLBinaryOpExprProcessor</p>
 * <p>Description:
 * </p>
 * use case <p>select ((a1+a2)-a3)*a4/a5 as a
 * TODO: 待测试 join xxx on a.x = b.x
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 15:04
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@SQLObjectType(clazz = SQLBinaryOpExpr.class)
public class SQLBinaryOpExprProcessor implements SQLExprProcessor {
    @Override
    public void process(String dbType, SQLExpr expr, SqlExprContent content) {
        SQLBinaryOpExpr sqlBinaryOpExpr = (SQLBinaryOpExpr) expr;
        DruidProcessorRegister.getSQLExprProcessor(sqlBinaryOpExpr.getLeft().getClass()).process(dbType, sqlBinaryOpExpr.getLeft(), content);
        DruidProcessorRegister.getSQLExprProcessor(sqlBinaryOpExpr.getRight().getClass()).process(dbType, sqlBinaryOpExpr.getRight(), content);
    }
}
