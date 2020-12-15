package com.contract.dao;

import java.util.List;

import com.contract.entity.Contract_process;

public interface Contract_ProcessDao {
	public int insert(Contract_process contract_process);
	public int update(Contract_process contract_process);
	public int deleteByNum(String num);
	public List<Contract_process> queryByNum(String num);
	public int deleteByTypeAndUserAndNum(String username,int type,String num);
	public int deleteByUser(String username);
	public int deleteByTypeAndUser(String username,int type);
	public List<Contract_process> queryAll();
	public List<Contract_process> queryByUser(String username);
	public List<Contract_process> queryByTypeAndUser(String username,int type);
	public Contract_process queryByTypeAndUserAndNum(String username,int type,String num);
	public Contract_process queryByTypeAndUserAndNumWithDel(String username,int type,String num);
	public List<Contract_process> queryByNumAndType(String num,int type);
}
