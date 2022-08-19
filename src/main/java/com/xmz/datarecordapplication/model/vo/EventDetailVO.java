package com.xmz.datarecordapplication.model.vo;

import com.xmz.datarecordapplication.model.entity.event.EventRecord;
import lombok.Data;

/**
 * @author xiaomingzhang
 * @date 2022/8/9
 */
@Data
public class EventDetailVO {

    private EventRecord eventRecord;

    private Object extraInfo;
}
