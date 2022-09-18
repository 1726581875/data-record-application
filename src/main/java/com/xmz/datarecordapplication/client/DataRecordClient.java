package com.xmz.datarecordapplication.client;

import com.xmz.datarecordapplication.model.common.RespResult;
import com.xmz.datarecordapplication.model.dto.DataMigrationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public RespResult<List<List>> getDataList(String tableName, Integer offset, Integer limit) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("tableName", tableName);
        paramMap.put("offset", offset);
        paramMap.put("limit", limit);
        return super.doGet(dataRecordBaseUrl + "/data/getList?tableName={tableName}&offset={offset}&limit={limit}", paramMap);
    }

    public RespResult<Integer> getCount(String tableName) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("tableName", tableName);
        return super.doGet(dataRecordBaseUrl + "/data/count?tableName={tableName}", paramMap);
    }

}
