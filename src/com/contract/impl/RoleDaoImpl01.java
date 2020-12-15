package com.contract.impl;

import java.util.List;

import com.contract.advanced.RoleRowMapper;
import com.contract.dao.RoleDao;
import com.contract.entity.Role;
import com.contract.utils.DaoUtils;

public class RoleDaoImpl01 implements RoleDao {

	DaoUtils<Role> daoutils = new DaoUtils<Role>();
	
	@Override
	public int insert(Role role) {
		String sql = "insert into role(name,description,functions) values(?,?,?)";
		Object[] args = {role.getNameString(),role.getDescriptionString(),role.getFunctionString()};
		return daoutils.commonUpdate(sql, args);
	}

	@Override
	public int update(Role role) {
		String sql = "update role set name = ?,description = ?,functions = ?,del = ? where id = ?";
		Object[] args = {role.getNameString(),role.getDescriptionString(),role.getFunctionString(),role.getDel(),role.getId()};
		return daoutils.commonUpdate(sql, args);
	}

	@Override
	public List<Role> queryAll() {
		String sql = "select * from role where del = 0";
		List<Role> list = daoutils.commonQuery(sql, new RoleRowMapper(), null);
		return list;
	}

	@Override
	public Role queryByName(String name) {
		String sql = "select * from role where name = ? and del = 0";
		List<Role> list = daoutils.commonQuery(sql, new RoleRowMapper(), name);
		if (!list.isEmpty()) {			
			return list.get(0);
		} 
		
		return null;
	}

	@Override
	public int deleteByName(String name) {
		Role role = queryByName(name);
		role.setDel(1);
		return update(role);
	}

	@Override
	public int deleteFunction(int id,String rolename) {
		Role role = queryByName(rolename);
		String functions = role.getFunctionString();
		String[] funs = functions.split(",");
		String newfunctions = "";
		
		for (String fun : funs) {
			if (!fun.equals(id + "")) {
				newfunctions = newfunctions + fun + ",";
			}
		}
		
		newfunctions = newfunctions.substring(0,newfunctions.length()-1);
		role.setFunctionString(newfunctions);
		
		return update(role);
	}

}
