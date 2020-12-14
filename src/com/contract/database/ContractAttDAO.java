package com.contract.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ContractAttDAO 
{
	 public static ContractAtt getAtt(String cid) 
	    {
	    	Connection conn=null;
			Statement st=null;
			ResultSet rs=null;
	    
			try {
				//1����ȡ���Ӷ���
				conn=Conn.getconn();
				//2������statement���������ִ��SQL��䣡��
				st=conn.createStatement();
				//3������sql��ѯ���
				String sql="select * from \"contract_attachment\" where \"num\" = '"+cid+"'";
				//4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
				rs=st.executeQuery(sql);
	           
				if(rs.next()) 
				{  //ѭ�����������
					
					ContractAtt temp = new ContractAtt(rs.getString("con_id"),rs.getString("fileName"),rs.getString("path"),rs.getInt("type"),rs.getTimestamp("uploadTime"),rs.getInt("del"));
					return temp;
				}
			} catch (Exception e) {
	            	e.printStackTrace();
			} 
			
			return null;
	    	
	    	
	    }
	

//����һ����¼
public static boolean InsertContractAtt(ContractAtt con) 
{
	boolean tip = true;
	Connection conn=null;
    Statement st=null;
    conn=Conn.getconn();
    try {
        //�������
        st=conn.createStatement();
        //���������sql���
        String sql="insert into \"contract_attachment\" values('"+con.getCon_id()+"','"+con.getFileName()+"','"+con.getPath()+"',"+con.getType()+","+"to_timestamp('"+con.getUploadTime()+"','yyyy-mm-dd hh24:mi:ss.ff')"+","+con.getDel()+")";
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
public static boolean DeleteContractAtt(ContractAtt con) 
{
	boolean tip = true;
	Connection conn=null;
	    Statement st=null;
	    conn=Conn.getconn();
	    try {
	        st=conn.createStatement();
	        String sql="delete from \"contract_attachment\" where \"con_id\"='"+con.getCon_id()+"'";
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
public static boolean UpdateContractAtt(ContractAtt con) {
	boolean tip = true;
	Connection conn=null;
    Statement st=null;
    conn=Conn.getconn();
    try {
        st=conn.createStatement();
        String sql="update \"contract_attachment\" set "+"\"con_id\" = '"+con.getCon_id()+"',"+"\"fileName\" = '"+con.getFileName()+"',"+"\"path\" = '"+con.getPath()+"',"+"\"type\" = "+con.getType()+","+"\"uploadTime\" = "+"to_timestamp('"+con.getUploadTime()+"','yyyy-mm-dd hh24:mi:ss.ff'),"+"\"del\" = "+con.getDel()+" where \"con_id\"="+con.getCon_id();
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
public static void OPContractAtt(String sql) {
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

//���Ժ���
/*
 * public static void main(String[] args) {
 * 
 * Timestamp time1 = new Timestamp(System.currentTimeMillis());
 * System.out.println(time1); ContractAtt conn = new
 * ContractAtt(2,1,"filename","path1",1,time1,0); //getContractAtt();
 * UpdateContractAtt(conn);
 * 
 * }
 */
}