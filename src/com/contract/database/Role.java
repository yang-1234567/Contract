package com.contract.database;

public class Role {
private int id;
private int del;
private String name;
private String description;
public Role(String name, String description,int del) {
	super();

	this.del = del;
	this.name = name;
	this.description = description;
}
@Override
public String toString() {
	return "Role [id=" + id + ", del=" + del + ", name=" + name + ", description=" + description + "]";
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getDel() {
	return del;
}
public void setDel(int del) {
	this.del = del;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
}
