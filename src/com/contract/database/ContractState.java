package com.contract.database;

import java.sql.Timestamp;

public class ContractState {
private int id;
private int type;
private Timestamp time;
private int del;
private String name;
private String num;
public String getNum() {
	return num;
}
public void setNum(String num) {
	this.num = num;
}

public ContractState(int type, Timestamp time, int del, String name, String num) {
	super();

	this.type = type;
	this.time = time;
	this.del = del;
	this.name = name;
	this.num = num;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
public Timestamp getTime() {
	return time;
}
public void setTime(Timestamp time) {
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
	return "ContractState [id=" + id + ", type=" + type + ", time=" + time + ", del=" + del + "]";
}

public ContractState(int id, int type, Timestamp time, int del) {
	super();
	this.id = id;
	this.type = type;
	this.time = time;
	this.del = del;
}

}
