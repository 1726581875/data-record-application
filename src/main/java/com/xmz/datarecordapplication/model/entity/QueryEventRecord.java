package com.xmz.datarecordapplication.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @author xiaomingzhang
 * @date 2022/7/6
 */
@Data
public class QueryEventRecord {

    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private Long recordId;
    private Long threadId;
    private String databaseName;
    private String execSql;
    private Integer errorCode;
    private Long executionTime;
    private Date createTime;
    private Date updateTime;
}
