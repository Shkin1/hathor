package com.hathor.core.engine.process.expr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.hathor.core.engine.process.SqlExprContent;

/**
 * <p>SQLExprProcessor</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/27 20:00
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
public interface SqlExprProcessor {

    /**
     * SQLExpr 内容提取
     *
     * @param dbType  数据库类型
     * @param expr    SQLExpr
     * @param content SqlExprContent
     */
    void process(String dbType, SQLExpr expr, SqlExprContent content);
}
