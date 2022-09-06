package com.xmz.datarecordapplication.controller.sys;

import com.xmz.datarecordapplication.model.param.sys.LoginParam;
import com.xmz.datarecordapplication.model.param.sys.RegisterParam;
import com.xmz.datarecordapplication.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * @author xiaomingzhang
 * @date 2022/9/5
 */
@RestController
@RequestMapping("sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("login")
    private void login(@RequestBody @Valid LoginParam param){
        sysUserService.login(param);
    }

    @GetMapping("getVerifyCode")
    private void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        sysUserService.createVerifyCode(request,response);
    }

    @PostMapping("loginOut")
    private void loginOut(){
        sysUserService.loginOut();
    }

    @PostMapping("register")
    private void register(@RequestBody @Valid RegisterParam param){
        sysUserService.register(param);
    }

}
