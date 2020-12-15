package com.contract.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;

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
import com.contract.service.UserService;
import com.contract.utils.DBUtils;
import com.contract.utils.DateUtils;

public class UserServiceImpl01 implements UserService {

	private UserDao userdao = new UserDaoImpl01();
	private FunctionDao functiondao = new FunctionDaoImpl01();
	private RoleDao roledao = new RoleDaoImpl01();
	private RightDao rightdao = new RightDaoImpl01();
	private LogDao logdao = new LogDaoImpl01();
	private Contract_ProcessDao contract_ProcessDao = new Contract_ProcessDapImpl01();
	private ContractDao contractdao = new ContractDaoImpl01();
	private Contract_StateDao contract_statedao = new Contract_StateDaoImpl01();
	private CustomerDao customerdao = new CustomerDaoImpl01();
	
	@Override
	public User login(String name, String password) {
		User user = null;
		try {
			DBUtils.begin();
			user = userdao.queryByName(name);
			
			if (user == null) {//用户名不存在
				user = new User();
				user.setResult(101);
				return user;
			}
			
			user  = userdao.query(name, password);
			if (user == null) { // 密码错误
				user = new User();
				user.setResult(102);
				return user;
			}
			
		
			user.setResult(100);
			List<Right> rights = rightdao.queryByUserName(name);
			List<String> functionall = new ArrayList<String>();
			
			if (rights != null) {
				for (Right right : rights) {
					user.setRoleString(user.getRoleString() + "|" + right.getRolenameString());
					Role role = roledao.queryByName(right.getRolenameString());
					String functionString = role.getFunctionString();
					String[] functions = functionString.split(",");
					
					for (String string:functions) {
						if (!functionall.contains(string)) {
							functionall.add(string);
						}
					}
 				}
			}
			
			for (String string:functionall) {
				Function function = functiondao.queryByID(Integer.valueOf(string));
				user.setFunctionString(user.getFunctionString() + "|" + function.getNameString());
			}
			
			DBUtils.commit();
			return user;
		} catch (Exception e) {
			e.getStackTrace();
			DBUtils.rollback();
		}
		
		user = new User();
		user.setResult(0);
		return null;
	}

	@Override
	public int register(String name, String password) {
		try {
			DBUtils.begin();
			
			User user = userdao.queryByName(name);
			
			if (user != null) {//用户名已经存在
				return 201;
			}
			
			user = new User(name, password);
			
			userdao.insert(user);
			
			DBUtils.commit();
			return 200;
			
		} catch (Exception e) {
			e.getStackTrace();
			DBUtils.rollback();
		}
		return 0;
	}

	@Override
	public int addNewRole(String rolenameString, String description, String functions,String user_id,String time) {
		try {
			DBUtils.begin();
			
			int result = 0;	
			if (rolenameString == null || rolenameString.equals("")) {
				result = 301;
			} else if (description == null || description.equals("")) {
				result = 302;
			} else if (functions == null || functions.equals("")) {
				result = 303;
			} else {
				String[] funs = functions.split("\\|");
				String idString = "";
				for (String string : funs) {
					Function function = functiondao.queryByName(string);
					if (function != null) {
						idString = idString + "," + function.getId();						
					}
				}
				idString = idString.substring(1);
				
				Role role = new Role(rolenameString, description, idString);
				
				roledao.insert(role);
				User user = userdao.queryByID(Integer.valueOf(user_id));
				String content = time + ": " + user.getNameString() + "新加了角色" + role.getNameString() + ".";
				Log log = new Log(Integer.valueOf(user_id), DateUtils.stringToUDate(time), content);
				logdao.insert(log);
				
				result = 300;
			}
			DBUtils.commit();
			return result;
		} catch (Exception e) {
			DBUtils.rollback();
		}
		return 0;
	}

	@Override
	public int addNewRoleForUser(String username, String rolename,String user_id,String time) {
		try {
			DBUtils.begin();
			
			int result = 0;
			
			Right right = rightdao.queryByUserAndRole(username, rolename);
			
			if (right != null) {
				result = 401;
			} else {
				Right right2 = new Right(username, rolename, "好家伙!真不错!");
				rightdao.insert(right2);
				
				User user = userdao.queryByID(Integer.valueOf(user_id));
				String content = time + ": " + user.getNameString() + "为用户" + username + "赋予了" + rolename + "的权限."; 
				Log log = new Log(Integer.valueOf(user_id), DateUtils.stringToUDate(time), content);
				logdao.insert(log);
				
				result = 400;
			}
			
			DBUtils.commit();
			return result;
		} catch (Exception e) {
			DBUtils.rollback();
		}
		
		
		
		return 0;
	}

