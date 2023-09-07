package com.xmz.datarecordapplication.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.datarecordapplication.model.entity.event.DeleteRowRecord;
import com.xmz.datarecordapplication.model.entity.event.EventRecord;
import com.xmz.datarecordapplication.model.entity.event.InsertRowRecord;
import com.xmz.datarecordapplication.model.entity.event.UpdateRowRecord;
import com.xmz.datarecordapplication.model.param.CRUDStatParam;
import com.xmz.datarecordapplication.model.param.DataRecordParam;
import com.xmz.datarecordapplication.model.param.EventRecordListParam;
import com.xmz.datarecordapplication.model.vo.CrudStat;
import com.xmz.datarecordapplication.model.vo.EventDetailVO;

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
    Page<EventRecord> getList(EventRecordListParam param);

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
