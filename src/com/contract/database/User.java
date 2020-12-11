package com.contract.database;

public class User {
private int id;
private String name;
private String password;

public User(String name, String password, int del) {
	super();

	this.name = name;
	this.password = password;
	this.del = del;
}
@Override
public String toString() {
	return "User [id=" + id + ", name=" + name + ", password=" + password + ", del=" + del + "]";
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public int getDel() {
	return del;
}
public void setDel(int del) {
	this.del = del;
}
private int del;
}
