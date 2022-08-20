package com.xmz.datarecordapplication.model.param;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @author xiaomingzhang
 * @date 2022/8/20
 */
@Data
public class TableListParam extends Page {

    private String dbName;

    private String tableName;

}
