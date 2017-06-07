package com.ccut.literary.domain;

import net.sf.json.JSONObject;

public class WeiUserInfo {
	private String nickName = "";
	private int sex = 0;
	private String appId = "";
	private String openId = "";
	private String city = "";
	private String province = "";
	private String country = "";
	private String avatarUrl = "";
	private String unionId = "";

	public WeiUserInfo() {
	}

	/*
	 * {"openId":"oGZUI0egBJY1zhBYw2KhdUfwVJJE","nickName":"Band","gender":1,
	 * "language"
	 * :"zh_CN","city":"Guangzhou","province":"Guangdong","country":"CN",
	 * "avatarUrl":
	 * "http://wx.qlogo.cn/mmopen/vi_32/aSKcBBPpibyKNicHNTMM0qJVh8Kjgiak2AHWr8MHM4WgMEm7GFhsf8OYrySdbvAMvTsw3mo8ibKicsnfN5pRjl1p8HQ/0"
	 * , "unionId":"ocMvos6NjeKLIBqg5Mr9QjxrP1FA", "watermark":{
	 * "timestamp":1477314187, "appid":"wx4f4bc4dec97d474b"}}
	 */

	public WeiUserInfo(JSONObject json) {
		JSONObject watermark = json.getJSONObject("watermark");
		this.nickName = json.getString("nickName");
		this.sex = json.getInt("gender");
		this.appId = watermark.getString("appid");
		this.openId = json.getString("openId");
		this.city = json.getString("city");
		this.province = json.getString("province");
		this.country = json.getString("country");
		this.avatarUrl = json.getString("avatarUrl");
		if (json.has(unionId)) {
			this.unionId = json.getString("unionId");
		}
	}

	public User toUser() {
		User user = new User(nickName, sex, null, appId, openId, city,
				province, country, avatarUrl, unionId);
		return user;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	@Override
	public String toString() {
		return "WeiUserInfo [nickName=" + nickName + ", sex=" + sex
				+ ", appId=" + appId + ", openId=" + openId + ", city=" + city
				+ ", province=" + province + ", country=" + country
				+ ", avatarUrl=" + avatarUrl + ", unionId=" + unionId + "]";
	}

}
