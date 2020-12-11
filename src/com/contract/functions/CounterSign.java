package com.contract.functions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.contract.database.*;

public class CounterSign 
//tips��
//����tools�����ConSign����ѯ������Ӧ�ֵ����д���ǩ��ͬ��
//Ȼ���ٵ��ȷ������������࣬�Ѻ�ͬ��ţ��û���źͻ�ǩ���ݴ�������

{

	private String conNum; //�����ĺ�ͬ���
	private String user_id;  //�û����
	private String content;  //��ǩ����
	private boolean tip = true;//��ʾ,trueΪ��ǩ�ɹ���falseʧ��
	
	
  public CounterSign(String cn,String uid,String con) 
  {
	  
	  conNum = cn;
	  user_id=uid;
	  content=con;
	  
	  Timestamp day = new Timestamp(new Date().getTime());
	  ContractPro conn = new ContractPro(cn,uid,1,1,con,day);
	  	
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
	        String sql2 = "select * from \"contract_process\" where \"conNum\" = '"+cn+"' and \"type\" = 1 and \"state\" = 0";
	        rs2=st2.executeQuery(sql2);
	       if(rs.next()&&rs2.next()==false) 
	       {  //ѭ�����������
	    	  
	          Contract temp = new Contract(rs.getString("num"),rs.getString("name"),rs.getString("user_id"),rs.getString("customer"),rs.getString("content"),rs.getDate("beginTime"),rs.getDate("endTime"),rs.getInt("del"));
	          
	          ContractState cons2 = new ContractState(2,day,1,temp.getName(),cn);
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
	
  public static void main(String args[]) 
  {
	  
	  CounterSign sn = new CounterSign("1207181800","����","meiyou");
	  System.out.println(sn.getTip());
	  
	  
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

public boolean getTip() {
	return tip;
}

public void setTip(boolean tip) {
	this.tip = tip;
}
}
