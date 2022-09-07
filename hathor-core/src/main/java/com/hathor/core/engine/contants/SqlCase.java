package com.hathor.core.engine.contants;

/**
 * <p>SqlCase</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/31 11:31
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
public class SqlCase {

    /**
     * CREATE + JOIN
     *
     */
    public static final String CREATE_JOIN_1 = "CREATE TABLE tmp.tmp_table_a AS SELECT T1.id, T1.age, T2.name FROM dwd.table1 T1 LEFT JOIN dwd.table2 T2 ON T1.id = T2.id";


}
