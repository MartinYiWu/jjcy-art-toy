package com.designer.toys.user.exception;

import com.designer.toys.common.exception.BaseException;

/**
 * @author Martin Chen
 * @version 1.0
 * @className UserServiceException (此处以class为例)
 * @date 2025/11/17/周一
 * @description 用户服务异常类
 */
public class UserServiceException extends BaseException {
    public UserServiceException(int code, String message, String serviceName) {
        super(code, message, serviceName);
    }
}
