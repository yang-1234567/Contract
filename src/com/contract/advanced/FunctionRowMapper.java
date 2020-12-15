package com.contract.advanced;

import java.sql.ResultSet;

import com.contract.entity.Function;

public class FunctionRowMapper implements RowMapper<Function> {

	@Override
	public Function getRow(ResultSet resultSet) {
		Function function = null;
		try {
			int id = resultSet.getInt("id");
			String mumString = resultSet.getString("num");
			String nameString = resultSet.getString("name");
			String URLString = resultSet.getString("url");
			String descriptionString = resultSet.getString("description");
			int del = resultSet.getInt("del");
			
			function = new Function(id, mumString, nameString, URLString, descriptionString, del);
			return function;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return function;
	}

}
