package com.hathor.core.engine.util;

import com.hathor.core.engine.analyse.SqlResponseContext;
import com.hathor.core.engine.model.HathorLineage;
import com.hathor.core.engine.model.Node;
import com.hathor.core.engine.model.TableNode;
import com.hathor.core.engine.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>TreeNodeUtil</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/30 20:35
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
public class TreeNodeUtil {
    private TreeNodeUtil() {
    }

    /**
     * 返回中间以及叶子表
     *
     * @param root root
     * @return List<TableNode>
     */
    public static List<TableNode> searchTreeLeafNodeList(TreeNode<TableNode> root) {
        List<TableNode> list = new ArrayList<>();
//        traverseNodeLineageTree(root, list);
        unRootTableSource(root, list);
        return list;
    }

    /**
     * 返回叶子列表
     *
     * @param root           TreeNode<TableNode>
     * @param sourceNodeList List<TableNode>
     */
    private static <T extends Node> void traverseNodeLineageTree(TreeNode<T> root, List<T> sourceNodeList) {
        if (root.isLeaf()) {
            sourceNodeList.add(root.getValue());
        } else {
            root.getChildList().forEach(node -> traverseNodeLineageTree(node, sourceNodeList));
        }
    }


    /**
     * 返回非根节点
     *
     * @param root           TreeNode<TableNode>
     * @param sourceNodeList List<TableNode>
     */
    private static <T extends Node> void unRootTableSource(TreeNode<TableNode> root, List<TableNode> sourceNodeList) {
        if (root.isLeaf() && !root.getValue().getVirtualTemp()) {
            sourceNodeList.add(root.getValue());
            return;
        }
        List<TreeNode<TableNode>> childList = root.getChildList();
        for (TreeNode<TableNode> tableNodeTreeNode : childList) {
            // 中间虚拟的
            if (root.getParent() != null && Boolean.TRUE.equals(root.getValue().getVirtualTemp())) {
                sourceNodeList.add(root.getValue());
            }
            unRootTableSource(tableNodeTreeNode, sourceNodeList);
        }
    }

    // TODO 改为指定顺序的遍历
    private static void getAllTableNode(TreeNode<TableNode> root, List<TableNode> allNode) {
        if (root.getChildList() == null) {
            return;
        }
        List<TreeNode<TableNode>> childList = root.getChildList();
        for (TreeNode<TableNode> tableNodeTreeNode : childList) {
            TableNode value = tableNodeTreeNode.getValue();
            allNode.add(value);
            getAllTableNode(tableNodeTreeNode, allNode);
        }
    }

    private static void getMidTableNode(TreeNode<TableNode> root, List<TableNode> allNode) {
        if (root.getChildList() == null) {
            return;
        }
        List<TreeNode<TableNode>> childList = root.getChildList();
        for (TreeNode<TableNode> tableNodeTreeNode : childList) {
            TableNode value = tableNodeTreeNode.getValue();
            if (Node.V_TABLE_TYPE.equals(value.getType())) {
                allNode.add(value);
            }
            getMidTableNode(tableNodeTreeNode, allNode);
        }
    }

    private static void getLeafTableNode(TreeNode<TableNode> root, List<TableNode> allNode) {
        if (root.getChildList() == null) {
            return;
        }
        List<TreeNode<TableNode>> childList = root.getChildList();
        for (TreeNode<TableNode> tableNodeTreeNode : childList) {
            TableNode value = tableNodeTreeNode.getValue();
            if (Node.EXPR_TYPE.equals(value.getType())) {
                allNode.add(value);
            }
            getLeafTableNode(tableNodeTreeNode, allNode);
        }
    }


    private static void fillPkName(TreeNode<TableNode> lineageTableTree) {
        if (lineageTableTree.getChildList() == null) {
            return;
        }
        List<TreeNode<TableNode>> childList = lineageTableTree.getChildList();
        for (TreeNode<TableNode> tableNodeTreeNode : childList) {
            if (tableNodeTreeNode.getParent() != null) {
                TableNode value = tableNodeTreeNode.getValue();
                TreeNode<TableNode> parent = tableNodeTreeNode.getParent();
                value.setPkName(parent.getValue().getName());
            }
            fillPkName(tableNodeTreeNode);
        }


    }

    /**
     * 表级血缘
     *
     * @param response response.
     */
    public static HathorLineage tableRelHandle(SqlResponseContext response) {
        HathorLineage hathorLineage = new HathorLineage();
        // 表关系填充
        TreeNode<TableNode> lineageTableTree = response.getLineageTableTree();
        fillPkName(lineageTableTree);

        // 1. 根节点
        TableNode rootTableNode = lineageTableTree.getRoot().getValue();
        hathorLineage.setRootTableNode(rootTableNode);
        // 2. 中间节点
        ArrayList<TableNode> midTableNode = new ArrayList<>();
        getMidTableNode(lineageTableTree, midTableNode);
        hathorLineage.setMidTableNode(midTableNode);

        // 3. 叶子节点
        ArrayList<TableNode> leafTableNodes = new ArrayList<>();
        getLeafTableNode(lineageTableTree, leafTableNodes);
        hathorLineage.setLeafTableNode(leafTableNodes);

        // 4. 所有节点
        ArrayList<TableNode> allTableNodes = new ArrayList<>();
        getAllTableNode(lineageTableTree, allTableNodes);
        hathorLineage.setAllTableNode(allTableNodes);
        return hathorLineage;

    }

}
