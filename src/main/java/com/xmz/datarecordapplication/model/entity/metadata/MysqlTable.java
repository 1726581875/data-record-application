package com.xmz.datarecordapplication.model.entity.metadata;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author xiaomingzhang
 * @date 2022/8/19
 * 表字段
 */
@Data
public class MysqlTable {


    @TableId
    private Long id;

    private String tableCatalog;
    private String tableSchema;
    private String tableName;
    private String tableType;
    private String engine;
    private Long version;
    private String rowFormat;
    private Long tableRows;
    private Long avgRowLength;
    private Long dataLength;
    private Long maxDataLength;
    private Long indexLength;
    private Long dataFree;
    private Long autoIncrement;
    private Date createTime;
    private Date updateTime;
    private Date checkTime;
    private String tableCollation;
    private Long checksum;
    private String createOptions;
    private String tableComment;


    private Date dataCreateTime;
    private Date dataUpdateTime;

}
