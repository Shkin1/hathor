package com.hathor.common.entity;

import java.io.Serializable;

/**
 * <p>HathorBloodBase</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/24 00:07
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
public class HathorBloodExplain implements Serializable {
    public HathorBloodExplain(String dbType, String originalSql) {
        this.dbType = dbType;
        this.originalSql = originalSql;
    }

    /**
     * 数据库引擎类型
     */
    private String dbType;

    /**
     * 原始SQL语句.
     */
    private String originalSql;

    /**
     * 去除注释后的语句.
     */
    private String rmDescSql;

    /**
     * 原始SQL个数.
     */
    private Integer sqlSize;

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getOriginalSql() {
        return originalSql;
    }

    public void setOriginalSql(String originalSql) {
        this.originalSql = originalSql;
    }

    public String getRmDescSql() {
        return rmDescSql;
    }

    public void setRmDescSql(String rmDescSql) {
        this.rmDescSql = rmDescSql;
    }

    public Integer getSqlSize() {
        return sqlSize;
    }

    public void setSqlSize(Integer sqlSize) {
        this.sqlSize = sqlSize;
    }

    @Override
    public String toString() {
        return "HathorBloodExplain{" +
                "dbType='" + dbType + '\'' +
                ", originalSql='" + originalSql + '\'' +
                ", rmDescSql='" + rmDescSql + '\'' +
                ", sqlSize=" + sqlSize +
                '}';
    }
}
