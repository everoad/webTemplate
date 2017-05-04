package com.web.template.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

	
	private static final long serialVersionUID = -6527597558324837385L;
	
	private String cookieCheck;
	private String access_token;
	private String social_type;
	private String user_nick;
	
	public CustomWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		cookieCheck = request.getParameter("cookieCheck");
		access_token = request.getParameter("access_token");
		social_type = request.getParameter("social_type");
		user_nick = request.getParameter("user_nick");
	}

	public String getCookieCheck() {
		return cookieCheck;
	}

	public String getAccess_token() {
		return access_token;
	}

	public String getSocial_type() {
		return social_type;
	}

	public String getUser_nick() {
		return user_nick;
	}
	
	
}
