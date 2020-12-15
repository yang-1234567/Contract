package com.contract.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;

import com.contract.dao.ContractDao;
import com.contract.dao.Contract_ProcessDao;
import com.contract.dao.Contract_StateDao;
import com.contract.dao.Contract_attachmentDao;
import com.contract.dao.LogDao;
import com.contract.dao.UserDao;
import com.contract.dao.impl.ContractDaoImpl01;
import com.contract.dao.impl.Contract_ProcessDapImpl01;
import com.contract.dao.impl.Contract_StateDaoImpl01;
import com.contract.dao.impl.Contract_attchmentDaoImpl01;
import com.contract.dao.impl.LogDaoImpl01;
import com.contract.dao.impl.UserDaoImpl01;
import com.contract.entity.Contract;
import com.contract.entity.Contract_attachment;
import com.contract.entity.Contract_process;
import com.contract.entity.Contract_state;
import com.contract.entity.Log;
import com.contract.entity.User;
import com.contract.service.ContractService;
import com.contract.utils.DBUtils;
import com.contract.utils.DateUtils;
import com.contract.utils.ServerUtils;

public class ContractSerivceImpl01 implements ContractService {

	private ContractDao contractdao = new ContractDaoImpl01();
	private Contract_StateDao contract_statedao = new Contract_StateDaoImpl01();
	private Contract_attachmentDao contract_attactmentdao = new Contract_attchmentDaoImpl01();
	private LogDao LogDao = new LogDaoImpl01();
	private UserDao userdao = new UserDaoImpl01();
	private Contract_ProcessDao contract_processdao = new Contract_ProcessDapImpl01();
		
	@Override
	public int qicao(Contract contract,Date qicaotime,String filename,String filetype) {
		int result = 0;
		try {
			DBUtils.begin();
			
			//合同表插入
			contractdao.insert(contract);
			
			//状态表插入
			Contract_state contract_state = new Contract_state(contract.getNumString(), 1, qicaotime);
			contract_statedao.insert(contract_state);
			
			DBUtils.commit();
			result = 1;
		} catch (Exception e) {
			DBUtils.rollback();
			result = 0;
		}
		
		try {
			DBUtils.begin();
			contract = contractdao.queryByNum(contract.getNumString());
			if (filename != null) {
				//附件表插入
				String pathString = "uploadfile/" + filename + filetype;
				Contract_attachment contract_attachment = new Contract_attachment(contract.getId(), filename, pathString, filetype, qicaotime);
				contract_attactmentdao.insert(contract_attachment);
			}
			
			//日志表中插入
			User user = userdao.queryByID(contract.getUser_id());
			String content = DateUtils.udateToString(qicaotime) + ": " + user.getNameString() + "起草了" + contract.getNameString() + ".";
			Log log = new Log(user.getId(), qicaotime, content);
			LogDao.insert(log);
			
			DBUtils.commit();
			result = 2;
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
			DBUtils.rollback();
			result = 0;
		}
		
		
		return result;
	}

	@Override
	public int huiqian(String username, String connum, String time, String content) {
		int result = 0;
		try {
			DBUtils.begin();
			
			Contract_process contract_process = contract_processdao.queryByTypeAndUserAndNum(username, 1, connum);
			contract_process.setState(1);
			contract_process.setTime(DateUtils.stringToUDate(time));
			contract_process.setContentString(content);
			contract_processdao.update(contract_process);
			
			DBUtils.commit();
			result = 1;
		} catch (Exception e) {
			DBUtils.rollback();
			result = 0;
		}
		
		try {
			DBUtils.begin();
			
			List<Contract_process> list = contract_processdao.queryByNumAndType(connum, 1);
			boolean flag = true;
			for (Contract_process contract_process : list) {
				if (contract_process.getState() != 1) {
					flag = false;
					break;
				}
			}
			
			if (flag) {
				Contract_state contract_state = contract_statedao.queryByNum(connum);
				contract_state.setTime(DateUtils.stringToUDate(time));
				contract_state.setType(2);
				contract_statedao.update(contract_state);
			}
			
			Contract contract = contractdao.queryByNum(connum);
			User user = userdao.queryByName(username);
			String contentss = time + ": " + user.getNameString() + "会签了" + contract.getNameString() + ".";
			Log log = new Log(user.getId(), DateUtils.stringToUDate(time), contentss);
			LogDao.insert(log);
			
			DBUtils.commit();
			result = 2;
		} catch (Exception e) {
			DBUtils.rollback();
			result = 0;
		}
		
		return result;
	}

