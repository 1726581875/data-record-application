package com.xmz.application.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.application.model.sys.entity.SysDataSource;
import com.xmz.application.model.sys.param.DataSourceListParam;
import com.xmz.application.model.common.vo.SysDataSourceListVO;
import org.apache.ibatis.annotations.Param;

/**
 * @author xiaomingzhang
 * @date 2022/9/13
 */
public interface SysDataSourceMapper extends BaseMapper<SysDataSource> {

    Page<SysDataSourceListVO> getList(@Param("param") DataSourceListParam param);

}
