package com.xmz.datarecordapplication.service.sys;

import com.xmz.datarecordapplication.model.param.sys.LoginParam;
import com.xmz.datarecordapplication.model.param.sys.RegisterParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xiaomingzhang
 * @date 2022/9/5
 */
public interface SysUserService {

    void createVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void login(LoginParam param);

    void loginOut();

    void register(RegisterParam param);


}
