package com.contract.web;

import com.contract.database.Log;
import com.contract.database.LogDAO;
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
import java.sql.Timestamp;

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

        String operator = (String) jsonObject.get("operator");
        String contractId = (String) jsonObject.get("contractId");
        String type = (String) jsonObject.get("type");
        String approve = (String) jsonObject.get("approve");
        String suggestion = (String) jsonObject.get("suggestion");

        boolean flag = new Approve(contractId, operator, suggestion, Integer.parseInt(approve)).getTip();

        if(flag){
            if (approve.equals("1")) {
                LogDAO.InsertLog(new Log(operator, new Timestamp(System.currentTimeMillis()), "Approve contract " + contractId, 1));
            } else {
                LogDAO.InsertLog(new Log(operator, new Timestamp(System.currentTimeMillis()), "Reject contract " + contractId, 1));
            }
        }


        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("resule", flag ? 1 : 0);
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject1.toJSONString());
    }
}
