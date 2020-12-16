package com.contract.web;

import com.contract.database.*;
import com.contract.utils.myUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AddNewUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String resultString = myUtils.getRequestString(req);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject) new JSONParser().parse(resultString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String operator = (String) jsonObject.get("operator");
        String username = (String) jsonObject.get("username");
        String password = (String) jsonObject.get("password");
        String roleName = (String) jsonObject.get("roleName");

        boolean flag = Tools.Register(username,password);
        Right right = new Right(username, roleName, "");
        flag = RightDAO.InsertRight(right);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("result",flag ? 1 : 0);
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject1.toJSONString());
    }
}
