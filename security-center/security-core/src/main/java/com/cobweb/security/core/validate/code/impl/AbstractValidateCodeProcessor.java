package com.cobweb.security.core.validate.code.impl;

import com.cobweb.security.core.exception.RequestParamException;
import com.cobweb.security.core.validate.code.ValidateCode;
import com.cobweb.security.core.validate.code.ValidateCodeGenerator;
import com.cobweb.security.core.validate.code.ValidateCodeProcessor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.util.Map;

/**
 * 验证码处理器
 * @author: XRom
 * @createdTime: 2018-07-30 15:54:21
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    /**
     * 创建验证码验证码
     * @param request
     * @throws RequestParamException
     * @throws IOException
     */
    @Override
    public void create(ServletWebRequest request) throws RequestParamException, IOException {
        C validateCode = generate(request); //生成
        save(request, validateCode);    //保存
        send(request, validateCode);    //发送
    }

    /**
     * 生成验证码
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    private C generate(ServletWebRequest request) {
        String type = getProcessorType(request);
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(type+CODE_GENERATOR_SUFFIX);
        return (C) validateCodeGenerator.generator(request.getRequest());
    }

    /**
     * 保存验证码
     * @param request
     * @param validateCode 验证码
     */
    private void save(ServletWebRequest request, C validateCode) {
        ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
        sessionStrategy.setAttribute(request, SESSION_KET_PREFIX+getProcessorType(request).toUpperCase(), code);
    }

    /**
     * 发送验证码
     * @param request
     * @param validateCode  验证码
     * @throws RequestParamException
     * @throws IOException
     */
    protected abstract void send(ServletWebRequest request, C validateCode) throws RequestParamException, IOException;

    /**
     * 根据请求路径获取验证码类型
     * @param request
     * @return
     */
    private static String getProcessorType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
    }
}
