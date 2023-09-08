package com.xmz.datarecordapplication.controller;

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
import com.xmz.datarecordapplication.model.vo.EventRecordListVo;
import com.xmz.datarecordapplication.service.EventRecordService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiaomingzhang
 * @date 2022/8/4
 * binlog事件controller
 */
@RestController
@RequestMapping("/eventRecord")
public class EventRecordController {

    @Autowired
    private EventRecordService eventRecordService;

    @PostMapping("/getList")
    public Page<EventRecordListVo> getList(@RequestBody EventRecordListParam param) {
        return eventRecordService.getList(param);
    }

    @GetMapping("/getById")
    public EventDetailVO getList(@RequestParam Long id, @Param("dataSourceId") String dataSourceId) {
        return eventRecordService.getById(id, dataSourceId);
    }

    @PostMapping("/getInsertRows")
    public Page<InsertRowRecord> getInsertRows(@RequestBody DataRecordParam param) {
        return eventRecordService.getInsertRowList(param);
    }

    @PostMapping("/getDeleteRows")
    public Page<DeleteRowRecord> getDeleteRows(@RequestBody DataRecordParam param) {
        return eventRecordService.getDeleteRowList(param);
    }

    @PostMapping("/getUpdateRows")
    public Page<UpdateRowRecord> getUpdateRows(@RequestBody DataRecordParam param) {
        return eventRecordService.getUpdateRowList(param);
    }

    @PostMapping("/getCRUDStat")
    public CrudStat getCRUDStat(@RequestBody CRUDStatParam param) {
        return eventRecordService.getCRUDStat(param);
    }

}
