package com.contract.entity;

public class Customer {

	private int id;
	private String numlString;
	private String nameString;
	private String addressString;
	private String telString;
	private String fax;	
	private String codeString;
	private String bankString;
	private String accountString;
	private int del;
	
	public Customer() {
		id = 0;
		del = 0;
		fax = null;
		codeString = null;
		bankString = null;
		accountString = null;
	}

	public Customer(String numlString, String nameString, String addressString, String telString, String fax,
			String codeString, String bankString, String accountString) {
		super();
		this.numlString = numlString;
		this.nameString = nameString;
		this.addressString = addressString;
		this.telString = telString;
		this.fax = fax;
		this.codeString = codeString;
		this.bankString = bankString;
		this.accountString = accountString;
	}

	public Customer(int id, String numlString, String nameString, String addressString, String telString, String fax,
			String codeString, String bankString, String accountString, int del) {
		super();
		this.id = id;
		this.numlString = numlString;
		this.nameString = nameString;
		this.addressString = addressString;
		this.telString = telString;
		this.fax = fax;
		this.codeString = codeString;
		this.bankString = bankString;
		this.accountString = accountString;
		this.del = del;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumlString() {
		return numlString;
	}

	public void setNumlString(String numlString) {
		this.numlString = numlString;
	}

	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	public String getAddressString() {
		return addressString;
	}

	public void setAddressString(String addressString) {
		this.addressString = addressString;
	}

	public String getTelString() {
		return telString;
	}

	public void setTelString(String telString) {
		this.telString = telString;
	}

	public String getFax() {
		if (fax == null) {
			return "";
		}
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCodeString() {
		if (codeString == null) {
			return "";
		}
		return codeString;
	}

	public void setCodeString(String codeString) {
		this.codeString = codeString;
	}

	public String getBankString() {
		if (bankString == null) {
			return "";
		}
		return bankString;
	}

	public void setBankString(String bankString) {
		this.bankString = bankString;
	}

	public String getAccountString() {
		if (accountString == null) {
			return "";
		}
		return accountString;
	}

	public void setAccountString(String accountString) {
		this.accountString = accountString;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", numlString=" + numlString + ", nameString=" + nameString + ", addressString="
				+ addressString + ", telString=" + telString + ", fax=" + fax + ", codeString=" + codeString
				+ ", bankString=" + bankString + ", accountString=" + accountString + ", del=" + del + "]";
	}

}
