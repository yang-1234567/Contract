package com.contract.service;

import org.json.simple.JSONObject;

import com.contract.entity.User;

public interface UserService {

	public User login(String name,String password);//100
	public int register(String name,String password);//200
	public int addNewRole(String rolenameString,String description,String functions,String userid,String time);//300
	public int addNewRoleForUser(String username,String rolename,String userid,String time);//400
	public int FeiPei(String numString,String resultString,String userid,String finalstate);//500
	public int addCustomer(JSONObject jsonObject);

}
