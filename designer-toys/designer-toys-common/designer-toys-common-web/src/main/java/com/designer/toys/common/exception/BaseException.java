package com.designer.toys.common.exception;

import com.designer.toys.common.enums.BaseErrorCodeEnum;

/**
 * @author Martin Chen
 * @version 1.0
 * @className BaseException (此处以class为例)
 * @date 2025/11/17/周一
 * @description  基础异常类
 */
public class BaseException extends RuntimeException {
    private final int code;
    private final String message;
    private final String serviceName;

    public BaseException(BaseErrorCodeEnum errorCode) {
        this(errorCode, null, null);
    }

    public BaseException(BaseErrorCodeEnum errorCode, String serviceName) {
        this(errorCode, serviceName, null);
    }

    public BaseException(BaseErrorCodeEnum errorCode, String serviceName, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.serviceName = serviceName;
    }

    public BaseException(int code, String message, String serviceName) {
        super(message);
        this.code = code;
        this.message = message;
        this.serviceName = serviceName;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getServiceName() {
        return serviceName;
    }
}
