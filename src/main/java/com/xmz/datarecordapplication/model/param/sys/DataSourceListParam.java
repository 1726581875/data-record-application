package com.xmz.datarecordapplication.model.param.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @author xiaomingzhang
 * @date 2022/9/13
 */
@Data
public class DataSourceListParam extends Page {

    private String name;

    private String tenantId;

}
