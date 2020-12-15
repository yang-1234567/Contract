package com.contract.service;

import java.util.List;

public interface SearchService {
	public String searchContract(int type,String condition);
	public String searchByType(int type);
	public String searchContract(int type,String condition,String username);
	public String searchByType(int type,String username);
	public String searchUserAndRole();
	public String searchUserAndRole(String condition);
	public String searchRole();
	public List<String> searchFenpei(String num);
	public String searchLog(String username,String time);
	public String searchFinal(String conditon);
	public String searchFinal();
	public String searchCustomer(String condition);
	public String searchCustomer();
}
