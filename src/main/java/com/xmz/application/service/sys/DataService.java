package com.xmz.application.service.sys;

import com.xmz.application.model.common.vo.DataListVO;

/**
 * @author xiaomingzhang
 * @date 2022/9/18
 */
public interface DataService {

    DataListVO getDataList(String tableName, Integer current, Integer size);

}
