package com.designer.toys.common.enums;

public enum BaseErrorCodeEnum {
    // 通用业务异常 10000-19999
    SUCCESS(200, "成功"),
    FAIL(-1, "失败"),
    /**
     * 参数错误
     * 请通过throw new IllegalArgumentException("xxx")使用此异常
     */
    PARAMETER_ERROR(1000, "业务参数错误"),

    /**
     * 参数检验失败
     * 请通过throw new IllegalArgumentException("xxx")使用此异常
     */
    PARAMETER_VALIDATE(1001, "业务参数校验不通过"),
    /**
     * 位置错误
     */
    UNKNOWN(400, "未知错误"),

    REMOTE_FAIL(600, "远程请求失败"),
    PARAM_ERROR(10001, "参数错误"),
    DATA_NOT_FOUND(10002, "数据不存在"),
    OPERATION_FAILED(10003, "操作失败"),
    // 系统异常 50000-59999
    SYSTEM_ERROR(500, "系统异常");

    private final int code;
    private final String message;

    BaseErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     * 根据code获取枚举
     */
    public static BaseErrorCodeEnum getByCode(int code) {
        for (BaseErrorCodeEnum errorCode : values()) {
            if (errorCode.getCode() == code) {
                return errorCode;
            }
        }
        return SYSTEM_ERROR;
    }
}
