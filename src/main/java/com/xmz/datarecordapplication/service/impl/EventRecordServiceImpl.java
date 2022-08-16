package com.xmz.datarecordapplication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.datarecordapplication.mapper.*;
import com.xmz.datarecordapplication.model.entity.*;
import com.xmz.datarecordapplication.model.param.DataRecordParam;
import com.xmz.datarecordapplication.model.param.EventRecordListParam;
import com.xmz.datarecordapplication.model.vo.EventDetailVO;
import com.xmz.datarecordapplication.service.EventRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author xiaomingzhang
 * @date 2022/8/4
 */
@Slf4j
@Service
public class EventRecordServiceImpl implements EventRecordService {

    @Resource
    private EventRecordMapper eventRecordMapper;
    @Resource
    private DeleteRowRecordMapper deleteRowRecordMapper;
    @Resource
    private UpdateRowRecordMapper updateRowRecordMapper;
    @Resource
    private QueryEventRecordMapper queryEventRecordMapper;
    @Resource
    private InsertRowRecordMapper insertRowRecordMapper;

    @Override
    public Page<EventRecord> getList(EventRecordListParam param) {
        return eventRecordMapper.selectPage(param, getQueryWrapper(param));
    }

    private LambdaQueryWrapper<EventRecord> getQueryWrapper(EventRecordListParam param) {

        LambdaQueryWrapper<EventRecord> queryWrapper = new LambdaQueryWrapper<>();
        // 时间范围条件
        if (Objects.nonNull(param.getTimeRange())) {
            if (param.getTimeRange().getStartTime() != null) {
                queryWrapper.ge(EventRecord::getEventTimestamp, param.getTimeRange().getStartTime());
            }
            if (param.getTimeRange().getEndTime() != null) {
                queryWrapper.le(EventRecord::getEventTimestamp, param.getTimeRange().getEndTime());
            }
        }
        // 事件类型
        if(StringUtils.hasLength(param.getEventType())){
            queryWrapper.eq(EventRecord::getEventType, param.getEventType());
        }
        // 排序方式
        if(Objects.nonNull(param.getAsc()) && param.getAsc().equals(true)){
            queryWrapper.orderByAsc(EventRecord::getEventTimestamp);
        } else {
            queryWrapper.orderByDesc(EventRecord::getEventTimestamp);
        }
        return queryWrapper;
    }



    @Override
    public EventDetailVO getById(Long id) {

        EventDetailVO eventDetailVO = new EventDetailVO();
        EventRecord eventRecord = eventRecordMapper.selectById(id);
        if(eventRecord == null) {
            throw new IllegalStateException("数据不存在，id=" + id);
        }
        eventDetailVO.setEventRecord(eventRecord);
        String eventType = eventDetailVO.getEventRecord().getEventType();
        switch (eventType) {
            case "EXT_WRITE_ROWS":

                break;
            case "EXT_UPDATE_ROWS":

                break;
            case "EXT_DELETE_ROWS":

                break;
            case "QUERY":
                eventDetailVO.setExtraInfo(queryEventRecordMapper.selectOne(new LambdaQueryWrapper<QueryEventRecord>()
                        .eq(QueryEventRecord::getRecordId, id)));
                break;
            case "TABLE_MAP":
                eventDetailVO.setExtraInfo(null);
                break;
            case "XID":
                eventDetailVO.setExtraInfo(null);
                break;
            default:
                break;
        }


        return eventDetailVO;
    }

    @Override
    public Page<InsertRowRecord> getInsertRowList(DataRecordParam param) {
        return insertRowRecordMapper.selectPage(param, new LambdaQueryWrapper<InsertRowRecord>()
                .eq(InsertRowRecord::getRecordId, param.getRecordId()));
    }

    @Override
    public Page<DeleteRowRecord> getDeleteRowList(DataRecordParam param) {
        return deleteRowRecordMapper.selectPage(param, new LambdaQueryWrapper<DeleteRowRecord>()
                .eq(DeleteRowRecord::getRecordId, param.getRecordId()));
    }

    @Override
    public Page<UpdateRowRecord> getUpdateRowList(DataRecordParam param) {
        return updateRowRecordMapper.selectPage(param, new LambdaQueryWrapper<UpdateRowRecord>()
                .eq(UpdateRowRecord::getRecordId, param.getRecordId()));
    }
}
