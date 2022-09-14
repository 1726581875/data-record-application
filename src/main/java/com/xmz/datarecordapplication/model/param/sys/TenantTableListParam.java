package com.xmz.datarecordapplication.model.param.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author xiaomingzhang
 * @date 2022/9/13
 */
@Data
public class TenantTableListParam extends Page {
    @NotBlank
    private String dataSourceId;

}
