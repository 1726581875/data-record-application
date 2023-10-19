package com.xmz.application.model.common.vo;

import com.xmz.application.model.sys.entity.SysDataSource;
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
