package com.contract.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public static User getUser(String uid) {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            //1����ȡ���Ӷ���
            conn = Conn.getconn();
            //2������statement���������ִ��SQL��䣡��
            st = conn.createStatement();
            //3������sql��ѯ���
            String sql = "select *from \"user\" where \"name\" = '" + uid + "'";
            //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
            rs = st.executeQuery(sql);

            while (rs.next()) {  //ѭ�����������
                User temp = new User(rs.getString("name"), rs.getString("password"), rs.getInt("del"));
                return temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            //1����ȡ���Ӷ���
            conn = Conn.getconn();
            //2������statement���������ִ��SQL��䣡��
            st = conn.createStatement();
            //3������sql��ѯ���
            String sql = "select * from \"user\"";
            //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
            rs = st.executeQuery(sql);

            while (rs.next()) {  //ѭ�����������
                User temp = new User(rs.getString("name"), rs.getString("password"), rs.getInt("del"));
                users.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    //����һ����¼
    public static boolean InsertUser(User con) {
        boolean tip = true;
        Connection conn = null;
        Statement st = null;
        conn = Conn.getconn();
        try {
            //�������
            st = conn.createStatement();
            //���������sql���
            String sql = "insert into \"user\" values('" + con.getName() + "','" + con.getPassword() + "'," + con.getDel() + ")";
            //����һ�����д˲����Ľ����Ҫô�ɹ���Ҫôʧ�ܣ�������صĽ��>0���ǳɹ�����֮ʧ��
            int result = st.executeUpdate(sql);
            if (result > 0) {
                System.out.println("��ӳɹ�");
            } else {
                System.out.println("���ʧ��");
            }

        } catch (SQLException e) {
            tip = false;
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return tip;
    }

    //ɾ��һ����¼(��id)
    public static boolean DeleteUser(User con) {
        boolean tip = true;
        Connection conn = null;
        Statement st = null;
        Statement st1 = null;
        conn = Conn.getconn();
        try {
            st = conn.createStatement();
            st1 = conn.createStatement();
            String sql = "delete from \"user\" where \"name\"='" + con.getName() + "'";
            String sql2 = "delete from \"right\" where \"useName\"='" + con.getName() + "'";
            st1.executeUpdate(sql2);
            int result = st.executeUpdate(sql);


        } catch (SQLException e) {
            tip = false;
            e.printStackTrace();
        }
        return tip;

    }

    //����һ����¼(��id)
    public static boolean UpdateUser(User con) {
        boolean tip = true;
        Connection conn = null;
        Statement st = null;
        conn = Conn.getconn();
        try {
            st = conn.createStatement();
            String sql = "update \"user\" set \"password\" = '" + con.getPassword() + "'," + "\"del\" = " + con.getDel() + " where \"name\"='" + con.getName() + "'";
            int result = st.executeUpdate(sql);
            if (result > 0)
                System.out.println("���ĳɹ�");
            else
                System.out.println("����ʧ��");
        } catch (SQLException e) {
            tip = false;
            e.printStackTrace();
        }
        return tip;
    }

    //���Ӳ�������Ҫ����aql��䣩
    public static void OPUser(String sql) {
        Connection conn = null;
        Statement st = null;
        conn = Conn.getconn();
        try {
            st = conn.createStatement();
            int result = st.executeUpdate(sql);
            if (result > 0)
                System.out.println("���ĳɹ�");
            else
                System.out.println("����ʧ��");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {


        User conn = new User("����", "123456", 1);
        InsertUser(conn);

    }

}
