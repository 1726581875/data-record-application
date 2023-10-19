package com.xmz.application.controller.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.application.model.sys.entity.SysDataSyncRecord;
import com.xmz.application.model.common.param.TableListParam;
import com.xmz.application.service.sys.SysDataSyncRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaomingzhang
 * @date 2022/9/19
 */
@RestController
@RequestMapping("/syncRecord")
public class SysDataSyncRecordController {

    @Autowired
    private SysDataSyncRecordService syncRecordService;

    @PostMapping("/getList")
    public Page<SysDataSyncRecord> getList(@RequestBody TableListParam param) {
        return syncRecordService.getList(param);
    }


}
