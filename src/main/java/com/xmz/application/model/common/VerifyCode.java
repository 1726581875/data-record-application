package com.xmz.application.model.common;

import lombok.Data;

/**
 * @author xiaomingzhang
 * @date 2022/9/5
 */
@Data
public class VerifyCode {

    private String code;

    private Long expirationTime;

    public VerifyCode(){

    }

    public VerifyCode(String code, Long expirationTime) {
        this.code = code;
        this.expirationTime = expirationTime;
    }


    public static boolean isExpired(Long expirationTime) {
        return expirationTime < System.currentTimeMillis();
    }

}
