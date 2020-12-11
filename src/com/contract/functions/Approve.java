package com.contract.functions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import com.contract.database.Conn;
import com.contract.database.ConstractProDAO;
import com.contract.database.Contract;
import com.contract.database.ContractPro;
import com.contract.database.ContractState;
import com.contract.database.ContractStateDAO;
import com.contract.database.Role;

public class Approve {
	
	private String conNum; //�����ĺ�ͬ���
	private String user_id;  //�û����
	private String content;  //����
	private boolean tip = true;//��ʾ,trueΪ��ǩ�ɹ���falseʧ��
	
	public Approve(String cid,String uid,String content,int key) 
	{
		 conNum = cid;
		  user_id=uid;
		  this.content=content;
		  
		if(key == 0) 
		{
			
			Connection conn=null;
		    Statement st=null;
		    ResultSet rs=null;
		    Statement st2=null;
		    ResultSet rs2=null;
		        try {
		            //1����ȡ���Ӷ���
		            conn=Conn.getconn();
		            //2������statement���������ִ��SQL��䣡��
		            st=conn.createStatement();
		            st2=conn.createStatement();
		            //3������sql��ѯ���
		            String sql="update \"contract_process\" set \"state\" = 2 where \"conNum\" = '"+cid + "' and \"userName\" = '"+ uid+"'";
		            String sql2="update \"contract_state\" set \"type\" = 6 where \"num\" = '"+cid+"'";
		            //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
		            rs=st.executeQuery(sql);
		            rs2=st2.executeQuery(sql2);
		            
		        } catch (Exception e) {
		            e.printStackTrace();
		        } 
			
			
		}
		else 
		{
			Timestamp day = new Timestamp(new Date().getTime());
			ContractPro conn = new ContractPro(cid,uid,3,1,content,day);
		  	
		  	if(ConstractProDAO.UpdateContractPro(conn)==false)tip = false;
			Connection conn2=null;
		    Statement st=null;	
		    Statement st2=null;
		  
		    ResultSet rs=null;
		    ResultSet rs2=null;
		    
		    try 
		    {
		    	//1����ȡ���Ӷ���
		    	conn2=Conn.getconn();
		    	//2������statement���������ִ��SQL��䣡��
		        st=conn2.createStatement();
		        st2=conn2.createStatement();
		        
		        //3������sql��ѯ���
		        String sql="select * from \"contract\" where \"num\" = '"+cid+"'";
		        //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
		        rs=st.executeQuery(sql);
		        String sql2 = "select * from \"contract_process\" where \"conNum\" = '"+cid+"' and \"type\" = 3 and \"state\" = 0";
		        rs2=st2.executeQuery(sql2);
		       if(rs.next()&&rs2.next()==false) 
		       {  //ѭ�����������
		    	  
		          Contract temp = new Contract(rs.getString("num"),rs.getString("name"),rs.getString("user_id"),rs.getString("customer"),rs.getString("content"),rs.getDate("beginTime"),rs.getDate("endTime"),rs.getInt("del"));
		          
		          ContractState cons2 = new ContractState(4,day,1,temp.getName(),cid);
		          if(ContractStateDAO.UpdateContractState(cons2)== false)tip = false;
				
		       }
		      
		    }  catch (SQLException e) {
				// TODO Auto-generated catch block
		    	tip =false;
				e.printStackTrace();
			} 
		}
		
		
		
	}

	public String getConNum() {
		return conNum;
	}

	public void setConNum(String conNum) {
		this.conNum = conNum;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isTip() {
		return tip;
	}

	public void setTip(boolean tip) {
		this.tip = tip;
	}

	public static void main(String args[]) {
		
		Approve approve = new Approve("1211085950","����","no",0);
		
		
	}
	
}
