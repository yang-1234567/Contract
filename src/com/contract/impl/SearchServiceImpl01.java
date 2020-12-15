package com.contract.impl;

import java.util.ArrayList;
import java.util.List;

import com.contract.dao.ContractDao;
import com.contract.dao.Contract_ProcessDao;
import com.contract.dao.Contract_StateDao;
import com.contract.dao.CustomerDao;
import com.contract.dao.FunctionDao;
import com.contract.dao.LogDao;
import com.contract.dao.RightDao;
import com.contract.dao.RoleDao;
import com.contract.dao.UserDao;
import com.contract.dao.impl.ContractDaoImpl01;
import com.contract.dao.impl.Contract_ProcessDapImpl01;
import com.contract.dao.impl.Contract_StateDaoImpl01;
import com.contract.dao.impl.CustomerDaoImpl01;
import com.contract.dao.impl.FunctionDaoImpl01;
import com.contract.dao.impl.LogDaoImpl01;
import com.contract.dao.impl.RightDaoImpl01;
import com.contract.dao.impl.RoleDaoImpl01;
import com.contract.dao.impl.UserDaoImpl01;
import com.contract.entity.Contract;
import com.contract.entity.Contract_process;
import com.contract.entity.Contract_state;
import com.contract.entity.Customer;
import com.contract.entity.Function;
import com.contract.entity.Log;
import com.contract.entity.Right;
import com.contract.entity.Role;
import com.contract.entity.User;
import com.contract.service.SearchService;
import com.contract.utils.DBUtils;
import com.contract.utils.DateUtils;

public class SearchServiceImpl01 implements SearchService {

	private Contract_StateDao contract_statedao = new Contract_StateDaoImpl01();
	private ContractDao contractdao = new ContractDaoImpl01();
	private UserDao userdao = new UserDaoImpl01();
	private RightDao rightdao = new RightDaoImpl01();
	private RoleDao roledao = new RoleDaoImpl01();
	private FunctionDao functiondao = new FunctionDaoImpl01();
	private Contract_ProcessDao contract_ProcessDao = new Contract_ProcessDapImpl01();
	private LogDao logdao = new LogDaoImpl01();
	private CustomerDao customerdao = new CustomerDaoImpl01();
	
	
	@Override
	public String searchContract(int type, String condition) {
		
		String resultString = "";
		List<String> resultList = new ArrayList<String>();
		try {
			DBUtils.begin();
			
			List<Contract> list_Contracts = contractdao.queryByConditon(condition);
			for (Contract contract : list_Contracts) {
				Contract_state contract_state = contract_statedao.queryByNum(contract.getNumString());
				if (contract_state.getType() == type) {
					String string = contract.getNameString() + "&" + contract.getNumString() + "&" + DateUtils.udateToString(contract_state.getTime());
					resultList.add(string);
				}
			}
			
			for(String string : resultList) {
				resultString = resultString + "|" + string;
			}
			
			DBUtils.commit();
			return resultString;
		} catch (Exception e) {
			DBUtils.rollback();
		}
	
		
		return resultString;
	}
	
	@Override
	public String searchContract(int type, String condition,String username) {
		
		String resultString = "";
		List<String> resultList = new ArrayList<String>();
		try {
			DBUtils.begin();
			
			int processType = 0;
			
			if (type == 6) {
				processType = 1;
			} else if (type == 3) {
				processType = 2;
			} else if (type == 4) {
				processType = 3;
			}
			
			List<Contract> list_Contracts = contractdao.queryByConditon(condition);
			for (Contract contract : list_Contracts) {
				Contract_state contract_state = contract_statedao.queryByNum(contract.getNumString());
				Contract_process contract_process = contract_ProcessDao.queryByTypeAndUserAndNum(username, processType, contract.getNumString());
				if (contract_state.getType() == type && contract_process != null && contract_process.getState() == 0 ) {
					String string = contract.getNameString() + "&" + contract.getNumString() + "&" + DateUtils.udateToString(contract_state.getTime());
					resultList.add(string);
				}
			}
			
			for(String string : resultList) {
				resultString = resultString + "|" + string;
			}
			
			DBUtils.commit();
			return resultString;
		} catch (Exception e) {
			DBUtils.rollback();
		}
	
		
		return resultString;
	}

	@Override
	public String searchByType(int type) {
		String resultString = "";
		List<String> resultList = new ArrayList<String>();
		try {
			DBUtils.begin();
			
			List<Contract_state> list_Contract_states = contract_statedao.queryByType(type);
			
			for (Contract_state contract_state : list_Contract_states) {
				Contract contract = contractdao.queryByNum(contract_state.getConNumString());
				String string = contract.getNameString() + "&" + contract.getNumString() + "&" + DateUtils.udateToString(contract_state.getTime());
				resultList.add(string);
			}
			
			for(String string : resultList) {
				resultString = resultString + "|" + string;
			}
			
			DBUtils.commit();
			return resultString;
		} catch (Exception e) {
			DBUtils.rollback();
		}
		return null;
	}
	
