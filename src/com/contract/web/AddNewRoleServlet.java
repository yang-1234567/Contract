package com.contract.web;

import com.contract.database.Log;
import com.contract.database.LogDAO;
import com.contract.database.Role;
import com.contract.database.RoleDAO;
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

public class AddNewRoleServlet extends HttpServlet {
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
        String roleName = (String) jsonObject.get("roleName");
        String description = (String) jsonObject.get("description");

        Role newRole = new Role(roleName, description, 1);
        boolean flag = RoleDAO.InsertRole(newRole);

        LogDAO.InsertLog(new Log(operator, new Timestamp(System.currentTimeMillis()), "Add a new role:" + roleName, 1));

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("result", flag ? 1 : 0);
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject1.toJSONString());
    }
}
