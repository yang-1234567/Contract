package com.contract.web;

import com.contract.functions.Draft;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileUploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        //先判断上传的数据是否为多端数据
        if (ServletFileUpload.isMultipartContent(req)){
            //创建fileitem工厂
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            JSONObject jsonObject = new JSONObject();

            try {
                List<FileItem> list = servletFileUpload.parseRequest(req);
                FileItem file = null;

                for (FileItem fileItem : list) {
                    if (fileItem.isFormField()){//普通表单

                        jsonObject.put(fileItem.getFieldName(),fileItem.getString("UTF-8"));

                    } else {//文件表单
                        jsonObject.put("filename",fileItem.getName());
                        jsonObject.put(fileItem.getFieldName(),fileItem.getString("UTF-8"));
                        file = fileItem;
                    }
                }

                String name = (String)jsonObject.get("contract_name");
                name = name.trim();
                String content =(String) jsonObject.get("contract_content");
                String beginTime = (String)jsonObject.get("starttime");
                String endTime = (String)jsonObject.get("endtime");
                String customer = (String)jsonObject.get("customer");
                String uid = (String)jsonObject.get("username");
                String filename = (String)jsonObject.get("filename");
                Draft draft = new Draft(name,content,customer,beginTime,endTime,uid,filename);
                file.write(new File("E:\\idea\\workspace\\Contract\\web\\upload\\" + draft.getNum()));

            } catch (Exception e) {
                e.printStackTrace();
            }

            resp.setCharacterEncoding("UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.write("起草成功");
        }
    }
}
