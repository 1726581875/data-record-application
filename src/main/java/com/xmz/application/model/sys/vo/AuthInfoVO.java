package com.xmz.application.model.sys.vo;

import lombok.Data;

/**
 * @author xiaomingzhang
 * @date 2023/10/19
 */
@Data
public class AuthInfoVO {

    private String account;

    private String tenantId;

    private String token;
}
