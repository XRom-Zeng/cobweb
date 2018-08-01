package com.cobweb.security.core.validate.code;

import com.cobweb.security.core.exception.RequestParamException;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * 验证码处理器， 封装不同验证码的处理罗
 * @author: XRom
 * @createdTime: 2018-07-30 15:51:12
 */
public interface ValidateCodeProcessor {

    String SESSION_KET_PREFIX = "SESSION_KET_FOR_CODE_";

    String CODE_GENERATOR_SUFFIX = "CodeGenerator";

    void create(ServletWebRequest request) throws RequestParamException, IOException;
}
