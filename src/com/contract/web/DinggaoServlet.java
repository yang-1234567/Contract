package com.contract.web;

import com.contract.database.Contract;
import com.contract.database.ContractAttDAO;
import com.contract.database.Tools;
import com.contract.functions.Finalize;
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
import java.util.List;

public class DinggaoServlet extends HttpServlet {
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

        String contractId = (String) jsonObject.get("contractId");
        String content = (String) jsonObject.get("content");

        Contract contract = Tools.getOneCon(contractId);
        Finalize finalize = new Finalize(contractId,contract.getName(),contract.getCustomer(),
                contract.getUser_id(),content, DateUtils.udateToString(contract.getBeginTime()),
                DateUtils.udateToString(contract.getEndTime()),ContractAttDAO.getAtt(contractId).getFileName());

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("result",finalize.isTip() ? 1 : 0);
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject1.toJSONString());
    }
}
