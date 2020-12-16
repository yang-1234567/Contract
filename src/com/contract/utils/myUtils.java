package com.contract.utils;

import com.contract.database.Tools;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpRequest;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class myUtils {

    public static String getId()
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("MMddHHmmss");//�ж����ڸ�ʽ
        format.setLenient(false);

        java.util.Date day = new java.util.Date();
        return format2.format(day);
    }

    public static String getRequestString(HttpServletRequest req) throws IOException {
        InputStream inputStream = req.getInputStream();
        byte[] b  = inputStream.readAllBytes();
        String requestString = new String(b,"UTF-8");
        return requestString;
    }
}
