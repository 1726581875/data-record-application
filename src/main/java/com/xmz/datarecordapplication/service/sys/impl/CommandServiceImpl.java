package com.xmz.datarecordapplication.service.sys.impl;

import com.xmz.datarecordapplication.client.CommandClient;
import com.xmz.datarecordapplication.common.UserContext;
import com.xmz.datarecordapplication.model.common.RespResult;
import com.xmz.datarecordapplication.model.dto.TenantSourceDTO;
import com.xmz.datarecordapplication.service.sys.CommandService;
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
