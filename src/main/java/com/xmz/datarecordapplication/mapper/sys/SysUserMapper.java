package com.xmz.datarecordapplication.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.datarecordapplication.model.entity.sys.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author xiaomingzhang
 * @date 2022/9/5
 */
public interface SysUserMapper extends BaseMapper<SysUser> {


    SysUser getUserByAccount(@Param("account") String account);


}
