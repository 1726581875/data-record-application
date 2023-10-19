package com.xmz.application.model.sys.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author xiaomingzhang
 * @date 2022/9/15
 */
@Data
public class DataMigrationParam {

    @NotBlank
    private Long dataSourceId;

    private String tableName;

}
