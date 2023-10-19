package com.xmz.application.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.application.model.event.entity.DeleteRowRecord;
import com.xmz.application.model.common.param.DataRecordParam;
import org.apache.ibatis.annotations.Param;

/**
 * @author xiaomingzhang
 * @date 2022/8/9
 */
public interface DeleteRowRecordMapper extends BaseMapper<DeleteRowRecord> {

    Page<DeleteRowRecord> getDeleteRowList(@Param("param") DataRecordParam param, @Param("tenantSuffix") String tenantSuffix);

}
