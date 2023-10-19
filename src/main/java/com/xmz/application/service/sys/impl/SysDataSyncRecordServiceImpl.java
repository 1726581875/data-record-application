package com.xmz.application.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.application.common.UserContext;
import com.xmz.application.mapper.sys.SysDataSyncRecordMapper;
import com.xmz.application.model.sys.entity.SysDataSyncRecord;
import com.xmz.application.model.common.param.TableListParam;
import com.xmz.application.service.sys.SysDataSyncRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author xiaomingzhang
 * @date 2022/9/19
 */
@Slf4j
@Service
public class SysDataSyncRecordServiceImpl implements SysDataSyncRecordService {

    @Resource
    private SysDataSyncRecordMapper syncRecordMapper;

    @Override
    public Page<SysDataSyncRecord> getList(TableListParam param) {

        String tenantId = UserContext.getAuthorizeUser().getTenantId();
        LambdaQueryWrapper<SysDataSyncRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysDataSyncRecord::getTenantId, tenantId);
        if (StringUtils.hasLength(param.getDbName())) {
            queryWrapper.like(SysDataSyncRecord::getDbName, param.getDbName());
        }
        if (StringUtils.hasLength(param.getTableName())) {
            queryWrapper.like(SysDataSyncRecord::getSourceTableName, param.getTableName());
        }

        queryWrapper.orderByDesc(SysDataSyncRecord::getCreateTime);

        return syncRecordMapper.selectPage(param, queryWrapper);
    }
}
