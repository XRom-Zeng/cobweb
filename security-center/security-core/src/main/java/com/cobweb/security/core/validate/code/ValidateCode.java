package com.cobweb.security.core.validate.code;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 验证码
 * @author: XRom
 * @createdTime: 2018-07-28 23:40:19
 */
@Data
public class ValidateCode implements Serializable {

    /* 验证码 */
    private String code;

    /* 验证码过期时间 */
    private LocalDateTime expireTime;

    /**
     * 构造方法
     * @param code  验证码
     * @param expireIn  验证码有效时间（单位：秒）
     */
    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    /**
     * 构造方法
     * @param code 验证码
     * @param expireTime 具体过期时间
     */
    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    /**
     * 验证码是否过期
     * @return
     */
    public boolean isExpire() {
        return LocalDateTime.now().isAfter(expireTime);
    }

}
