package com.xmz.datarecordapplication.model.param;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.datarecordapplication.model.common.TimeRange;
import lombok.Data;

/**
 * @author xiaomingzhang
 * @date 2022/8/4
 */
@Data
public class EventRecordListParam extends Page {

    private String dataSourceId;

    private TimeRange timeRange;

    private String eventType;

    private Boolean asc;
}
