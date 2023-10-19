package com.xmz.application.common.exception;

/**
 * @author xiaomingzhang
 * @date 2022/9/5
 */
public class ValidationException extends RuntimeException {

    public ValidationException(String msg){
        super(msg);
    }

}
