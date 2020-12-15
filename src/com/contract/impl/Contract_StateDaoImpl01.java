package com.contract.impl;

import java.util.List;

import com.contract.advanced.Contract_stateRowMapper;
import com.contract.dao.Contract_StateDao;
import com.contract.entity.Contract_state;
import com.contract.utils.DaoUtils;
import com.contract.utils.DateUtils;

public class Contract_StateDaoImpl01 implements Contract_StateDao {

	DaoUtils<Contract_state> daoutils = new DaoUtils<Contract_state>();

	@Override
	public int insert(Contract_state contract_state) {
		String sql = "insert into Contract_State(connum,type,time) values(?,?,?)";
		Object[] args = {contract_state.getConNumString(),contract_state.getType(),DateUtils.uDateToSQLDate(contract_state.getTime())};
		return daoutils.commonUpdate(sql, args);
	}

	@Override
	public int update(Contract_state contract_state) {
		String sql = "update Contract_State set connum = ?,type = ?,time = ?,del = ? where id = ?";
		Object[] args = {contract_state.getConNumString(),contract_state.getType(),DateUtils.uDateToSQLDate(contract_state.getTime()),
				contract_state.getDel(),contract_state.getId()};
		return daoutils.commonUpdate(sql, args);
	}

	@Override
	public Contract_state queryByNum(String num) {
		String sqlString = "select * from Contract_State where connum = ? and del = 0";
		List<Contract_state> list = daoutils.commonQuery(sqlString, new Contract_stateRowMapper(), num);
		
		if (!list.isEmpty()) {	
			return list.get(0);
		} 
		
		return null;
	}

	@Override
	public List<Contract_state> queryByType(int type) {
		String sql = "Select * from Contract_State where type = ? and del = 0";
		List<Contract_state> list = daoutils.commonQuery(sql, new Contract_stateRowMapper(), type);
		return list;
	}

	@Override
	public List<Contract_state> queryAll() {
		String sql = "select * from Contract_State where del = 0";
		List<Contract_state> list = daoutils.commonQuery(sql, new Contract_stateRowMapper(), null);
		return list;
	}

	@Override
	public int deleteByNum(String num) {
		Contract_state contract_state = queryByNum(num);
		contract_state.setDel(1);
		return update(contract_state);
	}

}
