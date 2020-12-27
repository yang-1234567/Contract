package com.contract.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
   
    
    public static Connection getconn() {
        Connection conn = null;
        try {
        Class.forName("oracle.jdbc.driver.OracleDriver"); //�������ݿ�����
        String url = "jdbc:oracle:thin:@localhost:1521/orcl";    //��ȡ����URL
        String user = "c##ouyyyang"; //�����û���
        String password = "926997"; //��������
        conn = DriverManager.getConnection(url, user, password); //��ȡ���ݿ�����
        if (conn != null) {
        System.out.println("�ɹ�����Oracle���ݿ⽨�����ӣ���");
        }
        } catch (Exception e) {
        e.printStackTrace();
        } 
        return conn; //����Connectionʵ�� 
        }
}
//�Լ��ڲ������ӵ�ʱ����Բ��ö������ӷ���