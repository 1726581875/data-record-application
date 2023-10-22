package com.xmz.application.common.exception;

/**
 * @author xiaomingzhang
 * @date 2023/10/22
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }
}
