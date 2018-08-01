package com.cobweb.security.core.validate.code.image;

import com.cobweb.security.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图片验证码
 * @author: XRom
 * @createdTime: 2018-07-13 19:11:40
 */
public class ImageCode extends ValidateCode {

    /* 图片流 */
    private BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * 构造方法
     * @param image 图片流
     * @param code  验证码
     * @param expireIn  验证码有效时间（单位：秒）
     */
    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

    /**
     * 构造方法
     * @param code 验证码
     * @param expireTime 具体过期时间
     */
    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }

    /**
     * 验证码是否过期
     * @return
     */
    public boolean isExpire() {
        return LocalDateTime.now().isAfter(super.getExpireTime());
    }
}
