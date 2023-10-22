package com.xmz.application.service.yanysql;

import com.xmz.application.model.common.vo.DataListVO;
import com.xmz.application.model.yanysql.BakTable;

import java.util.List;

/**
 * @author xiaomingzhang
 * @date 2022/9/18
 */
public interface BakTableService {

    List<BakTable> getAllBakTables(String tableName);

    DataListVO getDataList(String tableName, Integer current, Integer size);

}
