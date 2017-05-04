package com.web.template.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.template.constance.SubSystem;
import com.web.template.security.CustomUserDetails;
import com.web.template.service.BoardService;
import com.web.template.service.UserService;
import com.web.template.vo.BoardListVO;
import com.web.template.vo.MenuFirVO;
import com.web.template.vo.SearchVO;

@Controller
@RequestMapping("")
public class MainController {
	
	@Autowired
	private BoardService boardService;

	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private SubSystem subSystem;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String mainView(
			@AuthenticationPrincipal CustomUserDetails details,
			SearchVO searchVo, Model model) throws Exception {
		
		List<BoardListVO> list = new ArrayList<>();
		List<MenuFirVO> menuList = boardService.getMenuList();
		
		for(MenuFirVO vo : menuList) {
			searchVo.setMenu_fir_seq(vo.getMenu_fir_seq());
			BoardListVO boardListVo = boardService.getMainList(searchVo);
			boardListVo.setMenu_fir_seq(vo.getMenu_fir_seq());
			boardListVo.setMenu_fir_name(vo.getMenu_fir_name());
			list.add(boardListVo);
		}
		
		String skey = searchVo.getSkey();
		if (skey != null) {
			subSystem.publishMyEvent(skey, details);			
		}
		
		model.addAttribute("device", subSystem.checkDevice());
		model.addAttribute("list", list);
		model.addAttribute("homeVo", boardService.getHomeInfo());
		model.addAttribute("menuList", menuList);
		return "main";
	}
	
	
	
	
	@RequestMapping(value="about", method=RequestMethod.GET)
	public String aboutView(Model model) {
		
		model.addAttribute("homeVo", boardService.getHomeInfo());
		model.addAttribute("menuList", boardService.getMenuList());
		model.addAttribute("bestList", boardService.getBestAll());
		model.addAttribute("recentList", boardService.getRecentAll());
		return "about";
	}
	
	
	
	
	
	@RequestMapping(value="profile", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody Map<String, String> getProfile(
													@AuthenticationPrincipal CustomUserDetails user) {
		Map<String, String> map = userService.getIntroduction();
		
		if (user != null) {
			Collection<? extends GrantedAuthority> auths = user.getAuthorities();
			
			for(GrantedAuthority auth : auths) {
				if (auth.getAuthority().equals("ROLE_ADMIN"))  {
					map.put("authorize", "true");
				}				
			}
		}
		return map;
	}
	
	
	
	
	
	@RequestMapping(value="profile", method=RequestMethod.PUT, produces="application/json")
	public @ResponseBody Map<String, Boolean> updateProfile(@RequestBody Map<String, String> map) {
		return userService.updateIntroduction(map);
	}
	
	
	
	
	
	
	
	@RequestMapping(value="denied", method=RequestMethod.GET)
	public String denied(HttpServletRequest req, HttpServletResponse res) {
		return "denied";
	}
	
	
	
	
	
	
	@RequestMapping(value="error", method=RequestMethod.GET)
	public String error() {
		return "error";
	}
	
	
	
	
}
