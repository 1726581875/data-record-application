package com.xmz.application.service.sys.impl;

import com.xmz.application.client.CommandClient;
import com.xmz.application.common.UserContext;
import com.xmz.application.model.common.RespResult;
import com.xmz.application.model.common.dto.TenantSourceDTO;
import com.xmz.application.service.sys.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaomingzhang
 * @date 2022/10/9
 */
@Service
public class CommandServiceImpl implements CommandService {

    @Autowired
    private CommandClient commandClient;


    @Override
    public RespResult<Boolean> ping(TenantSourceDTO dto) {
        dto.setTenantId(UserContext.getAuthorizeUser().getTenantId());
        return commandClient.ping(dto);
    }
}
