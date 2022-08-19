package com.xmz.datarecordapplication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.datarecordapplication.mapper.metadata.MysqlColumnMapper;
import com.xmz.datarecordapplication.model.entity.metadata.MysqlColumn;
import com.xmz.datarecordapplication.model.param.ColumnListParam;
import com.xmz.datarecordapplication.service.MysqlColumnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author xiaomingzhang
 * @date 2022/8/14
 */
@Slf4j
@Service
public class MysqlColumnServiceImpl implements MysqlColumnService {

    @Resource
    private MysqlColumnMapper columnMapper;

    @Override
    public Page<MysqlColumn> getList(ColumnListParam param) {
        LambdaQueryWrapper<MysqlColumn> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.hasLength(param.getDbName())){
            queryWrapper.like(MysqlColumn::getTableSchema, param.getDbName());
        }
        if(StringUtils.hasLength(param.getTableName())){
            queryWrapper.like(MysqlColumn::getTableName, param.getTableName());
        }
        if(StringUtils.hasLength(param.getColumnName())) {
            queryWrapper.like(MysqlColumn::getColumnName, param.getColumnName());
        }

        queryWrapper.orderByAsc(MysqlColumn::getTableSchema)
                .orderByAsc(MysqlColumn::getTableName)
                .orderByAsc(MysqlColumn::getOrdinalPosition);

        return columnMapper.selectPage(param, queryWrapper);
    }
}
