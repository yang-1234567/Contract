package com.contract.web;

import com.contract.database.Contract;
import com.contract.database.ContractDAO;
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

public class ContractDetailServlet extends HttpServlet {
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

        String contractid = (String)jsonObject.get("contractId");
        String username = (String)jsonObject.get("username");

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("contractName","");
        jsonObject1.put("customerName","");
        jsonObject1.put("startTime","");
        jsonObject1.put("endTime","");
        jsonObject1.put("filename","");
        jsonObject1.put("content","");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject1.toJSONString());
    }
}
