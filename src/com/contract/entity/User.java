package com.contract.entity;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = -6060343040263809614L; 
	
	private int id;
	private String nameString;
	private String passwordString;
	private int del;
	private int result;
	private String functionString = "";
	private String roleString = "";
	
	public User() {
		id = 0;
		del = 0;
		result = 0;
		// TODO Auto-generated constructor stub
	}

	public User(String nameString, String passwordString) {
		super();
		this.nameString = nameString;
		this.passwordString = passwordString;
	}

	public User(int id, String nameString, String passwordString, int del) {
		super();
		this.id = id;
		this.nameString = nameString;
		this.passwordString = passwordString;
		this.del = del;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	public String getPasswordString() {
		return passwordString;
	}

	public void setPasswordString(String passwordString) {
		this.passwordString = passwordString;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getFunctionString() {
		return functionString;
	}

	public void setFunctionString(String functionString) {
		this.functionString = functionString;
	}

	public String getRoleString() {
		return roleString;
	}

	public void setRoleString(String roleString) {
		this.roleString = roleString;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nameString=" + nameString + ", passwordString=" + passwordString + ", del=" + del
				+ "]";
	}

	
}
