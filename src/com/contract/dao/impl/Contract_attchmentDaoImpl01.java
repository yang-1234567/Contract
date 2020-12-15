package com.contract.dao.impl;

import java.util.List;

import com.contract.advanced.Contract_attachmentRowMapper;
import com.contract.dao.Contract_attachmentDao;
import com.contract.entity.Contract_attachment;
import com.contract.utils.DaoUtils;
import com.contract.utils.DateUtils;

public class Contract_attchmentDaoImpl01 implements Contract_attachmentDao {

	DaoUtils<Contract_attachment> daoutils = new DaoUtils<Contract_attachment>();

	@Override
	public int insert(Contract_attachment contract_attachment) {
		String sqlString = "insert into Contract_attachment(con_id,filename,path,type,uploadtime) values(?,?,?,?,?)";
		Object[] args = {contract_attachment.getCon_id(),contract_attachment.getFilenameString(),contract_attachment.getPathString(),contract_attachment.getType(),
						DateUtils.uDateToSQLDate(contract_attachment.getUploadTime())};
		return daoutils.commonUpdate(sqlString, args);
	}

	@Override
	public int update(Contract_attachment contract_attachment) {
		String sqlString = "update Contract_attachment set con_id = ?,filename = ?,path = ?,type = ?,uploadtime = ?,del = ? where id = ?";
		Object[] args = {contract_attachment.getCon_id(),contract_attachment.getFilenameString(),contract_attachment.getPathString(),
						contract_attachment.getType(),contract_attachment.getUploadTime(),contract_attachment.getDel(),contract_attachment.getId()};
		return daoutils.commonUpdate(sqlString, args);
	}

	@Override
	public Contract_attachment queryByContractId(int con_id) {
		String sqlString = "select * from Contract_attachment where con_id = ? and del = 0";
		List<Contract_attachment> list = daoutils.commonQuery(sqlString, new Contract_attachmentRowMapper(), con_id);

		if (!list.isEmpty()) {			
			return list.get(0);
		} 
		
		return null;
	}

	@Override
	public int deleteByContractId(int con_id) {
		Contract_attachment contract_attachment = queryByContractId(con_id);
		contract_attachment.setDel(1);
		return update(contract_attachment);
	}

}
