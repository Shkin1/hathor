package com.hathor.portal.controller;

import com.alibaba.fastjson2.JSON;
import com.hathor.common.entity.response.ResponseVO;
import com.hathor.core.engine.analyse.SqlRequestContext;
import com.hathor.core.engine.analyse.SqlResponseContext;
import com.hathor.core.engine.analyse.handler.DefaultAnalyseTreeBuildChain;
import com.hathor.core.engine.gsqlparser.GSqlDataFlowAnalyzer;
import com.hathor.core.engine.model.HathorLineage;
import com.hathor.core.engine.model.v1.HathorTableBlood;
import com.hathor.core.engine.util.TreeNodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;


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
        HathorLineage hathorLineage = TreeNodeUtil.tableRelHandle(response);
        return ResponseVO.ok(hathorLineage);
    }


    @GetMapping("tableLineage")
    @ApiOperation(value = "tableLineage", notes = "tableLineage", httpMethod = "GET")

    public ResponseVO tableLineage(String sql, String dbType) {

        GSqlDataFlowAnalyzer gSqlDataFlowAnalyzer = new GSqlDataFlowAnalyzer();
        HathorTableBlood tableBlood = gSqlDataFlowAnalyzer.parse(sql, dbType);
        String result = JSON.toJSONString(tableBlood);
        return ResponseVO.ok(result);
    }


}
