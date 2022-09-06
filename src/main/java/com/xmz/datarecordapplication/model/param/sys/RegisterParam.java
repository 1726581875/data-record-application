package com.xmz.datarecordapplication.model.param.sys;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author xiaomingzhang
 * @date 2022/9/6
 */
@Data
public class RegisterParam extends LoginParam {

    @NotBlank
    private String confirmPassword;
}
