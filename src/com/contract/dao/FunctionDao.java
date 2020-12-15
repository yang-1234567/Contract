package com.contract.dao;

import java.util.List;

import com.contract.entity.Function;

public interface FunctionDao {
	public int insert(Function function);
	public int update(Function function);
	public int deleteByName(String name);
	public List<Function> queryAll();
	public Function queryByName(String name);
	public Function queryByNum(String num);
	public Function queryByID(int id);
}
