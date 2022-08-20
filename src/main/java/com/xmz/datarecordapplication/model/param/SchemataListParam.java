package com.xmz.datarecordapplication.model.param;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @author xiaomingzhang
 * @date 2022/8/20
 */
@Data
public class SchemataListParam extends Page {

    private String dbName;
}
