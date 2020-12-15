package com.contract.entity;

public class Contract_User {

	private int id;
	private String connumString;
	private int user_id;
	private int type;
	private int del;
	
	public Contract_User() {
		id = 0;
		del = 0;
	}

	public Contract_User(String connumString, int user_id, int type) {
		super();
		this.connumString = connumString;
		this.user_id = user_id;
		this.type = type;
	}

	public Contract_User(int id, String connumString, int user_id, int type, int del) {
		super();
		this.id = id;
		this.connumString = connumString;
		this.user_id = user_id;
		this.type = type;
		this.del = del;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConnumString() {
		return connumString;
	}

	public void setConnumString(String connumString) {
		this.connumString = connumString;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}
	
	
}
