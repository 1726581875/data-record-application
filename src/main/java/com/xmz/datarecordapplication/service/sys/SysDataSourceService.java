package com.xmz.datarecordapplication.service.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.datarecordapplication.model.entity.sys.SysDataSource;
import com.xmz.datarecordapplication.model.entity.sys.SysTenantTable;
import com.xmz.datarecordapplication.model.param.sys.DataSourceListParam;
import com.xmz.datarecordapplication.model.param.sys.TenantTableListParam;

/**
 * @author xiaomingzhang
 * @date 2022/9/13
 */
public interface SysDataSourceService {


    Page<SysDataSource> getList(DataSourceListParam param);

    void addDataSource(SysDataSource dataSource);

    void updateDataSourceById(SysDataSource dataSource);


    Page<SysTenantTable> getTenantTableList(TenantTableListParam param);
}
