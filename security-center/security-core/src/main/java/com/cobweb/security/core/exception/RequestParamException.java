package com.cobweb.security.core.exception;

/**
 * 请求参数异常
 * @author: XRom
 * @createdTime: 2018-07-30 16:10:43
 */
public class RequestParamException extends Exception{
    public RequestParamException() {
        super();
    }

    public RequestParamException(String s) {
        super(s);
    }

    public RequestParamException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public RequestParamException(Throwable throwable) {
        super(throwable);
    }

    protected RequestParamException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
