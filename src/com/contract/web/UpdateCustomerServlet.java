package com.contract.web;

import com.contract.database.Customer;
import com.contract.database.CustomerDAO;
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

public class UpdateCustomerServlet extends HttpServlet {
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
        String id  = (String) jsonObject.get("id");
        String name = (String) jsonObject.get("name");
        String address = (String) jsonObject.get("address");
        String phone = (String) jsonObject.get("phone");
        String fax = (String) jsonObject.get("fax");
        String email = (String) jsonObject.get("email");
        String bankName = (String) jsonObject.get("bankName");
        String bankAccount = (String) jsonObject.get("bankAccount");

        Customer customer = CustomerDAO.getCustomer(id);
        customer.setName(name);
        customer.setAddress(address);
        customer.setTel(phone);
        customer.setFax(fax);
        customer.setCode(email);
        customer.setBank(bankName);
        customer.setAccount(bankAccount);

        boolean flag = CustomerDAO.UpdateCustomer(customer);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("result",flag ? 1 : 0);
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject1.toJSONString());
    }
}
