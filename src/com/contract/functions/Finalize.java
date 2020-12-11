package com.contract.functions;

import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.contract.database.Contract;
import com.contract.database.ContractAtt;
import com.contract.database.ContractAttDAO;
import com.contract.database.ContractDAO;
import com.contract.database.ContractState;
import com.contract.database.ContractStateDAO;

public class Finalize {

	private String name;	
	private String content;
	private Date beginTime;
	private Date endTime;
	
	
	
	private String num;
	private String userId;
	private String customer;
	
	boolean tip = true;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public boolean isTip() {
		return tip;
	}
	public void setTip(boolean tip) {
		this.tip = tip;
	}
	public Finalize(String cn,String nm,String cus,String uid,String con,String beg,String end,String file) 
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
															
		format.setLenient(false);
		
		try 
		{
			java.util.Date dayDateUtil =  format.parse(beg);
			java.util.Date dayDateUtil2 =  format.parse(end);
			Date date1 = new Date(dayDateUtil.getTime());
			Date date2 = new Date(dayDateUtil2.getTime());
			System.out.println(date1.toString());
			System.out.println(date2.toString());
			
			//����Ŀ¼
			String str1 = "attachment";
			File myFolderPath = new File(str1);   
			  try
			  {   
			      if (!myFolderPath.exists()) 
			      {   
			         myFolderPath.mkdir();   
			     }   
			  }   
			  catch (Exception e)
			  {   
			    System.out.println("�½�Ŀ¼��������");   
			     e.printStackTrace();   
			  } 
			
			name = nm;
			content = con;
			beginTime = date1;
			endTime = date2;
			java.util.Date day = new java.util.Date();
			num = cn;
			customer = cus;
			userId = uid;
			
						
			Contract contract = new
			Contract(num,name,userId,customer,content,beginTime,endTime,1);
			
			if(ContractDAO.UpdateContract(contract)==false)tip = false; 
			 
			
			Timestamp tmp = new Timestamp(day.getTime());
			ContractState conState = new ContractState(3,tmp,1,name,num);
			if(ContractStateDAO.UpdateContractState(conState)==false)tip = false;
			
			ContractAtt conAtt = new ContractAtt(num,file,str1,3,tmp,1);
			if(ContractAttDAO.UpdateContractAtt(conAtt)==false)tip = false;
			
			
		} catch (Exception  e) 
		{
			tip=false;
			e.printStackTrace();
		}
		
		
		
		
	}
	public static void main(String args[]) 
	{
		Finalize fin = new Finalize("1207181800","newName","niubi","1","2","2000-10-10","2020-10-10","nmsl");
	}
}
