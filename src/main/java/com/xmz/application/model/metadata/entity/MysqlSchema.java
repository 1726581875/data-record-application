package com.xmz.application.model.metadata.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author xiaomingzhang
 * @date 2022/8/19
 * 数据库对象
 */
@Data
public class MysqlSchema {

    @TableId
    private Long id;

    private String catalogName;
    private String schemaName;
    private String defaultCharacterSetName;
    private String defaultCollationName;
    private String sqlPath;

    private Date createTime;
    private Date updateTime;


}
