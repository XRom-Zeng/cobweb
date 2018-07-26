package com.cobweb.security.demo.config.exception;

import com.cobweb.security.core.support.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.cobweb.security.core.support.ApiResponse.error;

/**
 * @author: XRom
 * @createdTime: 2018-07-27 01:07:27
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ApiResponse exceptionHandler(Exception e) {
        log.error(e.getMessage());
        return error();
    }
}
