package com.hathor.core.engine.model.v1;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * <p>NodeRelPool</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/08/2022/8/1 18:03
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
public class NodeRelPool implements Serializable {
    private final Map<String, Set<String>> pools;

    public NodeRelPool() {
        this.pools = new HashMap<>();
    }

    public void put(String parentId, String sonId) {
        if (pools.containsKey(parentId)) {
            Set<String> childList = pools.get(parentId);
            // TODO 去重
            childList.add(sonId);
            pools.put(parentId, childList);
            return;
        }
        Set<String> childList = new TreeSet<>();
        childList.add(sonId);
        pools.put(parentId, childList);
    }

    public Map<String, Set<String>> getPools() {
        return pools;
    }
}
