package com.web.template.mapper;

import java.util.List;
import java.util.Map;

import com.web.template.vo.RoleVO;
import com.web.template.vo.UserVO;

public interface UserMapper {

	public int addUser(UserVO userVo);

	public int addRole(RoleVO roleVo);

	public String checkNickname(Map<String, String> map);

	
	public UserVO getUser(String user_email);
	
	public List<RoleVO> getRole(String user_email);

	
	public int outUser(String user_email);
	
	
	
	public int updateIntroduction(Map<String, String> map);
	
	public Map<String, Object> getIntroduction();

}
