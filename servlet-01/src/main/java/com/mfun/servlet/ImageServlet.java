package com.mfun.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class ImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 1. 在内存中创建图片
        BufferedImage image = new BufferedImage(91, 20, BufferedImage.TYPE_INT_RGB);

        // 2. 在图片上绘制内容
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        // 绘制背景色
        // 设置要绘制的颜色
        graphics.setColor(Color.WHITE);
        // 设置填充形状和区域
        graphics.fillRect(0,0,91,20);
        graphics.setBackground(Color.WHITE);
        // 绘制数字
        graphics.setColor(Color.BLUE);
        // 设置要绘制的字体
        graphics.setFont(new Font(null, Font.BOLD, 20));
        // 绘制数字
        graphics.drawString(generateNum(), 0, 18);

        // 3. 设置浏览器响应格式，响应为图片
        resp.setContentType("image/jpeg");

        // 4. 不让浏览器缓存响应内容
        resp.setDateHeader("Expires", 0);
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Pragma", "no-cache");

        // 5.让浏览器每 3 秒自动刷新一次
        resp.setHeader("refresh", "3");

        // 6. 写出图片
        try (OutputStream out = resp.getOutputStream()) {
            ImageIO.write(image, "jpg", out);
        }
    }

    /**
     * 生成 7 位数字的字符串
     * @return 字符串格式的 7 位数字
     */
    private String generateNum() {
        Random random = new Random();
        int num = random.nextInt(9999999);
        return String.format("%07d", num);
    }
}
