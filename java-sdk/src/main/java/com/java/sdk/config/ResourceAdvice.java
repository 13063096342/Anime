package com.java.sdk.config;

import com.java.sdk.enums.ProductExceptionCode;
import com.java.sdk.model.Response;
import com.java.sdk.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author elvis.xu
 */
@ControllerAdvice
@Slf4j
public class ResourceAdvice {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Response handleThrowable(Throwable e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (stackTraceElement.getClassName().contains("cn.ecpark.service.product.mall")) {
                log.error(stackTraceElement.toString() + e.getMessage());
                break;
            }
        }
        return getResponseMessage(ProductExceptionCode.SYSTEM_ERROR.getCode(), ProductExceptionCode.SYSTEM_ERROR.getDesc());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        Object rt = ResponseUtil.error(ProductExceptionCode.PARAM_ERROR.getCode(), e.getMessage());
        return rt;
    }

/*
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleGisInternalException(BusinessException e) {
        Object rt = Responses.from(e);
        return getResponseMessage(e.getCode(), e.getMessage());
    }
*/

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Response handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("缺少请求参数", e);
        return getResponseMessage(ProductExceptionCode.INVALID_REQUEST_FORMAT.getCode(),
                "required_parameter_is_not_present");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Response handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("参数解析失败", e);
        return getResponseMessage(ProductExceptionCode.INVALID_REQUEST_FORMAT.getCode(),
                "could_not_read_parameter");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errors = new ArrayList<>();
        for (ObjectError error : e.getBindingResult().getAllErrors()) {
            FieldError fError = (FieldError) error;
            String params = fError.getField();
            String errorMsg = fError.getDefaultMessage();
            errors.add(params + ":" + errorMsg);
        }
        String desc = "参数错误:" + errors;
        return getResponseMessage(ProductExceptionCode.INVALID_REQUEST_FORMAT.getCode(), desc);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Response handleBindException(BindException e) {
        log.error("参数绑定失败", e);

        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        List<String> errorMsgList = new ArrayList<>();
        for (ObjectError error : allErrors) {
            errorMsgList.add(((FieldError) error).getField() + ":" + error.getDefaultMessage());
        }

        return getResponseMessage(
                ProductExceptionCode.INVALID_REQUEST_FORMAT.getCode(),
                errorMsgList.toString());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Response handleServiceException(ConstraintViolationException e) {
        log.error("参数验证失败", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();
        return getResponseMessage(ProductExceptionCode.INVALID_REQUEST_FORMAT.getCode(), ("parameter:" + message));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public Response handleValidationException(ValidationException e) {
        log.error("参数验证失败", e);
        return getResponseMessage(ProductExceptionCode.INVALID_REQUEST_FORMAT.getCode(), "validation_exception");
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public Response handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("不支持当前请求方法", e);
        return getResponseMessage(
                ProductExceptionCode.INVALID_REQUEST_FORMAT.getCode(), "Method Not Allowed");
    }


    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public Response handleHttpMediaTypeNotSupportedException(Exception e) {
        log.error("不支持当前媒体类型", e);
        return getResponseMessage(
                ProductExceptionCode.PARAM_ERROR.getCode(), "content_type_not_supported");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Response system(Exception e) {
        log.error("系统错误", e);
        return getResponseMessage(ProductExceptionCode.SYSTEM_ERROR.getCode(), ProductExceptionCode.SYSTEM_ERROR.getDesc());
    }

    private Response getResponseMessage(String responseCode, String responseMessage) {
        Response response = new Response();
        response.setCode(responseCode);
        response.setMsg(responseMessage);
        return response;
    }
}
