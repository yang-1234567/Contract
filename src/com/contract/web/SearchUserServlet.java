package com.contract.web;

import com.contract.dao.RightDao;
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

public class SearchUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String resultString = myUtils.getRequestString(req);

        List<User> users = UserDAO.getUsers();
        String usernames = "";
        String roles = "";
        for (User user : users) {
            usernames += user.getName() + " ";
            Right right = RightDAO.getRight(user.getName());
            if (right != null){
                String roleName = right.getRoleName();
                roles += roleName + " ";
            } else {
                roles += ", ";
            }

        }
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("num", users.size());
        jsonObject1.put("usernames", usernames.trim());
        jsonObject1.put("roles", roles.trim());
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject1.toJSONString());
    }
}
