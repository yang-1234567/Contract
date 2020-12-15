package com.contract.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
/**
 * ���ݿ�Ĺ����࣬����һ�·���
 * @author 76557
 *1.getconnection�������
 *2.begin��ʼ����
 *3.commit�ύ����
 *4.roolback�ع�
 *5.closeall�ͷ���Դ
 */
public class DBUtils {
	private static final Properties PROPERTIES = new Properties();
	private static final ThreadLocal<Connection> THREAD_LOCAL = new ThreadLocal<Connection>();
	
	static {
		//���������ļ�
		InputStream iStream = DBUtils.class.getResourceAsStream("/db.properties");
		
		try {
			//�������
			PROPERTIES.load(iStream);
			//��������
			Class.forName(PROPERTIES.getProperty("driver"));
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//�������
	public static Connection getConnection() {
		Connection connection = THREAD_LOCAL.get();
		
		try {
			
			if(connection == null) {
				connection = DriverManager.getConnection(PROPERTIES.getProperty("url"),PROPERTIES.getProperty("username"),PROPERTIES.getProperty("password"));
				THREAD_LOCAL.set(connection);
			}
			return connection;
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		return null;
	}
	
	//��ʼ����
	public static void begin() {
		Connection connection = null;
		
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
		}catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	//�ύ����
	public static void commit() {
		Connection connection = null;
		
		try {
			connection = getConnection();
			connection.commit();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			closeall(connection, null, null);
		}
		
	}
	
	//�ع�����
	public static void rollback() {
		Connection connection = null;
		
		try {
			connection = getConnection();
			connection.rollback();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			closeall(connection, null, null);
		}
	}
	
	
	//�ͷ���Դ
	public static void closeall(Connection connection,Statement statement,ResultSet resultSet) {
		
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			
			if (statement != null) {
				statement.close();
			}
			
			if (connection != null) {
				connection.close();
				THREAD_LOCAL.remove();
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
}
