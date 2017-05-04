package com.web.template.exception;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mysql.jdbc.exceptions.jdbc4.MySQLDataException;

@ControllerAdvice
public class ExceptionHandlingAdivce extends ResponseEntityExceptionHandler  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlingAdivce.class);

	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ModelAndView dataIntegrityViolationException(DataIntegrityViolationException ex) {
		LOGGER.error("DataIntegrityViolationException");
		printExceptionInfo(ex);
		return makeModelAndView(ex);
	}

	
	@ExceptionHandler(MySQLDataException.class)
	public ModelAndView MySQLDataException(MySQLDataException ex) {
		LOGGER.error("MySQLSyntaxErrorException");
		printExceptionInfo(ex);
		return makeModelAndView(ex);
	}

	
	@ExceptionHandler(BadSqlGrammarException.class)
	public ModelAndView BadSqlGrammarException(BadSqlGrammarException ex) {
		LOGGER.error("BadSqlGrammarException");
		printExceptionInfo(ex);
		return makeModelAndView(ex);
	}

	
	@ExceptionHandler(SQLException.class)
	public ModelAndView sqlException(SQLException ex) {
		LOGGER.error("SQLException");
		printExceptionInfo(ex);
		return makeModelAndView(ex);
	}
	
	//ajax 사용시 이거 쓰면 될듯.
/*	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
		return new ResponseEntity<Object>("Access denied message here", new HttpHeaders(), HttpStatus.FORBIDDEN);
	}*/
	
	@ExceptionHandler(AccessDeniedException.class)
	public void accessDeniedException(AccessDeniedException ex, HttpServletRequest req, HttpServletResponse res) throws IOException {
		LOGGER.error("AccessDeniedException");
		
		String contextPath = req.getContextPath();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//인증을 안받은 상태에서 거부 당했을 때
		if(auth.getName().equals("anonymousUser")) {
			//로그인 페이지로 이동시키기 전에 이동하려던 url 주소 저장.
			RequestCache rc = new HttpSessionRequestCache();
			rc.saveRequest(req, res);
			res.sendRedirect(contextPath + "/user/login");
		
		//인증 받은 상태에서 거부 당했을 때
		} else {
			res.sendRedirect(contextPath + "/denied");
		}
	}
	
	//위와 동일. 아직 완성 안됨.
	@ExceptionHandler(AuthenticationException.class)
	public void authenticationException(AuthenticationException ex, HttpServletRequest req, HttpServletResponse res) throws IOException {
		LOGGER.error("AuthenticationException");
		String contextPath = req.getContextPath();
		res.sendRedirect(contextPath + "/test/denied");
	}
	
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView exception(HttpServletResponse res, Exception ex) {
		printExceptionInfo(ex);
		return makeModelAndView(ex);
	}
	
	private ModelAndView makeModelAndView(Exception ex) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", ex.getMessage());
		mv.setViewName("error");
		return mv;
	}
	
	private void printExceptionInfo(Throwable ex) {
		LOGGER.error("============================");
		LOGGER.error(ex.getMessage());
		LOGGER.error("============================");
		for (StackTraceElement s : ex.getStackTrace()) {
			LOGGER.error("STACK: {}", s);
		}
		LOGGER.error("============================");
	}
	
	
}
