package com.web.template.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	
	private final Map<String, DeferredResult<List<Map<String, String>>>> responseBodyMap = new ConcurrentHashMap<>();
	
	
	
	@Autowired
	private SearchService searchService;

	
	
	
		
	@RequestMapping(value="init", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, String>> getInit() throws Exception {
		LOGGER.info("getInit Method");
		
		return searchService.getPopularAll();
	}
	
	
	
	

	
	@RequestMapping(value="after", method= RequestMethod.GET)
	public @ResponseBody DeferredResult<List<Map<String, String>>> getAfter(@RequestParam("flag") Boolean flag, HttpServletRequest req) throws Exception {
		LOGGER.info("getAfter Method : flag=" + flag);
		
		DeferredResult<List<Map<String, String>>> result = null;
		
		//동일 클라이언트에 대한 중복 요청을 방지하기 위해 ip를 key값으로 저장.
		String ip = req.getHeader("X-FORWARDED-FOR");
		
    	if (ip == null) {
    		ip = req.getRemoteAddr();
    	}
		
		if (flag) {
			result = new DeferredResult<>();
			responseBodyMap.put(ip, result);	
			
		} else {
			responseBodyMap.remove(ip);
		}

	 	return result;
	}
	
	
	
	
	
	
	
	@EventListener
	public void refreshAll(MyEvent event) throws Exception  {
		LOGGER.info("refreshAll Method");
		
		List<Map<String, String>> list = searchService.getPopularAll();
		
		for (String key : responseBodyMap.keySet()) {	
			responseBodyMap.get(key).setResult(list);
			responseBodyMap.remove(key);
		}
	}
	
	
	



}
