package com.xmz.datarecordapplication.model.entity.sys;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @author xiaomingzhang
 * @date 2022/9/5
 */
@Data
public class SysUser {

    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String name;

    private String account;

    private String password;

    private Integer status;

    private Date loginTime;

    private Date createTime;

    private Date updateTime;

}
