package com.xmz.datarecordapplication.service.sys.impl;

import com.xmz.datarecordapplication.client.DataRecordClient;
import com.xmz.datarecordapplication.model.common.RespResult;
import com.xmz.datarecordapplication.model.vo.DataListVO;
import com.xmz.datarecordapplication.service.sys.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaomingzhang
 * @date 2022/9/18
 */
@Slf4j
@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataRecordClient dataRecordClient;

    @Override
    public DataListVO getDataList(String tableName, Integer current, Integer size) {

        // todo 直接接收前端传来的tableName拼接查询，存在sql注入风险
        DataListVO dataListVO = new DataListVO();
        RespResult<Integer> countResult = dataRecordClient.getCount(tableName);
        if (countResult == null || !countResult.isSuccess()) {
            log.error("获取数据失败，RespResult={}", countResult);
            throw new RuntimeException("获取数据失败");
        }

        if(Long.valueOf(countResult.getData().intValue()) == 0L){
            return dataListVO;
        }

        dataListVO.setTotal(Long.valueOf(countResult.getData().intValue()));


        RespResult<List<List>> dataListResult = dataRecordClient.getDataList(tableName, (current - 1) * size, size);
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
