package com.xmz.application.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.application.model.metadata.entity.MysqlSchema;
import com.xmz.application.model.common.param.SchemataListParam;

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
