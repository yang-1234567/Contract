package com.contract.web;

import com.contract.database.Contract;
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
        req.setCharacterEncoding("UTF-8");
        String resultString = myUtils.getRequestString(req);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject) new JSONParser().parse(resultString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //List<Contract> list = new ArrayList<>();

        //String str1 = "";
        //String str2 = "";
        //String str3 = "";
        //for (Contract contract : list) {
        //    str1 = str1 + contract.getId() + " ";
        //    str2 = str2 + contract.getName() + " ";
        //    str3 = str3 + contract.getBeginTime() + " ";
        //}

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("ids","1 2 3 4 5");
        jsonObject1.put("names","合同1a 合同2a 合同3a 合同4a 合同5a");
        jsonObject1.put("times","2020-12-01 2020-12-04 2020-12-06 2020-12-09 2020-12-10");
        jsonObject1.put("countersign","张三1 李四1 王五1 赵六1 钱七1");
        jsonObject1.put("approve","张三1 李四1 王五1 钱七1");
        jsonObject1.put("sign","张三1 李四1 赵六1 钱七1");

        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject1.toJSONString());
    }
}
