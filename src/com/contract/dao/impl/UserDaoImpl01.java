package com.contract.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import com.contract.advanced.UserRowMapper;
import com.contract.dao.UserDao;
import com.contract.entity.User;
import com.contract.utils.DaoUtils;

public class UserDaoImpl01 implements UserDao {

	DaoUtils<User> daoutils = new DaoUtils<User>();
	
	@Override
	public int insert(User user) {
		String sqlString = "insert into user(name,password) values(?,?)";
		Object[] args = {user.getNameString(),user.getPasswordString()};
		return daoutils.commonUpdate(sqlString, args);
	}

	@Override
	public int update(User user) {
		String sql = "update user set name = ?,password = ?,del = ? where id = ?";
		Object[] args = {user.getNameString(),user.getPasswordString(),user.getDel(),user.getId()};
		return daoutils.commonUpdate(sql, args);
	}
	
	@Override
	public User query(String name, String password) {
		String sqString = "select * from user where name = ? and password = ? and del = 0";
		Object[] args = {name,password};
		List<User> list = daoutils.commonQuery(sqString, new UserRowMapper(), args);
		if (!list.isEmpty()) {
			return list.get(0);
		} 
		return null;
	}

	@Override
	public User queryByName(String name) {
		String sqString = "select * from user where name = ? and del = 0";
		List<User> list = daoutils.commonQuery(sqString, new UserRowMapper(), name);
		if (!list.isEmpty()) {
			return list.get(0);
		} 
		return null;
	}

	@Override
	public List<User> queryAll() {
		String sqString = "select * from user where del = 0";
		List<User> list = daoutils.commonQuery(sqString, new UserRowMapper(), null);
		return list;
	}

	@Override
	public User queryByID(int id) {
		String sqString = "select * from user where id = ? and del = 0";
		List<User> list = daoutils.commonQuery(sqString, new UserRowMapper(), id);
		if (!list.isEmpty()) {
			return list.get(0);
		} 
		return null;
	}

	@Override
	public List<User> queryByCondition(String condition) {
		String sql = "select * from user where name like ? and del = 0";
		String condition1 = condition + "%";
		String condition2 = "%" + condition;
		String condition3 = "%" + condition + "%";
		List<User> list1 = daoutils.commonQuery(sql, new UserRowMapper(),condition1);
		List<User> list2 = daoutils.commonQuery(sql, new UserRowMapper(),condition2);
		List<User> list3 = daoutils.commonQuery(sql, new UserRowMapper(),condition3);
		List<User> listall = new ArrayList<User>();
		List<String> nameStrings = new ArrayList<String>();
		for (User user : list1) {
			if (!nameStrings.contains(user.getNameString())) {
				nameStrings.add(user.getNameString());
				listall.add(user);
			}
		}
		for (User user : list2) {
			if (!nameStrings.contains(user.getNameString())) {
				nameStrings.add(user.getNameString());
				listall.add(user);
			}
		}
		for (User user : list3) {
			if (!nameStrings.contains(user.getNameString())) {
				nameStrings.add(user.getNameString());
				listall.add(user);
			}
		}
		listall = new ArrayList<User>(new LinkedHashSet<>(listall));
		
		return listall;
	}

	@Override
	public int delete(User user) {
		user.setDel(1);
		return update(user);
	}

	@Override
	public int deleteByName(String name) {
		User user = queryByName(name);
		return delete(user);
	}

	@Override
	public int deleteByID(int id) {
		User user = queryByID(id);
		return delete(user);
	}

}
