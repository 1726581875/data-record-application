package com.xmz.application.service.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.application.model.sys.entity.SysDataSyncRecord;
import com.xmz.application.model.common.param.TableListParam;

/**
 * @author xiaomingzhang
 * @date 2022/9/19
 */
public interface SysDataSyncRecordService {


    Page<SysDataSyncRecord> getList(TableListParam param);



}
