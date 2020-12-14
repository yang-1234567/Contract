package com.contract.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ContractStateDAO {
	public static ContractState getContractState(String cid){
		
		Connection conn=null;
	    Statement st=null;
	    ResultSet rs=null;
	        try {
	            //1����ȡ���Ӷ���
	            conn=Conn.getconn();
	            //2������statement���������ִ��SQL��䣡��
	            st=conn.createStatement();
	            //3������sql��ѯ���
	            String sql="select *from \"contract_state\" where \"num\" = '"+cid+"'";
	            //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
	            rs=st.executeQuery(sql);
	           
	            if(rs.next()) 
	            {  //ѭ�����������
	            	ContractState temp = new ContractState(rs.getInt("type"),rs.getTimestamp("time"),rs.getInt("del"),rs.getString("name"),rs.getString("num"));
	            	return temp;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
		
		return null;
	}
	//����һ����¼
	public static boolean InsertContractState(ContractState con) 
	{
		boolean tip = true;
		Connection conn=null;
	    Statement st=null;
	    conn=Conn.getconn();
	    try {
	        //�������
	        st=conn.createStatement();
	        //���������sql���
	        String sql="insert into \"contract_state\" values('"+con.getNum()+"','"+con.getName()+"',"+con.getType()+","+"to_timestamp('"+con.getTime()+"','yyyy-mm-dd hh24:mi:ss.ff')"+","+con.getDel()+")";
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
	public static boolean DeleteContractState(ContractState con) 
	{
			boolean tip = true;
		 	Connection conn=null;
		    Statement st=null;
		    conn=Conn.getconn();
		    try {
		        st=conn.createStatement();
		        String sql="delete from \"contract_state\" where \"num\"="+con.getNum();
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
	public static boolean UpdateContractState(ContractState con) {
		boolean tip = true;
		Connection conn=null;
	    Statement st=null;
	    conn=Conn.getconn();
	    try {
	        st=conn.createStatement();
	        String sql="update \"contract_state\" set "+"\"type\" = "+con.getType()+","+"\"num\" = '"+con.getNum()+"',"+"\"name\" = '"+con.getName()+"',"+"\"time\" = "+"to_timestamp('"+con.getTime()+"','yyyy-mm-dd hh24:mi:ss.ff'),"+"\"del\" = "+con.getDel()+" where \"num\"='"+con.getNum()+"'";
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
	
	//���Ӳ�������Ҫ����sql��䣩
	public static void OPContractState(String sql) {
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
	 * Timestamp time1 = new Timestamp(System.currentTimeMillis());
	 * System.out.println(time1); ContractState conn = new
	 * ContractState(2,7,time1,0); //getContractAtt(); getContractState();
	 * 
	 * }
	 */
	
}
