package com.hathor.core.engine.process.expr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLAllColumnExpr;
import com.hathor.core.engine.annotation.SQLObjectType;
import com.hathor.core.engine.process.SqlExprContent;

/**
 * <p>SQLAllColumnExprProcessor</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 13:42
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@SQLObjectType(clazz = SQLAllColumnExpr.class)
public class SQLAllColumnExprProcessor implements SQLExprProcessor{

    @Override
    public void process(String dbType, SQLExpr expr, SqlExprContent content) {
        // 需后置处理，节点处理时由下至上
        // *  select *
        content.addItem(SqlExprContent.builder()
                .name("*")
                .build());
        // select a.*, b*. 识别为 SQLIdentifierExpr
    }
}