	@Override
	public String searchByType(int type,String username) {
		String resultString = "";
		List<String> resultList = new ArrayList<String>();
		try {
			DBUtils.begin();
			
			int processType = 0;
			
			if (type == 6) {
				processType = 1;
			} else if (type == 3) {
				processType = 2;
			} else if (type == 4) {
				processType = 3;
			}
			List<Contract_state> list_Contract_states = contract_statedao.queryByType(type);
			
			for (Contract_state contract_state : list_Contract_states) {
				Contract contract = contractdao.queryByNum(contract_state.getConNumString());
				Contract_process contract_process = contract_ProcessDao.queryByTypeAndUserAndNum(username, processType, contract.getNumString());
				if (contract_process != null && contract_process.getState() == 0) {
					String string = contract.getNameString() + "&" + contract.getNumString() + "&" + DateUtils.udateToString(contract_state.getTime());
					resultList.add(string);
				}
			}
			
			for(String string : resultList) {
				resultString = resultString + "|" + string;
			}
			
			DBUtils.commit();
			return resultString;
		} catch (Exception e) {
			DBUtils.rollback();
		}
		return null;
	}

	@Override
	public String searchUserAndRole() {
		String resultString = "";
		List<String> resultList = new ArrayList<String>();
		try {
			DBUtils.begin();
			
			List<User> list_Users = userdao.queryAll();
			
			for (User user : list_Users) {
				List<Right> rights = rightdao.queryByUserName(user.getNameString());
				user.setRoleString(user.getNameString());
				if (rights != null) {
					for (Right right : rights) {
						user.setRoleString(user.getRoleString() + "&" + right.getRolenameString());
	 				}
				}
				resultList.add(user.getRoleString());
			}
			
			for(String string : resultList) {
				resultString = resultString + "|" + string;
			}
			
			DBUtils.commit();
			return resultString;
			
		} catch (Exception e) {
			DBUtils.rollback();
		}
		
		return null;
	}

	@Override
	public String searchUserAndRole(String condition) {
		String resultString = "";
		List<String> resultList = new ArrayList<String>();
		try {
			DBUtils.begin();
			
			List<User> list_Users = userdao.queryByCondition(condition);
			
			for (User user : list_Users) {
				List<Right> rights = rightdao.queryByUserName(user.getNameString());
				user.setRoleString(user.getNameString());
				if (rights != null) {
					for (Right right : rights) {
						user.setRoleString(user.getRoleString() + "&" + right.getRolenameString());
	 				}
				}
				resultList.add(user.getRoleString());
			}
			
			for(String string : resultList) {
				resultString = resultString + "|" + string;
			}
			
			DBUtils.commit();
			return resultString;
			
		} catch (Exception e) {
			DBUtils.rollback();
		}
		
		return null;
	}

	@Override
	public String searchRole() {
		String resultString = "";
		List<String> resultList = new ArrayList<String>();
		try {
			DBUtils.begin();
			
			List<Role> list_Roles = roledao.queryAll();
			for (Role role : list_Roles) {
				resultList.add(role.getNameString());
			}
			
			for(String string : resultList) {
				resultString = resultString + "|" + string;
			}
			
			DBUtils.commit();
			return resultString;
			
		} catch (Exception e) {
			DBUtils.rollback();
		}
		
		return "";
	}

	@Override
	public List<String> searchFenpei(String num) {
		List<String> resultList = new ArrayList<String>(2);
		try {
			DBUtils.begin();
			List<Role> list_Roles = roledao.queryAll();
			List<Role> list1 = new ArrayList<Role>();
			List<Role> list2 = new ArrayList<Role>();
			List<Role> list3 = new ArrayList<Role>();
			
			Function function1 = functiondao.queryByName("会签合同");
			Function function2 = functiondao.queryByName("审批合同");
			Function function3 = functiondao.queryByName("签订合同");
			
			String fun1 = function1.getId() + "";
			String fun2 = function2.getId() + "";
			String fun3 = function3.getId() + "";
			for (Role role:list_Roles) {
				String functions = role.getFunctionString();
				String[] funs = functions.split(",");
				for (String fun : funs) {
					if (fun.equals(fun1)) {
						list1.add(role);
					}
					if (fun.equals(fun2)) {
						list2.add(role);
					}
					if (fun.equals(fun3)) {
						list3.add(role);
					}
				}
 			}
			
			List<String> r1 = roleListToString(list1,num,1);
			List<String> r2 = roleListToString(list2,num,2);
			List<String> r3 = roleListToString(list3,num,3);
			
			String resultString1 = r1.get(0) + "|" + r2.get(0) + "|" + r3.get(0);
			String resultString2 = r1.get(1) + "|" + r2.get(1) + "|" + r3.get(1);
			
			resultList.add(resultString1);
			resultList.add(resultString2);
			
			DBUtils.commit();
			return resultList;
		} catch (Exception e) {
			DBUtils.rollback();
		}
		
		return null;
	}
	