	@Override
	public int FeiPei(String numString, String resultString,String userid,String finalstate) {
		try {
			DBUtils.begin();
			int result = 0;
			
			List<String> grants = getUserGrant(userid);
			String[] roles = resultString.split("\\|");
			List<List<String>> lists = new ArrayList<List<String>>();
		
			for (int i = 0;i < 3 ;i++ ) {
				lists.add(new ArrayList<String>());
			}
			
			for (int i = 0;i < roles.length;i++) {
				String[] users = roles[i].split("&");
				for (String string : users) {
					if (!string.equals("")) {
						lists.get(i).add(string);
					}
				}
			}
			for (int i=0;i < 3;i++) {
				List<Contract_process> list = contract_ProcessDao.queryByNumAndType(numString, i+1);

				List<String> list2 = new ArrayList<String>();
				for (Contract_process contractuser: list) {
					User user = userdao.queryByName(contractuser.getUsernameString());
					if (user != null)
						list2.add(user.getNameString());
				}
				
				
				if (!lists.get(i).isEmpty()) {
					if (i == 0 && !grants.contains("分配会签")) {
						return 501;
					}
					if (i == 0 && !grants.contains("分配审批")) {
						return 502;
					}
					if (i == 0 && !grants.contains("分配签订")) {
						return 503;
					}
					
					for (Contract_process  contract_User : list) {
						User user = userdao.queryByName(contract_User.getUsernameString());
						if (!lists.get(i).contains(user.getNameString())) {
							contract_User.setDel(1);
							contract_ProcessDao.update(contract_User);
						}
					}
					
					for (String string : lists.get(i)) {
						if (!list2.contains(string)) {
							User user = userdao.queryByName(string);
							Contract_process contract_User = contract_ProcessDao.queryByTypeAndUserAndNumWithDel(user.getNameString(), i + 1, numString);
							if (contract_User != null) {
								contract_User.setDel(0);
								contract_ProcessDao.update(contract_User);
							} else {
								Contract_process contract_User2 = new Contract_process(numString, i + 1, 0, user.getNameString(), "没啥好说的", new Date());
								contract_ProcessDao.insert(contract_User2);
							}
						}
					}
					
				} else {
					for (Contract_process  contract_User : list) {
						User user = userdao.queryByName(contract_User.getUsernameString());
						contract_User.setDel(1);
						contract_ProcessDao.update(contract_User);
					}
				}
			}
		
			User user = userdao.queryByID(Integer.valueOf(userid));
			Contract contract = contractdao.queryByNum(numString);
			String content = DateUtils.udateToString(new Date()) + ": " + user.getNameString() + "分配了合同" + contract.getNameString() + "." ; 
			Log log = new Log(Integer.valueOf(userid), new Date(), content);
			logdao.insert(log);
			
			if (finalstate.equals("1")) {
				Contract_state contract_state = contract_statedao.queryByNum(numString);
				contract_state.setType(6);
				contract_state.setTime(new Date());
				contract_statedao.update(contract_state);
			}
			
			result = 500;
			
			DBUtils.commit();
			return result;
		} catch (Exception e) {
			DBUtils.rollback();
		}
		
		return 0;
	}
	
	private List<String> getUserGrant(String userid){
		List<String> grantList = new ArrayList<String>();
		User user = userdao.queryByID(Integer.valueOf(userid));
		
		List<Right> rights = rightdao.queryByUserName(user.getNameString());
		
		for (Right right : rights) {
			Role role = roledao.queryByName(right.getRolenameString());
			String string = role.getFunctionString();
			String[] funs = string.split(",");
			for (String fun:funs) {
				Function function = functiondao.queryByID(Integer.valueOf(fun));
				if (!grantList.contains(function.getNameString())) {
					grantList.add(function.getNameString());
				}
			}
 		}
		return grantList;
	}

	@Override
	public int addCustomer(JSONObject jsonObject) {
		try {
			DBUtils.begin();
			
			Customer customer = new Customer((String)jsonObject.get("customer_num"), (String)jsonObject.get("name"), 
					(String)jsonObject.get("address"), (String)jsonObject.get("tel"), (String)jsonObject.get("fax"), 
					(String)jsonObject.get("email"), (String)jsonObject.get("bank"), (String)jsonObject.get("account"));
			customerdao.insert(customer);
			
			User user = userdao.queryByName((String)jsonObject.get("user_name"));
			String content = DateUtils.udateToString(new Date()) + ": " + user.getNameString() + "添加了新客户" + customer.getNameString() + "." ; 
			Log log = new Log(user.getId(), new Date(), content);
			logdao.insert(log);
			
			DBUtils.commit();
			return 2;
		} catch (Exception e) {
			DBUtils.rollback();
		}
		return 0;
	}

}
