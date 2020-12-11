package com.contract.database;

import java.sql.Timestamp;

public class ContractPro {
private String conNum;
private String userName;
@Override
public String toString() {
	return "ContractPro [conNum=" + conNum + ", userName=" + userName + ", type=" + type + ", state=" + state
			+ ", content=" + content + ", time=" + time + "]";
}
public ContractPro(String conNum, String userName, int type, int state, String content, Timestamp time) {
	super();
	this.conNum = conNum;
	this.userName = userName;
	this.type = type;
	this.state = state;
	this.content = content;
	this.time = time;
}
private int type;
private int state;
private String content;
private Timestamp time;
public String getConNum() {
	return conNum;
}
public void setConNum(String conNum) {
	this.conNum = conNum;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
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
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Timestamp getTime() {
	return time;
}
public void setTime(Timestamp time) {
	this.time = time;
}

}
