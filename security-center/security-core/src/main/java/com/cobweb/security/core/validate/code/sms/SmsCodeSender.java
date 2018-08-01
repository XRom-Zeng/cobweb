package com.cobweb.security.core.validate.code.sms;

/**
 * 短信发送接口
 * @author: XRom
 * @createdTime: 2018-07-29 22:47:52
 */
public interface SmsCodeSender {

    void send(String mobile, String code);
}
