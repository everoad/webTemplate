package com.web.template.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.web.template.constance.Const;
import com.web.template.constance.SubSystem;
import com.web.template.service.AdminService;
import com.web.template.service.BoardService;
import com.web.template.vo.HomeVO;

@Controller
@Secured(Const.ROLE_ADMIN)
@RequestMapping("admin")
public class AdminController {
	
	
	private static final Logger LOGGER = Logger.getLogger(AdminController.class);
	
	@Autowired
	private AdminService adminService;

	@Autowired
	private BoardService boardService;
	
	
	@Autowired
	private SubSystem subSystem;
	
	
	
	
	@RequestMapping(value="menu", method=RequestMethod.GET)
	public String editMenuView(Model model) throws Exception {
		
		model.addAttribute("homeVo", boardService.getHomeInfo());
		model.addAttribute("menuList", boardService.getMenuList());
		return "admin/menu";
	}
	
	
	
	
	
	@RequestMapping(value="menu/list", method=RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getMenuList() throws Exception {
		return adminService.getMenuList();
	}
	

	
	
	
	
	@RequestMapping(value="menu", method=RequestMethod.POST)
	public @ResponseBody Map<String, String> editMenu(@RequestBody Map<String, List<Map<String, Object>>> map) {
		LOGGER.info("editMenu : " + map.toString());
		
		adminService.editMenuList(map);
		Map<String ,String> result = new HashMap<>();
		result.put("result", "success");
		return result;
	}
	
	
	
	
	
	@RequestMapping(value="home", method=RequestMethod.GET)
	public String editHomeView(Model model) throws Exception {

		model.addAttribute("homeVo", boardService.getHomeInfo());
		model.addAttribute("menuList", boardService.getMenuList());
		return "admin/home";
	}
	
	
	
	
	
	@RequestMapping(value="home", method=RequestMethod.POST)
	public String editHome(HomeVO homeVo) throws Exception {
		LOGGER.info("editHome : " + homeVo.toString());
		
		MultipartFile logFile = homeVo.getHome_logo_file();
		MultipartFile imgFile = homeVo.getHome_img_file();

		if (!logFile.getOriginalFilename().equals("")) {
			homeVo.setHome_logo(subSystem.uploadImg(logFile)[0]);
		}
		
		if (!imgFile.getOriginalFilename().equals("")) {
			homeVo.setHome_img(subSystem.uploadImg(imgFile)[0]);
		}
		
		adminService.editHome(homeVo);
		return "redirect:/admin/home";
	}
	

}
