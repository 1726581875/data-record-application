package com.xmz.application.controller.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.application.model.common.RespResult;
import com.xmz.application.model.common.dto.TenantSourceDTO;
import com.xmz.application.model.sys.entity.SysDataSource;
import com.xmz.application.model.sys.entity.SysTenantTable;
import com.xmz.application.model.sys.param.DataMigrationParam;
import com.xmz.application.model.sys.param.DataSourceListParam;
import com.xmz.application.model.sys.param.TenantTableListParam;
import com.xmz.application.model.common.vo.SysDataSourceListVO;
import com.xmz.application.service.sys.SysDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public Page<SysDataSourceListVO> getList(@RequestBody DataSourceListParam param) {
        return sourceService.getList(param);
    }

    @PostMapping("/save")
    public void save(@RequestBody SysDataSource param) {
        sourceService.save(param);
    }


    @PostMapping("/add")
    public void add(@RequestBody SysDataSource param) {
         sourceService.addDataSource(param);
    }

    @PostMapping("/update")
    public void update(@RequestBody SysDataSource param) {
         sourceService.updateDataSourceById(param);
    }

    @DeleteMapping("/deleteById")
    public void deleteById(@RequestParam("id") String id) {
        sourceService.deleteById(id);
    }

    @PostMapping("/getTenantTables")
    public Page<SysTenantTable> getTenantTableList(@RequestBody @Valid TenantTableListParam param) {
        return sourceService.getTenantTableList(param);
    }

    @PostMapping("/dataMigration")
    public void dataMigration(@RequestBody DataMigrationParam param) {
        sourceService.dataMigration(param);
    }

    /**
     * 监听binlog日志
     * @param dto
     * @return
     */
    @PostMapping("/listenBinlog")
    public RespResult listenBinlog(@RequestBody TenantSourceDTO dto) {
        return sourceService.listenBinlog(dto);
    }

    @PostMapping("/cancelListen")
    public RespResult cancelListen(@RequestBody TenantSourceDTO dto){
        return sourceService.cancelListen(dto);
    }



}
