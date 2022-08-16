package com.xmz.datarecordapplication.model.param;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @author xiaomingzhang
 * @date 2022/8/9
 */
@Data
public class DataRecordParam extends Page {

    private Long recordId;

}
