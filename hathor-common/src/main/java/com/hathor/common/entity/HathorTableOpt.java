package com.hathor.common.entity;

import java.util.Map;

/**
 * <p>HathorTableOpt</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/24 00:17
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
public class HathorTableOpt extends HathorBloodExplain {
    public HathorTableOpt(String dbType, String originalSql) {
        super(dbType, originalSql);
    }

    private Map<String, String> tableOpt;

    @Override
    public String toString() {
        return "HathorTableOpt{" +
                "tableOpt=" + tableOpt +
                '}';
    }
}
