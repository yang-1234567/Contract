package com.contract.advanced;

import java.sql.ResultSet;

import com.contract.entity.User;
public class UserRowMapper implements RowMapper<User> {

	@Override
	public User getRow(ResultSet resultSet) {
		User user = null;
		try {
			int id = resultSet.getInt("id");
			int del = resultSet.getInt("del");
			String nameString = resultSet.getString("name");
			String password = resultSet.getString("password");
			user = new User(id, nameString, password, del);
			return user;
		} catch (Exception e) {
			e.getStackTrace();
		}

		return user;
	}

}

