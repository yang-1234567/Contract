package com.contract.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
	public static Customer getCustomer(String cid){
		
		Connection conn=null;
	    Statement st=null;
	    ResultSet rs=null;
	        try {
	            //1����ȡ���Ӷ���
	            conn=Conn.getconn();
	            //2������statement���������ִ��SQL��䣡��
	            st=conn.createStatement();
	            //3������sql��ѯ���
	            String sql="select *from \"customer\" where \"num\" = '"+cid+"'";
	            //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
	            rs=st.executeQuery(sql);
	           
	            if(rs.next()) 
	            {  //ѭ�����������
	            	Customer temp = new Customer(rs.getString("num"),rs.getString("name"),rs.getString("address"),rs.getString("tel"),rs.getString("fax"),rs.getString("code"),rs.getString("bank"),rs.getString("account"),rs.getInt("del"));
	            	return temp;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
		
		return null;
	}
	//����һ����¼
	public static boolean InsertCustomer(Customer con) 
	{
		boolean tip = true;
		Connection conn=null;
	    Statement st=null;
	    conn=Conn.getconn();
	    try {
	        //�������
	        st=conn.createStatement();
	        //���������sql���
	        String sql="insert into \"customer\" values('"+con.getNum()+"','"+con.getName()+"','"+con.getAddress()+"','"+con.getTel()+"','"+con.getFax()+"','"+con.getCode()+"','"+con.getBank()+"','"+con.getAccount()+"',"+con.getDel()+")";
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
	public static boolean DeleteCustomer(Customer con) 
	{
			boolean tip = true;
			Connection conn=null;
		    Statement st=null;
		    conn=Conn.getconn();
		    try {
		        st=conn.createStatement();
		        String sql="delete from \"customer\" where \"num\"='"+con.getNum()+"'";
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
	public static boolean UpdateCustomer(Customer con) {
		boolean tip = true;
		Connection conn=null;
	    Statement st=null;
	    conn=Conn.getconn();
	    try {
	        st=conn.createStatement();
	        String sql="update \"customer\" set "+"\"num\" = '"+con.getNum()+"',"+"\"name\" = '"+con.getName()+"',"+"\"address\" = '"+con.getAddress()+"',"+"\"tel\" = '"+con.getTel()+"',"+"\"fax\" = '"+con.getFax()+"',"+"\"code\" = '"+con.getCode()+"',"+"\"bank\" = '"+con.getBank()+"',"+"\"account\" = '"+con.getAccount()+"',"+"\"del\" = "+con.getDel()+" where \"num\"='"+con.getNum()+"'";
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
	public static void OPCustomer(String sql) {
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
	/*
	 * public static void main(String[] args) {
	 * 
	 * 
	 * Customer conn = new
	 * Customer(2,"nmsl3","nmsl4","nmsl5","nmsl6","nmsl3","nmsl4","nmsl5","nmsl6",0)
	 * ; //getContractAtt(); InsertCustomer(conn); getCustomer();
	 * UpdateCustomer(conn); }
	 */
}
