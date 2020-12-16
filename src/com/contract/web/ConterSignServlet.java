package com.contract.web;

import com.contract.database.Log;
import com.contract.database.LogDAO;
import com.contract.functions.CounterSign;
import com.contract.functions.Sign;
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

public class ConterSignServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String resultString = myUtils.getRequestString(req);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject) new JSONParser().parse(resultString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String contractId = (String) jsonObject.get("contractId");
        String operator = (String) jsonObject.get("operator");
        String type = (String) jsonObject.get("type");
        String suggestion = (String) jsonObject.get("suggestion");
        boolean flag;
        if(type.equals("1")){

            flag = new CounterSign(contractId, operator, suggestion).getTip();
        }else {
            flag = new Sign(contractId,operator,suggestion).isTip();
        }


        if (flag && type.equals("1")) {
            LogDAO.InsertLog(new Log(operator, new Timestamp(System.currentTimeMillis()), "Countersign contract " + contractId, 1));
        } else if (flag && type.equals("4")) {
            LogDAO.InsertLog(new Log(operator, new Timestamp(System.currentTimeMillis()), "Sign contract " + contractId, 1));
        }
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("result", flag ? 1 : 0);
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject1.toJSONString());
    }
}
