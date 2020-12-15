package com.contract.dao;

import java.util.List;

import com.contract.entity.Contract;

public interface ContractDao {
	public int insert(Contract contract);
	public int update(Contract contract);
	public int delete(Contract contract);
	public int deleteByID(int id);
	public int deleteByNum(String num);
	public Contract queryByID(int id);
	public Contract queryByNum(String num);
	public List<Contract> queryByContractName(String name);
	public List<Contract> queryByCustomerName(String name);
	public List<Contract> queryAll();
	public List<Contract> queryByConditon(String condition);
}
