package com.contract.web;

import com.contract.functions.Approve;
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

public class ApproveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String resultString = myUtils.getRequestString(req);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject) new JSONParser().parse(resultString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String operator = (String)jsonObject.get("operator");
        String contractId = (String)jsonObject.get("contractId");
        String type = (String)jsonObject.get("type");
        String approve = (String)jsonObject.get("approve");
        String suggestion = (String)jsonObject.get("suggestion");

        new Approve(contractId,operator,suggestion,Integer.parseInt(approve));

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("resule","");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject1.toJSONString());
    }
}
