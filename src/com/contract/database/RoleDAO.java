package com.contract.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO {
	
	public static Role getRole(String nm){
		
		Connection conn=null;
	    Statement st=null;
	    ResultSet rs=null;
	        try {
	            //1����ȡ���Ӷ���
	            conn=Conn.getconn();
	            //2������statement���������ִ��SQL��䣡��
	            st=conn.createStatement();
	            //3������sql��ѯ���
	            String sql="select *from \"role\" where \"name\" = '"+nm+"'";
	            //4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
	            rs=st.executeQuery(sql);

	            if(rs.next()) 
	            {  //ѭ�����������
	            	Role temp = new Role(rs.getString("name"),rs.getString("description"),rs.getInt("del"));
	            	return temp;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

		return null;
	}


	public static List<Role> getRoles(){

		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		List<Role> roles = new ArrayList<>();
		try {
			//1����ȡ���Ӷ���
			conn=Conn.getconn();
			//2������statement���������ִ��SQL��䣡��
			st=conn.createStatement();
			//3������sql��ѯ���
			String sql="select *from \"role\"";
			//4��ִ��sql��䲢�һ���һ����ѯ�Ľ����
			rs=st.executeQuery(sql);

			if(rs.next())
			{  //ѭ�����������
				Role temp = new Role(rs.getString("name"),rs.getString("description"),rs.getInt("del"));
				roles.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return roles;
	}
	//����һ����¼
	public static boolean InsertRole(Role con)
	{
		boolean tip = true;
		Connection conn=null;
	    Statement st=null;
	    conn=Conn.getconn();
	    try {
	        //�������
	        st=conn.createStatement();
	        //���������sql���
	        String sql="insert into \"role\" values('"+con.getName()+"','"+con.getDescription()+"',"+con.getDel()+")";
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
	public static boolean DeleteRole(Role con)
	{
		boolean tip = true;
		Connection conn=null;
		    Statement st=null;
		    conn=Conn.getconn();
		    try {
		        st=conn.createStatement();
		        String sql="delete from \"role\" where \"name\"='"+con.getName()+"'";
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
	public static boolean UpdateRole(Role con) {
		boolean tip = true;
		Connection conn=null;
	    Statement st=null;
	    conn=Conn.getconn();
	    try {
	        st=conn.createStatement();
	        String sql="update \"role\" set "+"\"name\" = '"+con.getName()+"',"+"\"description\" = '"+con.getDescription()+"',"+"\"del\" = "+con.getDel()+" where \"name\"='"+con.getName()+"'";
	        int result=st.executeUpdate(sql);
	        if(result>0)
	            System.out.println("���ĳɹ�");
	        else
	            System.out.println("����ʧ��");
	    } catch (SQLException e) {
	        tip =false;
	    	e.printStackTrace();
	    }
		return tip;
	}
	//���Ӳ�������Ҫ����aql��䣩
	public static void OPRole(String sql) {
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
	
	  public static void main(String[] args) {
	  
	  
	  Role conn = new Role("administrator","administrator",1); //getContractAtt();
	  InsertRole(conn); 
	  //getRole(); UpdateRole(conn); 
	 
}
}
