package com.ccut.literary.domain;

import java.util.Date;

public class Attention {
    private Integer attentionId;

    private Integer userId;

    private Integer bUserId;

    private Date date;

    public Attention() {
	}
    
    public Attention(Integer userId, Integer bUserId) {
		super();
		this.userId = userId;
		this.bUserId = bUserId;
	}

	public Integer getAttentionId() {
        return attentionId;
    }

    public void setAttentionId(Integer attentionId) {
        this.attentionId = attentionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getbUserId() {
        return bUserId;
    }

    public void setbUserId(Integer bUserId) {
        this.bUserId = bUserId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}