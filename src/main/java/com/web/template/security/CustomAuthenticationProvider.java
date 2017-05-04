package com.web.template.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.web.template.service.UserService;
import com.web.template.vo.UserVO;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	//로그인 로직 수행클래스
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {

		String user_email = auth.getName();
		CustomUserDetails user = null;
		String user_pwd = (String) auth.getCredentials();
		CustomWebAuthenticationDetails customDetails = (CustomWebAuthenticationDetails) auth.getDetails();
		
		String social = customDetails.getSocial_type();
		String access_token = customDetails.getAccess_token();
		String user_nick = customDetails.getUser_nick();

		if (social != null && access_token != null && user_nick != null) {
			
			if (userService.getUser(user_email) == null) {
				UserVO userVo = new UserVO();
				userVo.setUser_email(user_email);
				userVo.setAccess_token(access_token);
				userVo.setUser_nick(user_nick);
				userVo.setSocial_type(social);
				userService.addUser(userVo);				
			}
			
		}
		user = (CustomUserDetails) userDetailsService.loadUserByUsername(user_email);
		
		if (social == null || access_token == null || user_nick == null) {
			ShaPasswordEncoder encoder = new ShaPasswordEncoder();
			
			if(!encoder.isPasswordValid(user.getPassword(), user_pwd, null)) {
				throw new BadCredentialsException("");					
			}
		}
		

		return new UsernamePasswordAuthenticationToken(user, user_pwd, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
