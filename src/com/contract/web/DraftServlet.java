package com.contract.web;

import com.contract.database.Tools;
import com.contract.functions.Draft;
import com.contract.utils.myUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

public class DraftServlet extends HttpServlet {
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

        System.out.println((String) jsonObject.get("startTime"));
        Draft draft = new Draft((String) jsonObject.get("contractName"), (String) jsonObject.get("contractContent"),
                (String) jsonObject.get("customerName"), (String) jsonObject.get("startTime"), (String) jsonObject.get("endTime"), (String) jsonObject.get("username"),
                (String) jsonObject.get("contractFile"));

        int i = draft.getTip() == true ? 1 : 0;

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("result", i + "");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject1.toJSONString());
    }
}
