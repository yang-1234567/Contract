package com.contract.impl;

import java.util.List;

import com.contract.advanced.RightRowMapper;
import com.contract.dao.RightDao;
import com.contract.entity.Right;
import com.contract.utils.DaoUtils;

public class RightDaoImpl01 implements RightDao {

	DaoUtils<Right> daoutils = new DaoUtils<Right>();

	@Override
	public int insert(Right right) {
		String sql = "insert into rightss(username,rolename,description) values(?,?,?)";
		Object[] args = {right.getUsernameString(),right.getRolenameString(),right.getDescriptionString()};
		return daoutils.commonUpdate(sql, args);
	}

	@Override
	public int update(Right right) {
		String sql = "update rightss username = ?,rolename = ?,description = ?,del = ? where id = ? ";
		Object[] args = {right.getUsernameString(),right.getRolenameString(),right.getDescriptionString(),right.getDel(),right.getId()};
		return daoutils.commonUpdate(sql, args);
	}

	@Override
	public List<Right> queryAll() {
		String sql = "select * from rightss where del = 0";
		List<Right> list = daoutils.commonQuery(sql, new RightRowMapper(), null);
		return list;
	}

	@Override
	public List<Right> queryByUserName(String name) {
		String sql = "select * from rightss where username = ? and del = 0";
		List<Right> list = daoutils.commonQuery(sql, new RightRowMapper(), name);
		return list;
	}

	@Override
	public List<Right> queryByRoleName(String name) {
		String sql = "select * from rightss where rolename = ? and del = 0";
		List<Right> list = daoutils.commonQuery(sql, new RightRowMapper(), name);
		return list;
	}

	@Override
	public Right queryByUserAndRole(String username, String rolename) {
		String sql = "select * from rightss where username = ? and rolename = ? and del = 0";
		Object[] args = {username,rolename};
		List<Right> list = daoutils.commonQuery(sql, new RightRowMapper(), args);
		if (!list.isEmpty()) {
			return list.get(0);
		} 
		return null;
	}

	@Override
	public int deleteByUser(String username) {
		List<Right> list = queryByUserName(username);
		for (Right right : list) {
			right.setDel(1);
			update(right);
		}
		return 1;
	}

	@Override
	public int deleteByRole(String rolename) {
		List<Right> list = queryByRoleName(rolename);
		for (Right right : list) {
			right.setDel(1);
			update(right);
		}
		return 1;
	}

	@Override
	public int deleteByUserAndRole(String username, String rolename) {
		Right right = queryByUserAndRole(username, rolename);
		right.setDel(1);
		return update(right);
	}

}
