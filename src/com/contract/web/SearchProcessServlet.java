package com.contract.web;

import com.contract.database.Contract;
import com.contract.database.ContractState;
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
import java.util.ArrayList;
import java.util.List;

public class SearchProcessServlet extends HttpServlet {
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
        String state = (String) jsonObject.get("process");

        List<Contract> contracts = new ArrayList<>();
        if(state.equals("")){
            contracts = Tools.getContracts(keyword);
        }else {
            contracts =Tools.getDraft(keyword,Integer.valueOf(state));
        }

        String ids = "";
        String names = "";
        String times = "";
        String states = "";
        for (int i = 0; i < contracts.size(); i++) {
            ids += (contracts.get(i).getId() + " ");
            names += (contracts.get(i).getName() + " ");
            times += (contracts.get(i).getBeginTime() + " ");
            if(state.equals("")){
                states += Tools.getState2(contracts.get(i).getNum()) + " ";
            }
        }

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("num",contracts.size());
        jsonObject1.put("ids",ids.trim());
        jsonObject1.put("names",names.trim());
        jsonObject1.put("times",times.trim());
        jsonObject1.put("states",states.trim());
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject1.toJSONString());
    }
}
