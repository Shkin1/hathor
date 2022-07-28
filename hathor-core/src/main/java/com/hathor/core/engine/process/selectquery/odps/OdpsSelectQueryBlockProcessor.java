package com.hathor.core.engine.process.selectquery.odps;

import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.alibaba.druid.sql.dialect.odps.ast.OdpsSelectQueryBlock;
import com.hathor.core.engine.annotation.SQLObjectType;
import com.hathor.core.engine.model.TableNode;
import com.hathor.core.engine.model.TreeNode;
import com.hathor.core.engine.process.selectquery.AbstractSQLSelectQueryProcessor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>OdpsSelectQueryBlockProcessor</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 14:36
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@SQLObjectType(clazz = OdpsSelectQueryBlock.class)
@Slf4j
public class OdpsSelectQueryBlockProcessor extends AbstractSQLSelectQueryProcessor {

    @Override
    public void process(String dbType, AtomicInteger sequence, TreeNode<TableNode> parent, SQLSelectQuery sqlSelectQuery) {
        log.info("ODPS todo");
        super.process(dbType, sequence, parent, sqlSelectQuery);
    }
}
