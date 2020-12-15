package com.contract.test;

import com.contract.utils.DBUtils;

public class TestConnection {
    public static void main(String[] args) {
        System.out.println(DBUtils.getConnection());
    }
}
