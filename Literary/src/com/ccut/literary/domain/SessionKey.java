package com.ccut.literary.domain;

public class SessionKey {
	private String openId;
	private String session_key;

	public SessionKey() {
	}

	public SessionKey(String openId, String session_key) {
		super();
		this.openId = openId;
		this.session_key = session_key;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getSession_key() {
		return session_key;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}

	@Override
	public String toString() {
		return "SessionKey [openId=" + openId + ", session_key=" + session_key
				+ "]";
	}

}
