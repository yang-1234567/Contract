package com.contract.web;

import com.contract.database.Right;
import com.contract.database.RightDAO;
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

public class AddNewRoleForUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String resultString = myUtils.getRequestString(req);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject) new JSONParser().parse(resultString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String username = (String)jsonObject.get("username");
        String operator = (String) jsonObject.get("operator");
        String password = (String) jsonObject.get("password");
        String roleName = (String) jsonObject.get("roleName");

        User user = new User(username,password,1);
        UserDAO.UpdateUser(user);

        Right right = new Right(username,roleName,"");
        RightDAO.UpdateRight(right);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("result","");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject1.toJSONString());
    }
}
