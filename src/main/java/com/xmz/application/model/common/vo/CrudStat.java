package com.xmz.application.model.common.vo;

import lombok.Data;

/**
 * @author xiaomingzhang
 * @date 2023/3/20
 */
@Data
public class CrudStat {

    private Integer insertNum;

    private Integer deleteNum;

    private Integer queryNum;

    private Integer updateNum;
}
