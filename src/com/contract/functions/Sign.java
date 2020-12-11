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

public class Sign {

	private String conNum; //�����ĺ�ͬ���
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


	private String user_id;  //�û����
	private String content;  //����
	private boolean tip = true;//��ʾ,trueΪ�ɹ���falseʧ��
	
	
  public Sign(String cn,String uid,String con) 
  {
	  
	  conNum = cn;
	  user_id=uid;
	  content=con;
	  
	  Timestamp day = new Timestamp(new Date().getTime());
	  ContractPro conn = new ContractPro(cn,uid,4,1,con,day);
	  	
	  	if(ConstractProDAO.UpdateContractPro(conn)==false)tip = false;
		Connection conn2=null;
	    Statement st=null;	
	    Statement st2=null;
	    Statement st3=null;
	    ResultSet rs=null;
	    ResultSet rs2=null;
	    ResultSet rs3=null;
	    try 
	    {
	    	//1����ȡ���Ӷ���
	    	conn2=Conn.getconn();
	    	//2������statement���������ִ��SQL��䣡��
	        st=conn2.createStatement();
	        st2=conn2.createStatement();
	        
	        //3������sql��ѯ���
	        String sql="select * from \"contract\" where \"num\" = '"+cn+"'";
	        //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
	        rs=st.executeQuery(sql);
	        String sql2 = "select * from \"contract_process\" where \"conNum\" = '"+cn+"' and \"type\" = 4 and \"state\" = 0";
	        rs2=st2.executeQuery(sql2);
	       if(rs.next()&&rs2.next()==false) 
	       {  //ѭ�����������
	    	  
	          Contract temp = new Contract(rs.getString("num"),rs.getString("name"),rs.getString("user_id"),rs.getString("customer"),rs.getString("content"),rs.getDate("beginTime"),rs.getDate("endTime"),rs.getInt("del"));
	          
	          ContractState cons2 = new ContractState(5,day,1,temp.getName(),cn);
	          if(ContractStateDAO.UpdateContractState(cons2)== false)tip = false;
				/*
				 * String
				 * sql3="update \"contract_process\" set \"type\" = 2 , \"state\" = 0  where \"conNum\" = '"
				 * +cn+"' and \"type\" = 1 and \"state\" = 1" ; st3=conn2.createStatement();
				 * rs3=st3.executeQuery(sql3);
				 */
	          
	       }
	      
	    }  catch (SQLException e) {
			// TODO Auto-generated catch block
	    	tip =false;
			e.printStackTrace();
		} 
  }
  
}

