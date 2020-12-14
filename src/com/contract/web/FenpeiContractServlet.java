package com.contract.web;

import com.contract.functions.Distribute;
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
import java.util.Arrays;

public class FenpeiContractServlet extends HttpServlet {
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
        String[] countersign = ((String) jsonObject.get("countersign")).split(" ");
        String[] exam_and_approve = ((String) jsonObject.get("approve")).split(" ");
        String[] sign = ((String) jsonObject.get("sign")).split(" ");

        new Distribute(Arrays.asList(countersign),Arrays.asList(exam_and_approve),Arrays.asList(sign),contractId);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("result","");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject1.toJSONString());
    }
}
