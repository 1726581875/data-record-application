package com.xmz.datarecordapplication.model.entity.metadata;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author xiaomingzhang
 * @date 2022/8/10
 *
 * 字段对象
 */
@Data
@TableName("mysql_column")
public class MysqlColumn {

    @TableId
    private Long id;

    private String tableSchema;
    private String tableName;
    private String columnName;
    private Long ordinalPosition;
    private String columnDefault;
    private String isNullable;
    private String dataType;
    private Long characterMaximumLength;
    private Long characterOctetLength;
    private Long numericPrecision;
    private Long numericScale;
    private String characterSetName;
    private String collationName;

    private Date createTime;
    private Date updateTime;
}
