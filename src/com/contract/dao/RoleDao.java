package com.contract.dao;

import java.util.List;

import com.contract.entity.Role;

public interface RoleDao {
	public int insert(Role role);
	public int update(Role role);
	public int deleteByName(String name);
	public int deleteFunction(int id,String rolename);
	public List<Role> queryAll();
	public Role queryByName(String name);
}
