package com.xmz.application.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.application.model.event.entity.InsertRowRecord;
import com.xmz.application.model.common.param.CRUDStatParam;
import com.xmz.application.model.common.vo.CrudStat;
import com.xmz.application.model.event.entity.DeleteRowRecord;
import com.xmz.application.model.event.entity.UpdateRowRecord;
import com.xmz.application.model.common.param.DataRecordParam;
import com.xmz.application.model.common.param.EventRecordListParam;
import com.xmz.application.model.common.vo.EventDetailVO;
import com.xmz.application.model.common.vo.EventRecordListVo;

/**
 * @author xiaomingzhang
 * @date 2022/8/4
 */
public interface EventRecordService {


    /**
     * 分页获取事件记录列表
     * @param param
     * @return
     */
    Page<EventRecordListVo> getList(EventRecordListParam param);

    /**
     * 根据id获取详情
     * @param id
     * @return
     */
    EventDetailVO getById(Long id, String dataSourceId);


    /**
     * 新增事件关对应的新增数据
     * @param param
     * @return
     */
    Page<InsertRowRecord> getInsertRowList(DataRecordParam param);
    /**
     * 删除事件对应的数据删除数据
     * @param param
     * @return
     */
    Page<DeleteRowRecord> getDeleteRowList(DataRecordParam param);
    /**
     * 更新事件对应的数据变更记录
     * @param param
     * @return
     */
    Page<UpdateRowRecord> getUpdateRowList(DataRecordParam param);

    /**
     * 获取数据库增删查改记录数统计
     * @param param
     * @return
     */
    CrudStat getCRUDStat(CRUDStatParam param);


}
