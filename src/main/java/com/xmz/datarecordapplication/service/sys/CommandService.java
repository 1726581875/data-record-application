package com.xmz.datarecordapplication.service.sys;

import com.xmz.datarecordapplication.model.common.RespResult;
import com.xmz.datarecordapplication.model.dto.TenantSourceDTO;

/**
 * @author xiaomingzhang
 * @date 2022/10/9
 */
public interface CommandService {


    RespResult<Boolean> ping(TenantSourceDTO dto);

}
