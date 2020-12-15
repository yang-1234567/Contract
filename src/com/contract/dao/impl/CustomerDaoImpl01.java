package com.contract.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import com.contract.advanced.CustomerRowMapper;
import com.contract.dao.CustomerDao;
import com.contract.entity.Customer;
import com.contract.utils.DaoUtils;

public class CustomerDaoImpl01 implements CustomerDao {

	DaoUtils<Customer> daoutils = new DaoUtils<Customer>();
	
	@Override
	public int insert(Customer customer) {
		String sql = "insert into customer(num,name,address,tel,fax,code,bank,account) values(?,?,?,?,?,?,?,?)";
		Object[] args = {customer.getNumlString(),customer.getNameString(),customer.getAddressString(),customer.getTelString(),customer.getFax(),
				customer.getCodeString(),customer.getBankString(),customer.getAccountString()};
		return daoutils.commonUpdate(sql, args);
	}

	@Override
	public int update(Customer customer) {
		String sql = "update customer set num = ?,name = ?, address = ?,tel = ?,fax = ?,code = ?,bank = ?,account =?,del =? where id = ?";
		Object[] args = {customer.getNumlString(),customer.getNameString(),customer.getAddressString(),customer.getTelString(),customer.getFax(),
				customer.getCodeString(),customer.getBankString(),customer.getAccountString(),customer.getDel(),customer.getId()};
		return daoutils.commonUpdate(sql, args);
	}

	@Override
	public List<Customer> queryAll() {
		String sql = "select * from Customer where del = 0";
		List<Customer> list = daoutils.commonQuery(sql, new CustomerRowMapper(), null);
		return list;
	}

	@Override
	public Customer queryByName(String name) {
		String sql = "select * from Customer where name = ? and del = 0";
		List<Customer> list = daoutils.commonQuery(sql, new CustomerRowMapper(), name);
		if (!list.isEmpty()) {			
			return list.get(0);
		} 
		
		return null;
	}

	@Override
	public Customer queryByNum(String num) {
		String sql = "select * from Customer where num = ? and del = 0";
		List<Customer> list = daoutils.commonQuery(sql, new CustomerRowMapper(), num);
		if (!list.isEmpty()) {			
			return list.get(0);
		} 
		
		return null;
	}

	@Override
	public Customer queryByTel(String tel) {
		String sql = "select * from Customer where tel = ? and del = 0";
		List<Customer> list = daoutils.commonQuery(sql, new CustomerRowMapper(), tel);
		if (!list.isEmpty()) {			
			return list.get(0);
		} 
		
		return null;
	}

	@Override
	public int delete(Customer customer) {
		customer.setDel(1);
		return update(customer);
	}

	@Override
	public int deleteByName(String name) {
		Customer customer = queryByName(name);
		return delete(customer);
	}

	@Override
	public int deleteByNum(String num) {
		Customer customer = queryByNum(num);
		return delete(customer);
	}

	@Override
	public int deleteByTel(String tel) {
		Customer customer = queryByTel(tel);
		return delete(customer);
	}

	@Override
	public List<Customer> queryByCondition(String condition) {
		String sql = "select * from customer where name like ? and del = 0";
		String condition1 = condition + "%";
		String condition2 = "%" + condition;
		String condition3 = "%" + condition + "%";
		List<Customer> list1 = daoutils.commonQuery(sql, new CustomerRowMapper(),condition1);
		List<Customer> list2 = daoutils.commonQuery(sql, new CustomerRowMapper(),condition2);
		List<Customer> list3 = daoutils.commonQuery(sql, new CustomerRowMapper(),condition3);
		List<Customer> listall = new ArrayList<Customer>();
		List<String> nameStrings = new ArrayList<String>();
		for (Customer customer : list1) {
			if (!nameStrings.contains(customer.getNameString())) {
				nameStrings.add(customer.getNameString());
				listall.add(customer);
			}
		}
		for (Customer customer : list2) {
			if (!nameStrings.contains(customer.getNameString())) {
				nameStrings.add(customer.getNameString());
				listall.add(customer);
			}
		}
		for (Customer customer : list3) {
			if (!nameStrings.contains(customer.getNameString())) {
				nameStrings.add(customer.getNameString());
				listall.add(customer);
			}
		}
		listall = new ArrayList<Customer>(new LinkedHashSet<>(listall));
		
		return listall;
	}

}
