package com.contract.advanced;

import com.contract.entity.Contract_attachment;

import java.sql.ResultSet;
import java.util.Date;

public class Contract_attachmentRowMapper implements RowMapper<Contract_attachment> {

	@Override
	public Contract_attachment getRow(ResultSet resultSet) {
		Contract_attachment contract_attachment = null;
		try {
			int id = resultSet.getInt("id");
			int con_id = resultSet.getInt("con_id");
			String filenameString = resultSet.getString("filename");
			String pathString = resultSet.getString("path");
			String type = resultSet.getString("type");
			Date uploadTime = resultSet.getDate("uploadtime");
			int del = resultSet.getInt("del");
			
			contract_attachment = new Contract_attachment(id, con_id, filenameString, pathString, type, uploadTime, del);
			return contract_attachment;
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		
		return contract_attachment;
	}

}
