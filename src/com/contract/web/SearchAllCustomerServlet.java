package com.contract.web;

import com.contract.database.Customer;
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

public class SearchAllCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String resultString = myUtils.getRequestString(req);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject) new JSONParser().parse(resultString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String keyword = (String)jsonObject.get("keyword");

        List<Customer> customers = Tools.getCustomers(keyword);

        String ids = "";
        String names = "";
        String address = "";
        String phones = "";

        for (int i=0;i<customers.size();i++){
            ids+=(customers.get(i).getNum()+" ");
            names+=(customers.get(i).getName()+" ");
            address+=(customers.get(i).getAddress()+" ");
            phones+=(customers.get(i).getTel()+" ");
        }

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("num",customers.size());
        jsonObject1.put("ids",ids.trim());
        jsonObject1.put("names",names.trim());
        jsonObject1.put("address",address.trim());
        jsonObject1.put("phones",phones.trim());
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject1.toJSONString());
    }
}
