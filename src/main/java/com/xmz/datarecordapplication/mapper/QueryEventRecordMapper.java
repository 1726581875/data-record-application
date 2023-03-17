package com.xmz.datarecordapplication.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.datarecordapplication.model.entity.event.QueryEventRecord;
import org.apache.ibatis.annotations.Param;

/**
 * @author xiaomingzhang
 * @date 2022/8/9
 */
public interface QueryEventRecordMapper extends BaseMapper<QueryEventRecord> {

    QueryEventRecord getQueryEventByRecordId(@Param("recordId") Long recordId, @Param("tenantSuffix") String tenantSuffix);

}
