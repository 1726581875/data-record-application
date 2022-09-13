package com.xmz.datarecordapplication.common.exception;

/**
 * @author xiaomingzhang
 * @date 2022/9/13
 */
public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String msg) {
        super(msg);
    }
}
