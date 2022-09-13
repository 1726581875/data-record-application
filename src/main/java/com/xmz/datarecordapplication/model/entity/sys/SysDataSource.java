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
public class SysDataSource {

    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String name;

    private String tenantId;

    private String hostname;

    private String serverPort;

    private String schemaName;

    private String username;

    private String password;

    private Integer status;

    private Date createTime;

    private Date updateTime;

}
