package com.xmz.application.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.application.model.metadata.entity.MysqlSchema;
import com.xmz.application.model.common.param.SchemataListParam;
import com.xmz.application.service.MysqlSchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaomingzhang
 * @date 2022/8/19
 */
@RestController
@RequestMapping("/mysqlSchema")
public class MysqlSchemaController {

    @Autowired
    private MysqlSchemaService schemaService;

    @PostMapping("getList")
    private Page<MysqlSchema> getList(@RequestBody SchemataListParam param) {
        return schemaService.getList(param);
    }



}
