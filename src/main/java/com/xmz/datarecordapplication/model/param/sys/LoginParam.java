package com.xmz.datarecordapplication.model.param.sys;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author xiaomingzhang
 * @date 2022/9/5
 */
@Data
public class LoginParam {

    @NotBlank
    private String account;
    @NotBlank
    private String password;
    @NotBlank
    private String verifyCode;

}
