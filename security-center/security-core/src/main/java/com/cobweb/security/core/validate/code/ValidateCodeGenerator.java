package com.cobweb.security.core.validate.code;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: XRom
 * @createdTime: 2018-07-15 00:25:51
 * 验证码生成器接口
 */
public interface ValidateCodeGenerator {

    /**
     * 生成验证码
     * @param request
     * @return
     */
    ValidateCode generator(HttpServletRequest request);
}
