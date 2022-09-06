package com.xmz.datarecordapplication.model;

import lombok.Data;

/**
 * @author xiaomingzhang
 * @date 2022/9/5
 * 已授权用户信息
 */
@Data
public class AuthorizeUser {

    public static String USER_KEY = "SYS_USER_KEY";

    private Long userId;

    private String name;

    private String account;

    public AuthorizeUser() {
    }

    public AuthorizeUser(Long userId, String name, String account) {
        this.userId = userId;
        this.name = name;
        this.account = account;
    }
}
