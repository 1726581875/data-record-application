package com.xmz.datarecordapplication.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @author xiaomingzhang
 * @date 2022/6/14
 * 更新行记录表
 */
@Data
public class UpdateRowRecord {

    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Long recordId;

    private String beforeRow;

    private String afterRow;

    private Date createTime;

    private Date updateTime;
}