	@Override
	public JSONObject detail(String connum, JSONObject jsonObject) {
		try {
			DBUtils.begin();
			
			Contract contract = contractdao.queryByNum(connum);
			Contract_state contract_state = contract_statedao.queryByNum(connum);
			User user = userdao.queryByID(contract.getUser_id());
			Contract_attachment contract_attachment = contract_attactmentdao.queryByContractId(contract.getId());
			
			jsonObject.put("contract_name", contract.getNameString());
			String customer = contract.getCustomerString();
			if (customer == null || customer.equals("null")) customer = "";
			jsonObject.put("customer", customer);
			jsonObject.put("begintime", DateUtils.udateToString(contract.getBeginTime()));
			jsonObject.put("endtime", DateUtils.udateToString(contract.getEndTime()));
			jsonObject.put("qicaoren", user.getNameString());
			List<Contract_process> dinggao = contract_processdao.queryByNumAndType(connum, 4);
			if (dinggao.isEmpty()) {
				jsonObject.put("dinggaoren", "");
			} else {
				jsonObject.put("dinggaoren", dinggao.get(0).getUsernameString());
			}
			
			jsonObject.put("contract_state", contract_state.getType() + "");
			jsonObject.put("contract_content", contract.getContentString());
			
			List<Contract_process> huiqian = contract_processdao.queryByNumAndType(connum, 1);
			String huiqianString = "";
			if (!huiqian.isEmpty()) {
				for (Contract_process contract_process : huiqian) {
					huiqianString = huiqianString + contract_process.getUsernameString() + "&" + contract_process.getState() + "|";
				}
				huiqianString = huiqianString.substring(0,huiqianString.length() - 1);
			}
			jsonObject.put("huiqian", huiqianString);
			
			List<Contract_process> shenpi = contract_processdao.queryByNumAndType(connum, 1);
			String shenpiString = "";
			if (!shenpi.isEmpty()) {
				for (Contract_process contract_process : shenpi) {
					shenpiString = shenpiString + contract_process.getUsernameString() + "&" + contract_process.getState() + "|";
				}
				shenpiString = shenpiString.substring(0,shenpiString.length() - 1);
			}
			jsonObject.put("shenpi", shenpiString);
			
			List<Contract_process> qianding = contract_processdao.queryByNumAndType(connum, 1);
			String qiandingString = "";
			if (!qianding.isEmpty()) {
				for (Contract_process contract_process : qianding) {
					qiandingString = qiandingString + contract_process.getUsernameString() + "&" + contract_process.getState() + "|";
				}
				qiandingString = qiandingString.substring(0,qiandingString.length() - 1);
			}		
			jsonObject.put("qianding", qiandingString);
			jsonObject.put("result", "OK");
			
			if (contract_attachment != null) {
				jsonObject.put("filestring", ServerUtils.filetoString(new File(contract_attachment.getPathString())));
				jsonObject.put("filename", contract_attachment.getFilenameString());
				jsonObject.put("filetype", contract_attachment.getType());
			}
			
			DBUtils.commit();
			return jsonObject;
		} catch (Exception e) {
			DBUtils.rollback();
		}
		
		return jsonObject;
	}

	@Override
	public JSONObject dingGaoDetail(String connum, JSONObject jsonObject) {
		try {
			DBUtils.begin();
			Contract contract = contractdao.queryByNum(connum);
			
			jsonObject.put("contract_name", contract.getNameString());
			String customer = contract.getCustomerString();
			if (customer == null || customer.equals("null")) customer = "";
			jsonObject.put("customer", customer);
			jsonObject.put("begintime", DateUtils.udateToString(contract.getBeginTime()));
			jsonObject.put("endtime", DateUtils.udateToString(contract.getEndTime()));
			jsonObject.put("contract_content", contract.getContentString());
			jsonObject.put("result", "OK");
			
			List<Contract_process> list = contract_processdao.queryByNumAndType(connum, 1);
			String resultString = "";
			for (Contract_process contract_process : list) {
				resultString = resultString + contract_process.getUsernameString() + "&" + contract_process.getContentString() + "|";
			}
			resultString = resultString.substring(0, resultString.length() -1 );
			jsonObject.put("resultString", resultString);
			
			DBUtils.commit();
			return jsonObject;
		} catch (Exception e) {
			DBUtils.rollback();
			
		}
		return null;
	}

