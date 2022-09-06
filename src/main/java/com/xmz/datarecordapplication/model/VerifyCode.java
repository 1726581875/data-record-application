package com.xmz.datarecordapplication.model;

import lombok.Data;

/**
 * @author xiaomingzhang
 * @date 2022/9/5
 */
@Data
public class VerifyCode {

    private String code;

    private Long expirationTime;

    public VerifyCode(String code, Long expirationTime) {
        this.code = code;
        this.expirationTime = expirationTime;
    }

    public boolean isExpired() {
        return expirationTime < System.currentTimeMillis();
    }

}
