package com.xmz.datarecordapplication.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.datarecordapplication.model.entity.event.InsertRowRecord;
import com.xmz.datarecordapplication.model.param.DataRecordParam;
import org.apache.ibatis.annotations.Param;

/**
 * @author xiaomingzhang
 * @date 2022/8/9
 */
public interface InsertRowRecordMapper extends BaseMapper<InsertRowRecord> {

    Page<InsertRowRecord> getInsertRowList(@Param("param") DataRecordParam param, @Param("tenantSuffix") String tenantSuffix);

}
