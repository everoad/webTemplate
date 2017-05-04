package com.web.template.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.template.constance.Const;
import com.web.template.service.UserService;
import com.web.template.validator.UserValidator;
import com.web.template.vo.UserVO;

@Controller
@RequestMapping("user")
public class UserController {

	private static final Logger LOGGER = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	
	@InitBinder("userVo")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new UserValidator());
	}

	
	
	
	@Secured(Const.ROLE_ANONYMOUS)
	@RequestMapping(value = "join", method = RequestMethod.GET)
	public String joinView(Model model, HttpServletRequest req) {
		if (req.getSession().getAttribute("redirectUrl") == null) {
			req.getSession().setAttribute("redirectUrl", req.getHeader("REFERER"));
		}
		model.addAttribute("userVo", new UserVO());
		return "user/join";
	}

	
	
	
	@Secured(Const.ROLE_ANONYMOUS)
	@RequestMapping(value = "join", method = RequestMethod.POST)
	public String join(@ModelAttribute("userVo") @Valid UserVO userVo, BindingResult br) {

		LOGGER.info("join method : " + userVo.toString());

		if (br.hasErrors()) {
			return "user/join";
		}

		if (userService.getUser(userVo.getUser_email()) == null) {
			userService.addUser(userVo);
		}
		return "redirect:/user/login";
	}

	
	
	
	@Secured(Const.ROLE_ANONYMOUS)
	@RequestMapping(value = "check/email", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> checkEmail(@RequestBody Map<String, String> map) {
		LOGGER.info(map.toString());
		Boolean result = userService.checkEmail(map);
		map.clear();
		map.put("success", result + "");
		return map;
	}

	
	
	
	@Secured(Const.ROLE_ANONYMOUS)
	@RequestMapping(value = "check/nick", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> checkNick(@RequestBody Map<String, String> map) {
		LOGGER.info(map.toString());
		Boolean result = userService.checkNick(map);
		map.clear();
		map.put("success", result + "");
		return map;
	}

	
	
	
	@Secured(Const.ROLE_ANONYMOUS)
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginView(@CookieValue(value="auth", required=false) String auth,
											UserVO userVo,	Model model, HttpServletRequest req) throws IOException, ServletException {

		if (auth != null) {
			userVo.setUser_email(auth);
		}
		
		if (req.getSession().getAttribute("redirectUrl") == null) {
			req.getSession().setAttribute("redirectUrl", req.getHeader("REFERER"));
		}
		
		model.addAttribute("userVo", userVo);
		return "user/login";
	}

	
	
	
	@Secured(value = { Const.ROLE_USER, Const.ROLE_ADMIN })
	@RequestMapping(value = "out", method = RequestMethod.POST)
	public String outUser(@RequestParam("user_email") String user_email) {
		userService.outUser(user_email);
		return "redirect:/";
	}

}
