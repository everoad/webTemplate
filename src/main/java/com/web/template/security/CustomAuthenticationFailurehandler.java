package com.web.template.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenticationFailurehandler implements AuthenticationFailureHandler {
	//로그인 실패시 후처리하는 클래스
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException ex)
			throws IOException, ServletException {
		
		String contextPath = req.getContextPath();
		
		res.sendRedirect(contextPath + "/user/login?error");
	}

}
