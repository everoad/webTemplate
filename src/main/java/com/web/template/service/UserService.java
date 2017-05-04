package com.web.template.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.template.constance.Const;
import com.web.template.mapper.UserMapper;
import com.web.template.vo.RoleVO;
import com.web.template.vo.UserVO;

@Service
public class UserService {
	
	

	@Autowired
	private UserMapper userMapper;

	
	
	
	public UserVO addUser(UserVO userVo) {
		ShaPasswordEncoder encoder = new ShaPasswordEncoder();

		userVo.setUser_pwd(encoder.encodePassword(userVo.getUser_pwd(), null));
		
		RoleVO roleVo = new RoleVO();
		roleVo.setUser_email(userVo.getUser_email());
		roleVo.setUser_role(Const.ROLE_USER);
		userMapper.addUser(userVo); 
		userMapper.addRole(roleVo);

		return userVo;
	}
	
	
	public Boolean checkEmail(Map<String, String> map) {
		if (userMapper.getUser(map.get("user_email")) != null) {
			return false;
		} else {
			return true;
		}
	
	}
	
	
	public Boolean checkNick(Map<String, String> map) {

		if (userMapper.checkNickname(map) != null) {
			return false;
		} else {
			return true;
		}
	}
	
	
	
	
	
	
	public UserVO getUser(String user_email) {
		return userMapper.getUser(user_email);
	}
	 
	
	
	
	
	@PreAuthorize("principal.user_email == #user_email")
	public UserVO getEditUser(String user_email) {
		return userMapper.getUser(user_email);
	}
	
	
	
	
	
	//유저일경우 로그인 정보와 전송된 데이터가 같을 시 통과, 관리자일 경우 그냥 통과.
	@PreAuthorize("(princiapl.user_email == #user_email) OR hasRole('ADMIN') ")
	public void outUser(String user_email) {
		userMapper.outUser(user_email);
	}
	
	
	
	@PreAuthorize("hasRole('ADMIN')")
	public Map<String, Boolean> updateIntroduction(Map<String, String> map) {
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		if (userMapper.updateIntroduction(map) == 1) {
			result.put("success", true); 
		} else {
			result.put("success", false);
		}
		return result;
	}
	
	
	
	public Map<String, String> getIntroduction() {
		return userMapper.getIntroduction();
	}
	
}
