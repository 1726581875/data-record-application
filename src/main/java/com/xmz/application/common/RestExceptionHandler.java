package com.xmz.application.common;

import com.xmz.application.common.exception.ServiceException;
import com.xmz.application.common.exception.ValidationException;
import com.xmz.application.model.common.RespResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author xiaomingzhang
 * @date 2022/4/8
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler(ValidationException.class)
    public RespResult ValidationException(ValidationException e) {
        log.error("全局异常信息 ValidationException={}", e.getMessage(), e);
        return RespResult.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RespResult exception(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return RespResult.fail("未知异常");
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RespResult handleServiceException(ServiceException e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return RespResult.fail(e.getMessage());
    }


}
