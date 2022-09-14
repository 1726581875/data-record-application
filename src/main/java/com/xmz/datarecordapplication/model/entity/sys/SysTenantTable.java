package com.xmz.datarecordapplication.model.entity.sys;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @author xiaomingzhang
 * @date 2022/9/13
 */
@Data
public class SysTenantTable {
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String tenantId;

    private Long dataSourceId;

    private String tableName;

    private Date createTime;

    private Date updateTime;
}
