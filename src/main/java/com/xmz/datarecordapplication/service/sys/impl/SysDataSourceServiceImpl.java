package com.xmz.datarecordapplication.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.datarecordapplication.client.BinlogClient;
import com.xmz.datarecordapplication.client.DataRecordClient;
import com.xmz.datarecordapplication.common.UserContext;
import com.xmz.datarecordapplication.common.exception.DataNotFoundException;
import com.xmz.datarecordapplication.mapper.sys.SysDataSourceMapper;
import com.xmz.datarecordapplication.mapper.sys.SysTenantTableMapper;
import com.xmz.datarecordapplication.model.common.RespResult;
import com.xmz.datarecordapplication.model.dto.DataMigrationDTO;
import com.xmz.datarecordapplication.model.dto.TenantSourceDTO;
import com.xmz.datarecordapplication.model.entity.sys.SysDataSource;
import com.xmz.datarecordapplication.model.entity.sys.SysTenantTable;
import com.xmz.datarecordapplication.model.param.sys.DataMigrationParam;
import com.xmz.datarecordapplication.model.param.sys.DataSourceListParam;
import com.xmz.datarecordapplication.model.param.sys.TenantTableListParam;
import com.xmz.datarecordapplication.model.vo.SysDataSourceListVO;
import com.xmz.datarecordapplication.service.sys.SysDataSourceService;
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
