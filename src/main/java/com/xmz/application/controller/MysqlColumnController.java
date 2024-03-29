package com.xmz.application.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.application.model.metadata.entity.MysqlColumn;
import com.xmz.application.service.MysqlColumnService;
import com.xmz.application.model.common.param.ColumnListParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaomingzhang
 * @date 2022/8/14
 */
@RestController
@RequestMapping("/mysqlColumn")
public class MysqlColumnController {

    @Autowired
    private MysqlColumnService columnService;

    @PostMapping("getList")
    private Page<MysqlColumn> getList(@RequestBody ColumnListParam param){
        return columnService.getList(param);
    }



}
