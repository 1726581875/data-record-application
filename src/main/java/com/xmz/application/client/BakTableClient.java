package com.xmz.application.client;

import com.xmz.application.model.common.RespResult;
import com.xmz.application.model.yanysql.BakTable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaomingzhang
 * @date 2023/10/22
 */
@Component
public class BakTableClient extends CommonBaseClient {



    public RespResult<List<BakTable>> getBakTables(String tenantId, String tableName) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("tenantId", tenantId);
        paramMap.put("tableName", tableName);
        return super.doGet( "/bakTable/getBakTableList?tenantId={tenantId}&tableName={tableName}", paramMap);
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
        return super.doGet( "/bakTable/getDataList?tableName={tableName}&offset={offset}&limit={limit}", paramMap);
    }

    /**
     * 获取数据总数接口
     * @param tableName
     * @return
     */
    public RespResult<Integer> getCount(String tableName) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("tableName", tableName);
        return super.doGet( "/bakTable/getDataCount?tableName={tableName}", paramMap);
    }

}
