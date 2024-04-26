package com.tjj.zj.tjjwork.web;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * @author:
 * @date:
 */
@ApiModel("返回结果")
public class CommonResult<T> {

    @ApiModelProperty("状态 0 成功 1 失败")
    private Integer status;

    @ApiModelProperty("返回信息")
    private String msg;

    @ApiModelProperty("数据对象")
    private T obj;


    protected CommonResult() {

    }

    protected CommonResult(Integer status, String msg, T obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    /**
     * 成功返回结果
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), message, data);
    }
    public static <T> CommonResult<T> success(String message) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> failed() {
        return new CommonResult<T>(ResultCode.IErrorCode.getCode(), ResultCode.IErrorCode.getMessage(), null);
    }

    public static <T> CommonResult<T> failed(T data, String message) {
        return new CommonResult<T>(ResultCode.IErrorCode.getCode(), ResultCode.IErrorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T>(ResultCode.IErrorCode.getCode(), message, null);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
