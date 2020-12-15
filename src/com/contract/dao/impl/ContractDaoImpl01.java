package com.contract.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import com.contract.advanced.ContractRowMapper;
import com.contract.dao.ContractDao;
import com.contract.entity.Contract;
import com.contract.utils.DaoUtils;
import com.contract.utils.DateUtils;

public class ContractDaoImpl01 implements ContractDao {

	DaoUtils<Contract> daoutils = new DaoUtils<Contract>();
	@Override
	public int insert(Contract contract) {
		String sqlString = "insert into contract(num,name,user_id,customer,begintime,endtime,content) values(?,?,?,?,?,?,?)";
		Object[] args = {contract.getNumString(),contract.getNameString(),contract.getUser_id(),contract.getCustomerString(),
						 DateUtils.uDateToSQLDate(contract.getBeginTime()),DateUtils.uDateToSQLDate(contract.getEndTime()),contract.getContentString()};
		return daoutils.commonUpdate(sqlString, args);
	}

	@Override
	public int update(Contract contract) {
		String sql = "update contract set num = ?,name = ?,user_id = ?,customer = ?,begintime = ?,endtime = ?, content = ?,del = ? where id = ?";
		Object[] args = {contract.getNumString(),contract.getNameString(),contract.getUser_id(),contract.getCustomerString(),
				 DateUtils.uDateToSQLDate(contract.getBeginTime()),DateUtils.uDateToSQLDate(contract.getEndTime()),contract.getContentString(),
				 contract.getDel(),contract.getId()};
		return daoutils.commonUpdate(sql, args);
	}

	@Override
	public Contract queryByID(int id) {
		String sql = "select * from contract where id = ? and del = 0";
		List<Contract> list = daoutils.commonQuery(sql, new ContractRowMapper(), id); 
		if (!list.isEmpty()) {			
			return list.get(0);
		} 
		
		return null;
	}

	@Override
	public Contract queryByNum(String num) {
		String sql = "select * from contract where num = ? and del = 0";
		List<Contract> list = daoutils.commonQuery(sql, new ContractRowMapper(), num); 
		if (!list.isEmpty()) {			
			return list.get(0);
		} 
		
		return null;	
	}

	@Override
	public List<Contract> queryByContractName(String name) {
		String sql = "select * from contract where name = ? and del = 0";
		List<Contract> list = daoutils.commonQuery(sql, new ContractRowMapper(), name); 
		return list;
	}

	@Override
	public List<Contract> queryByCustomerName(String name) {
		String sql = "select * from contract where customer = ? and del = 0";
		List<Contract> list = daoutils.commonQuery(sql, new ContractRowMapper(), name); 
		return list;
	}

	@Override
	public List<Contract> queryAll() {
		String sql = "select * from contract where del = 0";
		List<Contract> list = daoutils.commonQuery(sql, new ContractRowMapper(), null); 
		return list;
	}

	@Override
	public List<Contract> queryByConditon(String condition) {
		String sql = "select * from contract where name like ? and del = 0";
		String condition1 = condition + "%";
		String condition2 = "%" + condition;
		String condition3 = "%" + condition + "%";
		List<Contract> list1 = daoutils.commonQuery(sql, new ContractRowMapper(),condition1);
		List<Contract> list2 = daoutils.commonQuery(sql, new ContractRowMapper(),condition2);
		List<Contract> list3 = daoutils.commonQuery(sql, new ContractRowMapper(),condition3);
		List<Contract> listall = new ArrayList<Contract>();
		List<String> numStrings = new ArrayList<String>();
		for (Contract contract : list1) {
			if (!numStrings.contains(contract.getNumString())) {
				numStrings.add(contract.getNumString());
				listall.add(contract);
			}
		}
		for (Contract contract : list2) {
			if (!numStrings.contains(contract.getNumString())) {
				numStrings.add(contract.getNumString());
				listall.add(contract);
			}
		}
		for (Contract contract : list3) {
			if (!numStrings.contains(contract.getNumString())) {
				numStrings.add(contract.getNumString());
				listall.add(contract);
			}
		}
		listall = new ArrayList<Contract>(new LinkedHashSet<>(listall));
		
		return listall;
	}

	@Override
	public int delete(Contract contract) {
		contract.setDel(1);
		return update(contract);
	}

	@Override
	public int deleteByID(int id) {
		Contract contract = queryByID(id);
		return delete(contract);
	}

	@Override
	public int deleteByNum(String num) {
		Contract contract = queryByNum(num);
		return delete(contract);
	}

}
