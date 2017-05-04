package com.web.template.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.template.mapper.SearchMapper;

@Service
public class SearchService {
	
	
	@Autowired
	private SearchMapper searchMapper;

	
	public List<Map<String, String>> getPopularAll() throws Exception {
		return searchMapper.getPopularAll();
	}
	
	
	
	public void setLog(Map<String, String> map) throws Exception {
		searchMapper.setLog(map);
	}
	
}
