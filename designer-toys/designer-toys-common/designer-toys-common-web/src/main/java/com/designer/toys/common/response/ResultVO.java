package com.designer.toys.common.response;

import com.designer.toys.common.enums.BaseErrorCodeEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 统一返回数据格式定义
 *
 * @param <T>
 */
@Data
public class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String message;
    private T data;

    public ResultVO(int code, String message, T data) {

        this.code = code;
        this.message = message;
        this.data = data;

    }

    public ResultVO() {

    }

    public static <T> ResultVO<T> success(T data) {
        ResultVO<T> result = new ResultVO<>();
        result.setCode(200);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    public static <T> ResultVO<T> error(String message) {
        ResultVO<T> result = new ResultVO<>();
        result.setCode(500);
        result.setMessage(message);
        return result;
    }

    public static ResultVO fail() {
        return new ResultVO<Void>(BaseErrorCodeEnum.FAIL.getCode(), null, null);
    }

    public static <T> ResultVO<T> fail(String message) {
        return new ResultVO<T>(BaseErrorCodeEnum.FAIL.getCode(), message, null);
    }


    public static ResultVO fail(int code, String message) {
        return new ResultVO<Void>(code, message, null);
    }

    public static <T> ResultVO<T> fail(String message, T result) {
        return new ResultVO<>(BaseErrorCodeEnum.FAIL.getCode(), message, result);
    }

    public static ResultVO fail(int code, String message, Map<String, Object> errorDetail) {
        return new ResultVO<Void>(code, message, null);
    }
}
