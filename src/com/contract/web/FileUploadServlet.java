package com.contract.web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileUploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //先判断上传的数据是否为多端数据
        if (ServletFileUpload.isMultipartContent(req)){
            //创建fileitem工厂
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);

            try {
                List<FileItem> list = servletFileUpload.parseRequest(req);

                for (FileItem fileItem : list) {
                    if (fileItem.isFormField()){//普通表单

                        System.out.println("表单项的name值" + fileItem.getFieldName());
                        System.out.println("表单项的value值" + fileItem.getString("UTF-8"));

                    } else {//文件表单
                        System.out.println("表单项的name值" + fileItem.getFieldName());
                        System.out.println("上传的文件名" + fileItem.getName());

                        fileItem.write(new File("E:\\idea\\workspace\\Contract\\web\\upload\\" + fileItem.getName()));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
