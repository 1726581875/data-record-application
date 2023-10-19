package com.xmz.application.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.application.model.metadata.entity.MysqlColumn;
import com.xmz.application.model.common.param.ColumnListParam;

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
