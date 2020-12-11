package com.contract.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class LogDAO {
	
	public static List<Log> getLog(){
		List<Log> cons = new ArrayList<>();
		Connection conn=null;
	    Statement st=null;
	    ResultSet rs=null;
	        try {
	            //1����ȡ���Ӷ���
	            conn=Conn.getconn();
	            //2������statement���������ִ��SQL��䣡��
	            st=conn.createStatement();
	            //3������sql��ѯ���
	            String sql="select *from \"log\"";
	            //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
	            rs=st.executeQuery(sql);
	           
	            while(rs.next()) 
	            {  //ѭ�����������
	            	Log temp = new Log(rs.getString("user_id"),rs.getTimestamp("time"),rs.getString("content"),rs.getInt("del"));
	            	cons.add(temp);
	            	System.out.println("��ǰ��ȡ��"+cons.size()+"�С�");
	            	System.out.println(temp.toString());
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
		
		return cons;
	}
	//����һ����¼
	public static boolean InsertLog(Log con) 
	{
		boolean tip = true;
		Connection conn=null;
	    Statement st=null;
	    conn=Conn.getconn();
	    try {
	        //�������
	        st=conn.createStatement();
	        //���������sql���
	        String sql="insert into \"log\" values('"+con.getUser_id()+"',"+"to_timestamp('"+con.getTime()+"','yyyy-mm-dd hh24:mi:ss.ff')"+",'"+con.getContent()+"',"+con.getDel()+")";
	        //����һ�����д˲����Ľ����Ҫô�ɹ���Ҫôʧ�ܣ�������صĽ��>0���ǳɹ�����֮ʧ��
	        int result=st.executeUpdate(sql);
	        if(result>0) {
	            System.out.println("��ӳɹ�");
	        }
	        else {
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
	public static boolean DeleteLog(Log con) 
	{
		boolean tip = true;
		 Connection conn=null;
		    Statement st=null;
		    conn=Conn.getconn();
		    try {
		        st=conn.createStatement();
		        String sql="delete from \"log\" where \"user_id\"='"+con.getUser_id()+"'";
		        int result=st.executeUpdate(sql);
		        //�Ͳ���һ����������صĽ������0������ɹ�
		        if(result>0) {
		            System.out.println("ɾ���ɹ�");
		        }
		        else{
		            System.out.println("ɾ��ʧ��");
		        }
		            
		    } catch (SQLException e) {
		       tip = false;
		    	e.printStackTrace();
		    }
		return tip;
		
	}
	//����һ����¼(��id)
	public static boolean UpdateLog(Log con) {
		boolean tip = true;
		Connection conn=null;
	    Statement st=null;
	    conn=Conn.getconn();
	    try {
	        st=conn.createStatement();
	        String sql="update \"log\" set "+"\"user_id\" = '"+con.getUser_id()+"',"+"\"time\" = "+"to_timestamp('"+con.getTime()+"','yyyy-mm-dd hh24:mi:ss.ff'),"+"\"content\" = '"+con.getContent()+"',"+"\"del\" = "+con.getDel()+" where \"id\"="+con.getId();
	        int result=st.executeUpdate(sql);
	        if(result>0)
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
	public static void OPLog(String sql) {
		Connection conn=null;
	    Statement st=null;
	    conn=Conn.getconn();
	    try {
	        st=conn.createStatement();
	        int result=st.executeUpdate(sql);
	        if(result>0)
	            System.out.println("���ĳɹ�");
	        else
	            System.out.println("����ʧ��");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}
	public static void main(String[] args) {
		Timestamp time1 = new Timestamp(System.currentTimeMillis());
		Log conn = new Log("1",time1,"niubi",0);
		getLog();
	}
}
