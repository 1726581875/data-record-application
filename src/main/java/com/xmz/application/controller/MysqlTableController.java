package com.xmz.application.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.application.model.common.param.TableListParam;
import com.xmz.application.model.metadata.entity.MysqlTable;
import com.xmz.application.service.MysqlTableService;
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
@RequestMapping("/mysqlTable")
public class MysqlTableController {

    @Autowired
    private MysqlTableService tableService;

    @PostMapping("getList")
    private Page<MysqlTable> getList(@RequestBody TableListParam param){
        return tableService.getList(param);
    }
}
