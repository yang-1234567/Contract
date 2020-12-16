package com.contract.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RightDAO {
	public static Right getRight(String un){
		
		Connection conn=null;
	    Statement st=null;
	    ResultSet rs=null;
	        try {
	            //1����ȡ���Ӷ���
	            conn=Conn.getconn();
	            //2������statement���������ִ��SQL��䣡��
	            st=conn.createStatement();
	            //3������sql��ѯ���
	            String sql="select *from \"right\" where \"useName\" = '"+un+"'";
	            //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
	            rs=st.executeQuery(sql);
	           
	            if(rs.next()) 
	            {  //ѭ�����������
	            	Right temp = new Right(rs.getString("useName"),rs.getString("rolName"),rs.getString("description"));

	            	return temp;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
		
		return null;
	}
	//����һ����¼
	public static boolean InsertRight(Right con) 
	{
		boolean tip = true;
		Connection conn=null;
	    Statement st=null;
	    conn=Conn.getconn();
	    try {
	        //�������
	        st=conn.createStatement();
	        //���������sql���
	        String sql="insert into \"right\" values('"+con.getUserName()+"','"+con.getRoleName()+"','"+con.getDescription()+"')";
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
	//ɾ��һ����¼(��Rightʵ��)
	public static boolean DeleteRight(Right con) 
	{
		boolean tip = true;
		Connection conn=null;
		    Statement st=null;
		    conn=Conn.getconn();
		    try {
		        st=conn.createStatement();
		        String sql="delete from \"right\" where \"useName\" = '"+con.getUserName()+"'and \"rolName\" = '"+con.getRoleName()+"'";
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
	//����һ����¼(��rightʵ��)
	public static boolean UpdateRight(Right con) {
		boolean tip = true;
		Connection conn=null;
	    Statement st=null;
	    conn=Conn.getconn();
	    try {
	        st=conn.createStatement();
	        String sql="update \"right\" set \"rolName\" = '"+con.getRoleName()+"',"+"\"description\" = '"+con.getDescription()+"'"+" where \"useName\"='"+con.getUserName()+"'";
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
	public static void OPRole(String sql) {
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
		Right right = new Right("����","operator","nmsl2");
		InsertRight(right);
		
	}
}
