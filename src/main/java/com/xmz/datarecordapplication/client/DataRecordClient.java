package com.xmz.datarecordapplication.client;

import com.xmz.datarecordapplication.model.common.RespResult;
import com.xmz.datarecordapplication.model.dto.DataMigrationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xiaomingzhang
 * @date 2022/9/15
 */
@Slf4j
@Component
public class DataRecordClient extends CommonBaseClient {


    @Value("${client.url.dataRecord:http://127.0.0.1:8989}")
    private String dataRecordBaseUrl;


    public RespResult doDataMigration(DataMigrationDTO dto) {
        return super.doPost(dataRecordBaseUrl + "/dm/do",dto);
    }

}
