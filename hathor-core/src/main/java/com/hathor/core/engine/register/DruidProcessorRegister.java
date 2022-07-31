package com.hathor.core.engine.register;

import com.hathor.core.engine.process.expr.SQLExprProcessor;
import com.hathor.core.engine.process.selectquery.SQLSelectQueryProcessor;
import com.hathor.core.engine.process.statement.StatementProcessor;
import com.hathor.core.engine.process.tablesource.TableSourceProcessor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>DruidProcessorRegister</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 10:55
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@Slf4j
public class DruidProcessorRegister {


    /**
     * SQLStatement 处理器
     */
    private static final Map<Type, StatementProcessor> STATEMENT_PROCESSOR_MAP = new HashMap<>(16);

    /**
     * SQLSelectQuery 处理器
     */
    private static final Map<Type, SQLSelectQueryProcessor> SQL_SELECT_QUERY_PROCESSOR_MAP = new HashMap<>(16);

    /**
     * SQLTableSource 处理器
     */
    private static final Map<Type, TableSourceProcessor> TABLE_SOURCE_PROCESSOR_MAP = new HashMap<>(16);

    /**
     * SQLTableSource 处理器
     */
    private static final Map<Type, SQLExprProcessor> TABLE_SQL_EXPR_MAP = new HashMap<>(16);

    public static void register(Class<?> clazz, Object bean) {
        if (bean instanceof StatementProcessor) {
            log.info("[StatementProcessor] - load statement processor : {}", bean.getClass().getName());
            STATEMENT_PROCESSOR_MAP.put(clazz, (StatementProcessor) bean);
        } else if (bean instanceof SQLSelectQueryProcessor) {
            log.info("[SQLSelectQueryProcessor] - load select query processor : {}", bean.getClass().getName());
            SQL_SELECT_QUERY_PROCESSOR_MAP.put(clazz, (SQLSelectQueryProcessor) bean);
        } else if (bean instanceof TableSourceProcessor) {
            log.info("[TableSourceProcessor] - load table source processor : {}", bean.getClass().getName());
            TABLE_SOURCE_PROCESSOR_MAP.put(clazz, (TableSourceProcessor) bean);
        } else if (bean instanceof SQLExprProcessor) {
            log.info("[SQLExprProcessor] - load table sql expr processor : {}", bean.getClass().getName());
            TABLE_SQL_EXPR_MAP.put(clazz, (SQLExprProcessor) bean);
        }
    }

    public static StatementProcessor getStatementProcessor(Type clazz) {
        StatementProcessor statementProcessor = STATEMENT_PROCESSOR_MAP.get(clazz);
        if (Objects.isNull(statementProcessor)) {
            log.info("[TODO] - 待开发: {}", clazz.getTypeName());
            throw new UnsupportedOperationException(clazz.getTypeName());
        }
        return statementProcessor;
    }

    public static SQLExprProcessor getSQLExprProcessor(Type clazz) {
        SQLExprProcessor sqlExprProcessor = TABLE_SQL_EXPR_MAP.get(clazz);
        if (Objects.isNull(sqlExprProcessor)) {
            log.info("[TODO] - 待开发: {}", clazz.getTypeName());
            throw new UnsupportedOperationException(clazz.getTypeName());
        }
        return sqlExprProcessor;
    }

    public static SQLSelectQueryProcessor getSQLSelectQueryProcessor(Type clazz) {
        SQLSelectQueryProcessor sqlSelectQueryProcessor = SQL_SELECT_QUERY_PROCESSOR_MAP.get(clazz);
        if (Objects.isNull(sqlSelectQueryProcessor)) {
            log.info("[TODO] - 待开发: {}", clazz.getTypeName());
            throw new UnsupportedOperationException(clazz.getTypeName());
        }
        return sqlSelectQueryProcessor;
    }

    public static TableSourceProcessor getTableSourceProcessor(Type clazz) {
        TableSourceProcessor tableSourceProcessor = TABLE_SOURCE_PROCESSOR_MAP.get(clazz);
        if (Objects.isNull(tableSourceProcessor)) {
            log.info("[TODO] - 待开发: {}", clazz.getTypeName());
            throw new UnsupportedOperationException(clazz.getTypeName());
        }
        return tableSourceProcessor;
    }

}
