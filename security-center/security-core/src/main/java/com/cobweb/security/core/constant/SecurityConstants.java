package com.cobweb.security.core.constant;

/**
 * @author: XRom
 * @createdTime: 2018-08-01 16:39:15
 */
public interface SecurityConstants {

    /**
     * 默认用户密码登录请求处理url
     */
    public final static String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/security/authentication/form";


    /**
     * 默认手机验证码登录请求处理url
     */
    public final static String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/security/authentication/mobile";

    /**
     * 默认获取图形验证码url
     */
    public final static String DEFAULT_GET_IMG__CODE_URL = "/security/code/image";

    /**
     * 默认获取短信验证码url
     */
    public final static String DEFAULT_GET_SMS__CODE_URL = "/security/code/sms";

    /**
     * 默认session失效处理url
     */
    public static String DEFAULT_SESSION_INVALID_URL = "/security/browser/"+SecurityConstants.SESSION_INVALID_ULR_SUFFIX;


    public static String SESSION_INVALID_ULR_SUFFIX = "session/invalid";


}
