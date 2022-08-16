package com.xmz.datarecordapplication.model.param;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @author xiaomingzhang
 * @date 2022/8/14
 */
@Data
public class ColumnListParam extends Page {

    private String dbName;

    private String tableName;

    private String columnName;
}
