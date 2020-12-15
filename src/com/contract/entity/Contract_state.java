package com.contract.entity;

import java.util.Date;

public class Contract_state {

	private int id;
	private String connum;
	private int type;
	private Date time;
	private int del;
	
	public Contract_state() {
		del = 0;
		id = -1;
	}

	public Contract_state(String connum, int type, Date time) {
		super();
		this.connum = connum;
		this.type = type;
		this.time = time;
	}

	public Contract_state(int id, String connum, int type, Date time, int del) {
		super();
		this.id = id;
		this.connum = connum;
		this.type = type;
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
		return connum;
	}

	public void setConNumString(String connum) {
		this.connum = connum;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
		return "Contract_state [id=" + id + ", conNumString=" + connum + ", type=" + type + ", time=" + time
				+ ", del=" + del + "]";
	}

}
