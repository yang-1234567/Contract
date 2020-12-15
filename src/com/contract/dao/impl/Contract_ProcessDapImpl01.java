package com.contract.dao.impl;

import java.util.List;

import com.contract.advanced.Contract_ProcessRowMapper;
import com.contract.dao.Contract_ProcessDao;
import com.contract.entity.Contract_process;
import com.contract.utils.DaoUtils;
import com.contract.utils.DateUtils;

public class Contract_ProcessDapImpl01 implements Contract_ProcessDao {

	DaoUtils<Contract_process> daoutils = new DaoUtils<Contract_process>();
	
	@Override
	public int insert(Contract_process contract_process) {
		String sqlString = "insert into Contract_Process(connum,type,state,username,content,time) values(?,?,?,?,?,?)";
		Object[] args = {contract_process.getConNumString(),contract_process.getType(),contract_process.getState(),contract_process.getUsernameString(),
				contract_process.getContentString(),DateUtils.uDateToSQLDate(contract_process.getTime())};
		return daoutils.commonUpdate(sqlString, args);
	}

	@Override
	public int update(Contract_process contract_process) {
		String sql = "update Contract_Process set connum = ?,type = ?,state = ?,username = ?,content = ?,time = ?,del = ? where id =?";
		Object[] args = {contract_process.getConNumString(),contract_process.getType(),contract_process.getState(),contract_process.getUsernameString(),
				contract_process.getContentString(),DateUtils.uDateToSQLDate(contract_process.getTime()),contract_process.getDel(),contract_process.getId()};
		return daoutils.commonUpdate(sql, args);
	}

	@Override
	public List<Contract_process> queryByNum(String num) {
		String sql = "select * from Contract_Process where connum = ? and del = 0";
		List<Contract_process> list = daoutils.commonQuery(sql, new Contract_ProcessRowMapper(), num);
		return list;
	}

	@Override
	public int deleteByNum(String num) {
		List<Contract_process> list = queryByNum(num);
		for (Contract_process contract_process : list) {
			contract_process.setDel(1);
			update(contract_process);
		}
		return 1;
	}

	@Override
	public int deleteByTypeAndUserAndNum(String username, int type, String num) {
		Contract_process contract_process = queryByTypeAndUserAndNum(username, type, num);
		contract_process.setDel(1);
		return update(contract_process);
	}

	@Override
	public int deleteByUser(String username) {
		List<Contract_process> list = queryByUser(username);
		for (Contract_process contract_process : list) {
			contract_process.setDel(1);
			update(contract_process);
		}
		return 1;
	}

	@Override
	public int deleteByTypeAndUser(String username, int type) {
		List<Contract_process> list = queryByTypeAndUser(username, type);
		for (Contract_process contract_process : list) {
			contract_process.setDel(1);
			update(contract_process);
		}
		return 1;
	}

	@Override
	public List<Contract_process> queryAll() {
		String sql = "select * from Contract_process where del = 0";
		List<Contract_process> list = daoutils.commonQuery(sql, new Contract_ProcessRowMapper(), null);
		return list;
	}

	@Override
	public List<Contract_process> queryByUser(String username) {
		String sql = "select * from Contract_process where del = 0 and username = ?";
		List<Contract_process> list = daoutils.commonQuery(sql, new Contract_ProcessRowMapper(), username);
		return list;
	}

	@Override
	public List<Contract_process> queryByTypeAndUser(String username, int type) {
		String sql = "select * from Contract_process where del = 0 and username = ? and type = ?";
		Object[] args = {username,type};
		List<Contract_process> list = daoutils.commonQuery(sql, new Contract_ProcessRowMapper(), args);
		return list;
	}

	@Override
	public Contract_process queryByTypeAndUserAndNum(String username, int type, String num) {
		String sql = "select * from Contract_process where del = 0 and username = ? and type = ? and connum = ?";
		Object[] args = {username,type,num};
		List<Contract_process> list = daoutils.commonQuery(sql, new Contract_ProcessRowMapper(), args);
		if (!list.isEmpty()) {			
			return list.get(0);
		} 
		
		return null;
	}

	@Override
	public Contract_process queryByTypeAndUserAndNumWithDel(String username, int type, String num) {
		String sql = "select * from Contract_process where del = 1 and username = ? and type = ? and connum = ?";
		Object[] args = {username,type,num};
		List<Contract_process> list = daoutils.commonQuery(sql, new Contract_ProcessRowMapper(), args);
		if (!list.isEmpty()) {			
			return list.get(0);
		} 
		
		return null;
	}

	@Override
	public List<Contract_process> queryByNumAndType(String num, int type) {
		String sql = "select * from Contract_process where connum = ? and type = ?";
		Object[] args = {num,type};
		List<Contract_process> list = daoutils.commonQuery(sql, new Contract_ProcessRowMapper(),args);
		return list;
	}

}
