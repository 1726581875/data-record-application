package com.xmz.application.service.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.application.model.common.RespResult;
import com.xmz.application.model.common.dto.TenantSourceDTO;
import com.xmz.application.model.sys.entity.SysDataSource;
import com.xmz.application.model.sys.entity.SysTenantTable;
import com.xmz.application.model.sys.param.DataMigrationParam;
import com.xmz.application.model.sys.param.DataSourceListParam;
import com.xmz.application.model.sys.param.TenantTableListParam;
import com.xmz.application.model.common.vo.SysDataSourceListVO;

/**
 * @author xiaomingzhang
 * @date 2022/9/13
 */
public interface SysDataSourceService {


    Page<SysDataSourceListVO> getList(DataSourceListParam param);

    void save(SysDataSource dataSource);

    void addDataSource(SysDataSource dataSource);

    void updateDataSourceById(SysDataSource dataSource);

    void deleteById(String id);

    Page<SysTenantTable> getTenantTableList(TenantTableListParam param);

    void dataMigration(DataMigrationParam param);

    RespResult listenBinlog(TenantSourceDTO dto);

    RespResult cancelListen(TenantSourceDTO dto);
}
