package com.xmz.application.model.common;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author xmz
 * @Date: 2020/10/06
 * 封装统一返回结果给前端
 */
@Getter
@ToString
public class RespResult<T> implements Serializable {

    private static final long serialVersionUID = 3069934188369972954L;
    /**
     * 状态码 0表示成功
     */
    private Integer status;
    /**
     * 状态信息
     */
    private String msg;
    /**
     * 数据内容
     */
    private T data;

    /**
     * 链路追踪id,方便日志排查
     * todo 目前暂时不使用
     */
    private String traceId;

    public RespResult() {
    }

    public RespResult(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static RespResult build(){
        return new RespResult();
    }

    public static RespResult success(){
        return new RespResult(0, "success" ,null);
    }
    public static RespResult success(String msg){
        return new RespResult(0, msg ,null);
    }
    public static RespResult success(Object date){
        return new RespResult(0, "success" ,date);
    }
    public static RespResult success(Object date, String msg){
        return new RespResult(0, msg ,date);
    }

    public static RespResult fail(){
        return new RespResult(-1, "fail" ,null);
    }
    public static RespResult fail(String msg){
        return new RespResult(-1, msg ,null);
    }
    public static RespResult fail(int status, String msg){
        return new RespResult(status, msg ,null);
    }

    public static RespResult parameterError(){
        return new RespResult(-2,"参数异常",null);
    }
    public static RespResult parameterError(String msg){
        return new RespResult(-2,msg,null);
    }

    public static RespResult failUnKnownError(){
        return new RespResult(-1, "未知异常" ,null);
    }

    /**
     * 判断是否成功
     * @return
     */
    public  boolean isSuccess(){
        return (status!=null && status.equals(0));
    }

    public RespResult setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public RespResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public RespResult setData(T data) {
        this.data = data;
        return this;
    }


}