	private List<String> roleListToString(List<Role> rolelist,String numString,int type) {//把拥有该类角色的所有user变成string
		String string = "";
		String string2 = "";
		List<String> userList = new ArrayList<String>();
		
		for (Role role : rolelist) {
			List<Right> list_Rights = rightdao.queryByRoleName(role.getNameString());
			for (Right right : list_Rights) {
				if (!userList.contains(right.getUsernameString())) {
					userList.add(right.getUsernameString());
				}
			}
		}
		
		
		for (String string3 : userList) {
			User user = userdao.queryByName(string3);
			if (user != null) {
				Contract_process contract_process = contract_ProcessDao.queryByTypeAndUserAndNum(user.getNameString(),type , numString);
				
				if (contract_process != null) {
					string2 = string2 + string3 + "&";
				} else {
					string = string + string3 + "&";
				}
			}
		}

		if (!string.isEmpty()) {
			string = string.substring(0, string.length() -1);
		}
		if (!string2.isEmpty()) {
			string2 = string2.substring(0, string2.length() -1);
		}
		

		List<String> list_Strings = new ArrayList<String>();
		list_Strings.add(string);
		list_Strings.add(string2);
		
		return list_Strings;
	}

	@Override
	public String searchLog(String username, String time) {
		try {
			DBUtils.begin();
			List<Log> result = new ArrayList<Log>();
			if (!username.equals("") && !time.equals("")) {//两个都不空
				List<User> list_Users = userdao.queryByCondition(username);
				for(User user : list_Users) {
					List<Log> list = logdao.queryByName(user.getId());
					for (Log log : list) {
						if (log.getTime().equals(DateUtils.stringToUDate(time))) {
							result.add(log);
						}
					}
				}
			} else if (!time.equals("")) {//只有time是不空
				List<Log> list_Logs = logdao.queryByTime(DateUtils.stringToUDate(time));
				result.addAll(list_Logs);
			} else if (!username.equals("")) {//只有user是不空
				List<User> list_Users = userdao.queryByCondition(username);
				for(User user : list_Users) {
					List<Log> list = logdao.queryByName(user.getId());
					result.addAll(list);
				}	
			}
			
			String resultString = "";
			for (Log log : result) {
				resultString = resultString + userdao.queryByID(log.getUser_id()).getNameString() + "&" + DateUtils.udateToString(log.getTime()) + "&" +
			log.getContentString() + "|";
			}
			resultString = resultString.substring(0,resultString.length() - 1 );
			
			DBUtils.commit();
			return resultString;
		} catch (Exception e) {
			DBUtils.rollback();
		}
		return "";
	}

	@Override
	public String searchFinal(String conditon) {
		try {
			DBUtils.begin();
			
			List<Contract> list = contractdao.queryByConditon(conditon);
			
			String string = "";
			for (Contract contract : list) {
				Contract_state contract_state = contract_statedao.queryByNum(contract.getNumString());
				string = string + contract.getNameString() + "&" + contract.getNumString()+ "&" + DateUtils.udateToString(contract_state.getTime()) + "|";
			}
			string = string.substring(0,string.length() - 1);
			
			DBUtils.commit();
			return string;
		} catch (Exception e) {
			DBUtils.rollback();
		}
		return "";
	}

	@Override
	public String searchFinal() {
		try {
			DBUtils.begin();
			
			List<Contract> list = contractdao.queryAll();
			System.out.println(list.size());
			String string = "";
			for (Contract contract : list) {
				Contract_state contract_state = contract_statedao.queryByNum(contract.getNumString());
				string = string + contract.getNameString() + "&" + contract.getNumString()+ "&" + DateUtils.udateToString(contract_state.getTime()) + "|";
			}
			string = string.substring(0,string.length() - 1);
			
			DBUtils.commit();
			return string;
		} catch (Exception e) {
			DBUtils.rollback();
		}
		return "";
	}

	@Override
	public String searchCustomer(String condition) {
		try {
			DBUtils.begin();
			
			List<Customer> list = customerdao.queryByCondition(condition);
			
			String string = "";
			for (Customer customer : list) {
				string = string + customer.getNameString() +  "&" + customer.getAccountString() +"&" + customer.getBankString() + 
						"&" + customer.getCodeString() + "&" + customer.getFax() + "&" + customer.getTelString() + "&" +
						customer.getAddressString() + "|";
			}
			
			string = string.substring(0,string.length() - 1);
			
			DBUtils.commit();
			return string;
		} catch (Exception e) {
			DBUtils.rollback();
		}
		return "";
	}

	@Override
	public String searchCustomer() {
		try {
			DBUtils.begin();
			
			List<Customer> list = customerdao.queryAll();
			
			String string = "";
			for (Customer customer : list) {
				string = string + customer.getNameString() +  "&" + customer.getAccountString() +"&" + customer.getBankString() + 
						"&" + customer.getCodeString() + "&" + customer.getFax() + "&" + customer.getTelString() + "&" + 
						customer.getAddressString() + "|";
			}
			
			string = string.substring(0,string.length() - 1);
			
			DBUtils.commit();
			return string;
		} catch (Exception e) {
			DBUtils.rollback();
		}
		return "";
	}

}
