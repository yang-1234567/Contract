package com.contract.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Tools {

	public static String StrSplit(String str) //��ȡ�ַ��������λ���ж��ļ���ʽ
	{
		String tips = "";
		String tmp = "";
		tmp += str.substring(str.length()-4);
		if(tmp.equals(".doc"))
			tips +="The file is .doc format.\n";
		else if(tmp.equals(".jpg"))
			tips+="The file is .jpg format.\n";	
		else if(tmp.equals("jpeg"))
			tips+="The file is jpeg format.\n";	
		else if(tmp.equals(".png"))
			tips+="The file is .png format.\n";	
		else if(tmp.equals(".bmp"))
			tips+="The file is .bmp format.\n";	
		else if(tmp.equals(".gif"))
			tips+="The file is .gif format.\n";	
		else tips +="The file is wrong format.\n";
		
		return tips;
	}
										//�ؼ��ʣ�״̬
	public static List<Contract> getDraft(String cn,int i)//��ͬ���̲�ѯ
	{
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
	        String sql="select * from \"contract_state\",\"contract\" where \"contract_state\".\"num\" = \"contract\".\"num\" and \"contract\".\"name\" like '%"+cn+"%' and \"contract_state\".\"type\" = "+i;
	        //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
	        rs=st.executeQuery(sql);
	           
	       while(rs.next()) 
	       {  //ѭ�����������
	          Contract temp = new Contract(rs.getString("num"),rs.getString("name"),rs.getString("user_id"),rs.getString("customer"),rs.getString("content"),rs.getDate("beginTime"),rs.getDate("endTime"),rs.getInt("del"));
	          cons.add(temp);
	          System.out.println("��ǰ��ȡ��"+cons.size()+"�С�");
	         
	       }
	    } catch (Exception e) {
	            e.printStackTrace();
	    } 
		
		return cons;
	}
												//�ؼ��ʣ�״̬���û���
	public static List<Contract> getUDraft(String cn,int i,String unm)//ģ����ѯ    ��ǩ��������ǩ��
	{
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
	        String sql="select * from \"contract_state\",\"contract\",\"contract_process\" where \"contract_state\".\"num\" = \"contract\".\"num\" and \"contract_state\".\"num\"=\"contract_process\".\"conNum\" and \"contract\".\"name\" like '%"+cn+"%' and \"contract_state\".\"type\" = "+i;
	        //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
	        rs=st.executeQuery(sql);
	           
	       while(rs.next()) 
	       {  //ѭ�����������
	          Contract temp = new Contract(rs.getString("num"),rs.getString("name"),rs.getString("user_id"),rs.getString("customer"),rs.getString("content"),rs.getDate("beginTime"),rs.getDate("endTime"),rs.getInt("del"));
	          cons.add(temp);
	          System.out.println("��ǰ��ȡ��"+cons.size()+"�С�");
	         
	       }
	    } catch (Exception e) {
	            e.printStackTrace();
	    } 
		
		return cons;
		
	}
		
	public static List<String> getDistributor(String Fnum) //�������ܱ�ţ����ض�Ӧ��ɫ�Ķ�Ӧ�û�
	{
		
		List<String> cons = new ArrayList<>();
		Connection conn=null;
	    Statement st=null;
	    ResultSet rs=null;
	    
	    try {
	    	//1����ȡ���Ӷ���
	    	conn=Conn.getconn();
	    	//2������statement���������ִ��SQL��䣡��
	        st=conn.createStatement();
	        //3������sql��ѯ���
	        
	        String sql="select \"useName\" from \"right\" where \"rolName\" in(select \"name\" from \"role_function\" where \"num\" = '"+Fnum+"')";
	        //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
	        rs=st.executeQuery(sql);
	           
	       while(rs.next()) 
	       {  //ѭ�����������
	          String temp = rs.getString("useName");
	          cons.add(temp);
	          System.out.println("��ǰ��ȡ��"+cons.size()+"�С�");
	         
	       }
	    } catch (Exception e) {
	            e.printStackTrace();
	            
	    } 
		
		return cons;
		
	}
	
	public static List<Contract> getApproved(String key,String unum)//�����ؼ��֣��û��������ش�����
	{
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
	        String sql="select * from \"contract\",\"contract_process\",\"contract_state\" where \"contract_state\".\"type\" = 2" + 
	        		"and \"contract\".\"num\" = \"contract_process\".\"conNum\" and \"contract\".\"num\" = \"contract_state\".\"num\"" + 
	        		"and \"contract\".\"name\" like '%"+key+"%'" +" and \"contract_process\".\"userName\" = '"+unum+"'";
	        //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
	        rs=st.executeQuery(sql);
	           
	       while(rs.next()) 
	       {  //ѭ�����������
	          Contract temp = new Contract(rs.getString("num"),rs.getString("name"),rs.getString("user_id"),rs.getString("customer"),rs.getString("content"),rs.getDate("beginTime"),rs.getDate("endTime"),rs.getInt("del"));
	          cons.add(temp);
	          System.out.println("��ǰ��ȡ��"+cons.size()+"�С�");
	         
	       }
	    } catch (Exception e) {
	            e.printStackTrace();
	    } 
		
		return cons;
	}
	
    public static List<ContractPro> getConsign(String key)//���ݹؼ��ַ��ش���ǩ�ĺ�ͬ״̬
    {
    	List<ContractPro> cons = new ArrayList<>();
		Connection conn=null;
	    Statement st=null;
	    ResultSet rs=null;
	    
	    try {
	    	//1����ȡ���Ӷ���
	    	conn=Conn.getconn();
	    	//2������statement���������ִ��SQL��䣡��
	        st=conn.createStatement();
	        //3������sql��ѯ���
	        String sql="select * from \"contract_process\" where \"conNum\" like '%"+key+"%' and \"type\" = 1";
	        //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
	        rs=st.executeQuery(sql);
	           
	       while(rs.next()) 
	       {  //ѭ�����������
	          ContractPro temp = new ContractPro(rs.getString("conNum"),rs.getString("userName"),rs.getInt("type"),rs.getInt("state"),rs.getString("content"),rs.getTimestamp("time"));
	          cons.add(temp);
	          System.out.println("��ǰ��ȡ��"+cons.size()+"�С�");
	         
	       }
	    } catch (Exception e) {
	            e.printStackTrace();
	    } 
		
		return cons;
    }	
	
    public static String getContent(String cid,String uid) //���ݺ�ͬ�ؼ��֣��û���ţ��������ͬ����
    {
    	String content = "";
    	Connection conn=null;
	    Statement st=null;
	    ResultSet rs=null;
	    
	    try {
	    	//1����ȡ���Ӷ���
	    	conn=Conn.getconn();
	    	//2������statement���������ִ��SQL��䣡��
	        st=conn.createStatement();
	        //3������sql��ѯ���
	        String sql="select \"content\" from \"contract_process\" where \"conNum\" = '"+cid+"' and \"userName\" = '"+uid+"'";
	        //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
	        rs=st.executeQuery(sql);
	           
	       if(rs.next()) 
	       {  //ѭ�����������
	          content +=rs.getString("content");
	         
	       }
	    } catch (Exception e) {
	            e.printStackTrace();
	    } 
    	return content;
    }
    
    public static List<Contract> getState1(String cid,String uid)  //���ݺ�ͬ�ؼ��֣��û���ţ��������ͬ�б�
    {
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
	        String sql="select * from \"contract\" where \"num\" like '%"+cid+"%' and  \"user_id\" = '"+uid+"'";
	        //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
	        rs=st.executeQuery(sql);
	           
	       while(rs.next()) 
	       {  //ѭ�����������
	          Contract temp = new Contract(rs.getString("num"),rs.getString("name"),rs.getString("user_id"),rs.getString("customer"),rs.getString("content"),rs.getDate("beginTime"),rs.getDate("endTime"),rs.getInt("del"));
	          cons.add(temp);
	          System.out.println("��ǰ��ȡ��"+cons.size()+"�С�");
	         
	       }
	    } catch (Exception e) {
	            e.printStackTrace();
	    } 
		
		return cons;
    	
    	
    }
    
    public static int getState2(String cid) //���ݺ�ͬ��ţ�������״̬

    {
    	int id = 0;
    	Connection conn=null;
	    Statement st=null;
	    ResultSet rs=null;
	    
	    try {
	    	//1����ȡ���Ӷ���
	    	conn=Conn.getconn();
	    	//2������statement���������ִ��SQL��䣡��
	        st=conn.createStatement();
	        //3������sql��ѯ���
	        String sql="select \"type\" from \"contract_state\" where \"num\" = '"+cid+"'"; 
	        //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
	        rs=st.executeQuery(sql);
	           
	       if(rs.next()) 
	       {  //ѭ�����������
	          id +=rs.getInt("type");
	         
	       }
	    } catch (Exception e) {
	            e.printStackTrace();
	    } 
    	
    	return id;
    }
   
    public static List<Contract> getContracts(String cid)//���ݹؼ��ַ��غ�ͬ�б�
    {	
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
			String sql="select * from \"contract\" where \"num\" like '%"+cid+"%'";
			//4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
			rs=st.executeQuery(sql);
           
			while(rs.next()) 
			{  //ѭ�����������
				Contract temp = new Contract(rs.getString("num"),rs.getString("name"),rs.getString("user_id"),rs.getString("customer"),rs.getString("content"),rs.getDate("beginTime"),rs.getDate("endTime"),rs.getInt("del"));
				cons.add(temp);
				System.out.println("��ǰ��ȡ��"+cons.size()+"�С�");
         
			}
		} catch (Exception e) {
            	e.printStackTrace();
		} 
	
		return cons;
	}
	
    public static List<Customer> getCustomers(String cid)//���ݹؼ��ַ��ؿͻ��б�
    {
    	List<Customer> cons = new ArrayList<>();
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
    
		try {
			//1����ȡ���Ӷ���
			conn=Conn.getconn();
			//2������statement���������ִ��SQL��䣡��
			st=conn.createStatement();
			//3������sql��ѯ���
			String sql="select * from \"customer\" where \"num\" like '%"+cid+"%'";
			//4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
			rs=st.executeQuery(sql);
           
			while(rs.next()) 
			{  //ѭ�����������
				Customer temp = new Customer(rs.getString("num"),rs.getString("name"),rs.getString("address"),rs.getString("tel"),rs.getString("fax"),rs.getString("code"),rs.getString("bank"),rs.getString("account"),rs.getInt("del"));
				cons.add(temp);
				System.out.println("��ǰ��ȡ��"+cons.size()+"�С�");
         
			}
		} catch (Exception e) {
            	e.printStackTrace();
		} 
	
		return cons;
    	
    	
    }
    
    public static Contract getOneCon(String cid) //���ݺ�ͬ��ŷ���ĳһ��ͬ
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
			String sql="select * from \"contract\" where \"num\" = '"+cid+"'";
			//4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
			rs=st.executeQuery(sql);
           
			if(rs.next()) 
			{  //ѭ�����������
				Contract temp = new Contract(rs.getString("num"),rs.getString("name"),rs.getString("user_id"),rs.getString("customer"),rs.getString("content"),rs.getDate("beginTime"),rs.getDate("endTime"),rs.getInt("del"));
				return temp;
			}
		} catch (Exception e) {
            	e.printStackTrace();
		} 
		
		return null;
		
    	
    }
    
    public static String getPassword(String uid) //�����û�id ��������
    {
    	String content = "";
    	Connection conn=null;
	    Statement st=null;
	    ResultSet rs=null;
	    
	    try {
	    	//1����ȡ���Ӷ���
	    	conn=Conn.getconn();
	    	//2������statement���������ִ��SQL��䣡��
	        st=conn.createStatement();
	        //3������sql��ѯ���
	        String sql="select \"password\" from \"user\" where \"name\" = '"+uid+"'";
	        //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
	        rs=st.executeQuery(sql);
	           
	       if(rs.next()) 
	       {  //ѭ�����������
	          content +=rs.getString("password");
	         
	       }
	    } catch (Exception e) {
	            e.printStackTrace();
	    } 
    	return content;
    	
    	
    }
    
    public static String getURole(String uid) //�����û�id���������ɫ
    {
    	String content = "";
    	Connection conn=null;
	    Statement st=null;
	    ResultSet rs=null;
	    
	    try {
	    	//1����ȡ���Ӷ���
	    	conn=Conn.getconn();
	    	//2������statement���������ִ��SQL��䣡��
	        st=conn.createStatement();
	        //3������sql��ѯ���
	        String sql="select \"rolName\" from \"right\" where \"useName\" = '"+uid+"'";
	        //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
	        rs=st.executeQuery(sql);
	           
	       if(rs.next()) 
	       {  //ѭ�����������
	          content +=rs.getString("rolName");
	         
	       }
	    } catch (Exception e) {
	            e.printStackTrace();
	    } 
    	return content;
    	
    }
    
    public static boolean changeUser(String uid,String ukey,String rName)//���ݸ������û�id�����룬��ɫ������������
    {
    	boolean tip = true;
    	
    	Connection conn=null;
	    Statement st=null;
	    ResultSet rs=null;
	    
	    try {
	    	//1����ȡ���Ӷ���
	    	conn=Conn.getconn();
	    	//2������statement���������ִ��SQL��䣡��
	        st=conn.createStatement();
	        //3������sql��ѯ���
	        String sql="update \"user\" set\"password\" = '"+ukey+"'  where \"name\" = '"+uid+"'";
	        //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
	        rs=st.executeQuery(sql);
	        String sql2="update \"right\" set\"rolName\" = '"+rName+"'  where \"useName\" = '"+uid+"'";
	        rs=st.executeQuery(sql2);
	    } catch (Exception e) {
	    	tip  = false;
	            e.printStackTrace();
	    } 
    	
    	
    	return tip;
    }
    
    public static List<String> getRFunction(String rName)//���ݽ�ɫ��������Ȩ��
    {
    	List<String> cons = new ArrayList<>();
		Connection conn=null;
	    Statement st=null;
	    ResultSet rs=null;
	    
	    try {
	    	//1����ȡ���Ӷ���
	    	conn=Conn.getconn();
	    	//2������statement���������ִ��SQL��䣡��
	        st=conn.createStatement();
	        //3������sql��ѯ���
	        
	        String sql="select \"num\" from \"role_function\"  where \"name\" = '"+rName+"'";
	        //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
	        rs=st.executeQuery(sql);
	           
	       while(rs.next()) 
	       {  //ѭ�����������
	          String temp = rs.getString("num");
	          cons.add(temp);
	          System.out.println("��ǰ��ȡ��"+cons.size()+"�С�");
	         
	       }
	    } catch (Exception e) {
	            e.printStackTrace();
	            
	    } 
		
		return cons;
    	
    	
    	
    }
    
    public static boolean changeRFunction(String rName,List<String> fn) //���Ľ�ɫȨ��
    {

    	boolean tip = true;
    	
    	Connection conn=null;
	    Statement st=null;
	    ResultSet rs=null;
	    
	    try {
	    	//1����ȡ���Ӷ���
	    	conn=Conn.getconn();
	    	//2������statement���������ִ��SQL��䣡��
	        st=conn.createStatement();
	        //3������sql��ѯ���
	        String sql="delete from \"role_function\" where \"name\" = '"+rName+"'";
	        //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
	        rs=st.executeQuery(sql);
	        
	        for(int i=0;i<fn.size();i++) 
	        {
	        	String sql2 = "insert into \"role_function\" values('"+rName+"','"+fn.get(i)+"')";
	        	 rs=st.executeQuery(sql2);
	        }
	        
	        
	    } catch (Exception e) {
	    	tip  = false;
	            e.printStackTrace();
	    } 
    	
    	
    	return tip;
    }
    
    public static List<User> getFUser(String fn) //���ݸ����Ĺ��ܱ���ҵ���Ӧ�������û�ʵ��
    {
    	List<User> cons = new ArrayList<>();
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
    
		try {
			//1����ȡ���Ӷ���
			conn=Conn.getconn();
			//2������statement���������ִ��SQL��䣡��
			st=conn.createStatement();
			//3������sql��ѯ���
			String sql="select * from \"user\" where \"name\" in (select \"useName\" from \"right\" where \"rolName\" in  (select \"name\" from \"role_function\" where \"num\" = '"+fn+"'))";
			//4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
			rs=st.executeQuery(sql);
           
			while(rs.next()) 
			{  //ѭ�����������
				
				User temp = new User(rs.getString("name"),rs.getString("password"),rs.getInt("del"));
				cons.add(temp);
				System.out.println("��ǰ��ȡ��"+cons.size()+"�С�");
         
			}
		} catch (Exception e) {
            	e.printStackTrace();
		} 
	
		return cons;
    	
    	
    }
    
    public static boolean Login(String unm,String upw) //��¼
    {
    	boolean tip = true;
    	
    	Connection conn=null;
	    Statement st=null;
	    ResultSet rs=null;
	    
	    try {
	    	//1����ȡ���Ӷ���
	    	conn=Conn.getconn();
	    	//2������statement���������ִ��SQL��䣡��
	        st=conn.createStatement();
	        //3������sql��ѯ���
	        String sql="select * from \"user\" where \"password\" = '"+upw+"' and \"name\" = '"+unm+"'";
	        //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
	        rs=st.executeQuery(sql);
	       if(rs.next()) {tip=true; System.out.println("��½�ɹ�");}
	       else tip = false;
	        
	        
	    } catch (Exception e) {
	    	tip  = false;
	            e.printStackTrace();
	    } 
    	
    	return tip;
    }
    
    public static boolean Register(String unm,String upw) //ע��
    {
    	boolean tip = true;
    	
    	User user = new User(unm,upw,1);
    	tip = UserDAO.InsertUser(user);
    	return tip;
    	
    }
    
    public static List<String> getUFunction(String uid)//ͨ���û�id������Ȩ��
    {
    	List<String> cons = new ArrayList<>();
		Connection conn=null;
	    Statement st=null;
	    ResultSet rs=null;
	    
	    try {
	    	//1����ȡ���Ӷ���
	    	conn=Conn.getconn();
	    	//2������statement���������ִ��SQL��䣡��
	        st=conn.createStatement();
	        //3������sql��ѯ���
	        
	        String sql="select \"num\" from \"role_function\"  where \"name\" in (select \"rolName\" from \"right\" where \"useName\" = '"+uid+"')";
	        //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
	        rs=st.executeQuery(sql);
	           
	       while(rs.next()) 
	       {  //ѭ�����������
	          String temp = rs.getString("num");
	          cons.add(temp);
	          System.out.println("��ǰ��ȡ��"+cons.size()+"�С�");
	         
	       }
	    } catch (Exception e) {
	            e.printStackTrace();
	            
	    } 
		
		return cons;
    	
    	
    }
   
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
       
    
    public static void main(String args[]) {
		
		/*
		 * List<String> str = getDistributor("1"); int i = 0;
		 * while(i<str.size())System.out.println(str.get(i++));
		 */
		//getApproved("","����");
		//getConsign("18");
		//getContent("","");
		//Contract con = getOneCon("1207181800");
		//System.out.println(con.getName());
    	//System.out.println(getPassword("����"));
    	//changeUser("����","000000","operator");
    	//getRFunction("operator");
    	//getFUser("1");
		/*
		 * List<String> str = new ArrayList<>(); str.add("0"); str.add("1");
		 * str.add("2"); str.add("3"); str.add("4"); str.add("6");
		 * changeRFunction("operator",str);
		 */
    	//Register("����","123456");
    	
	}
	
	
	
}


