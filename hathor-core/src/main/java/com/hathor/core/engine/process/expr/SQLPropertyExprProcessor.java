package com.hathor.core.engine.process.expr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.hathor.core.engine.annotation.SQLObjectType;
import com.hathor.core.engine.process.SqlExprContent;

/**
 * <p>SQLPropertyExprProcessor</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 14:26
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@SQLObjectType(clazz = SQLPropertyExpr.class)
public class SQLPropertyExprProcessor implements SQLExprProcessor {
    @Override
    public void process(String dbType, SQLExpr expr, SqlExprContent content) {
        SQLPropertyExpr sqlPropertyExpr = (SQLPropertyExpr) expr;
        // 出口
        content.addItem(SqlExprContent.builder()
                .name(sqlPropertyExpr.getName())
                .owner(sqlPropertyExpr.getOwnernName())
                .build());
    }
}
