package com.contract.web;

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

public class SearchCounterSignServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("我被调用了");
        req.setCharacterEncoding("UTF-8");
        String resultString = myUtils.getRequestString(req);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject) new JSONParser().parse(resultString);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        String keyword = (String) jsonObject.get("keyword");
        String state = (String) jsonObject.get("state");

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("ids","1 2 3 4 5");
        jsonObject1.put("names","合同1a 合同2a 合同3a 合同4a 合同5a");
        jsonObject1.put("times","2020-12-01 2020-12-04 2020-12-06 2020-12-09 2020-12-10");

        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject1.toJSONString());
        System.out.println(jsonObject1);
    }
}
