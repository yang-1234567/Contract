package com.contract.web;

import com.contract.database.Contract;
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

public class SearchContractByProcessServlet extends HttpServlet {
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

        String username = (String) jsonObject.get("username");
        String process = (String) jsonObject.get("process");
        String keyword = (String) jsonObject.get("keyword");

        List<Contract> list = Tools.getUDraft(keyword, Integer.parseInt(process), username);

        String str1 = "";
        String str2 = "";
        String str3 = "";
        for (Contract contract : list) {
            str1 = str1 + contract.getId() + " ";
            str2 = str2 + contract.getName() + " ";
            str3 = str3 + contract.getBeginTime() + " ";
        }

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("ids", str1);
        jsonObject1.put("names", str2);
        jsonObject1.put("times", str3);
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject1.toJSONString());
    }
}
