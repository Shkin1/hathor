package com.hathor.core.engine.analyse;

import com.hathor.core.engine.model.ColumnNode;
import com.hathor.core.engine.model.TableNode;
import com.hathor.core.engine.model.TreeNode;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * <p>SqlResponseContext</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 09:19
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@Data
public class SqlResponseContext {
    /**
     * 语句类型 INSERT ..., CREATE VIEW AS... , ...
     */
    private String statementType;
    /**
     * 引擎处理的类型
     */
    private String engineProcessingType;
    /**
     * 表血缘解析结果
     */
    private TreeNode<TableNode> lineageTableTree;
    /**
     * 字段血缘解析结果
     */
    private List<TreeNode<ColumnNode>> lineageColumnTreeList;

    /**
     * 表级别血缘
     */
    private List<Map<String, String>> tableRelation;

    /**
     * 字段级别血缘
     */
    private List<Map<String, String>> columnRelation;


}