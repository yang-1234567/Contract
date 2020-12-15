package com.contract.entity;

public class Function {

	private int id;
	private String mumString;
	private String nameString;
	private String URLString;
	private String descriptionString;
	private int del;
	
	public Function() {
		id = 0;
		del = 0;
	}

	public Function(String mumString, String nameString, String uRLString, String descriptionString) {
		super();
		this.mumString = mumString;
		this.nameString = nameString;
		this.URLString = uRLString;
		this.descriptionString = descriptionString;
	}

	public Function(int id, String mumString, String nameString, String uRLString, String descriptionString, int del) {
		super();
		this.id = id;
		this.mumString = mumString;
		this.nameString = nameString;
		URLString = uRLString;
		this.descriptionString = descriptionString;
		this.del = del;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMumString() {
		return mumString;
	}

	public void setMumString(String mumString) {
		this.mumString = mumString;
	}

	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	public String getURLString() {
		return URLString;
	}

	public void setURLString(String uRLString) {
		URLString = uRLString;
	}

	public String getDescriptionString() {
		return descriptionString;
	}

	public void setDescriptionString(String descriptionString) {
		this.descriptionString = descriptionString;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	@Override
	public String toString() {
		return "Function [id=" + id + ", mumString=" + mumString + ", nameString=" + nameString + ", URLString="
				+ URLString + ", descriptionString=" + descriptionString + ", del=" + del + "]";
	}

}
