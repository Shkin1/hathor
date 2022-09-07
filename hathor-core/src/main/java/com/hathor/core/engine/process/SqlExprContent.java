package com.hathor.core.engine.process;

import com.hathor.core.engine.model.TableNode;
import com.hathor.core.engine.model.TreeNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>SqlExprContent</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/27 20:03
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SqlExprContent {

    /**
     * items 中的字段
     */
    private String owner;

    /**
     * name = "*" & owner!=null & isConstant=true  此时为AllColumn，
     * 若只是 "*" 还可能是 SQLCharExprProcessor 处理的结果
     */
    private String name;

    /**
     * select 子查询列
     *
     */
    private TreeNode<TableNode> treeNode;

    /**
     * 是否为字符串常量，
     * 默认为false
     * true @see SQLCharExpr
     */
    @Builder.Default
    private boolean isConstant = false;

    private final List<SqlExprContent> items = new ArrayList<>();

    public boolean isFirst() {
        return name == null;
    }

    public void addItem(SqlExprContent content) {
        if (isFirst()) {
            this.owner = content.owner;
            this.name = content.name;
        } else {
            items.add(content);
        }
    }

    public List<SqlExprContent> getAllItems() {
        List<SqlExprContent> list = new ArrayList<>();
        list.add(SqlExprContent.builder().owner(this.owner).name(this.name).build());
        list.addAll(items);
        return list;
    }

    public boolean isEmptyChildren() {
        return items.size() == 0;
    }

    public static SqlExprContent of() {
        return new SqlExprContent();
    }
}
