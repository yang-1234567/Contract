package com.contract.advanced;

import java.sql.ResultSet;
import java.util.Date;

import com.contract.entity.Contract_state;

public class Contract_stateRowMapper implements RowMapper<Contract_state> {

	@Override
	public Contract_state getRow(ResultSet resultSet) {
		Contract_state contract_state = null;
		try {
			int id = resultSet.getInt("id");
			String conNumString = resultSet.getString("connum");
			int type = resultSet.getInt("type");
			Date time = resultSet.getDate("time");
			int del = resultSet.getInt("del");
			
			contract_state = new Contract_state(id, conNumString, type, time, del);
			return contract_state;
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		return contract_state;
	}

}
