package com.xmz.datarecordapplication.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.datarecordapplication.model.entity.sys.SysDataSource;
import com.xmz.datarecordapplication.model.param.sys.DataSourceListParam;
import com.xmz.datarecordapplication.model.vo.SysDataSourceListVO;
import org.apache.ibatis.annotations.Param;

/**
 * @author xiaomingzhang
 * @date 2022/9/13
 */
public interface SysDataSourceMapper extends BaseMapper<SysDataSource> {

    Page<SysDataSourceListVO> getList(@Param("param") DataSourceListParam param);

}
