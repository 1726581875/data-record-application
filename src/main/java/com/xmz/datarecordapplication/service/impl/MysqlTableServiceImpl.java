package com.xmz.datarecordapplication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.datarecordapplication.mapper.metadata.MysqlTableMapper;
import com.xmz.datarecordapplication.model.entity.metadata.MysqlTable;
import com.xmz.datarecordapplication.model.param.TableListParam;
import com.xmz.datarecordapplication.service.MysqlTableService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author xiaomingzhang
 * @date 2022/8/19
 */
@Service
public class MysqlTableServiceImpl implements MysqlTableService {

    @Resource
    private MysqlTableMapper tableMapper;

    @Override
    public Page<MysqlTable> getList(TableListParam param) {
        LambdaQueryWrapper queryWrapper = new LambdaQueryWrapper<MysqlTable>()
                .like(StringUtils.hasLength(param.getDbName()),MysqlTable::getTableSchema, param.getDbName())
                .like(StringUtils.hasLength(param.getTableName()), MysqlTable::getTableName, param.getTableName());
        return tableMapper.selectPage(param, queryWrapper);
    }
}
