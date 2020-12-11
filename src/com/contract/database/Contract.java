package com.contract.database;

import java.sql.Date;

public class Contract {

	private int id;
	private String num;
	private String name;
	private String user_id;
	private String customer;
	private String content;
	private Date beginTime;
	private Date endTime;
	private int del;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public int getDel() {
		return del;
	}
	
	public Contract(String num, String name, String user_id, String customer, String content, Date beginTime,
			Date endTime, int del) {
		super();
		
		this.num = num;
		this.name = name;
		this.user_id = user_id;
		this.customer = customer;
		this.content = content;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.del = del;
	}
	
	public void setDel(int del) {
		this.del = del;
	}
	@Override
	public String toString() {
		return "Contract [id=" + id + ", num=" + num + ", name=" + name + ", user_id=" + user_id + ", customer="
				+ customer + ", content=" + content + ", beginTime=" + beginTime + ", endTime=" + endTime + ", del="
				+ del + "]";
	}
	
	
	
}
