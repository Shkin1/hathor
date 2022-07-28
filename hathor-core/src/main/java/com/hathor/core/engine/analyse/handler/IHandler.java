package com.hathor.core.engine.analyse.handler;

import com.hathor.core.engine.analyse.SqlRequestContext;
import com.hathor.core.engine.analyse.SqlResponseContext;

/**
 * <p>IAnalyseTree</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 10:04
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
public interface IHandler {
    /**
     * 表血缘分析树构建
     *
     * @param request  sql 请求
     * @param response 响应
     */
    void handleRequest(SqlRequestContext request, SqlResponseContext response);
}
