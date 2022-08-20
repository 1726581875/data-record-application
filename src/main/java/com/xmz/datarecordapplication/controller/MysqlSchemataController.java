package com.xmz.datarecordapplication.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.datarecordapplication.model.entity.metadata.MysqlSchemata;
import com.xmz.datarecordapplication.model.param.SchemataListParam;
import com.xmz.datarecordapplication.service.MysqlSchemataService;
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
@RequestMapping("/mysqlSchemata")
public class MysqlSchemataController {

    @Autowired
    private MysqlSchemataService schemataService;

    @PostMapping("getList")
    private Page<MysqlSchemata> getList(@RequestBody SchemataListParam param) {
        return schemataService.getList(param);
    }



}
