package com.web.template.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	//로그인 성공후 처리 클래스.
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth)
			throws IOException, ServletException {
		
		RequestCache requestCache = new HttpSessionRequestCache();
		
		SavedRequest savedRequest = requestCache.getRequest(req, res);
		
		String check = ((CustomWebAuthenticationDetails) auth.getDetails()).getCookieCheck();
		String user_email = auth.getName();

		if (check != null) {
			Cookie cookie = new Cookie("auth", user_email);
			res.addCookie(cookie);
			
		} else {
			Cookie[] cookies = req.getCookies();
			
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("auth")) {
						c.setMaxAge(0);
						res.addCookie(c);
					}
				}
			}
		}
		
		
		
		if(savedRequest == null) {
			//유저가 직접 로그인페이지로 들어왔을 경우
			String redirectUrl = (String)req.getSession().getAttribute("redirectUrl");
			req.getSession().removeAttribute("redirectUrl");
			
			//url로 로그인 페이지 직접 접근했을 경우
			if(redirectUrl == null) {
				res.sendRedirect(req.getContextPath());
			}
			
			res.sendRedirect(redirectUrl);
		} else {
			//접근 제어로 인해 로그인 페이지로 강제 이동 했을 경우
			res.sendRedirect(savedRequest.getRedirectUrl());
		}
		
	}

}
