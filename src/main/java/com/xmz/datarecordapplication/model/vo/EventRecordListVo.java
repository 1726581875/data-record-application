package com.xmz.datarecordapplication.model.vo;

import com.xmz.datarecordapplication.model.entity.event.EventRecord;
import lombok.Data;

/**
 * @author xiaomingzhang
 * @date 2023/9/7
 */
@Data
public class EventRecordListVo extends EventRecord {

    private String sql;



}
