package com.xmz.datarecordapplication.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.datarecordapplication.model.entity.event.EventRecord;
import com.xmz.datarecordapplication.model.param.EventRecordListParam;
import com.xmz.datarecordapplication.model.vo.CrudStat;
import org.apache.ibatis.annotations.Param;

/**
 * @author xiaomingzhang
 * @date 2022/8/3
 */
public interface EventRecordMapper extends BaseMapper<EventRecord> {

    /**
     * 获取事件列表
     * @param param
     * @return
     */
    Page<EventRecord> getEventList(@Param("param") EventRecordListParam param, @Param("tenantSuffix") String tenantSuffix);

    EventRecord getEventRecordById(@Param("id") Long id, @Param("tenantSuffix") String tenantSuffix);


    CrudStat getCRUDStat(@Param("tenantSuffix") String tenantSuffix);

}
