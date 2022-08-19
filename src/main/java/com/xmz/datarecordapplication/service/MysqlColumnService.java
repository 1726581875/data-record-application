package com.xmz.datarecordapplication.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.datarecordapplication.model.entity.metadata.MysqlColumn;
import com.xmz.datarecordapplication.model.param.ColumnListParam;

/**
 * @author xiaomingzhang
 * @date 2022/8/14
 */
public interface MysqlColumnService {

    /**
     * 获取mysql列字段列表
     * @param param
     * @return
     */
    Page<MysqlColumn> getList(ColumnListParam param);


}
