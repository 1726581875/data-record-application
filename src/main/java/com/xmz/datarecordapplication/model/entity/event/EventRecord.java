package com.xmz.datarecordapplication.model.entity.event;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @author xiaomingzhang
 * @date 2022/6/13
 * 事件记录表
 */
@Data
public class EventRecord {

    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * binlog文件名
     */
    private String binLogFileName;
    /**
     * binlog开始位置
     */
    private Long pos;
    /**
     * binlog结束
     */
    private Long endLogPos;
    /**
     * 语eI句开始执行的时间
     */
    private Date eventTimestamp;

    /**
     * 数据库名
     */
    private String databaseName;

    private String tableId;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 事件类型
     * @see
     */
    private String eventType;

    /**
     * 包含列
     */
    private String includedColumns;
    /**
     * 更新之后的列
     */
    private String includedColumnsBeforeUpdate;

    /**
     * 包含列名称
     */
    private String includedColumnNames;
    /**
     * 更新之后的列名称
     */
    private String columnNamesBeforeUpdate;

    /**
     * 业务步骤
     * @see StepEnum
     */
    private String step;

    /**
     * 业务状态
     * @see StateEnum
     */
    private String state;

    private Date createTime;

    private Date updateTime;

}
