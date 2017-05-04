package com.web.template.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.web.template.mapper.UserMapper;
import com.web.template.vo.RoleVO;
import com.web.template.vo.UserVO;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	// Service단
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String user_email) throws UsernameNotFoundException {

		UserVO userVo = userMapper.getUser(user_email);
		if (userVo == null) {
			userVo = new UserVO();			
		}

		List<RoleVO> roleVoList = userMapper.getRole(user_email);

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (RoleVO vo : roleVoList) {
			authorities.add(new SimpleGrantedAuthority(vo.getUser_role()));
		}
		return new CustomUserDetails(userVo, authorities);
	}

}
