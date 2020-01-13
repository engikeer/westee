package com.mfun.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 1. 解析请求，确定客户端想要的文件

        // 2. 确定文件的路径，获取文件的名称
        ServletContext context = getServletContext();
        String realPath = context.getRealPath("WEB-INF/classes/img/夜与海.jpg");
        String fileName = realPath.substring(realPath.lastIndexOf("/") + 1);
        // 3. 设置响应类型，让客户端/浏览器支持下载内容
        // 注意，非英文字符需要用 URLEncoder 进行编码
        resp.setHeader(
                "Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        // 4. 准备读入文件的内容
        try (FileInputStream fin = new FileInputStream(realPath)) {
            // 5. 将文件的内容写出到响应中
            int len = 0;
            byte[] buffer = new byte[1024];
            try (OutputStream out = resp.getOutputStream()) {
                while ((len = fin.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
            }
        }
    }
}
