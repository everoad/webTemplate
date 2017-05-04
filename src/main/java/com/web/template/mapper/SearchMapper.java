package com.web.template.mapper;

import java.util.List;
import java.util.Map;

public interface SearchMapper {

	public int setLog(Map<String, String> map);
	
	
	public List<Map<String, String>> getPopularAll();
	
	
}
