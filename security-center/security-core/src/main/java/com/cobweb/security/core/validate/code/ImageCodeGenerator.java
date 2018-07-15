package com.cobweb.security.core.validate.code;

import com.cobweb.security.core.properties.SecurityProperties;
import lombok.Data;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author: XRom
 * @createdTime: 2018-07-15 01:08:21
 */
@Data
public class ImageCodeGenerator implements ValidateCodeGenerator{

     private SecurityProperties securityProperties;

    @Override
    public ImageCode generator(HttpServletRequest request) {
        int width = ServletRequestUtils.getIntParameter(request, "width", securityProperties.getCode().getImage().getWidth());
        int height = ServletRequestUtils.getIntParameter(request, "height", securityProperties.getCode().getImage().getHeight());
        int red = 0, blue = 0, green = 0;
        // 1.创建验证码图像(宽,高,类型)
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        // 2.在该图像上获取画笔
        Graphics g = image.getGraphics();
        // 设置图像背景色和前景色
        g.setColor(Color.WHITE);//设置笔刷白色
        g.fillRect(0,0,width,height);//填充整个屏幕 (x,y,w,h)
        // 字体
        g.setFont(new Font("Fixedsys", Font.ITALIC , 20));

        Random random = new Random();
        // 画上一些随机线条
        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            // 产生随机的颜色值，让输出的每个干扰线的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawLine(x, y, x+x1, y+y1);
        }

        // 验证码
        StringBuffer code  = new StringBuffer();
        for (int i = 0; i < securityProperties.getCode().getImage().getLength(); i++) {
            String str = String.valueOf(random.nextInt(10));
            code.append(str);
            // 产生随机的颜色值，让输出的每个字符的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawString(str, 13*i+6, 16);
        }

        // 释放画笔资源
        g.dispose();
        return new ImageCode(image, code.toString(), securityProperties.getCode().getImage().getExpireTime());
    }
}
