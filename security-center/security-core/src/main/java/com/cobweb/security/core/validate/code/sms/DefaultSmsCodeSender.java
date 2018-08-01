package com.cobweb.security.core.validate.code.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * 短信发送默认实现实现
 * @author: XRom
 * @createdTime: 2018-07-29 22:50:10
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
        log.info("发送短信验证码，手机号：" + mobile + "  验证码：" + code);
        log.warn("请实现com.cobweb.security.core.validate.code.sms.SmsCodeSender接口自定义短信发送逻辑");
    }
}
