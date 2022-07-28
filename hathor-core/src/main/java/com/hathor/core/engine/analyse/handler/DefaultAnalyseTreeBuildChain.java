package com.hathor.core.engine.analyse.handler;

import com.hathor.core.engine.analyse.SqlRequestContext;
import com.hathor.core.engine.analyse.SqlResponseContext;

import java.util.List;

/**
 * <p>DefaultBuildChain</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 10:10
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
public class DefaultAnalyseTreeBuildChain implements IHandlerChain {

    private final List<IHandler> handlerList;

    public DefaultAnalyseTreeBuildChain(List<IHandler> handlerList) {
        this.handlerList = handlerList;
    }

    @Override
    public void build(SqlRequestContext request, SqlResponseContext response) {
        handlerList.forEach(handler -> handler.handleRequest(request, response));
    }
}
