package com.contract.impl;

import java.util.List;

import com.contract.advanced.FunctionRowMapper;
import com.contract.dao.FunctionDao;
import com.contract.entity.Function;
import com.contract.utils.DaoUtils;

public class FunctionDaoImpl01 implements FunctionDao {

	DaoUtils<Function> daoutils = new DaoUtils<Function>();

	@Override
	public int insert(Function function) {
		String sql = "insert into function(num,name,url,description) values(?,?,?,?)";
		Object[] args = {function.getMumString(),function.getNameString(),function.getURLString(),function.getDescriptionString()};
		return daoutils.commonUpdate(sql, args);
	}

	@Override
	public int update(Function function) {
		String sql = "update function set num = ?,name = ?,url = ?,description = ?, del = ? where id = ?";
		Object[] args = {function.getMumString(),function.getNameString(),function.getURLString(),function.getDescriptionString(),
				function.getDel(),function.getId()};
		return daoutils.commonUpdate(sql, args);
	}

	@Override
	public List<Function> queryAll() {
		String sql = "select * from function where del = 0";
		List<Function> list = daoutils.commonQuery(sql, new FunctionRowMapper(), null);
		return list;
	}

	@Override
	public Function queryByName(String name) {
		String sql = "select * from function where name = ? and del = 0";
		List<Function> list = daoutils.commonQuery(sql, new FunctionRowMapper(), name);
		if (!list.isEmpty()) {			
			return list.get(0);
		} 
		
		return null;
	}

	@Override
	public Function queryByNum(String num) {
		String sql = "select * from function where num = ? and del = 0";
		List<Function> list = daoutils.commonQuery(sql, new FunctionRowMapper(), num);
		if (!list.isEmpty()) {			
			return list.get(0);
		} 
		
		return null;
	}

	@Override
	public Function queryByID(int id) {
		String sql = "select * from function where id = ? and del = 0";
		List<Function> list = daoutils.commonQuery(sql, new FunctionRowMapper(), id);
		if (!list.isEmpty()) {			
			return list.get(0);
		} 
		
		return null;
	}

	@Override
	public int deleteByName(String name) {
		Function function = queryByName(name);
		function.setDel(1);
		return update(function);
	}

}
