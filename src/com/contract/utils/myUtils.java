package com.contract.utils;

import com.contract.database.Tools;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpRequest;

public class myUtils {
    public static String getRequestString(HttpServletRequest req) throws IOException {
        InputStream inputStream = req.getInputStream();
        byte[] b  = inputStream.readAllBytes();
        String requestString = new String(b,"UTF-8");
        return requestString;
    }
}
