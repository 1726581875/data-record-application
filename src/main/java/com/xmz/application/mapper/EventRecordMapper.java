package com.xmz.application.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.application.model.common.vo.CrudStat;
import com.xmz.application.model.event.entity.EventRecord;
import com.xmz.application.model.common.param.EventRecordListParam;
import com.xmz.application.model.common.vo.EventRecordListVo;
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
    Page<EventRecordListVo> getEventList(@Param("param") EventRecordListParam param, @Param("tenantSuffix") String tenantSuffix);

    EventRecord getEventRecordById(@Param("id") Long id, @Param("tenantSuffix") String tenantSuffix);


    CrudStat getCRUDStat(@Param("tenantSuffix") String tenantSuffix);

}
