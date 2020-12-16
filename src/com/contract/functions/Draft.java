package com.contract.functions;
import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import com.contract.database.*;
public class Draft {
	private boolean tip = true;
	private String name;	
	private String content;
	private Date beginTime;
	private Date endTime;
	
	
	
	private String num;
	private String user_id;
	private String customer;
	
	public Draft(String nm,String con,String cus,String beg,String end,String uid,String file) //���ļ�������
	{
		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("MMddHHmmss");//�ж����ڸ�ʽ
		format.setLenient(false);
		
		try 
		{
			java.util.Date dayDateUtil =  format.parse(beg);
			java.util.Date dayDateUtil2 =  format.parse(end);
			Date date1 = new Date(dayDateUtil.getTime());
			Date date2 = new Date(dayDateUtil2.getTime());
			
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
			num = format2.format(day);
			customer = cus;
			user_id = uid;
			
						
			Contract contract = new
			Contract(num,name,user_id,customer,content,beginTime,endTime,1);
			
			if(ContractDAO.InsertContract(contract)==false)tip = false; 
			 
			
			Timestamp tmp = new Timestamp(day.getTime());
			ContractState conState = new ContractState(0,tmp,1,name,num);
			if(ContractStateDAO.InsertContractState(conState)==false)tip = false;
			
			
						
			ContractAtt conAtt = new ContractAtt(num,file,str1,1,tmp,1);
			if(ContractAttDAO.InsertContractAtt(conAtt)==false)tip = false;
			
			
		} catch (Exception  e) 
		{
			tip=false;
			e.printStackTrace();
		}
		
		
		
	}
	
	public Draft(String nm,String con,String cus,String beg,String end,String uid)//���ļ�������
	{
		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("MMddHHmmss");//�ж����ڸ�ʽ
		format.setLenient(false);
		try 
		{
			
			java.util.Date dayDateUtil =  format.parse(beg);
			java.util.Date dayDateUtil2 =  format.parse(end);
			Date date1 = new Date(dayDateUtil.getTime());
			Date date2 = new Date(dayDateUtil2.getTime());
			System.out.println(date1.toString());
			System.out.println(date2.toString());
			
			name = nm;
			content = con;
			beginTime = date1;
			endTime = date2;
			java.util.Date day = new java.util.Date();
			
			num = format2.format(day);
			customer = cus;
			user_id = uid;
			
			
			Contract contract = new
			Contract(num,name,user_id,customer,content,beginTime,endTime,1);
			if(ContractDAO.InsertContract(contract)==false)tip = false; 
			 
			
			Timestamp tmp = new Timestamp(day.getTime());
			ContractState conState = new ContractState(0,tmp,1,name,num);
			if(ContractStateDAO.InsertContractState(conState)==false)tip = false;
			
			
			
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			tip =false;
			System.out.println(tip);
			e.printStackTrace();
			
		}
		
		
		
	}
	
	public boolean getTip() {
		return tip;
	}

	public void setTip(boolean tip) {
		this.tip = tip;
	}

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

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public static void main(String args[])
	{
		
		Draft dra = new Draft("name","con","niubi","2000-10-10","2020-10-10","1","nmsl");
		
	}
	
}
