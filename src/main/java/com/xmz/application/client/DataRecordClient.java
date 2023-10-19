package com.xmz.application.client;

import com.xmz.application.model.common.RespResult;
import com.xmz.application.model.common.dto.DataMigrationDTO;
import lombok.extern.slf4j.Slf4j;
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


    /**
     * 数据同步接口
     * @param dto
     * @return
     */
    public RespResult doDataMigration(DataMigrationDTO dto) {
        return super.doPost("/dm/do",dto);
    }

    /**
     * 获取数据列表接口
     * @param tableName
     * @param offset
     * @param limit
     * @return
     */
    public RespResult<List<List>> getDataList(String tableName, Integer offset, Integer limit) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("tableName", tableName);
        paramMap.put("offset", offset);
        paramMap.put("limit", limit);
        return super.doGet( "/data/getList?tableName={tableName}&offset={offset}&limit={limit}", paramMap);
    }

    /**
     * 获取数据总数接口
     * @param tableName
     * @return
     */
    public RespResult<Integer> getCount(String tableName) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("tableName", tableName);
        return super.doGet( "/data/count?tableName={tableName}", paramMap);
    }

}
