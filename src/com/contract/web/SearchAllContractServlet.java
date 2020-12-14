package com.contract.web;

import com.contract.database.Contract;
import com.contract.database.ContractDAO;
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
import java.util.List;

public class SearchAllContractServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String resultString = myUtils.getRequestString(req);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject) new JSONParser().parse(resultString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String keyword = (String) jsonObject.get("keyword");

        List<Contract> contracts = Tools.getContracts(keyword);
        String ids = "";
        String names = "";
        String times = "";
        for (int i = 0; i < contracts.size(); i++) {
            ids += (contracts.get(i).getId() + " ");
            names += (contracts.get(i).getName() + " ");
            times += (contracts.get(i).getBeginTime() + " ");
        }
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("ids", ids);
        jsonObject1.put("names", names);
        jsonObject1.put("times", times);
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject1.toJSONString());
    }
}
