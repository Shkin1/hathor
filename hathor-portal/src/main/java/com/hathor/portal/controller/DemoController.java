package com.hathor.portal.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.hathor.common.entity.response.ResponseVO;
import com.hathor.core.engine.analyse.SqlRequestContext;
import com.hathor.core.engine.analyse.SqlResponseContext;
import com.hathor.core.engine.analyse.handler.DefaultAnalyseTreeBuildChain;
import com.hathor.core.engine.model.HathorLineage;
import com.hathor.core.engine.model.TableNode;
import com.hathor.core.engine.model.TreeNode;
import com.hathor.core.engine.util.TreeNodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * <p>DemoController</p>
 * <p>Description:
 * </p>
 *
 * @author 普帝
 * @version v1.0.0
 * @date 2022/07/2022/7/28 18:27
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
@CrossOrigin
@RestController
@Api(value = "SQL解析", tags = {"SQL解析"})
@RequestMapping("/api/sql/parse/demo")
@Slf4j
public class DemoController {
    private final DefaultAnalyseTreeBuildChain defaultHandlerChain;

    @Autowired
    public DemoController(DefaultAnalyseTreeBuildChain defaultHandlerChain) {
        this.defaultHandlerChain = defaultHandlerChain;
    }

    @GetMapping("parse")
    @ApiOperation(value = "parse", notes = "demo1", httpMethod = "GET")
    public ResponseVO parse(String value, String dbType) {
        SqlRequestContext sqlRequestContext = new SqlRequestContext();
        SqlResponseContext response = new SqlResponseContext();

        log.info("SQL ---->: {}", value);
        sqlRequestContext.setSql(value);
        sqlRequestContext.setDbType(dbType);

        defaultHandlerChain.build(sqlRequestContext, response);
        return null;
    }


}
