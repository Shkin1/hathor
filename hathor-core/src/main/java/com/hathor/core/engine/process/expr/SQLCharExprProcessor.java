package com.hathor.core.engine.process.expr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLCharExpr;
import com.hathor.core.engine.annotation.SQLObjectType;
import com.hathor.core.engine.process.SqlExprContent;

/**
 * <p>SQLCharExprProcessor</p>
 * <p>Description:
 * select 'str1' + 'st2r' as c
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 14:27
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@SQLObjectType(clazz = SQLCharExpr.class)
public class SQLCharExprProcessor implements SQLExprProcessor {

    @Override
    public void process(String dbType, SQLExpr expr, SqlExprContent content) {
        // SQLCharExpr
        SQLCharExpr sqlCharExpr = (SQLCharExpr) expr;
        // 出口
        content.addItem(SqlExprContent
                .builder()
                .name(sqlCharExpr.getText())
                .isConstant(true)
                .build());
        // TODO 常量解析待开发
    }
}
