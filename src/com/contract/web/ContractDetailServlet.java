package com.contract.web;

import com.contract.database.*;
import com.contract.utils.DateUtils;
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

        String contractId = (String)jsonObject.get("contractId");

        Contract contract = Tools.getOneCon(contractId);


        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("contractName",contract.getName());
        jsonObject1.put("customerName",contract.getCustomer());
        jsonObject1.put("startTime", DateUtils.udateToString(contract.getBeginTime()));
        jsonObject1.put("endTime",DateUtils.udateToString(contract.getEndTime()));
        jsonObject1.put("filename", ContractAttDAO.getAtt(contractId).getFileName());
        jsonObject1.put("content",contract.getContent());
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject1.toJSONString());
    }
}
