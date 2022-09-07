package com.hathor.core.engine.process.expr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.hathor.core.engine.annotation.SQLObjectType;
import com.hathor.core.engine.process.SqlExprContent;

/**
 * <p>SQLIdentifierExprProcessor</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 13:57
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@SQLObjectType(clazz = SQLIdentifierExpr.class)
public class SQLIdentifierExprProcessor implements SQLExprProcessor {
    @Override
    public void process(String dbType, SQLExpr expr, SqlExprContent content) {
        SQLIdentifierExpr sqlIdentifierExpr = (SQLIdentifierExpr) expr;
        // 第一层 除了SQLIdentifierExpr 外，其它可看作是需要查找来源字段的出口
        content.addItem(SqlExprContent
                .builder()
                .name(sqlIdentifierExpr.getName())
                .build());
    }
}
