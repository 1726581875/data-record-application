package com.xmz.datarecordapplication.common.exception;

/**
 * @author xiaomingzhang
 * @date 2023/3/17
 */
public class SysUnauthorizedException extends RuntimeException {
    public SysUnauthorizedException(String msg){
        super(msg);
    }
}
