package com.xmz.datarecordapplication.client;

import com.xmz.datarecordapplication.model.common.RespResult;
import com.xmz.datarecordapplication.model.dto.TenantSourceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * @author xiaomingzhang
 * @date 2022/10/9
 */
@Slf4j
@Component
public class CommandClient extends CommonBaseClient {

    /**
     * 数据同步接口
     * @param dto
     * @return
     */
    public RespResult ping(TenantSourceDTO dto) {
        return super.doPost("/command/ping", dto);
    }

}
