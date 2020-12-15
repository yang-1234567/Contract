package com.contract.dao;

import java.util.List;

import com.contract.entity.Right;

public interface RightDao {
	public int insert(Right right);
	public int update(Right right);
	public int deleteByUser(String username);
	public int deleteByRole(String rolename);
	public int deleteByUserAndRole(String username,String rolename);
	public List<Right> queryAll();
	public List<Right> queryByUserName(String name);
	public List<Right> queryByRoleName(String name);
	public Right queryByUserAndRole(String username,String rolename);
}
