package com.contract.web;

import com.contract.database.Role;
import com.contract.database.RoleDAO;
import com.contract.database.Tools;
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

public class SearchAllRoleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String resultString = myUtils.getRequestString(req);
        JSONObject jsonObject = new JSONObject();

        List<Role> roles = RoleDAO.getRoles();

        String roleNames = "";
        String des = "";

        for (int i = 0; i < roles.size(); i++) {
            roleNames += (roles.get(i).getName() + " ");
            des += (roles.get(i).getDescription() + " ");
        }

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("num",roles.size());
        jsonObject1.put("roleName", roleNames.trim());
        jsonObject1.put("description", des.trim());
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject1.toJSONString());
    }
}
