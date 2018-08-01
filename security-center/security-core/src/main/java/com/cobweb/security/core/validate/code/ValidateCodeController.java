package com.cobweb.security.core.validate.code;

import com.cobweb.security.core.exception.RequestParamException;
import com.cobweb.security.core.support.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author: XRom
 * @createdTime: 2018-07-13 19:16:35
 */
@RestController
@RequestMapping("security")
public class ValidateCodeController {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    private final String CODE_PROCESSOR_SUFFIX = "CodeProcessor";

    /**
     * 获取验证码（短信验证码、图形验证码）
     * @param request
     * @param response
     * @param type  路径参数：sms-获取短信验证码； image-获取图形验证码
     * @return
     * @throws IOException
     * @throws RequestParamException
     */
    @GetMapping("code/{type}")
    public ApiResponse getValidateCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws IOException, RequestParamException {
        ValidateCodeProcessor validateCodeProcessor = validateCodeProcessors.get(type + CODE_PROCESSOR_SUFFIX);
        if (validateCodeProcessor == null) {
            return ApiResponse.error("请求路径错误");
        }
        //创建验证码
        validateCodeProcessor.create(new ServletWebRequest(request, response));
        return ApiResponse.success();
    }
}
