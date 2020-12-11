package com.contract.database;

public class Function {
private int id;
private String num;
private String name;
private String URL;
private String description;
public Function(String num, String name, String uRL, String description, int del) {
	super();

	this.num = num;
	this.name = name;
	URL = uRL;
	this.description = description;
	this.del = del;
}
@Override
public String toString() {
	return "Function [id=" + id + ", num=" + num + ", name=" + name + ", URL=" + URL + ", description=" + description
			+ ", del=" + del + "]";
}
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
public String getURL() {
	return URL;
}
public void setURL(String uRL) {
	URL = uRL;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public int getDel() {
	return del;
}
public void setDel(int del) {
	this.del = del;
}
private int del;
}
