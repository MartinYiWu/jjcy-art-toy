package com.designer.toys.common.exception;

import com.designer.toys.common.enums.BaseErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Martin Chen
 * @version 1.0
 * @className CustomException (此处以class为例)
 * @date 2025/11/17/周一
 * @description
 */
@Data
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private int code;

    private String message;


    public CustomException(BaseErrorCodeEnum errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public CustomException(BaseErrorCodeEnum errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

}
