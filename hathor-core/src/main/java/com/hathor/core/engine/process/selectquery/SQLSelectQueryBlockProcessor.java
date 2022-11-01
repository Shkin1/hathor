package com.hathor.core.engine.process.selectquery;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.dialect.postgresql.ast.stmt.PGSelectQueryBlock;
import com.hathor.common.contants.ParserConstant;
import com.hathor.core.engine.annotation.SQLObjectType;
import com.hathor.core.engine.model.ColumnNode;
import com.hathor.core.engine.model.Node;
import com.hathor.core.engine.model.TableNode;
import com.hathor.core.engine.model.TreeNode;
import com.hathor.core.engine.process.SqlExprContent;
import com.hathor.core.engine.register.DruidProcessorRegister;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * <p>SQLSelectQueryBlock</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/31 17:31
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@SQLObjectType(clazz = SQLSelectQueryBlock.class)
@Slf4j
public class SQLSelectQueryBlockProcessor extends AbstractSQLSelectQueryProcessor{
    @Override
    public void process(String dbType, AtomicInteger sequence, TreeNode<TableNode> parent,
                        SQLSelectQuery sqlSelectQuery) {
        SQLSelectQueryBlock sqlSelectQueryBlock = (SQLSelectQueryBlock) sqlSelectQuery;
        // 建立表节点，并关系父级关系
        TableNode proxyTable = new TableNode();
        proxyTable.setName(ParserConstant.TEMP_TABLE_PREFIX + sequence.incrementAndGet());
        proxyTable.setVirtualTemp(true);
        proxyTable.setProcessorName(sqlSelectQuery.getClass().getName());
        proxyTable.setExpression(SQLUtils.toSQLString(sqlSelectQueryBlock));
        proxyTable.setAlias(this.getSubqueryTableSourceAlias(sqlSelectQueryBlock));
        proxyTable.setType(Node.V_TABLE_TYPE);
        proxyTable.setProcessorName(sqlSelectQueryBlock.getClass().getName());
        TreeNode<TableNode> proxyNode = TreeNode.of(proxyTable);
        // 生成字段
        List<ColumnNode> columnList = sqlSelectQueryBlock.getSelectList()
                .stream()
                .map(sqlSelectItem -> this.convertSelectItem2Column(dbType, sqlSelectItem))
                .collect(Collectors.toList());
        // TODO 如果字段的表名没有, 考虑填充表名，以提高字段来源检索。
        // 表字段填充到表
        proxyTable.getColumns().addAll(columnList);
        parent.addChild(proxyNode);
        log.info("add column:{}", columnList);
        // 继续向下处理
        DruidProcessorRegister.getTableSourceProcessor(sqlSelectQueryBlock.getFrom().getClass())
                .process(dbType, sequence, proxyNode, sqlSelectQueryBlock.getFrom());
    }

    /**
     * 构建字段，带来源字段
     *
     * @param dbType        数据库类型
     * @param sqlSelectItem SQLSelectItem
     * @return ColumnNode
     */
    private ColumnNode convertSelectItem2Column(String dbType, SQLSelectItem sqlSelectItem) {
        //      1. 如果字段由多字段构成
        //        a. 别名不为空
        //   	    设置别名为第一层字段，来源字段为列表
        //        b. 别名为空
        //   	    // todo，现在考虑为多字段必须写上别名
        //          暂时考虑不为空
        //      2. 如果字段由单字段构成
        //        a. 别名为空。
        //   	    取出字段名，取出表名。
        //        b. 别名不为空。
        //      3. 考虑来源字段为文本｜int 值
        //         // todo 现在考虑为字段为文本｜ int值时过滤掉
        //   	设置别名为第一层字段，来源字段为列表
        SQLExpr sqlExpr = sqlSelectItem.getExpr();
        SqlExprContent sqlExprContent = SqlExprContent.of();
        DruidProcessorRegister.getSQLExprProcessor(sqlExpr.getClass()).process(dbType, sqlExpr, sqlExprContent);
        String alias = sqlSelectItem.getAlias();
        if (sqlExprContent.isEmptyChildren()) {
            String name = sqlExprContent.getName();
            String ownerTable = sqlExprContent.getOwner();
            ColumnNode columnNode = ColumnNode.builder()
                    .name(name)
                    .tableName(ownerTable)
                    .build();
            if (!StringUtils.isEmpty(alias)) {
                columnNode.setAlias(alias);
            }
            return columnNode;
        }
        ColumnNode firstColumnNode = ColumnNode.builder().alias(alias).build();

        List<SqlExprContent> allItems = sqlExprContent.getAllItems();
        List<ColumnNode> sourceColumnNodeList = new ArrayList<>();
        allItems.forEach(item -> sourceColumnNodeList
                .add(ColumnNode.builder().name(item.getName()).tableName(item.getOwner()).build()));
        firstColumnNode.getSourceColumns().addAll(sourceColumnNodeList);

        return firstColumnNode;
    }

}
