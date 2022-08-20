package com.xmz.datarecordapplication.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.datarecordapplication.model.entity.metadata.MysqlTable;
import com.xmz.datarecordapplication.model.param.TableListParam;
import com.xmz.datarecordapplication.service.MysqlTableService;
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
