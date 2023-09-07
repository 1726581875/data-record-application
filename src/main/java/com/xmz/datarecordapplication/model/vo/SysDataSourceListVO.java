package com.xmz.datarecordapplication.model.vo;

import com.xmz.datarecordapplication.model.entity.sys.SysDataSource;
import lombok.Data;


/**
 * @author xiaomingzhang
 * @date 2022/9/13
 */
@Data
public class SysDataSourceListVO extends SysDataSource {
    /**
     * 0 binlog没在监听
     * 1 binlog监听中
     */
    private String isListen;

}
