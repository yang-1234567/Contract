package com.contract.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContractDAO {
//select����������contract��List
	public static List<Contract> getContract(){
		List<Contract> cons = new ArrayList<>();
		Connection conn=null;
	    Statement st=null;
	    ResultSet rs=null;
	        try {
	            //1����ȡ���Ӷ���
	            conn=Conn.getconn();
	            //2������statement���������ִ��SQL��䣡��
	            st=conn.createStatement();
	            //3������sql��ѯ���
	            String sql="select *from \"contract\"";
	            //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
	            rs=st.executeQuery(sql);
	           
	            while(rs.next()) 
	            {  //ѭ�����������
	            	Contract temp = new Contract(rs.getString("num"),rs.getString("name"),rs.getString("user_id"),rs.getString("customer"),rs.getString("content"),rs.getDate("beginTime"),rs.getDate("endTime"),rs.getInt("del"));
	            	cons.add(temp);
	            	System.out.println("��ǰ��ȡ��"+cons.size()+"�С�");
	            	System.out.println(temp.toString());
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
		
		return cons;
		
		
	}
	//����һ������
	public static boolean InsertContract(Contract con) 
	{
		boolean tip = true;
		Connection conn=null;
	    Statement st=null;
	    conn=Conn.getconn();
	    try {
	        //�������
	        st=conn.createStatement();
	        //���������sql���
	        String sql="insert into \"contract\" values('"+con.getNum()+"','"+con.getName()+"','"+con.getUser_id()+"','"+con.getCustomer()+"','"+con.getContent()+"',"+"to_date('"+con.getBeginTime()+"','yyyy-mm-dd hh24:mi:ss'),"+"to_date('"+con.getEndTime()+"','yyyy-mm-dd hh24:mi:ss')"+","+con.getDel()+")";
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
	//ɾ��������id��
	public static boolean DeleteContract(Contract con) 
	{
			boolean tip = true;
			Connection conn=null;
		    Statement st=null;
		    Statement st2=null;
		    conn=Conn.getconn();
		    try {
		        st=conn.createStatement();
		        st2=conn.createStatement();
		        String sql="delete from \"contract\" where \"num\"='"+con.getNum()+"'";
		        String sql2="delete from \"contract_attachment\" where \"con_id\"='"+con.getNum()+"'";
		        int result=st.executeUpdate(sql);
		        int resul2=st2.executeUpdate(sql2);
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
	
	public static boolean UpdateContract(Contract con) {
		boolean tip = true;
		Connection conn=null;
	    Statement st=null;
	    conn=Conn.getconn();
	    try {
	        st=conn.createStatement();
	        String sql="update \"contract\" set "+"\"num\" = '"+con.getNum()+"',"+"\"name\" = '"+con.getName()+"',"+"\"user_id\" = '"+con.getUser_id()+"',"+"\"customer\" = '"+con.getCustomer()+"',"+"\"content\" = '"+con.getContent()+"',"+"\"beginTime\" = "+"to_date('"+con.getBeginTime()+"','yyyy-mm-dd hh24:mi:ss'),"+"\"endTime\" = "+"to_date('"+con.getEndTime()+"','yyyy-mm-dd hh24:mi:ss'),"+"\"del\" = "+con.getDel()+" where \"num\"="+con.getNum();
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
	//���Ӳ�����������sql���
	public static void OPContract(String sql) {
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
	//����
	
	  public static void main(String[] args) {
	  
	  Date time1 = new Date(System.currentTimeMillis()); System.out.println(time1);
	  Contract conn = new
	  Contract("6","name1","2","customer","content",time1,time1,0);
	  InsertContract(conn);
	  
	  }
	 
}
