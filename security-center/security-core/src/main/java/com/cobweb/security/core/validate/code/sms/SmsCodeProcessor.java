package com.cobweb.security.core.validate.code.sms;

import com.cobweb.security.core.exception.RequestParamException;
import com.cobweb.security.core.validate.code.ValidateCode;
import com.cobweb.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码处理器
 * @author: XRom
 * @createdTime: 2018-07-30 16:03:39
 */
@Component
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws RequestParamException {
        String mobile = null;
        try {
            mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");
        } catch (ServletRequestBindingException e) {
            throw new RequestParamException("缺少请求参数：mobile");
        }
        smsCodeSender.send(mobile, validateCode.getCode());;
    }
}
