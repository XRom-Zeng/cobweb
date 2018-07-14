package com.cobweb.security.core.validate.code;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author: XRom
 * @createdTime: 2018-07-13 19:11:40
 * 图片验证码
 */
@Data
public class ImageCode {

    /* 图片流 */
    private BufferedImage image;

    /* 验证码 */
    private String code;

    /* 验证码过期时间 */
    private LocalDateTime expireTime;

    /**
     * 构造方法
     * @param image 图片流
     * @param code  验证码
     * @param expireIn  验证码有效时间（单位：秒）
     */
    public ImageCode(BufferedImage image, String code, int expireIn) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        this.image = image;
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
