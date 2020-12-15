package com.contract.web;

import com.contract.database.Tools;
import com.contract.database.UserDAO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        InputStream inputStream = req.getInputStream();
        byte[] b = inputStream.readAllBytes();
        String requestString = new String(b, "UTF-8");
        String username = "";
        boolean flag = false;
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(requestString);
            username = (String) jsonObject.get("username");
            String password = (String) jsonObject.get("password");
            flag = Tools.Login(username, password);

        } catch (ParseException e) {
            e.printStackTrace();
        }


        PrintWriter writer = resp.getWriter();
        JSONObject jsonObject = new JSONObject();
        if (flag) {
            jsonObject.put("result", "1");
            jsonObject.put("role",Tools.getURole(username));
            jsonObject.put("rights", Tools.getRFunction(Tools.getURole(username)));
        } else {
            jsonObject.put("result", "0");
        }
        writer.write(jsonObject.toJSONString());
    }
}
