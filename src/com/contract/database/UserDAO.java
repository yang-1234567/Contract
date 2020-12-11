package com.contract.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	
	public static List<User> getUser(){
		List<User> cons = new ArrayList<>();
		Connection conn=null;
	    Statement st=null;
	    ResultSet rs=null;
	        try {
	            //1����ȡ���Ӷ���
	            conn=Conn.getconn();
	            //2������statement���������ִ��SQL��䣡��
	            st=conn.createStatement();
	            //3������sql��ѯ���
	            String sql="select *from \"user\"";
	            //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
	            rs=st.executeQuery(sql);
	           
	            while(rs.next()) 
	            {  //ѭ�����������
	            	User temp = new User(rs.getString("name"),rs.getString("password"),rs.getInt("del"));
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
	public static boolean InsertUser(User con) 
	{
		boolean tip = true;
		Connection conn=null;
	    Statement st=null;
	    conn=Conn.getconn();
	    try {
	        //�������
	        st=conn.createStatement();
	        //���������sql���
	        String sql="insert into \"user\" values('"+con.getName()+"','"+con.getPassword()+"',"+con.getDel()+")";
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
	public static boolean DeleteUser(User con) 
	{
		boolean tip = true;
		Connection conn=null;
		    Statement st=null;
		    conn=Conn.getconn();
		    try {
		        st=conn.createStatement();
		        String sql="delete from \"user\" where \"name\"="+con.getName()+"";
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
	public static boolean UpdateUser(User con) {
		boolean tip = true;
		Connection conn=null;
	    Statement st=null;
	    conn=Conn.getconn();
	    try {
	        st=conn.createStatement();
	        String sql="update \"user\" set "+"\"name\" = '"+con.getName()+"',"+"\"password\" = '"+con.getPassword()+"',"+"\"del\" = "+con.getDel()+" where \"id\"="+con.getId();
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
	public static void OPUser(String sql) {
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
	  
	  
	  User conn = new User("����","123456",1); 
	  InsertUser(conn);
	  
	  }
	 
}
