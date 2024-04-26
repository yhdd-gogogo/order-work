package com.tjj.zj.tjjwork.web;

/**
 * @author:
 * @date:
 */
public enum ResultCode {
    /**
     * 返回成功
     */
    SUCCESS(0, "成功"),

    IErrorCode(1, "失败");

    private Integer code;

    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
