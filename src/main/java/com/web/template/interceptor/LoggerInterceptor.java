package com.web.template.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = Logger.getLogger(LoggerInterceptor.class);
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("=======================     START     =======================");
			LOGGER.debug(" Request URI \t:   " + request.getRequestURI());
		}
		
		return super.preHandle(request, response, handler);
	}
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("=======================       END      =======================\n");
		}
		
		super.postHandle(request, response, handler, modelAndView);
	}
}
