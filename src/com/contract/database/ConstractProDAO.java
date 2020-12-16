package com.contract.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ConstractProDAO {
	public static ContractPro getContractPro(String cid){
		
		Connection conn=null;
	    Statement st=null;
	    ResultSet rs=null;
	        try {
	            //1����ȡ���Ӷ���
	            conn=Conn.getconn();
	            //2������statement���������ִ��SQL��䣡��
	            st=conn.createStatement();
	            //3������sql��ѯ���
	            String sql="select *from \"contract_process\" where \"conNum\" = '"+cid+"'";

	            //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
	            rs=st.executeQuery(sql);
	           
	            if(rs.next()) 
	            {  //ѭ�����������
	            	ContractPro temp = new ContractPro(rs.getString("conNum"),rs.getString("userName"),rs.getInt("type"),rs.getInt("state"),rs.getString("content"),rs.getTimestamp("time"));
	            	return temp;
	            	
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
		
		return null;
	}
	//����һ����¼
	public static boolean InsertContractPro(ContractPro con) 
	{
		boolean tip = true;
		Connection conn=null;
	    Statement st=null;
	    conn=Conn.getconn();
	    try {
	        //�������
	        st=conn.createStatement();
	        //���������sql���
	        String sql="insert into \"contract_process\" values('"+con.getConNum()+"','"+con.getUserName()+"',"+con.getType()+","+con.getState()+",'"+con.getContent()+"',"+"to_timestamp('"+con.getTime()+"','yyyy-mm-dd hh24:mi:ss.ff'))";
	        //����һ�����д˲����Ľ����Ҫô�ɹ���Ҫôʧ�ܣ�������صĽ��>0���ǳɹ�����֮ʧ��
	        int result=st.executeUpdate(sql);
	        if(result>0) {
	            System.out.println("��ӳɹ�");
	        }
	        else {
	            System.out.println("���ʧ��");
	        }
	            
	    } catch (SQLException e) {
	        // TODO Auto-generated catch block
	    	tip = false;
	        e.printStackTrace();
	    }
	    return tip;
	}
	//ɾ��һ����¼(��Rightʵ��)
	public static boolean DeleteContractPro(ContractPro con) 
	{
			boolean tip = true;
			Connection conn=null;
		    Statement st=null;
		    conn=Conn.getconn();
		    try {
		        st=conn.createStatement();
		        String sql="delete from \"contract_process\" where \"conNum\" = '"+con.getConNum()+"'and \"userName\" = '"+con.getUserName()+"'";
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
	public static boolean UpdateContractPro(ContractPro con) {
		boolean tip = true;
		Connection conn=null;
	    Statement st=null;
	    conn=Conn.getconn();
	    try {
	        st=conn.createStatement();
	        String sql="update \"contract_process\" set \"state\" = "+con.getState()+","+
					"\"content\" = '"+con.getContent()+"',"+
					"\"time\" = "+"to_timestamp('"+con.getTime()+"','yyyy-mm-dd hh24:mi:ss.ff')"+
					" where \"conNum\"='"+con.getConNum()+
					"' and \"userName\" = '"+con.getUserName()+"'"+
					" and \"type\" = "+con.getType();
	        int result=st.executeUpdate(sql);
	        if(result>0)
	            System.out.println("���ĳɹ�");
	        else
	            System.out.println("����ʧ��");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        tip = false;
	    }
		return tip;
	}
	//���Ӳ�������Ҫ����aql��䣩
	public static void OPContractPro(String sql) {
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
		ContractPro conn = new ContractPro("1207181800","����",1,0,"wocao",time1);
		DeleteContractPro(conn);
	}
}
