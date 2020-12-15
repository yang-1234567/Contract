package com.contract.dao;

import com.contract.entity.Contract_attachment;

public interface Contract_attachmentDao {
	public int insert(Contract_attachment contract_attachment);
	public int update(Contract_attachment contract_attachment);
	public int deleteByContractId(int con_id);
	public Contract_attachment queryByContractId(int con_id);
}
