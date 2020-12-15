package com.contract.advanced;

import java.sql.ResultSet;

import com.contract.entity.Customer;

public class CustomerRowMapper implements RowMapper<Customer> {

	@Override
	public Customer getRow(ResultSet resultSet) {
		Customer customer = null;
		
		try {
			int id = resultSet.getInt("id");
			String numlString = resultSet.getString("num");
			String nameString = resultSet.getString("name");
			String addressString = resultSet.getString("address");
			String telString = resultSet.getString("tel");
			String fax = resultSet.getString("fax");	
			String codeString = resultSet.getString("code");
			String bankString = resultSet.getString("bank");
			String accountString = resultSet.getString("account");
			int del = resultSet.getInt("del");
			
			customer = new Customer(id, numlString, nameString, addressString, telString, fax, codeString, bankString, accountString, del);
			return customer;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return customer;
	}

}
