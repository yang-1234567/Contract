package com.contract.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.contract.advanced.ContractRowMapper;
import com.contract.advanced.RowMapper;
import com.contract.entity.Contract;
import com.contract.entity.User;
import com.contract.service.ContractService;
import com.contract.service.SearchService;
import com.contract.service.UserService;
import com.contract.service.impl.ContractSerivceImpl01;
import com.contract.service.impl.SearchServiceImpl01;
import com.contract.service.impl.UserServiceImpl01;

public class ServerUtils {

	private static UserService userService = new UserServiceImpl01();
	private static ContractService contractService = new ContractSerivceImpl01();
	private static SearchService searchService = new SearchServiceImpl01();
	private static int i = 0;
	
	public static void close(Closeable... closeables ) {
		for(Closeable closeable : closeables) {
			if (closeable != null) {
				try {
					closeable.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String filetoString(File file) {
		try {
			InputStream iStream = new BufferedInputStream(new FileInputStream(file));
			ByteArrayOutputStream oStream = new ByteArrayOutputStream();
			
			byte[] b = new byte[1024];
			int len = -1;
			while((len = iStream.read(b)) != -1) {
				oStream.write(b, 0, len);
			}
			
			b = oStream.toByteArray();
			oStream.close();
			iStream.close();
			
			return new String(b, "ISO-8859-1");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void StringToFile(String string,String path) {
		File file = new File(path);
		try {
			file.createNewFile();
			
			OutputStream oStream2 = new BufferedOutputStream(new FileOutputStream(file));
	
			oStream2.write(string.getBytes("ISO-8859-1"));
			oStream2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	
	public static String getBackJsonObject(String string) {
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) new JSONParser().parse(string);
			if (jsonObject != null ) {
				String operation = (String) jsonObject.get("operation");
				int ope = operation(operation);
				jsonObject = reslove(ope, jsonObject);
			} 
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonObject.toJSONString();
	}

	private static int operation(String operation){
		if (operation.equals("login")) {
			return 1;
		} else if (operation.equals("register")) {
			return 2;
		} else if (operation.equals("qicao")) {
			return 3;
		} else if (operation.equals("contract_query")) {
			return 4;
		} else if (operation.equals("contract_queryall")) {
			return 5;
		} else if (operation.equals("add_new_role")) {
			return 6;
		} else if (operation.equals("user_norole_queryall")) {
			return 7;
		} else if (operation.equals("user_norole_query")) {
			return 8;
		} else if (operation.equals("role_queryall")) {
			return 9;
		} else if (operation.equals("role_fenpei")) {
			return 10;
		} else if (operation.equals("user_queryall_fenpei")){
			return 11;
		} else if (operation.equals("user_contract_allocate")) {
			return 12;
		} else if (operation.equals("contract_ope_query")){
			return 13;
		} else if (operation.equals("contract_ope_queryall")) {
			return 14;
		} else if (operation.equals("sign")) {
			return 15;
		} else if (operation.equals("contract_detail")){
			return 16;
		} else if (operation.equals("finalize")) {
			return 17;
		} else if (operation.equals("finalize_commit")) {
			return 18;
		} else if (operation.equals("approve")) {
			return 19;
		} else if (operation.equals("signing")) {
			return 20;
		} else if (operation.equals("signing_commit")) {
			return 21;
		} else if (operation.equals("log_query")){
			return 22;
		} else if (operation.equals("contract_info_query")) {
			return 23;
		} else if (operation.equals("contract_info_queryall")) {
			return 24;
		} else if (operation.equals("customer_add")) {
			return 25;
		} else if (operation.equals("customer_query")) {
			return 26;
		} else if (operation.equals("customer_queryall")) {
			return 27;
		}// 继续写操作
		return 0;
	}
	
	private static JSONObject reslove(int ope,JSONObject jsonObject) {
		//System.out.println("收到的" + jsonObject);
		JSONObject jsonObject2 = new JSONObject();
		switch (ope) {
		case 1://登录
			User user = userService.login((String)jsonObject.get("user_name"),(String)jsonObject.get("user_password"));
			jsonObject2.put("operation","login");
			jsonObject2.put("result", user.getResult() + "");
			jsonObject2.put("id", user.getId() + "");
			jsonObject2.put("functions", user.getFunctionString());
			jsonObject2.put("roles", user.getRoleString());
			jsonObject2.put("biaoshifu", i++);
			break;
		case 2://注册
			int result = userService.register((String)jsonObject.get("user_name"),(String)jsonObject.get("user_password"));
			jsonObject2.put("operation", "register");
			jsonObject2.put("result", result + "");
			jsonObject2.put("biaoshifu", i++);
			break;
		case 3: //起草文件
			jsonObject2.put("operation","qicao");
			jsonObject2.put("biaoshifu", i++);
			int user_id = Integer.valueOf((String) jsonObject.get("user_id"));
			Contract contract = new Contract((String)jsonObject.get("contract_num"),(String) jsonObject.get("contract_name"),user_id ,
					(String) jsonObject.get("customer"),DateUtils.stringToUDate((String) jsonObject.get("starttime")),
					DateUtils.stringToUDate((String) jsonObject.get("endtime")),(String) jsonObject.get("content"));
			Date qicaotime = DateUtils.stringToUDate((String) jsonObject.get("qicaotime"));
			String filebyte = (String)jsonObject.get("filebyte");
			String file_name = (String)jsonObject.get("file_name");
			String file_type = (String)jsonObject.get("file_type");
			if (file_name != null) {
				StringToFile(filebyte, "uploadfile/" + file_name + file_type );
			}
			int rescult = contractService.qicao(contract,qicaotime,file_name,file_type);
			if (rescult == 2) {
				jsonObject2.put("result", "OK");
			} else {
				jsonObject2.put("result","false");
			}
			break;
		case 4://查询待分配合同
			jsonObject2.put("operation", "contract_query");
			String string = searchService.searchContract(Integer.valueOf((String)jsonObject.get("type")), (String)jsonObject.get("condition"));
			jsonObject2.put("result", string);
			jsonObject2.put("biaoshifu", i++);
			break;
		case 5://查询所有待分配合作
			jsonObject2.put("operation", "contract_queryall");
			String string2 = searchService.searchByType(Integer.valueOf((String)jsonObject.get("type")));
			jsonObject2.put("result", string2);
			jsonObject2.put("biaoshifu", i++);
			break;
		case 6://添加新角色
			jsonObject2.put("operation","add_new_role");
			jsonObject2.put("biaoshifu", i++);
			int result2 = userService.addNewRole((String)jsonObject.get("role_name"), (String)jsonObject.get("description"), (String)jsonObject.get("function"),
					(String)jsonObject.get("user_id"),(String)jsonObject.get("time"));
			jsonObject2.put("result", result2 + "");
			break;
		case 7://查询所有角色以及用户
			jsonObject2.put("operation", "user_norole_queryall");
			jsonObject2.put("biaoshifu", i++);
			jsonObject2.put("result", searchService.searchUserAndRole());
			break;
		case 8://查询指定条件的角色以及用户
			jsonObject2.put("operation","user_norole_query");
			jsonObject2.put("biaoshifu", i++);
			jsonObject2.put("result", searchService.searchUserAndRole((String)jsonObject.get("condition")));
			break;
		case 9://查询所有角色
			jsonObject2.put("operation", "role_queryall");
			jsonObject2.put("biaoshifu", i++);
			jsonObject2.put("result", searchService.searchRole());
			break;
		case 10://分配角色
			jsonObject2.put("operation", "role_fenpei");
			jsonObject2.put("biaoshifu", i++);
			int result3 = userService.addNewRoleForUser((String)jsonObject.get("user_name"), (String)jsonObject.get("role_name"), (String)jsonObject.get("user_id"),
					(String)jsonObject.get("time"));
			jsonObject2.put("result", result3 + "");
			break;	
		case 11://查询可以分配的所有角色
			jsonObject2.put("operation", "user_queryall_fenpei");
			jsonObject2.put("biaoshifu", i++);
			List<String> list = searchService.searchFenpei((String)jsonObject.get("contract_num"));
			jsonObject2.put("result", list.get(0));
			jsonObject2.put("result2", list.get(1));
			break;
		case 12://合同分配
			jsonObject2.put("operation", "user_contract_allocate");
			jsonObject2.put("biaoshifu", i++);
			int result4 = userService.FeiPei((String)jsonObject.get("contract_num"), (String)jsonObject.get("result"),(String)jsonObject.get("user_id"),
					(String)jsonObject.get("final"));
			jsonObject2.put("result", result4 + "");
			break;
		case 13://查询操作合同
			jsonObject2.put("operation", "contract_ope_query");
			String string3 = searchService.searchContract(Integer.valueOf((String)jsonObject.get("type")), (String)jsonObject.get("condition"),
					(String)jsonObject.get("user_name"));
			if (string3 == null) {
				string3 = "";
			}
			jsonObject2.put("result", string3);
			jsonObject2.put("biaoshifu", i++);
			break;
		case 14://查询所有操作合同
			jsonObject2.put("operation", "contract_ope_queryall");
			String string4 = searchService.searchByType(Integer.valueOf((String)jsonObject.get("type")),(String)jsonObject.get("user_name"));
			if (string4 == null) {
				string4 = "";
			}
			jsonObject2.put("result", string4);
			jsonObject2.put("biaoshifu", i++);
			break;
		case 15://会签
			jsonObject2.put("operation", "sign");
			jsonObject2.put("biaoshifu", i++);
			int result5 = contractService.huiqian((String)jsonObject.get("user_name"), (String)jsonObject.get("contract_num"), 
					(String)jsonObject.get("huiqiantime"), (String)jsonObject.get("content"));
			System.out.println(result5);
			if (result5 == 2) {
				jsonObject2.put("result", "OK");
			} else {
				jsonObject2.put("result","false");
			}
			break;
		case 16://查看合同具体信息
			jsonObject2.put("operation", "contract_detail");
			jsonObject2.put("biaoshifu", i++);
			jsonObject2 = contractService.detail((String)jsonObject.get("contract_num"), jsonObject2);
			break;
		case 17://点击定稿
			jsonObject2.put("operation", "finalize");
			jsonObject2.put("biaoshifu", i++);
			jsonObject2 = contractService.dingGaoDetail((String)jsonObject.get("contract_num"), jsonObject2);
			break;
		case 18://定稿提交
			jsonObject2.put("operation", "finalize_commit");
			jsonObject2.put("biaoshifu", i++);
			int result6 = contractService.dinggao((String)jsonObject.get("contract_num"), (String)jsonObject.get("content"), 
					(String)jsonObject.get("dinggaotime"), (String)jsonObject.get("user_name"));
			jsonObject2.put("result", result6 + "");
			break;
		case 19://审批合同
			jsonObject2.put("operation", "qpprove");
			jsonObject2.put("biaoshifu", i++);
			int result7 = contractService.shenpi((String)jsonObject.get("contract_num"), (String)jsonObject.get("content"), 
					(String)jsonObject.get("shenpitime"), (String)jsonObject.get("user_name"),(String)jsonObject.get("shenpi_result"));
			jsonObject2.put("result", result7 + "");
			break;
		case 20://点击签订
			jsonObject2.put("operation", "signing");
			jsonObject2.put("biaoshifu", i++);
			jsonObject2 = contractService.qianDingDetail((String)jsonObject.get("contract_num"), jsonObject2);
			break;
		case 21://签订提交
			jsonObject2.put("operation", "signing_commit");
			jsonObject2.put("biaoshifu", i++);
			int result8 = contractService.qianding((String)jsonObject.get("contract_num"), (String)jsonObject.get("content"), 
					(String)jsonObject.get("qiandingtime"), (String)jsonObject.get("user_name"));
			jsonObject2.put("result", result8 + "");
			break;
		case 22://日志查询
			jsonObject2.put("operation", "log_query");
			jsonObject2.put("biaoshifu", i++);
			String string5 = searchService.searchLog((String)jsonObject.get("condition"), (String)jsonObject.get("condition_date"));
			jsonObject2.put("result", string5);
			break;
		case 23://合同信息查询
			jsonObject2.put("operation", "contract_info_query");
			jsonObject2.put("biaoshifu", i++);
			String string6 = searchService.searchFinal((String)jsonObject.get("condition"));
			jsonObject2.put("result", string6);
			break;
		case 24://合同信息全部查询
			jsonObject2.put("operation", "contract_info_queryall");
			jsonObject2.put("biaoshifu", i++);
			String string7 = searchService.searchFinal();
			jsonObject2.put("result", string7);
			break;
		case 25://客户增加
			jsonObject2.put("operation", "customer_add");
			jsonObject2.put("biaoshifu", i++);
			int result9 = userService.addCustomer(jsonObject);
			jsonObject2.put("result", result9 + "");
			break;
		case 26://客户查询
			jsonObject2.put("operation", "customer_query");
			jsonObject2.put("biaoshifu", i++);
			String string8 = searchService.searchCustomer((String)jsonObject.get("condition"));
			jsonObject2.put("result", string8);
			break;
		case 27://客户全部查询
			jsonObject2.put("operation", "customer_queryall");
			jsonObject2.put("biaoshifu", i++);
			String string9 = searchService.searchCustomer();
			jsonObject2.put("result", string9);
			break;
		}
		
		
		return jsonObject2;
	}
	
}
