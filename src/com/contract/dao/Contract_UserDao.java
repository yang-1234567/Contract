package com.contract.dao;

import java.util.List;

import com.contract.entity.Contract_User;

public interface Contract_UserDao {
	public int insert(Contract_User contract_User);
	public int update(Contract_User contract_User);
	public int deleteByNum(String num);
	public int deleteByTypeAndUserAndNum(int user_id,int type,String num);
	public int deleteByUser(int user_id);
	public int deleteByTypeAndUser(int user_id,int type);
	public List<Contract_User> queryAll();
	public List<Contract_User> queryByUser(int user_id);
	public List<Contract_User> queryByTypeAndUser(int user_id,int type);
	public Contract_User queryByTypeAndUserAndNum(int user_id,int type,String num);
	public Contract_User queryByTypeAndUserAndNumWithDel(int user_id,int type,String num);
	public List<Contract_User> queryByNum(String num);
	public List<Contract_User> queryByNumAndType(String num,int type);
}
