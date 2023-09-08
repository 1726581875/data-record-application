package com.xmz.datarecordapplication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.datarecordapplication.common.UserContext;
import com.xmz.datarecordapplication.common.exception.SysUnauthorizedException;
import com.xmz.datarecordapplication.mapper.*;
import com.xmz.datarecordapplication.model.entity.event.*;
import com.xmz.datarecordapplication.model.param.CRUDStatParam;
import com.xmz.datarecordapplication.model.param.DataRecordParam;
import com.xmz.datarecordapplication.model.param.EventRecordListParam;
import com.xmz.datarecordapplication.model.vo.CrudStat;
import com.xmz.datarecordapplication.model.vo.EventDetailVO;
import com.xmz.datarecordapplication.model.vo.EventRecordListVo;
import com.xmz.datarecordapplication.service.EventRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    public Page<EventRecordListVo> getList(EventRecordListParam param) {
        return eventRecordMapper.getEventList(param, getTenantSuffix(param.getDataSourceId()));
    }

    private String getTenantSuffix(String dataSourceId) {

        String tenantId = null;
        try {
            tenantId = UserContext.getAuthorizeUser().getTenantId();
        } catch (SysUnauthorizedException e) {
            log.info("获取租户id失败, 未授权异常", e);
        }

        if (tenantId == null) {
            return "";
        }

        return "_" + tenantId + "_" + dataSourceId;
    }



    @Override
    public EventDetailVO getById(Long id, String dataSourceId) {

        EventDetailVO eventDetailVO = new EventDetailVO();
        EventRecord eventRecord = eventRecordMapper.getEventRecordById(id, getTenantSuffix(dataSourceId));
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
                eventDetailVO.setExtraInfo(queryEventRecordMapper.getQueryEventByRecordId(id, getTenantSuffix(dataSourceId)));
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
        return insertRowRecordMapper.getInsertRowList(param, getTenantSuffix(param.getDataSourceId()));
    }

    @Override
    public Page<DeleteRowRecord> getDeleteRowList(DataRecordParam param) {
        return deleteRowRecordMapper.getDeleteRowList(param, getTenantSuffix(param.getDataSourceId()));
    }

    @Override
    public Page<UpdateRowRecord> getUpdateRowList(DataRecordParam param) {
        return updateRowRecordMapper.getUpdateRowList(param, getTenantSuffix(param.getDataSourceId()));
    }

    @Override
    public CrudStat getCRUDStat(CRUDStatParam param) {
        String tenantSuffix = getTenantSuffix(param.getDataSourceId());
        return eventRecordMapper.getCRUDStat(tenantSuffix);
    }


}
