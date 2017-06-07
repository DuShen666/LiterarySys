package com.ccut.literary.domain;

import java.util.Date;

public class Poetry {
	private Integer poetryId;

	private String title;

	private String content;

	private Integer userId;

	private Date createDate;

	private String picUrl;

	private int type;

	public Poetry() {
	}

	public Poetry(String title, String content, Integer userId, String picUrl,int type) {
		super();
		this.title = title;
		this.content = content;
		this.userId = userId;
		this.picUrl = picUrl;
		this.type=type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Integer getPoetryId() {
		return poetryId;
	}

	public void setPoetryId(Integer poetryId) {
		this.poetryId = poetryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Poetry [poetryId=" + poetryId + ", title=" + title
				+ ", content=" + content + ", userId=" + userId
				+ ", createDate=" + createDate + "]";
	}

}