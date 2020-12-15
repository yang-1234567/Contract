package com.contract.utils;

import com.contract.advanced.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Dao �����࣬��Ҫ������������
 * @author 76557
 *1CommonUpdate ��ɾ�ĵ�ͨ�÷���
 *
 *2CommenQuery ��ѯ��ͨ�÷���
 */

public class DaoUtils<T> {

	//commonUpdate
	public int commonUpdate(String sql,Object... args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		connection = DBUtils.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					preparedStatement.setObject(i+1, args[i]);
				}
			}
			
			result = preparedStatement.executeUpdate();
			
			return result;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			DBUtils.closeall(null, preparedStatement, null);
		}

		return 0;
	}
	
	//commonQuery
	public List<T> commonQuery(String sql, RowMapper<T> rowMapper, Object... args){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<T> list = new ArrayList<T>();
		
		connection = DBUtils.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			if (args != null) {
				
				for (int i = 0; i < args.length; i++) {
					preparedStatement.setObject(i+1, args[i]);
				}
			}
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				T t = rowMapper.getRow(resultSet);
				list.add(t);
			}
			
			return list;
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			DBUtils.closeall(null, preparedStatement, resultSet);
		}
		
		return null;
	}
	
}
