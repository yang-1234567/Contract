package com.contract.web;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Base64;

public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String downloadFileName  = "test.png";

        ServletContext servletContext = getServletContext();

        String mimeType = servletContext.getMimeType("/upload/" + downloadFileName);
        System.out.println("下载的文件类型" + mimeType);

        resp.setContentType(mimeType);

        if (req.getHeader("User-Agent").contains("Firefox")){
            resp.setHeader("Content-Disposition","attachment; filename==?UTF-8?B" +
                    Base64.getEncoder().encode(downloadFileName.getBytes("UTF-8")) +"?=" );
        } else {
            resp.setHeader("Content-Disposition","attachment; filename=" + URLEncoder.encode(downloadFileName,"UTF-8"));
        }

        InputStream resourceAsStream = servletContext.getResourceAsStream("/upload/" + downloadFileName);

        OutputStream outputStream = resp.getOutputStream();

        IOUtils.copy(resourceAsStream,outputStream);

    }
}
