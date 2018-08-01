package com.cobweb.security.core.validate.code;


import org.springframework.security.core.AuthenticationException;

/**
 * @author: XRom
 * @createdTime: 2018-07-13 20:27:03
 * 验证码异常类
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String s) {
        super(s);
    }
}
