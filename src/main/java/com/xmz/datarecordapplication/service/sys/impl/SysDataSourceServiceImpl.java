package com.xmz.datarecordapplication.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.datarecordapplication.client.DataRecordClient;
import com.xmz.datarecordapplication.common.UserContext;
import com.xmz.datarecordapplication.common.exception.DataNotFoundException;
import com.xmz.datarecordapplication.mapper.sys.SysDataSourceMapper;
import com.xmz.datarecordapplication.mapper.sys.SysTenantTableMapper;
import com.xmz.datarecordapplication.model.dto.DataMigrationDTO;
import com.xmz.datarecordapplication.model.entity.sys.SysDataSource;
import com.xmz.datarecordapplication.model.entity.sys.SysTenantTable;
import com.xmz.datarecordapplication.model.param.sys.DataMigrationParam;
import com.xmz.datarecordapplication.model.param.sys.DataSourceListParam;
import com.xmz.datarecordapplication.model.param.sys.TenantTableListParam;
import com.xmz.datarecordapplication.service.sys.SysDataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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

    @Override
    public Page<SysDataSource> getList(DataSourceListParam param) {

        String tenantId = UserContext.getAuthorizeUser().getTenantId();
        Page<SysDataSource> page = dataSourceMapper.selectPage(param,
                new LambdaQueryWrapper<SysDataSource>()
                        .eq(SysDataSource::getTenantId, tenantId)
                        .like(StringUtils.hasLength(param.getName()), SysDataSource::getName, param.getName()));

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
        Assert.notNull(dataSource.getId(), "id????????????");
        SysDataSource sysDataSource = dataSourceMapper.selectById(dataSource.getId());

        if(sysDataSource == null) {
            throw new DataNotFoundException("???????????????????????????id:" + dataSource.getId());
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

        return tenantTableMapper.selectPage(param, new LambdaQueryWrapper<SysTenantTable>()
                .eq(SysTenantTable::getDataSourceId, param.getDataSourceId())
                .eq(SysTenantTable::getTenantId, tenantId)
                .orderByDesc(SysTenantTable::getUpdateTime));
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

}
