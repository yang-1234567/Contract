package com.contract.database;

import java.sql.Timestamp;

public class Log {
    private int id;
    private String user_id;
    private Timestamp time;
    private String content;
    private int del;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    @Override
    public String toString() {
        return "Log [id=" + id + ", user_id=" + user_id + ", time=" + time + ", content=" + content + ", del=" + del + "]";
    }

    public Log(String user_id, Timestamp time, String content, int del) {
        super();

        this.user_id = user_id;
        this.time = time;
        this.content = content;
        this.del = del;
    }

}
