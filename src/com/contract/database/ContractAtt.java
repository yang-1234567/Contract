package com.contract.database;

import java.sql.Timestamp;

public class ContractAtt {

private int id;
private String con_id;
private String fileName;
private String path;
private int type;
private Timestamp uploadTime;
private int del;


//*******************************�ڲ����Է�װ*****************************
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getCon_id() {
	return con_id;
}
public void setCon_id(String con_id) {
	this.con_id = con_id;
}
public String getFileName() {
	return fileName;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}
public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
public Timestamp getUploadTime() {
	return uploadTime;
}
public void setUploadTime(Timestamp uploadTime) {
	this.uploadTime = uploadTime;
}
public int getDel() {
	return del;
}
public void setDel(int del) {
	this.del = del;
}


public ContractAtt(String con_id, String fileName, String path, int type, Timestamp uploadTime, int del) {
	super();

	this.con_id = con_id;
	this.fileName = fileName;
	this.path = path;
	this.type = type;
	this.uploadTime = uploadTime;
	this.del = del;
}


@Override
public String toString() {
	return "ContractEntity [id=" + id + ", con_id=" + con_id + ", fileName=" + fileName + ", path=" + path + ", type="
			+ type + ", uploadTime=" + uploadTime + ", del=" + del + "]";
}



}
