package com.ccut.literary.domain;

import java.util.Date;

public class Comment {
	private Integer commentId;

	private Integer poetryId;

	private Integer userId;

	private Integer reUserId;

	private String content;

	private Date date;

	public Comment() {
	}

	public Comment(Integer poetryId, Integer userId, String content) {
		super();
		this.poetryId = poetryId;
		this.userId = userId;
		this.content = content;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
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

	public Integer getReUserId() {
		return reUserId;
	}

	public void setReUserId(Integer reUserId) {
		this.reUserId = reUserId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}