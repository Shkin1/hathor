package com.hathor.core.engine.analyse.handler;

import com.hathor.core.engine.analyse.SqlRequestContext;
import com.hathor.core.engine.analyse.SqlResponseContext;
import com.hathor.core.engine.contants.StartOrderConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <p>FirstTableSupplementHandler</p>
 * <p>Description:
 *
 * 补全首节点column信息，在解析时针对字段不包含列名的情况
 * 1. create view xxx as select
 * 2. insert overwrite into ...
 * 3 ....
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 15:35
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@Order(StartOrderConstants.ORDER_SECOND)
@Component
@Slf4j
public class FirstTableSupplementHandler  implements IHandler{


    @Override
    public void handleRequest(SqlRequestContext request, SqlResponseContext response) {
        log.info("TODO 补全首节点column信息，在解析时针对字段不包含列名的情况");
    }
}
