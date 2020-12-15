package com.contract.advanced;

import java.sql.ResultSet;
import java.util.Date;

import com.contract.entity.Log;

public class LogRowMapper implements RowMapper<Log> {

	@Override
	public Log getRow(ResultSet resultSet) {
		Log log = null;
		try {
			int id = resultSet.getInt("id");
			int user_id = resultSet.getInt("user_id");
			Date time = resultSet.getDate("time");
			String contentString = resultSet.getString("content");
			int del = resultSet.getInt("del");
			
			log  = new Log(id, user_id, time, contentString, del);
			return log;
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		return log;
	}

}
