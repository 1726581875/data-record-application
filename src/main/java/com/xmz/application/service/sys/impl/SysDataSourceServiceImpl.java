package com.xmz.application.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.application.client.DataRecordClient;
import com.xmz.application.common.UserContext;
import com.xmz.application.common.exception.DataNotFoundException;
import com.xmz.application.mapper.sys.SysDataSourceMapper;
import com.xmz.application.mapper.sys.SysTenantTableMapper;
import com.xmz.application.model.common.dto.DataMigrationDTO;
import com.xmz.application.client.BinlogClient;
import com.xmz.application.model.common.RespResult;
import com.xmz.application.model.common.dto.TenantSourceDTO;
import com.xmz.application.model.sys.entity.SysDataSource;
import com.xmz.application.model.sys.entity.SysTenantTable;
import com.xmz.application.model.sys.param.DataMigrationParam;
import com.xmz.application.model.sys.param.DataSourceListParam;
import com.xmz.application.model.sys.param.TenantTableListParam;
import com.xmz.application.model.common.vo.SysDataSourceListVO;
import com.xmz.application.service.sys.SysDataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

/**
 * @author xiaomingzhang
 * @date 2022/9/13
 */
@Slf4j
@Service
public class SysDataSourceServiceImpl implements SysDataSourceService {

    @Resource
    private SysDataSourceMapper dataSourceMapper;
    @Resource
    private SysTenantTableMapper tenantTableMapper;
    @Autowired
    private DataRecordClient dataRecordClient;
    @Autowired
    private BinlogClient binlogClient;

    @Override
    public Page<SysDataSourceListVO> getList(DataSourceListParam param) {

        String tenantId = UserContext.getAuthorizeUser().getTenantId();
        param.setTenantId(tenantId);

        Page<SysDataSourceListVO> page = dataSourceMapper.getList(param);
        if(!CollectionUtils.isEmpty(page.getRecords())){
            page.getRecords().forEach(e -> e.setPassword(null));
        }

        return page;
    }

    @Override
    public void save(SysDataSource dataSource) {
        if(dataSource.getId() != null){
            updateDataSourceById(dataSource);
        } else {
            addDataSource(dataSource);
        }
    }

    @Override
    public void addDataSource(SysDataSource dataSource) {
        String tenantId = UserContext.getAuthorizeUser().getTenantId();
        dataSource.setTenantId(tenantId);
        dataSourceMapper.insert(dataSource);
    }

    @Override
    public void updateDataSourceById(SysDataSource dataSource) {
        Assert.notNull(dataSource.getId(), "id不能为空");
        SysDataSource sysDataSource = dataSourceMapper.selectById(dataSource.getId());

        if(sysDataSource == null) {
            throw new DataNotFoundException("用户数据源不存在，id:" + dataSource.getId());
        }

        dataSourceMapper.updateById(dataSource);

    }

    @Override
    public void deleteById(String id) {
        dataSourceMapper.deleteById(id);
    }

    @Override
    public Page<SysTenantTable> getTenantTableList(TenantTableListParam param) {

        String tenantId = UserContext.getAuthorizeUser().getTenantId();

        Page<SysTenantTable> page = tenantTableMapper.selectPage(param, new LambdaQueryWrapper<SysTenantTable>()
                .eq(SysTenantTable::getDataSourceId, param.getDataSourceId())
                .eq(SysTenantTable::getTenantId, tenantId)
                .orderByDesc(SysTenantTable::getUpdateTime));

        return page;
    }

    @Override
    public void dataMigration(DataMigrationParam param) {
        String tenantId = UserContext.getAuthorizeUser().getTenantId();
        DataMigrationDTO dto = new DataMigrationDTO();
        dto.setTenantId(tenantId);
        dto.setDataSourceId(param.getDataSourceId());
        dto.setTableName(param.getTableName());

        dataRecordClient.doDataMigration(dto);
    }

    @Override
    public RespResult listenBinlog(TenantSourceDTO dto) {
        String tenantId = UserContext.getAuthorizeUser().getTenantId();
        dto.setTenantId(tenantId);
        return binlogClient.listenBinlog(dto);
    }

    @Override
    public RespResult cancelListen(TenantSourceDTO dto) {
        String tenantId = UserContext.getAuthorizeUser().getTenantId();
        dto.setTenantId(tenantId);
        return binlogClient.cancelListen(dto);
    }

}
