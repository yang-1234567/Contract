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
import java.sql.Timestamp;

public class AddNewCustomerServlet extends HttpServlet {
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
        String name = (String) jsonObject.get("name");
        String address = (String) jsonObject.get("address");
        String phone = (String) jsonObject.get("phone");
        String fax = (String) jsonObject.get("fax");
        String email = (String) jsonObject.get("email");
        String bankName = (String) jsonObject.get("bankName");
        String bankAccount = (String) jsonObject.get("bankAccount");
        String append = (String) jsonObject.get("append");

        Customer customer = new Customer("", name, address, phone, fax, email, bankName, bankAccount, 1);
        boolean flag = CustomerDAO.InsertCustomer(customer);

        LogDAO.InsertLog(new Log(operator, new Timestamp(System.currentTimeMillis()), "Add a new customer:" + name, 1));

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("result", flag ? 1 : 0);
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject1.toJSONString());
    }
}
