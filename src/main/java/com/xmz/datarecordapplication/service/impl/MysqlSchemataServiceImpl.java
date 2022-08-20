package com.xmz.datarecordapplication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.datarecordapplication.mapper.metadata.MysqlSchemataMapper;
import com.xmz.datarecordapplication.model.entity.metadata.MysqlSchemata;
import com.xmz.datarecordapplication.model.param.SchemataListParam;
import com.xmz.datarecordapplication.service.MysqlSchemataService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author xiaomingzhang
 * @date 2022/8/19
 */
@Service
public class MysqlSchemataServiceImpl implements MysqlSchemataService {

    @Resource
    private MysqlSchemataMapper schemataMapper;

    @Override
    public Page<MysqlSchemata> getList(SchemataListParam param) {
        return schemataMapper.selectPage(param, new LambdaQueryWrapper<MysqlSchemata>()
                .like(StringUtils.hasLength(param.getDbName()),MysqlSchemata::getSchemaName, param.getDbName()));
    }
}
