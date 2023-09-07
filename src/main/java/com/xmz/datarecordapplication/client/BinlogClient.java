package com.xmz.datarecordapplication.client;

import com.xmz.datarecordapplication.model.common.RespResult;
import com.xmz.datarecordapplication.model.dto.TenantSourceDTO;
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
