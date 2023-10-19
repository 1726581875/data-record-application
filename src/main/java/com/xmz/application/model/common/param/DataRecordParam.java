package com.xmz.application.model.common.param;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @author xiaomingzhang
 * @date 2022/8/9
 */
@Data
public class DataRecordParam extends Page {

    private String dataSourceId;

    private Long recordId;

}
