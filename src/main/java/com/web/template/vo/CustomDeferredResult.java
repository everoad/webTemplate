package com.web.template.vo;

import java.util.List;
import java.util.Map;

import org.springframework.web.context.request.async.DeferredResult;

public class CustomDeferredResult extends DeferredResult<List<Map<String, String>>>  {
	
	private final String ip;
	
	    public CustomDeferredResult(String ip) {
	        this.ip = ip;
	    }
}
