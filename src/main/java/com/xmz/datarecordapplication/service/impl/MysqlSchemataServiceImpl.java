package com.xmz.datarecordapplication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.datarecordapplication.mapper.metadata.MysqlSchemaMapper;
import com.xmz.datarecordapplication.model.entity.metadata.MysqlSchema;
import com.xmz.datarecordapplication.model.param.SchemataListParam;
import com.xmz.datarecordapplication.service.MysqlSchemaService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author xiaomingzhang
 * @date 2022/8/19
 */
@Service
public class MysqlSchemataServiceImpl implements MysqlSchemaService {

    @Resource
    private MysqlSchemaMapper schemaMapper;

    @Override
    public Page<MysqlSchema> getList(SchemataListParam param) {
        return schemaMapper.selectPage(param, new LambdaQueryWrapper<MysqlSchema>()
                .like(StringUtils.hasLength(param.getDbName()), MysqlSchema::getSchemaName, param.getDbName()));
    }
}
