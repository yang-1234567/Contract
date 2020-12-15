package com.contract.advanced;

import java.sql.ResultSet;

import com.contract.entity.Role;

public class RoleRowMapper implements RowMapper<Role> {

	@Override
	public Role getRow(ResultSet resultSet) {
		Role role = null;
		try {
			int id = resultSet.getInt("id");
			String nameString = resultSet.getString("name");
			String descriptionString = resultSet.getString("description");
			String functionString = resultSet.getString("functions");
			int del = resultSet.getInt("del");
			
			role = new Role(id, nameString, descriptionString, functionString, del);
			return role;
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		return role;
	}

}
