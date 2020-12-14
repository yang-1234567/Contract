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
import java.util.ArrayList;
import java.util.List;

public class SearchAssignServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("我被调通了");
        req.setCharacterEncoding("UTF-8");
        String resultString = myUtils.getRequestString(req);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject) new JSONParser().parse(resultString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String keyword = (String) jsonObject.get("keyword");

        List<Contract> list = Tools.getDraft(keyword, 0);

        String str1 = "";
        String str2 = "";
        String str3 = "";
        for (Contract contract : list) {
            str1 = str1 + contract.getId() + " ";
            str2 = str2 + contract.getName() + " ";
            str3 = str3 + contract.getBeginTime() + " ";
        }

        List<String> countersign = Tools.getDistributor("1");
        List<String> apporve = Tools.getDistributor("3");
        List<String> sign = Tools.getDistributor("4");

        String str4 = "";
        String str5 = "";
        String str6 = "";

        for (String str : countersign) {
            str4 += str + " ";
        }
        for (String str : apporve) {
            str5 += str + " ";
        }
        for (String str : sign) {
            str6 += str + " ";
        }
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("ids", str1);
        jsonObject1.put("names", str2);
        jsonObject1.put("times", str3);
        jsonObject1.put("countersign", str4);
        jsonObject1.put("approve", str5);
        jsonObject1.put("sign", str6);

        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject1.toJSONString());
    }
}
