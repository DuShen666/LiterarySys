package com.ccut.literary.domain;

import java.util.Date;

public class Look {
    private Integer lookid;

    private Integer poetryid;

    private Integer userid;

    private Date date;

    private String ip;

    private String location;

    public Look() {
	}

    
    public Look(Integer poetryid, Integer userid) {
		super();
		this.poetryid = poetryid;
		this.userid = userid;
	}


	public Integer getLookid() {
        return lookid;
    }

    public void setLookid(Integer lookid) {
        this.lookid = lookid;
    }

    public Integer getPoetryid() {
        return poetryid;
    }

    public void setPoetryid(Integer poetryid) {
        this.poetryid = poetryid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }
}