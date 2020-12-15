package com.contract.entity;

public class Right {

	private int id;
	private String usernameString;
	private String rolenameString;
	private String descriptionString;
	private int del;
 	
	public Right() {
		id = 0;
		del = 0;
	}

	public Right(String usernameString, String rolenameString, String descriptionString) {
		super();
		this.usernameString = usernameString;
		this.rolenameString = rolenameString;
		this.descriptionString = descriptionString;
	}

	public Right(int id, String usernameString, String rolenameString, String descriptionString, int del) {
		super();
		this.id = id;
		this.usernameString = usernameString;
		this.rolenameString = rolenameString;
		this.descriptionString = descriptionString;
		this.del = del;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsernameString() {
		return usernameString;
	}

	public void setUsernameString(String usernameString) {
		this.usernameString = usernameString;
	}

	public String getRolenameString() {
		return rolenameString;
	}

	public void setRolenameString(String rolenameString) {
		this.rolenameString = rolenameString;
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
		return "Right [id=" + id + ", usernameString=" + usernameString + ", rolenameString=" + rolenameString
				+ ", descriptionString=" + descriptionString + ", del=" + del + "]";
	}

}
