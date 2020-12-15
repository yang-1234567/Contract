package com.contract.advanced;

import java.sql.ResultSet;
import java.util.Date;

import com.contract.entity.Contract_process;

public class Contract_ProcessRowMapper implements RowMapper<Contract_process> {

	@Override
	public Contract_process getRow(ResultSet resultSet) {
		Contract_process contract_process = null;
		try {
			int id = resultSet.getInt("id");
			String conNumString = resultSet.getString("connum");
			int type = resultSet.getInt("type");
			int state = resultSet.getInt("state");
			String usernameString = resultSet.getString("username");
			String contentString = resultSet.getString("content");
			Date time = resultSet.getDate("time");
			int del = resultSet.getInt("del");
			
			contract_process = new Contract_process(id, conNumString, type, state, usernameString, contentString, time, del);
			return contract_process;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return contract_process;
	}

}
