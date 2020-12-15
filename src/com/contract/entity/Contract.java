package com.contract.entity;

import java.util.Date;

public class Contract {

	private int id;
	private String numString;
	private String nameString;
	private int user_id;
	private String customerString;
	private Date beginTime;
	private Date endTime;
	private String contentString;
	private int del;
	
	public Contract() {
		id = 0;
		del = 0;
		customerString = null;
		user_id = 0;
	}

	public Contract(String numString) {
		super();
		this.numString = numString;
	}

	public Contract(String numString, String nameString, Date beginTime, Date endTime, String contentString) {
		super();
		this.numString = numString;
		this.nameString = nameString;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.contentString = contentString;
	}

	public Contract(String numString, String nameString, int user_id, Date beginTime, Date endTime,
			String contentString) {
		super();
		this.numString = numString;
		this.nameString = nameString;
		this.user_id = user_id;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.contentString = contentString;
	}

	public Contract(String numString, String nameString, int user_id, String customerString, Date beginTime,
			Date endTime, String contentString) {
		super();
		this.numString = numString;
		this.nameString = nameString;
		this.user_id = user_id;
		this.customerString = customerString;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.contentString = contentString;
	}

	public Contract(int id, String numString, String nameString, int user_id, String customerString, Date beginTime,
			Date endTime, String contentString, int del) {
		super();
		this.id = id;
		this.numString = numString;
		this.nameString = nameString;
		this.user_id = user_id;
		this.customerString = customerString;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.contentString = contentString;
		this.del = del;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumString() {
		return numString;
	}

	public void setNumString(String numString) {
		this.numString = numString;
	}

	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getCustomerString() {
		return customerString;
	}

	public void setCustomerString(String customerString) {
		this.customerString = customerString;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
		return "Contract [id=" + id + ", numString=" + numString + ", nameString=" + nameString + ", user_id=" + user_id
				+ ", customerString=" + customerString + ", beginTime=" + beginTime + ", endTime=" + endTime
				+ ", contentString=" + contentString + ", del=" + del + "]";
	}

	public boolean equals(Contract contract) {
		if (this.numString.equals(contract.getNumString())) {
			return true;
		} else {
			return false;
		}
	}
	
}
