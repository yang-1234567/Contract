package com.contract.dao;

import java.util.List;

import com.contract.entity.User;

public interface UserDao {
	public int insert(User user);
	public int update(User user);
	public int delete(User user);
	public int deleteByName(String name);
	public int deleteByID(int id);
	public List<User> queryAll();
	public User query(String name,String password);
	public User queryByName(String name);
	public User queryByID(int id);
	public List<User> queryByCondition(String condition);
}
