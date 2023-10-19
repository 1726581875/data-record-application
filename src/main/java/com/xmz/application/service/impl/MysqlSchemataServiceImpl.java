package com.xmz.application.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.application.mapper.metadata.MysqlSchemaMapper;
import com.xmz.application.model.metadata.entity.MysqlSchema;
import com.xmz.application.model.common.param.SchemataListParam;
import com.xmz.application.service.MysqlSchemaService;
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
