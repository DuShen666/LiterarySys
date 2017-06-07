package com.ccut.literary.domain;

import java.util.Date;

public class Support {
	private Integer supportId;

	private Integer poetryId;

	private Integer userId;

	private Date date;

	private Integer type;

	private Integer commentId;

	public Support() {
	}

	public Support(Integer userId, Integer type, Integer commentId) {
		this.userId = userId;
		this.type = type;
		this.commentId = commentId;
	}

	public Support(Integer poetryId, Integer userId, Integer type,Integer commentId) {
		this.poetryId = poetryId;
		this.userId = userId;
		this.type = type;
	}

	public Integer getSupportId() {
		return supportId;
	}

	public void setSupportId(Integer supportId) {
		this.supportId = supportId;
	}

	public Integer getPoetryId() {
		return poetryId;
	}

	public void setPoetryId(Integer poetryId) {
		this.poetryId = poetryId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
}