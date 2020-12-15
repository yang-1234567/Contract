package com.contract.entity;

public class Role {

	private int id;
	private String nameString;
	private String descriptionString;
	private String functionString;
	private int del;
	
	public Role() {
		id = 0;
		del = 0;
	}

	public Role(String nameString, String descriptionString, String functionString) {
		super();
		this.nameString = nameString;
		this.descriptionString = descriptionString;
		this.functionString = functionString;
	}

	public Role(int id, String nameString, String descriptionString, String functionString, int del) {
		super();
		this.id = id;
		this.nameString = nameString;
		this.descriptionString = descriptionString;
		this.functionString = functionString;
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

	public String getDescriptionString() {
		return descriptionString;
	}

	public void setDescriptionString(String descriptionString) {
		this.descriptionString = descriptionString;
	}

	public String getFunctionString() {
		return functionString;
	}

	public void setFunctionString(String functionString) {
		this.functionString = functionString;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	@Override
	public String toString() {
		return "role [id=" + id + ", nameString=" + nameString + ", descriptionString=" + descriptionString
				+ ", functionString=" + functionString + ", del=" + del + "]";
	}

}
