package com.xmz.application.model.common.vo;

import com.xmz.application.model.event.entity.EventRecord;
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
