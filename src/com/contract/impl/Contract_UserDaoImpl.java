package com.contract.impl;

import java.util.List;

import com.contract.advanced.Contract_UserRowMapper;
import com.contract.dao.Contract_UserDao;
import com.contract.entity.Contract_User;
import com.contract.utils.DaoUtils;

public class Contract_UserDaoImpl implements Contract_UserDao {

	DaoUtils<Contract_User> daoutils = new DaoUtils<Contract_User>();
	
	@Override
	public int insert(Contract_User contract_User) {
		String sql = "insert into Contract_user(connum,user_id,type) values(?,?,?)";
		Object[] args = {contract_User.getConnumString(),contract_User.getUser_id(),contract_User.getType()};
		return daoutils.commonUpdate(sql, args);
		
	}

	@Override
	public int update(Contract_User contract_User) {
		String sql = "update Contract_user set connum = ?,user_id = ?,type = ?,del = ? where id = ?";
		Object[] args = {contract_User.getConnumString(),contract_User.getUser_id(),contract_User.getType(),contract_User.getDel(),contract_User.getId()};
		return daoutils.commonUpdate(sql, args);
	}

	@Override
	public List<Contract_User> queryAll() {
		String sql = "select * from Contract_user where del = 0";
		List<Contract_User> list = daoutils.commonQuery(sql, new Contract_UserRowMapper(), null);
		return list;
	}

	@Override
	public List<Contract_User> queryByTypeAndUser(int user_id, int type) {
		String sql = "select * from Contract_user where user_id = ? and type = ? and del = 0";
		Object[] args = {user_id,type};
		List<Contract_User> list = daoutils.commonQuery(sql, new Contract_UserRowMapper(), args);
		return list;
	}

	@Override
	public List<Contract_User> queryByNum(String num) {
		String sql = "select * from Contract_user where connum = ? and del = 0";
		List<Contract_User> list = daoutils.commonQuery(sql, new Contract_UserRowMapper(), num);
		return list;
	}

	@Override
	public Contract_User queryByTypeAndUserAndNum(int user_id, int type, String num) {
		String sql = "select * from Contract_user where user_id = ? and type = ? and connum = ? and del = 0";
		Object[] args = {user_id,type,num};
		List<Contract_User> list = daoutils.commonQuery(sql, new Contract_UserRowMapper(), args);

		if (!list.isEmpty()) {			
			return list.get(0);
		} 
		
		return null;
	}

	@Override
	public List<Contract_User> queryByNumAndType(String num, int type) {
		String sql = "select * from Contract_user where connum = ? and type = ? and del = 0";
		Object[] args = {num,type};
		List<Contract_User> list = daoutils.commonQuery(sql, new Contract_UserRowMapper(), args);
		return list;
	}

	@Override
	public Contract_User queryByTypeAndUserAndNumWithDel(int user_id, int type, String num) {
		String sql = "select * from Contract_user where user_id = ? and type = ? and connum = ? and del = 1";
		Object[] args = {user_id,type,num};
		List<Contract_User> list = daoutils.commonQuery(sql, new Contract_UserRowMapper(), args);

		if (!list.isEmpty()) {			
			return list.get(0);
		} 
		
		return null;
	}

	@Override
	public int deleteByNum(String num) {
		List<Contract_User> list = queryByNum(num);
		for (Contract_User contract_User : list) {
			contract_User.setDel(1);
			update(contract_User);
		}
		return 1;
	}

	@Override
	public int deleteByTypeAndUserAndNum(int user_id,int type,String num) {
		Contract_User contract_User = queryByTypeAndUserAndNum(user_id, type, num);
		contract_User.setDel(1);
		return update(contract_User);
	}

	@Override
	public int deleteByUser(int user_id) {
		List<Contract_User> list = queryByUser(user_id);
		for (Contract_User contract_User : list) {
			contract_User.setDel(1);
			update(contract_User);
		}
		return 1;
	}

	@Override
	public int deleteByTypeAndUser(int user_id, int type) {
		List<Contract_User> list = queryByTypeAndUser(user_id, type);
		for (Contract_User contract_User : list) {
			contract_User.setDel(1);
			update(contract_User);
		}
		return 1;
	}


	@Override
	public List<Contract_User> queryByUser(int user_id) {
		String sql = "select * from Contract_user where user_id = ? and del = 0";
		List<Contract_User> list = daoutils.commonQuery(sql, new Contract_UserRowMapper(), user_id);
		return list;
	}


}
