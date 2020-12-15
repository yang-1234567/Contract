package com.contract.functions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.contract.database.Conn;
import com.contract.database.ConstractProDAO;
import com.contract.database.Contract;
import com.contract.database.ContractPro;
import com.contract.database.ContractState;
import com.contract.database.ContractStateDAO;
import com.contract.database.ContractDAO;
public class Distribute {

	private List<String> conSignName= new ArrayList<>();
	private List<String> approveName= new ArrayList<>();
	private List<String> signName = new ArrayList<>();
	private String conNum;//��ͬId
	private boolean tip = true;
	public boolean getTip(){return tip;};
	public Distribute(List<String> s1,List<String> s2, List<String> s3,String cNum) 
	{
		
		
		//s1Ϊ��ǩ��Ա�б�s2Ϊ������Ա�б�s3Ϊǩ����Ա�б�
		
		//��ǩ

		for(int i = 0;i< s1.size();i++) 
  	   	{
  		    Timestamp time1 = new Timestamp(System.currentTimeMillis());
 				ContractPro conn = new ContractPro(cNum,s1.get(i),1,0,"wocao",time1);
 				if(ConstractProDAO.InsertContractPro(conn)== false)tip = false;

  	   	}
		
		//����
		
		for(int i = 0;i< s2.size();i++) 
  	   	{
  		    Timestamp time1 = new Timestamp(System.currentTimeMillis());
 				ContractPro conn = new ContractPro(cNum,s2.get(i),3,0,"wocao",time1);
 				if(ConstractProDAO.InsertContractPro(conn)== false)tip = false;

  	   	}
		
		//ǩ��
		for(int i = 0;i< s3.size();i++) 
  	   	{
  		    Timestamp time1 = new Timestamp(System.currentTimeMillis());
 				ContractPro conn = new ContractPro(cNum,s3.get(i),4,0,"wocao",time1);
 				if(ConstractProDAO.InsertContractPro(conn)== false)tip = false;

  	   	}
  	   	Timestamp time1 = new Timestamp(System.currentTimeMillis());
		Contract cont = ContractDAO.getContract(cNum);
		ContractState constate = new ContractState(1,time1,1,cont.getName(),cNum);
		ContractStateDAO.updateContractState(constate);
		
	}
	
	
	public static void main(String args[]) 
	{
		List<String> str = new ArrayList<>();
		
		List<String> str2 = new ArrayList<>();
		List<String> str3 = new ArrayList<>();
		String cn = "1211085950";
		str2.add("����");
		Distribute dis = new Distribute(str,str2,str3,cn);
		//ConstractProDAO.getContractPro();

	}
}
