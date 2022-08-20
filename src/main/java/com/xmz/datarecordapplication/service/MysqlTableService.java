package com.xmz.datarecordapplication.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.datarecordapplication.model.entity.metadata.MysqlTable;
import com.xmz.datarecordapplication.model.param.TableListParam;

/**
 * @author xiaomingzhang
 * @date 2022/8/19
 */
public interface MysqlTableService {

    /**
     * 获取mysql表元数据List
     * @param param
     * @return
     */
    Page<MysqlTable> getList(TableListParam param);
}
