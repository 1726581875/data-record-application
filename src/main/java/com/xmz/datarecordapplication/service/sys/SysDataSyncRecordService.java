package com.xmz.datarecordapplication.service.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.datarecordapplication.model.entity.sys.SysDataSyncRecord;
import com.xmz.datarecordapplication.model.param.TableListParam;

/**
 * @author xiaomingzhang
 * @date 2022/9/19
 */
public interface SysDataSyncRecordService {


    Page<SysDataSyncRecord> getList(TableListParam param);



}
