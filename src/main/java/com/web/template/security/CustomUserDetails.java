package com.web.template.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.web.template.vo.UserVO;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private UserVO userVo;
	private Collection<? extends GrantedAuthority> roleList;
	
	public CustomUserDetails() {
	}

	public CustomUserDetails(UserVO userVo, Collection<? extends GrantedAuthority> roleList) {
		this.userVo = userVo;
		this.roleList = roleList;
	}
	
	public UserVO getUserVo() {
		return userVo;
	}

	public String getUser_email() {
		return userVo.getUser_email();
	}
	
	public String getUser_nick() {
		return userVo.getUser_nick();
	}
	
	public String getUser_jdate() {
		return userVo.getUser_jdate();
	}
	
	public String getAccess_token() {
		return userVo.getAccess_token();
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roleList;
	}

	@Override
	public String getPassword() {
		return userVo.getUser_pwd();
	}

	@Override
	public String getUsername() {
		return userVo.getUser_email();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return userVo.getEnabled();
	}

}
