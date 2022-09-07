package com.hathor.core.engine.process.expr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLMethodInvokeExpr;
import com.hathor.core.engine.annotation.SQLObjectType;
import com.hathor.core.engine.process.SqlExprContent;
import com.hathor.core.engine.register.DruidProcessorRegister;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>SQLMethodInvokeExprProcessor</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 14:24
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@SQLObjectType(clazz = SQLMethodInvokeExpr.class)
public class SQLMethodInvokeExprProcessor implements SQLExprProcessor {
    @Override
    public void process(String dbType, SQLExpr expr, SqlExprContent content) {
        SQLMethodInvokeExpr sqlMethodInvokeExpr = (SQLMethodInvokeExpr) expr;
        this.getAllCaseExprChild(sqlMethodInvokeExpr)
                .forEach(ep -> DruidProcessorRegister.getSQLExprProcessor(ep.getClass()).process(dbType, ep, content));
    }

    private List<SQLExpr> getAllCaseExprChild(SQLMethodInvokeExpr expr) {
        List<SQLExpr> list = new ArrayList<>();
        Optional.ofNullable(expr.getOwner()).ifPresent(list::add);
        Optional.ofNullable(expr.getFrom()).ifPresent(list::add);
        Optional.ofNullable(expr.getUsing()).ifPresent(list::add);
        Optional.ofNullable(expr.getFor()).ifPresent(list::add);
        if (CollectionUtils.isNotEmpty(expr.getArguments())) {
            list.addAll(expr.getArguments());
        }
        return list;
    }
}
