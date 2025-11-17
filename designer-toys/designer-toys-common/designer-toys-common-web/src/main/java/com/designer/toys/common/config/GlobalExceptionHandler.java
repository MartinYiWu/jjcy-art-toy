package com.designer.toys.common.config;

import cn.hutool.json.JSONUtil;
import com.designer.toys.common.enums.BaseErrorCodeEnum;
import com.designer.toys.common.exception.BaseException;
import com.designer.toys.common.exception.CustomException;
import com.designer.toys.common.response.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Martin Chen
 * @version 1.0
 * @className GlobalExceptionHandler (此处以class为例)
 * @date 2025/11/17/周一
 * @description 全局异常处理
 */
@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    /**
     * 捕获@校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.OK)
    public ResultVO validationBodyException(MethodArgumentNotValidException e) {
        Map<String, Object> errorDetail = getErrorDetail(e.getBindingResult());
        log.error("BindException:{}", JSONUtil.toJsonStr(errorDetail));
        return ResultVO.fail(BaseErrorCodeEnum.PARAMETER_VALIDATE.getCode(), BaseErrorCodeEnum.PARAMETER_VALIDATE.getMessage(), errorDetail);
    }

    /**
     * 捕获@校验异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.OK)
    public ResultVO validExceptionHandler(BindException e) {
        Map<String, Object> errorDetail = getErrorDetail(e.getBindingResult());
        log.error("BindException:{}", JSONUtil.toJsonStr(errorDetail));
        return ResultVO.fail(BaseErrorCodeEnum.PARAMETER_VALIDATE.getCode(), BaseErrorCodeEnum.PARAMETER_VALIDATE.getMessage(), errorDetail);
    }

    private Map<String, Object> getErrorDetail(BindingResult bindingResult) {
        if (null == bindingResult) {
            return Collections.emptyMap();
        }

        return bindingResult.getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, fieldError -> Optional.ofNullable(fieldError.getDefaultMessage()).orElse("")));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.OK)
    public ResultVO illegalArgumentExceptionHandler(IllegalArgumentException e) {
        log.error("IllegalArgumentException:{}", e.getMessage(), e);
        return ResultVO.fail(BaseErrorCodeEnum.PARAMETER_ERROR.getCode(), BaseErrorCodeEnum.PARAMETER_ERROR.getMessage() + ":" + e.getMessage());
    }

    @ExceptionHandler({SQLException.class})
    @ResponseBody
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.OK)
    public ResultVO handleSQLException(SQLException e) {
        log.error("SQLException:{}", e.getMessage(), e);
        return ResultVO.fail(BaseErrorCodeEnum.FAIL.getCode(), "执行SQL时发生错误，请检查日志确认SQL正确性");
    }

    @ExceptionHandler({BadSqlGrammarException.class})
    @ResponseBody
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.OK)
    public ResultVO handleSQLException(BadSqlGrammarException e) {
        log.error("BadSqlGrammarException:{}", e.getMessage(), e);
        return ResultVO.fail(BaseErrorCodeEnum.FAIL.getCode(), "执行SQL时发生错误，请检查日志确认SQL正确性");
    }

    /**
     * 通用的业务异常BaseException
     */
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.OK)
    public ResultVO handleCommonException(BaseException e) {
        log.error("BaseException:{}", e.getMessage(), e);
        return ResultVO.fail(e.getMessage());
    }

    /**
     * 捕捉运行时异常
     *
     * @param request
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.OK)
    public ResultVO runtimeExceptionHandler(HttpServletRequest request, final RuntimeException e, HttpServletResponse response) {
        log.error("RuntimeException:{}", e.getMessage(), e);
        return ResultVO.fail(500, e.getMessage());
    }

    /**
     * 未知异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.OK)
    public ResultVO handleException(Exception e) {
        log.error("Exception:{}", e.getMessage(), e);
        return ResultVO.fail(BaseErrorCodeEnum.UNKNOWN.getCode(), e.getMessage());
    }

    /**
     * 捕捉自定义异常
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.OK)
    public ResultVO customExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        CustomException customException = (CustomException) e;
        return ResultVO.fail(customException.getCode(), customException.getMessage());
    }
}
