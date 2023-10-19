package com.xmz.application.model.common.vo;

import lombok.Data;

import java.util.List;

/**
 * @author xiaomingzhang
 * @date 2022/9/17
 */
@Data
public class DataListVO {

    private List<String> columnNameList;

    private List<List<Object>> valueList;

    private Long total = 0L;


}
