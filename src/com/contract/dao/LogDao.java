package com.contract.dao;

import java.util.Date;
import java.util.List;

import com.contract.entity.Log;

public interface LogDao {
	public int insert(Log log);
	public int update(Log log);
	public List<Log> queryAll();
	public List<Log> queryByTime(Date date);
	public List<Log> queryByName(int user_id);
}
