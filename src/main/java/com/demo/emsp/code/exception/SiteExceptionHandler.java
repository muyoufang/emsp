package com.demo.emsp.code.exception;

import com.demo.emsp.code.entity.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Results;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


/**
 * 异常处理器
 */
@RestControllerAdvice
@Slf4j
public class SiteExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(SiteException.class)
    public R handleRenException(SiteException ex) {
        log.error("error{},{}", ex.getMessage(), ex);
        return R.error(ex.getCode(), ex.getMsg());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(SiteConfictException.class)
    public R handleConfictException(SiteConfictException ex) {
        log.error("error{},{}", ex.getMessage(), ex);
        return R.error(ex.getCode(), ex.getMsg());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SiteBadException.class)
    public R handleBadException(SiteBadException ex) {
        log.error("error{},{}", ex.getMessage(), ex);
        return R.error(ex.getCode(), ex.getMsg());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public R handleDuplicateKeyException(DuplicateKeyException ex) {
        System.out.println("error2");
        log.error("error{},{}", ex.getMessage(), ex);
        return R.error(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R handleException(Exception ex) {
        log.error("error{},{}", ex.getMessage(), ex);
        return R.error(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(MissingPathVariableException.class)
    public R handleMissingPathVariableException(MissingPathVariableException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.warn("请求路径中缺少必需的路径变量'{}',发生系统异常:{}", requestURI, e.getMessage());
        return R.error(String.format("请求路径中缺少必需的路径变量[%s]", e.getVariableName()));
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public R handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.warn("请求参数类型不匹配'{}',发生系统异常:{}", requestURI, e.getMessage());
        return R.error(
                String.format("请求参数类型不匹配，参数[%s]要求类型为：'%s'，但输入值为：'%s'", e.getName(), e.getRequiredType().getName(),
                        e.getValue()));
    }

    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    public R handleValidationException(ValidationException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.warn("请求参数数据校验失败'{}',发生系统异常:{}", requestURI, e.getMessage());
        return R.error(String.format("请求参数数据校验失败[%s]", e.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public R handleConstraintViolationException(ConstraintViolationException e) {
        log.warn("Get方式参数验证异常:{}", e.getMessage());
        // 获取所有错误信息
        HashSet<ConstraintViolation<?>> set = (HashSet<ConstraintViolation<?>>) e.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = set.iterator();
        if (iterator.hasNext()) {
            ConstraintViolation<?> next = iterator.next();
            String msg = next.getMessageTemplate();
            // 返回自定义信息格式
            return R.error(msg);
        }
        return R.error();
    }
/*

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e) {
        log.warn("Post方式参数验证异常:{}", e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        String message = bindingResult.getFieldError().getDefaultMessage();
        return R.error(message);
    }
*/

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        BindingResult br = e.getBindingResult();
        if (br.hasFieldErrors()) {
            List<FieldError> fieldErrorList = br.getFieldErrors();
            List<String> errors = new ArrayList<>(fieldErrorList.size());
            for (FieldError error : fieldErrorList) {
                errors.add(error.getField() + ":" + error.getDefaultMessage());
            }
            // 然后提取错误提示信息进行返回
            return R.error(errors.toString());
        }
        // 然后提取错误提示信息进行返回
        return R.error("校验错误");
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public R handleValidException(BindException e) {
        log.warn("数据绑定参数验证异常:{}", e.getMessage());
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return R.error(message);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public R handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error(">>>>>>>>>>>发生未知异常，请求地址:{}，错误信息:{}", requestURI, e.getMessage(), e);
        return R.error("当前网络繁忙，请稍后再试");
    }
}
