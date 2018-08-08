package com.cobweb.security.demo.config.exception;

import com.cobweb.security.core.support.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.cobweb.security.core.support.ApiResponse.error;

/**
 * @author: XRom
 * @createdTime: 2018-07-27 01:07:27
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ApiResponse exceptionHandler(Exception e) {
        e.printStackTrace();
        return error();
    }
}
