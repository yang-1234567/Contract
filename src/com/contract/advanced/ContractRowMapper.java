package com.contract.advanced;

import java.sql.ResultSet;
import java.util.Date;

import com.contract.entity.Contract;

public class ContractRowMapper implements RowMapper<Contract> {

	@Override
	public Contract getRow(ResultSet resultSet) {
		Contract contract = null;
		try {
			int id = resultSet.getInt("id");
			String numString = resultSet.getString("num");
			String nameString = resultSet.getString("name");
			int user_id = resultSet.getInt("user_id");
			String customerString = resultSet.getString("customer");
			Date beginTime = resultSet.getDate("begintime");
			Date endTime = resultSet.getDate("endtime");
			String contentString = resultSet.getString("content");
			int del = resultSet.getInt("del");
			
			contract = new Contract(id, numString, nameString, user_id, customerString, beginTime, endTime, contentString, del);
			return contract;
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		return contract;
	}

}
