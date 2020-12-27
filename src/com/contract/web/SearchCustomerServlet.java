package com.contract.web;

import com.contract.database.*;
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

public class SearchCustomerServlet extends HttpServlet {
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

        String customerId = (String) jsonObject.get("customerId");

        Customer customer = CustomerDAO.getCustomer(customerId);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name",customer.getName());
        jsonObject1.put("phone",customer.getTel());
        jsonObject1.put("address",customer.getAddress());
        jsonObject1.put("fax",customer.getFax());
        jsonObject1.put("email",customer.getCode());
        jsonObject1.put("bankName",customer.getBank());
        jsonObject1.put("bankAccount",customer.getAccount());
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject1.toJSONString());
    }
}
