package com.xmz.application.controller;

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
import com.xmz.application.service.EventRecordService;
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
