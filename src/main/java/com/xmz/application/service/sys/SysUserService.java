package com.xmz.application.service.sys;

import com.xmz.application.model.sys.param.LoginParam;
import com.xmz.application.model.sys.param.RegisterParam;
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
