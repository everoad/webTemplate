package com.web.template.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.async.DeferredResult;

import com.web.template.event.MyEvent;
import com.web.template.service.SearchService;

@Controller
@RequestMapping("search")
public class SearchController {

	
	private final static Logger LOGGER = Logger.getLogger(SearchController.class);

	
	private final Map<String, DeferredResult<List<Map<String, String>>>> responseBodyMap;
	
	
	@Autowired
	private SearchService searchService;

	
	
	public SearchController() {
		 responseBodyMap = new ConcurrentHashMap<>();
	}

	
	
	
	@RequestMapping(value="before", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, String>> getBeforeAll() throws Exception {
		LOGGER.info("getBeforeAll Method");
		return searchService.getPopularAll();
	}
	
	
	

	
	@RequestMapping(value="after", method= RequestMethod.GET)
	public @ResponseBody DeferredResult<List<Map<String, String>>> getAfterAll(HttpServletRequest req) throws Exception {
		LOGGER.info("getAfterAll Method");
        
		DeferredResult<List<Map<String, String>>> result = new DeferredResult<>();
		responseBodyMap.put(getIp(req), result);
		
	 	return result;
	}
	
	
	
	
	
	
	@EventListener
	public void refreshAll(MyEvent event) throws Exception  {
		LOGGER.info("refreshAll Method");
		
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
  
		String url = req.getRequestURL().toString();
        String uri = req.getRequestURI();
 
        String hostUrl = url.substring(url.lastIndexOf(req.getContextPath())) + "/";

        if (!hostUrl.equals(uri)) {
        	responseBodyMap.remove(getIp(req));
        }
        
		List<Map<String, String>> list = searchService.getPopularAll();
		
		for (String key : responseBodyMap.keySet()) {
			responseBodyMap.get(key).setResult(list);
			responseBodyMap.remove(key);
		}
	}
	
	
	
	
	private String getIp(HttpServletRequest req) {
		String ip = req.getHeader("X-FORWARDED-FOR");
    	if (ip == null) {
    		ip = req.getRemoteAddr();
    	}
    	responseBodyMap.remove(ip);
    	
    	return ip;
	}


}
