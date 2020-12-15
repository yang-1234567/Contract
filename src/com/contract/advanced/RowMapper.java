package com.contract.advanced;

import java.sql.ResultSet;
/**
 * ����
 * @author 76557
 *
 * @param <T>
 */
public interface RowMapper<T> {
	public T getRow(ResultSet resultSet);
}
