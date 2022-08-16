package com.xmz.datarecordapplication.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @author xiaomingzhang
 * @date 2022/6/14
 * 删除行记录表
 */
@Data
public class DeleteRowRecord {

    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Long recordId;

    private String row;

    private Date createTime;

    private Date updateTime;
}
