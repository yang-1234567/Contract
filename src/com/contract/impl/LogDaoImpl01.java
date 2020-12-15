package com.contract.impl;

import java.util.Date;
import java.util.List;

import com.contract.advanced.LogRowMapper;
import com.contract.dao.LogDao;
import com.contract.entity.Log;
import com.contract.utils.DaoUtils;
import com.contract.utils.DateUtils;

public class LogDaoImpl01 implements LogDao {

	DaoUtils<Log> daoutils = new DaoUtils<Log>();
	
	@Override
	public int insert(Log log) {
		String sql = "insert into log(user_id,time,content) values(?,?,?)";
		Object[] args = {log.getUser_id(),DateUtils.uDateToSQLDate(log.getTime()),log.getContentString()};
		return daoutils.commonUpdate(sql, args);
	}

	@Override
	public int update(Log log) {
		String sql = "update log set user_id = ?,time = ?,content = ?,del = ? where id = ?";
		Object[] args = {log.getUser_id(),DateUtils.uDateToSQLDate(log.getTime()),log.getContentString(),log.getDel(),log.getId()};
		return daoutils.commonUpdate(sql, args);
	}

	@Override
	public List<Log> queryAll() {
		String sql = "select * from log where del = 0";
		List<Log> list = daoutils.commonQuery(sql, new LogRowMapper(), null);
		return list;
	}

	@Override
	public List<Log> queryByTime(Date time) {
		String sql = "select * from log where time = ? and del = 0";
		List<Log> list = daoutils.commonQuery(sql, new LogRowMapper(), DateUtils.uDateToSQLDate(time));
		return list;
	}

	@Override
	public List<Log> queryByName(int user_id) {
		String sql = "select * from log where user_id = ? and del = 0";
		List<Log> list = daoutils.commonQuery(sql, new LogRowMapper(), user_id);
		return list;
	}
}
