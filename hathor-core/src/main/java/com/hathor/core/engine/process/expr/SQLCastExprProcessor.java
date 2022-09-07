package com.hathor.core.engine.process.expr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLCastExpr;
import com.hathor.core.engine.annotation.SQLObjectType;
import com.hathor.core.engine.process.SqlExprContent;
import com.hathor.core.engine.register.DruidProcessorRegister;

/**
 * <p>SQLCastExprProcessor</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 13:55
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@SQLObjectType(clazz = SQLCastExpr.class)
public class SQLCastExprProcessor implements SQLExprProcessor  {
    @Override
    public void process(String dbType, SQLExpr expr, SqlExprContent content) {
        SQLCastExpr sqlCastExpr = (SQLCastExpr) expr;
        SQLExpr castExprExpr = sqlCastExpr.getExpr();
        DruidProcessorRegister.getSQLExprProcessor(castExprExpr.getClass()).process(dbType, castExprExpr, content);

    }
}
