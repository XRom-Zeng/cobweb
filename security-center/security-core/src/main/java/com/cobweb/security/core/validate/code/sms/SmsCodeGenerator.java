package com.cobweb.security.core.validate.code.sms;

import com.cobweb.security.core.properties.SecurityProperties;
import com.cobweb.security.core.validate.code.ValidateCode;
import com.cobweb.security.core.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 短信验证码生成器
 * @author: XRom
 * @createdTime: 2018-07-29 22:43:06
 */
@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generator(HttpServletRequest request) {
        String code = RandomStringUtils.randomNumeric(6);
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireTime());
    }
}
