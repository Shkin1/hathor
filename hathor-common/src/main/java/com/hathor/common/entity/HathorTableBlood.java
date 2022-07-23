package com.hathor.common.entity;

import java.util.Set;

/**
 * <p>HathorTableLineage</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/24 00:06
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
public class HathorTableBlood extends HathorBloodExplain {
    public HathorTableBlood(String dbType, String originalSql) {
        super(dbType, originalSql);
    }

    private Set<String> sourceTable;
    private Set<String> targetTable;
}
