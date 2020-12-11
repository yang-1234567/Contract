package com.contract.database;

public class Customer {
private int id;
private String num;
private String name;
private String address;
public Customer(String num, String name, String address, String tel, String fax, String code, String bank,
		String account, int del) {
	super();

	this.num = num;
	this.name = name;
	this.address = address;
	this.tel = tel;
	this.fax = fax;
	this.code = code;
	this.bank = bank;
	this.account = account;
	this.del = del;
}
@Override
public String toString() {
	return "Customer [id=" + id + ", num=" + num + ", name=" + name + ", address=" + address + ", tel=" + tel + ", fax="
			+ fax + ", code=" + code + ", bank=" + bank + ", account=" + account + ", del=" + del + "]";
}
private String tel;
private String fax;
private String code;
private String bank;
private String account;
private int del;
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
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public String getFax() {
	return fax;
}
public void setFax(String fax) {
	this.fax = fax;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getBank() {
	return bank;
}
public void setBank(String bank) {
	this.bank = bank;
}
public String getAccount() {
	return account;
}
public void setAccount(String account) {
	this.account = account;
}
public int getDel() {
	return del;
}
public void setDel(int del) {
	this.del = del;
}

}
