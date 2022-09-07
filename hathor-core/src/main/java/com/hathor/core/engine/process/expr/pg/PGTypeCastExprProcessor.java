package com.hathor.core.engine.process.expr.pg;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.dialect.postgresql.ast.expr.PGTypeCastExpr;
import com.hathor.core.engine.annotation.SQLObjectType;
import com.hathor.core.engine.process.SqlExprContent;
import com.hathor.core.engine.process.expr.SQLExprProcessor;
import com.hathor.core.engine.register.DruidProcessorRegister;

/**
 * <p>PGTypeCastExprProcessor</p>
 * <p>Description:
 * PGTypeCastExpr
 * Use case:
 * CASE WHEN condition THEN result
 * [WHEN ...]
 * [ELSE result]
 * END
 *
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 13:47
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@SQLObjectType(clazz = PGTypeCastExpr.class)
public class PGTypeCastExprProcessor implements SQLExprProcessor {
    @Override
    public void process(String dbType, SQLExpr expr, SqlExprContent content) {
        PGTypeCastExpr pgTypeCastExpr = (PGTypeCastExpr) expr;
        SQLExpr pgTypeCastExprExpr = pgTypeCastExpr.getExpr();
        DruidProcessorRegister.getSQLExprProcessor(pgTypeCastExprExpr.getClass())
                .process(dbType, pgTypeCastExprExpr, content);
    }
}
