package com.xmz.datarecordapplication.common;

import com.xmz.datarecordapplication.model.common.RespResult;
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

    @ExceptionHandler(Exception.class)
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RespResult exception(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return RespResult.fail("发生服务内部错误");
    }


}
