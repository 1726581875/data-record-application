package com.xmz.datarecordapplication.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.datarecordapplication.model.entity.metadata.MysqlSchema;
import com.xmz.datarecordapplication.model.param.SchemataListParam;

/**
 * @author xiaomingzhang
 * @date 2022/8/19
 */
public interface MysqlSchemaService {

    /**
     * 获取数据库列表
     * @param param
     * @return
     */
    Page<MysqlSchema> getList(SchemataListParam param);
}
