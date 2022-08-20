package com.xmz.datarecordapplication.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.datarecordapplication.model.entity.metadata.MysqlSchemata;
import com.xmz.datarecordapplication.model.param.SchemataListParam;

/**
 * @author xiaomingzhang
 * @date 2022/8/19
 */
public interface MysqlSchemataService {

    /**
     * 获取数据库列表
     * @param param
     * @return
     */
    Page<MysqlSchemata> getList(SchemataListParam param);
}
