package com.contract.advanced;

import java.sql.ResultSet;

import com.contract.entity.Contract_User;

public class Contract_UserRowMapper implements RowMapper<Contract_User> {

	
	@Override
	public Contract_User getRow(ResultSet resultSet) {
		Contract_User contract_User = null;
		try {
			int id = resultSet.getInt("id");
			String connumString = resultSet.getString("connum");
			int user_id = resultSet.getInt("user_id");
			int type = resultSet.getInt("type");
			int del = resultSet.getInt("del");
			contract_User = new Contract_User(id, connumString, user_id, type, del);
			
			return contract_User;
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		return contract_User;
	}
}
