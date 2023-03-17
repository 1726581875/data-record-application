package com.xmz.datarecordapplication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.datarecordapplication.common.UserContext;
import com.xmz.datarecordapplication.common.exception.SysUnauthorizedException;
import com.xmz.datarecordapplication.mapper.*;
import com.xmz.datarecordapplication.model.entity.event.*;
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
        //return eventRecordMapper.selectPage(param, getQueryWrapper(param));

        return eventRecordMapper.getEventList(param, getTenantSuffix());
    }

    private String getTenantSuffix() {
        String tenantId = null;
        // TODO catch异常，为了方便测试，无需登录直接查询
        try {
            tenantId = UserContext.getAuthorizeUser().getTenantId();
        } catch (SysUnauthorizedException e){
            log.info("获取租户id失败, 未授权异常", e);
        }

        if(tenantId == null){
            return "";
        }
        // TODO _tenantId_dataSourceId
        return "_" + tenantId + "_1";
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
        if (StringUtils.hasLength(param.getEventType())) {
            queryWrapper.eq(EventRecord::getEventType, param.getEventType());
        }
        // 排序方式
        if (Objects.nonNull(param.getAsc()) && param.getAsc().equals(true)) {
            queryWrapper.orderByAsc(EventRecord::getEventTimestamp);
        } else {
            queryWrapper.orderByDesc(EventRecord::getEventTimestamp);
        }
        return queryWrapper;
    }



    @Override
    public EventDetailVO getById(Long id) {

        EventDetailVO eventDetailVO = new EventDetailVO();
        EventRecord eventRecord = eventRecordMapper.getEventRecordById(id, getTenantSuffix());
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
                eventDetailVO.setExtraInfo(queryEventRecordMapper.getQueryEventByRecordId(id, getTenantSuffix()));
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
        return insertRowRecordMapper.getInsertRowList(param, getTenantSuffix());
    }

    @Override
    public Page<DeleteRowRecord> getDeleteRowList(DataRecordParam param) {
        return deleteRowRecordMapper.getDeleteRowList(param, getTenantSuffix());
    }

    @Override
    public Page<UpdateRowRecord> getUpdateRowList(DataRecordParam param) {
        return updateRowRecordMapper.getUpdateRowList(param, getTenantSuffix());
    }
}
