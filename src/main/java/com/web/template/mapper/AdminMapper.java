package com.web.template.mapper;

import java.util.List;
import java.util.Map;

import com.web.template.vo.HomeVO;

public interface AdminMapper {

	public List<Map<String, Object>> getMenuFir();
	
	public List<Map<String, String>> getMenuSec(String menu_fir_seq);
	
	public int addMenuFir(Map<String, Object> map);
	
	public int editMenuFir(Map<String, Object> map);

	public int addMenuSec(Map<String, String> map);
	
	public int editMenuSec(Map<String, String> map);
	
	public int deleteMenuFir(Map<String, Object> map);
	
	public int deleteMenuSec(Map<String, Object> map);
	
	public int editHome(HomeVO homeVo);
	
}