	@Override
	public int dinggao(String connum, String connent, String time, String username) {
		try {
			DBUtils.begin();
			
			Contract contract = contractdao.queryByNum(connum);
			contract.setContentString(connent);
			contractdao.update(contract);
			
			Contract_process contract_process = new Contract_process(connum, 4, 1, username, "定稿", DateUtils.stringToUDate(time));
			contract_processdao.insert(contract_process);
			
			Contract_state contract_state = contract_statedao.queryByNum(connum);
			contract_state.setType(3);
			contract_state.setTime(DateUtils.stringToUDate(time));
			contract_statedao.update(contract_state);
			
			User user = userdao.queryByName(username);
			String contentss = time + ": " + user.getNameString() + "定稿了" + contract.getNameString() + ".";
			Log log = new Log(user.getId(), DateUtils.stringToUDate(time), contentss);
			LogDao.insert(log);
			
			DBUtils.commit();
			return 1;
		} catch (Exception e) {
			DBUtils.rollback();
		}
		return 0;
	}

	@Override
	public int shenpi(String connum, String connent, String time, String username, String type) {
		int result = 0;
		try {
			DBUtils.begin();
			
			Contract_process contract_process = contract_processdao.queryByTypeAndUserAndNum(username, 2, connum);
			
			if (type.equals("1")) {
				contract_process.setState(1);
			} else {
				contract_process.setState(2);
			}
			contract_process.setTime(DateUtils.stringToUDate(time));
			contract_process.setContentString(connent);
			contract_processdao.update(contract_process);
			
			
			DBUtils.commit();
			result = 1;
		} catch (Exception e) {
			DBUtils.rollback();
			result = 0;
		}
		
		try {
			DBUtils.begin();
			
			List<Contract_process> list = contract_processdao.queryByNumAndType(connum, 2);
			boolean flag = true;
			for (Contract_process contract_process : list) {
				if (contract_process.getState() != 1) {
					flag = false;
					break;
				}
			}
			
			if (flag) {
				Contract_state contract_state = contract_statedao.queryByNum(connum);
				contract_state.setTime(DateUtils.stringToUDate(time));
				contract_state.setType(4);
				contract_statedao.update(contract_state);
			}
			
			Contract contract = contractdao.queryByNum(connum);
			User user = userdao.queryByName(username);
			String contentss = time + ": " + user.getNameString() + "审批了" + contract.getNameString() + ".";
			Log log = new Log(user.getId(), DateUtils.stringToUDate(time), contentss);
			LogDao.insert(log);
			
			DBUtils.commit();
			result = 2;
		} catch (Exception e) {
			DBUtils.rollback();
			result = 0;
		}
		
		return result;
	}

	@Override
	public JSONObject qianDingDetail(String connum, JSONObject jsonObject) {
		try {
			DBUtils.begin();
			Contract contract = contractdao.queryByNum(connum);
			
			jsonObject.put("contract_name", contract.getNameString());
			String customer = contract.getCustomerString();
			if (customer == null || customer.equals("null")) customer = "";
			jsonObject.put("customer", customer);
			jsonObject.put("result", "OK");
			
			DBUtils.commit();
			return jsonObject;
		} catch (Exception e) {
			DBUtils.commit();
		}
		return null;
	}

	@Override
	public int qianding(String connum, String connent, String time, String username) {
		int result = 0;
		try {
			DBUtils.begin();
			
			Contract_process contract_process = contract_processdao.queryByTypeAndUserAndNum(username, 3, connum);
			contract_process.setState(1);
			contract_process.setTime(DateUtils.stringToUDate(time));
			contract_process.setContentString(connent);
			contract_processdao.update(contract_process);
			
			DBUtils.commit();
			result = 1;
		} catch (Exception e) {
			DBUtils.rollback();
			result = 0;
		}
		
		try {
			DBUtils.begin();
			
			List<Contract_process> list = contract_processdao.queryByNumAndType(connum, 1);
			boolean flag = true;
			for (Contract_process contract_process : list) {
				if (contract_process.getState() != 1) {
					flag = false;
					break;
				}
			}
			
			if (flag) {
				Contract_state contract_state = contract_statedao.queryByNum(connum);
				contract_state.setTime(DateUtils.stringToUDate(time));
				contract_state.setType(5);
				contract_statedao.update(contract_state);
			}
			
			Contract contract = contractdao.queryByNum(connum);
			User user = userdao.queryByName(username);
			String contentss = time + ": " + user.getNameString() + "签订了" + contract.getNameString() + ".";
			Log log = new Log(user.getId(), DateUtils.stringToUDate(time), contentss);
			LogDao.insert(log);
			
			DBUtils.commit();
			result = 2;
		} catch (Exception e) {
			DBUtils.rollback();
			result = 0;
		}
		
		return result;
	}	
}
