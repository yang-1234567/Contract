package com.contract.entity;

import java.util.Date;

public class Contract_process {

	private int id;
	private String conNumString;
	private int type;
	private int state;
	private String usernameString;
	private String contentString;
	private Date time;
	private int del;
	
	public Contract_process() {
		id = 0;
		del = 0;
	}

	public Contract_process(String conNumString, int type, int state, String usernameString, String contentString,
			Date time) {
		super();
		this.conNumString = conNumString;
		this.type = type;
		this.state = state;
		this.usernameString = usernameString;
		this.contentString = contentString;
		this.time = time;
	}

	public Contract_process(int id, String conNumString, int type, int state, String usernameString,
			String contentString, Date time, int del) {
		super();
		this.id = id;
		this.conNumString = conNumString;
		this.type = type;
		this.state = state;
		this.usernameString = usernameString;
		this.contentString = contentString;
		this.time = time;
		this.del = del;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConNumString() {
		return conNumString;
	}

	public void setConNumString(String conNumString) {
		this.conNumString = conNumString;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getUsernameString() {
		return usernameString;
	}

	public void setUsernameString(String usernameString) {
		this.usernameString = usernameString;
	}

	public String getContentString() {
		return contentString;
	}

	public void setContentString(String contentString) {
		this.contentString = contentString;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	@Override
	public String toString() {
		return "Contract_process [id=" + id + ", conNumString=" + conNumString + ", type=" + type + ", state=" + state
				+ ", usernameString=" + usernameString + ", contentString=" + contentString + ", time=" + time
				+ ", del=" + del + "]";
	}

}
