package com.contract.web;

import com.contract.database.Tools;
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

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        InputStream inputStream = req.getInputStream();
        byte[] b = inputStream.readAllBytes();
        String requestString = new String(b, "UTF-8");
        boolean flag = false;
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(requestString);
            String username = (String) jsonObject.get("username");
            String password = (String) jsonObject.get("password");
            flag = Tools.Register(username, password);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        PrintWriter writer = resp.getWriter();
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("result", flag ? 1 : 0);

        writer.write(jsonObject.toJSONString());
    }
}
