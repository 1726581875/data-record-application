package com.xmz.application.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.application.model.metadata.entity.MysqlTable;
import com.xmz.application.model.common.param.TableListParam;

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
