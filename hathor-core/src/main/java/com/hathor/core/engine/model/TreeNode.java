package com.hathor.core.engine.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>TreeNode</p>
 * <p>Description:
 * 书结构
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/30 22:38
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
public class TreeNode<T extends Node> {

    AtomicLong id = new AtomicLong(0);

    String parentName;

    T value;

    TreeNode<T> parent;

    List<TreeNode<T>> childList;

    int height;

    int subtreeSize;

    public TreeNode() {
    }

    TreeNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public TreeNode<T> getRoot() {
        TreeNode<T> current = this;
        while (current.parent != null) {
            current = current.parent;
        }
        return current;
    }

    public void initChildList() {
        if (childList == null) {
            childList = new ArrayList<>();
        }
    }

    public boolean isLeaf() {
        if (childList == null) {
            return true;
        }
        return childList.size() == 0;
    }

    public boolean isOneChildAndLeaf() {
        return childList != null && childList.size() == 1 && childList.get(0).isLeaf();
    }

    public void addChild(TreeNode<T> childNode) {
        initChildList();
        childNode.parent = this;
        childList.add(childNode);
        childNode.height = Optional.ofNullable(childNode.parent)
                .map(node -> node.height + 1)
                .orElse(0);
        this.subtreeSize++;
        childNode.id = new AtomicLong(Optional.ofNullable(childNode.parent)
                .map(node -> node.id.get() + 1)
                .orElse(0L));
    }

    public List<TreeNode<T>> getChildList() {
        return childList;
    }

    public AtomicLong getId() {
        return id;
    }

    public int getHeight() {
        return height;
    }

    public int getSubtreeSize() {
        return subtreeSize;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public static <T extends Node> TreeNode<T> of(T data) {
        TreeNode<T> treeNode = new TreeNode<>();
        treeNode.setValue(data);
        return treeNode;
    }

}


