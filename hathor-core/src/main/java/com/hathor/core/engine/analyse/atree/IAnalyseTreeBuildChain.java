package com.hathor.core.engine.analyse.atree;

import com.hathor.core.engine.analyse.SqlRequestContext;
import com.hathor.core.engine.analyse.SqlResponseContext;

/**
 * <p>IAnalyseTreeBuildChain</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 10:08
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
public interface IAnalyseTreeBuildChain {

    /**
     * 构建链
     *
     * @param request  request
     * @param response response
     */
    void build(SqlRequestContext request, SqlResponseContext response);
}
