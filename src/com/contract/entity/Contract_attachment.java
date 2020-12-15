package com.contract.entity;

import java.util.Date;

public class Contract_attachment {

	private int id;
	private int con_id;
	private String filenameString;
	private String pathString;
	private String type;
	private Date uploadTime;
	private int del;
	
	public Contract_attachment() {
		id = 0;
		del = 0;
	}

	public Contract_attachment(int con_id, String filenameString, String pathString, String type, Date uploadTime) {
		super();
		this.con_id = con_id;
		this.filenameString = filenameString;
		this.pathString = pathString;
		this.type = type;
		this.uploadTime = uploadTime;
	}

	public Contract_attachment(int id, int con_id, String filenameString, String pathString, String type, Date uploadTime,
			int del) {
		super();
		this.id = id;
		this.con_id = con_id;
		this.filenameString = filenameString;
		this.pathString = pathString;
		this.type = type;
		this.uploadTime = uploadTime;
		this.del = del;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCon_id() {
		return con_id;
	}

	public void setCon_id(int con_id) {
		this.con_id = con_id;
	}

	public String getFilenameString() {
		return filenameString;
	}

	public void setFilenameString(String filenameString) {
		this.filenameString = filenameString;
	}

	public String getPathString() {
		return pathString;
	}

	public void setPathString(String pathString) {
		this.pathString = pathString;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	@Override
	public String toString() {
		return "Contract_attachment [id=" + id + ", con_id=" + con_id + ", filenameString=" + filenameString
				+ ", pathString=" + pathString + ", type=" + type + ", uploadTime=" + uploadTime + ", del=" + del + "]";
	}

}
