package com.contract.web;

import com.contract.database.Log;
import com.contract.database.LogDAO;
import com.contract.database.User;
import com.contract.database.UserDAO;
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
import java.sql.Timestamp;

public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String resultString = myUtils.getRequestString(req);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject) new JSONParser().parse(resultString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String username = (String) jsonObject.get("username");
        String operator = (String) jsonObject.get("operator");

        boolean flag = UserDAO.DeleteUser(UserDAO.getUser(username));
        if (flag) {
            LogDAO.InsertLog(new Log(operator, new Timestamp(System.currentTimeMillis()), "Delete user " + username, 1));
        }

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("result", flag ? 1 : 0);
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject1.toJSONString());
    }
}
