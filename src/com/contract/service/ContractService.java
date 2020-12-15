package com.contract.service;

import java.util.Date;

import org.json.simple.JSONObject;

import com.contract.entity.Contract;

public interface ContractService {
	public int qicao(Contract contract,Date qicaotime,String filename,String filetype);
	public int huiqian(String username,String connum,String time,String content);
	public JSONObject detail(String connum,JSONObject jsonObject);
	public JSONObject dingGaoDetail(String connum,JSONObject jsonObject);
	public int dinggao(String connum,String connent,String time,String username);
	public int shenpi(String connum,String connent,String time,String username,String type);
	public JSONObject qianDingDetail(String connum,JSONObject jsonObject);
	public int qianding(String connum,String connent,String time,String username);
}
