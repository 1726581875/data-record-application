package com.xmz.application.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmz.application.model.common.RespResult;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;

/**
 * @author xiaomingzhang
 * @date 2022/4/7
 * 统一结果处理
 */
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    @Resource
    private ObjectMapper objectMapper;

    /**
     * 返回true表示启用该处理类
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        // 符串类型是会直接返回（框架默认不做json转换）, 这里要主动封装转换
        if (body instanceof String) {
            // 指定json格式
            response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            return objectMapper.writeValueAsString(RespResult.success(body));
        }

        // 如果是返回已经包装好的返回结果，则不需要做处理
        if(body instanceof RespResult){
            return body;
        }

        return RespResult.success(body);
    }
}
