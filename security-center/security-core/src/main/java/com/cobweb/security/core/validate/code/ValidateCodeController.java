package com.cobweb.security.core.validate.code;

import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author: XRom
 * @createdTime: 2018-07-13 19:16:35
 */
@RestController
@RequestMapping("security/core")
public class ValidateCodeController {

    public static final String SESSION_KEY = "IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 获取图形验证码
     * @param request
     * @param response
     */
    @GetMapping("code/image")
    public void getImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = createImageCode(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

    /**
     * 创建图形验证码
     * @param request
     * @return
     */
    private ImageCode createImageCode(HttpServletRequest request) {
        int width = 67;
        int height = 23;
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
        for (int i = 0; i<4; i++) {
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
        return new ImageCode(image, code.toString(), 60);
    }
}
