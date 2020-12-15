package com.contract.dao;

import java.util.List;

import com.contract.entity.Contract_state;

public interface Contract_StateDao {
	public int insert(Contract_state contract_state);
	public int update(Contract_state contract_state);
	public int deleteByNum(String num);
	public Contract_state queryByNum(String num);
	public List<Contract_state> queryByType(int type);
	public List<Contract_state> queryAll();
}
