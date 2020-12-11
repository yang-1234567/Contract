package com.contract.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FunctionDAO {
	public static List<Function> getFunction(){
		List<Function> cons = new ArrayList<>();
		Connection conn=null;
	    Statement st=null;
	    ResultSet rs=null;
	        try {
	            //1����ȡ���Ӷ���
	            conn=Conn.getconn();
	            //2������statement���������ִ��SQL��䣡��
	            st=conn.createStatement();
	            //3������sql��ѯ���
	            String sql="select *from \"function\"";
	            //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
	            rs=st.executeQuery(sql);
	           
	            while(rs.next()) 
	            {  //ѭ�����������
	            	Function temp = new Function(rs.getString("num"),rs.getString("name"),rs.getString("URL"),rs.getString("description"),rs.getInt("del"));
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
	public static boolean InsertFunction(Function con) 
	{
		boolean tip = true;
		Connection conn=null;
	    Statement st=null;
	    conn=Conn.getconn();
	    try {
	        //�������
	        st=conn.createStatement();
	        //���������sql���
	        String sql="insert into \"function\" values('"+con.getNum()+"','"+con.getName()+"','"+con.getURL()+"','"+con.getDescription()+"',"+con.getDel()+")";
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
	public static boolean DeleteFunction(Function con) 
	{
		boolean tip = true;
		 Connection conn=null;
		    Statement st=null;
		    conn=Conn.getconn();
		    try {
		        st=conn.createStatement();
		        String sql="delete from \"function\" where \"num\"='"+con.getNum()+"'";
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
	public static boolean UpdateFunction(Function con) {
		boolean tip = true;
		Connection conn=null;
	    Statement st=null;
	    conn=Conn.getconn();
	    try {
	        st=conn.createStatement();
	        String sql="update \"function\" set "+"\"num\" = '"+con.getNum()+"',"+"\"name\" = '"+con.getName()+"',"+"\"URL\" = '"+con.getURL()+"',"+"\"description\" = '"+con.getDescription()+"',"+"\"del\" = "+con.getDel()+" where \"num\"='"+con.getNum()+"'";
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
	public static void OPFunction(String sql) {
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
		  
	  Function conn = new Function("2","counterSign"," ","counterSign a contract",1);
	  InsertFunction(conn); //getFunction();
	  }
}
