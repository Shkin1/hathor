package com.hathor.core.engine.process.expr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLCaseExpr;
import com.hathor.core.engine.annotation.SQLObjectType;
import com.hathor.core.engine.process.SqlExprContent;
import com.hathor.core.engine.register.DruidProcessorRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>SQLCaseExprProcessor</p>
 * <p>Description:
 * SQLCaseExpr
 * use case:
 * CASE WHEN condition THEN result
 * [WHEN ...]
 * [ELSE result]
 * END
 * constant think
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 13:49
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@SQLObjectType(clazz = SQLCaseExpr.class)
public class SQLCaseExprProcessor implements SQLExprProcessor {
    @Override
    public void process(String dbType, SQLExpr expr, SqlExprContent content) {
        SQLCaseExpr sqlCaseExpr = (SQLCaseExpr) expr;
        this.getAllCaseExprChild(sqlCaseExpr)
                .forEach(expr1 -> DruidProcessorRegister.getSQLExprProcessor(expr1.getClass()).process(dbType, expr1, content));
    }

    private List<SQLExpr> getAllCaseExprChild(SQLCaseExpr sqlCaseExpr) {
        List<SQLExpr> list = new ArrayList<>();
        if (Objects.nonNull(sqlCaseExpr.getValueExpr())) {
            list.add(sqlCaseExpr.getValueExpr());
        }
        if (Objects.nonNull(sqlCaseExpr.getElseExpr())) {
            list.add(sqlCaseExpr.getElseExpr());
        }
        List<SQLExpr> sqlItemExprList = sqlCaseExpr.getItems()
                .stream()
                .map(SQLCaseExpr.Item::getValueExpr)
                .collect(Collectors.toList());
        list.addAll(sqlItemExprList);
        return list;
    }


}
