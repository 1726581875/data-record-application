package com.xmz.application.service.sys;

import com.xmz.application.model.common.RespResult;
import com.xmz.application.model.common.dto.TenantSourceDTO;

/**
 * @author xiaomingzhang
 * @date 2022/10/9
 */
public interface CommandService {


    RespResult<Boolean> ping(TenantSourceDTO dto);

}
