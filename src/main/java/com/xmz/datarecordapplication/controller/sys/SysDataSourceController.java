package com.xmz.datarecordapplication.controller.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.datarecordapplication.model.entity.sys.SysDataSource;
import com.xmz.datarecordapplication.model.param.sys.DataSourceListParam;
import com.xmz.datarecordapplication.service.sys.SysDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaomingzhang
 * @date 2022/9/13
 */
@RestController
@RequestMapping("/dataSource")
public class SysDataSourceController {

    @Autowired
    private SysDataSourceService sourceService;


    @PostMapping("/getList")
    public Page<SysDataSource> getList(@RequestBody DataSourceListParam param) {
        return sourceService.getList(param);
    }

    @PostMapping("/add")
    public void add(@RequestBody SysDataSource param) {
         sourceService.addDataSource(param);
    }

    @PostMapping("/update")
    public void update(@RequestBody SysDataSource param) {
         sourceService.updateDataSourceById(param);
    }



}
