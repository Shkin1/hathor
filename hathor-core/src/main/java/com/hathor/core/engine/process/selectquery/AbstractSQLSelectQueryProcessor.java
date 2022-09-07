package com.hathor.core.engine.process.selectquery;

import com.alibaba.druid.sql.ast.SQLObject;
import com.alibaba.druid.sql.ast.statement.SQLCreateTableStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLSubqueryTableSource;
import com.hathor.core.engine.model.TableNode;
import com.hathor.core.engine.model.TreeNode;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>AbstractSQLSelectQueryProcessor</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 13:16
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@Slf4j
public abstract class AbstractSQLSelectQueryProcessor implements SQLSelectQueryProcessor {

    @Override
    public void process(String dbType, AtomicInteger sequence, TreeNode<TableNode> parent,
                        SQLSelectQuery sqlSelectQuery) {

    }

    /**
     * SQLSelectStatement 考虑中
     * SQLCreateTableStatement 考虑中
     * SQLSubqueryTableSource V SQLObject Contain SQLSelect 时, 合并
     * SQLSubqueryTableSource 中的
     * TODO
     *
     * @param sqlObject sqlObject
     * @return alias
     */
    protected String getSubqueryTableSourceAlias(SQLObject sqlObject) {
        SQLObject parentObject = sqlObject.getParent().getParent();
        if (sqlObject.getParent() == null || parentObject == null) {
            return null;
        }
        if (parentObject instanceof SQLSubqueryTableSource) {
            SQLSubqueryTableSource sqlSubqueryTableSource = (SQLSubqueryTableSource) parentObject;
            return sqlSubqueryTableSource.getAlias();
        } else if (parentObject instanceof SQLSelectStatement
                || parentObject instanceof SQLCreateTableStatement) {
            log.info("TODO 开发~~~");
            return null;
        } else {
            return null;
        }
    }


}
