package com.contract.entity;

import java.util.Date;

public class Log {

	private int id;
	private int user_id;
	private Date time;
	private String contentString;
	private int del;
	
	public Log() {
		id = 0;
		del = 0;
	}

	public Log(int user_id, Date time, String contentString) {
		super();
		this.user_id = user_id;
		this.time = time;
		this.contentString = contentString;
	}

	public Log(int id, int user_id, Date time, String contentString, int del) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.time = time;
		this.contentString = contentString;
		this.del = del;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getContentString() {
		return contentString;
	}

	public void setContentString(String contentString) {
		this.contentString = contentString;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", user_id=" + user_id + ", time=" + time + ", contentString=" + contentString
				+ ", del=" + del + "]";
	}

}
