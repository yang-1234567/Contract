package com.contract.dao;

import java.util.List;

import com.contract.entity.Customer;

public interface CustomerDao {
	public int insert(Customer customer);
	public int update(Customer customer);
	public int delete(Customer customer);
	public int deleteByName(String name);
	public int deleteByNum(String num);
	public int deleteByTel(String tel);
	public List<Customer> queryAll();
	public Customer queryByName(String name);
	public Customer queryByNum(String num);
	public Customer queryByTel(String tel);
	public List<Customer> queryByCondition(String condition);
}
