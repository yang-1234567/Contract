package com.contract.advanced;

import java.sql.ResultSet;

import com.contract.entity.Right;

public class RightRowMapper implements RowMapper<Right> {

	@Override
	public Right getRow(ResultSet resultSet) {
		Right right = null;
		try {
			int id = resultSet.getInt("id");
			String usernameString = resultSet.getString("username");
			String rolenameString = resultSet.getString("rolename");
			String descriptionString = resultSet.getString("description");
			int del = resultSet.getInt("del");
			
			right = new Right(id, usernameString, rolenameString, descriptionString, del);
			return right;
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		return right;
	}

}
