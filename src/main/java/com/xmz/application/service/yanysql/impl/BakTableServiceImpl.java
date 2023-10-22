package com.xmz.application.service.yanysql.impl;

import com.xmz.application.client.BakTableClient;
import com.xmz.application.common.UserContext;
import com.xmz.application.common.exception.ServiceException;
import com.xmz.application.model.common.RespResult;
import com.xmz.application.model.common.vo.DataListVO;
import com.xmz.application.model.yanysql.BakTable;
import com.xmz.application.service.yanysql.BakTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaomingzhang
 * @date 2023/10/22
 */
@Slf4j
@Service
public class BakTableServiceImpl implements BakTableService {


    @Autowired
    private BakTableClient bakTableClient;


    @Override
    public List<BakTable> getAllBakTables(String tableName) {
        String tenantId = UserContext.getAuthorizeUser().getTenantId();
        RespResult<List<BakTable>> respResult = bakTableClient.getBakTables(tenantId, tableName);
        if (respResult.isSuccess()) {
            return respResult.getData();
        }
        log.error("获取备份表失败，tenantId={}, tableName={}", tenantId, tableName, respResult);
        throw new ServiceException("获取数据失败,调用引擎层失败");
    }

    @Override
    public DataListVO getDataList(String tableName, Integer current, Integer size) {

        DataListVO dataListVO = new DataListVO();
        RespResult<Integer> countResult = bakTableClient.getCount(tableName);
        if (countResult == null || !countResult.isSuccess()) {
            log.error("获取数据失败，RespResult={}", countResult);
            throw new RuntimeException("获取数据失败");
        }

        if (Long.valueOf(countResult.getData().intValue()) == 0L) {
            return dataListVO;
        }

        dataListVO.setTotal(Long.valueOf(countResult.getData().intValue()));


        RespResult<List<List>> dataListResult = bakTableClient.getDataList(tableName, (current - 1) * size, size);
        if (dataListResult == null || !dataListResult.isSuccess()) {
            log.error("获取数据失败，RespResult={}", dataListResult);
            throw new RuntimeException("获取数据失败");
        }
        List<List> listResultData = dataListResult.getData();
        if (CollectionUtils.isEmpty(listResultData)) {
            log.warn("获取数据列表为空");
            return dataListVO;
        }


        List<String> columnNameList = new ArrayList<>();
        List<List<Object>> valueList = new ArrayList<>();

        for (int i = 0; i < listResultData.size(); i++) {
            if (i == 0) {
                columnNameList = listResultData.get(i);
                continue;
            }
            valueList.add(listResultData.get(i));
        }

        dataListVO.setColumnNameList(columnNameList);
        dataListVO.setValueList(valueList);

        return dataListVO;
    }
}
