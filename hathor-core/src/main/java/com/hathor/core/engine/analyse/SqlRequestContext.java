package com.hathor.core.engine.analyse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>SqlRequestContext</p>
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
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SqlRequestContext {

    /**
     * sql 处理的数据库类型
     */
    private String dbType;
    /**
     * 单条 sql 语句
     */
    private String sql;
    /**
     * SQL 执行时的schema
     */
    private String schemaName;

    private String platformName;
    private String clusterName;
    private Long tenantId;
    private String datasourceCode;
    private String catalogName;
}
