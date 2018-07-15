package com.security.demo.config;

import com.cobweb.security.core.validate.code.ImageCode;
import com.cobweb.security.core.validate.code.ValidateCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: XRom
 * @createdTime: 2018-07-15 01:20:53
 */
@Component("imageCodeGenerator")
@Slf4j
public class DemoValidateCodeGenerator implements ValidateCodeGenerator {

    @Override
    public ImageCode generator(HttpServletRequest request) {
        System.out.println(" running ...");
        log.info("demo validate code generator running... ");
        return null;
    }
}
