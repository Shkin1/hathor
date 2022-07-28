package com.hathor.core.engine.analyse.handler;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.util.JdbcConstants;
import com.hathor.core.engine.analyse.SqlRequestContext;
import com.hathor.core.engine.analyse.SqlResponseContext;
import com.hathor.core.engine.contants.StartOrderConstants;
import com.hathor.core.engine.model.TableNode;
import com.hathor.core.engine.model.TreeNode;
import com.hathor.core.engine.register.DruidProcessorRegister;
import com.hathor.common.exception.ParserException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>TableTreeBuild</p>
 * <p>Description:
 * </p>
 * step1 - 构建血缘分析树
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 10:43
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@Order(StartOrderConstants.ORDER_FIRST)
@Component
public class TableLineageTreeHandler implements IHandler {

    @Override
    public void handleRequest(SqlRequestContext request, SqlResponseContext response) {
        check(request);
        lineageTreeBuild(request, response);
    }

    public void lineageTreeBuild(SqlRequestContext sqlContext, SqlResponseContext response) {
        AtomicInteger sequence = new AtomicInteger();
        TreeNode<TableNode> root = new TreeNode<>();
        SQLStatement statement;
        try {
            // 自动适配对应的statement, eg： PGInsertStatement
//            statement = SQLUtils.parseSingleStatement(
//                    sqlContext.getSql(),
//                    sqlContext.getDbType().toLowerCase());

            // 注意Hive 只有insert create 如何是select会自动转为SQLSelectStatement
            statement = SQLUtils.parseSingleStatement(sqlContext.getSql(),
                    JdbcConstants.HIVE.toLowerCase());
        } catch (Exception e) {
            throw new ParserException("statement.parser.err", e);
        }
        response.setStatementType(statement.getDbType().getClass().getSimpleName().toUpperCase());
        // 处理
        System.out.println("根据statement.getClass()获取对应的statementProcessor :" + statement.getClass());
        DruidProcessorRegister.getStatementProcessor(statement.getClass())
                .process(sqlContext.getDbType(), sequence, root, statement);
        // save
        response.setLineageTableTree(root);
    }


    private void check(SqlRequestContext sqlContext) {
        if (Objects.isNull(sqlContext)) {
            throw new ParserException("[parse] - sqlContext is null ");
        }
        if (StringUtils.isEmpty(sqlContext.getSql())) {
            throw new ParserException("[parse] - sql is null");
        }
        if (StringUtils.isEmpty(sqlContext.getDbType())) {
            throw new ParserException("[parse] - dbType is null");
        }
    }
}
