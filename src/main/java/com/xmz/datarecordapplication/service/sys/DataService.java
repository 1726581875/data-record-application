package com.xmz.datarecordapplication.service.sys;

import com.xmz.datarecordapplication.model.vo.DataListVO;

/**
 * @author xiaomingzhang
 * @date 2022/9/18
 */
public interface DataService {

    DataListVO getDataList(String tableName, Integer current, Integer size);

}
