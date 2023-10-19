package com.xmz.application.model.sys.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @author xiaomingzhang
 * @date 2022/9/19
 */
@Data
public class SysDataSyncRecord {

    @TableId(type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String tenantId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long dataSourceId;

    private String dbName;

    private String sourceTableName;

    private String syncStatus;

    private Date createTime;

    private Date updateTime;

}
