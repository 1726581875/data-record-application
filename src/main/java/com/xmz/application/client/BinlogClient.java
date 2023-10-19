package com.xmz.application.client;

import com.xmz.application.model.common.RespResult;
import com.xmz.application.model.common.dto.TenantSourceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author xiaomingzhang
 * @date 2023/9/7
 */
@Slf4j
@Component
public class BinlogClient extends CommonBaseClient {

    public RespResult listenBinlog(TenantSourceDTO dto) {
        return super.doPost("/binlog/listenBinlog", dto);
    }

    public RespResult cancelListen(TenantSourceDTO dto) {
        return super.doPost("/binlog/cancelListen", dto);
    }
}
