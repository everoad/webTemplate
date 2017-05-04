package com.web.template.vo;

import java.io.Serializable;

public class UserVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String user_email;
	private String user_nick;
	private String user_pwd;
	private String user_jdate;
	private Boolean enabled;
	private String access_token;
	private String social_type;

	public String getSocial_type() {
		return social_type;
	}

	public void setSocial_type(String social_type) {
		this.social_type = social_type;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_nick() {
		return user_nick;
	}

	public void setUser_nick(String user_nick) {
		this.user_nick = user_nick;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	public String getUser_jdate() {
		return user_jdate;
	}

	public void setUser_jdate(String user_jdate) {
		this.user_jdate = user_jdate;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "UserVO [user_email=" + user_email + ", user_nick=" + user_nick + ", user_pwd=" + user_pwd
				+ ", user_jdate=" + user_jdate + ", enabled=" + enabled + ", access_token=" + access_token
				+ ", social_type=" + social_type + "]";
	}
	
	
	

}
