package com.ccut.literary.domain;

import java.util.Date;

public class User {
	private Integer userId;

	private String nickName;

	private Integer sex;

	private Date birthdate;

	private Date dateTime;

	private String appId;

	private String openId;

	private String city;

	private String province;

	private String country;

	private String avatarurl;

	private String unionId;

	public User() {
	}

	public User(String nickName, Integer sex, Date birthdate,
			String appId, String openId, String city, String province,
			String country, String avatarurl, String unionId) {
		super();
		this.nickName = nickName;
		this.sex = sex;
		this.birthdate = birthdate;
		this.appId = appId;
		this.openId = openId;
		this.city = city;
		this.province = province;
		this.country = country;
		this.avatarurl = avatarurl;
		this.unionId = unionId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName == null ? null : nickName.trim();
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId == null ? null : appId.trim();
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId == null ? null : openId.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province == null ? null : province.trim();
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country == null ? null : country.trim();
	}

	public String getAvatarurl() {
		return avatarurl;
	}

	public void setAvatarurl(String avatarurl) {
		this.avatarurl = avatarurl == null ? null : avatarurl.trim();
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId == null ? null : unionId.trim();
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", nickName=" + nickName + ", sex="
				+ sex + ", birthdate=" + birthdate + ", dateTime=" + dateTime
				+ ", appId=" + appId + ", openId=" + openId + ", city=" + city
				+ ", province=" + province + ", country=" + country
				+ ", avatarurl=" + avatarurl + ", unionId=" + unionId + "]";
	}

}