package com.hathor.api;


import com.hathor.core.engine.model.v1.HathorTableBlood;

/**
 * <p>HathorSqlLineage</p>
 * <p>Description:
 * </p> Sql Flow Lineage API
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/23 23:54
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
public interface HathorSqlLineage {

    /**
     * sqlTableAnalysis.
     *
     * @param sql    input sql
     * @param dbType sql db type
     * @return HathorTableBlood
     */
    HathorTableBlood sqlTableAnalysis(String sql, String dbType);


}
