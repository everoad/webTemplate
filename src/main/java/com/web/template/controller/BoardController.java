package com.web.template.controller;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.web.template.constance.Const;
import com.web.template.constance.SubSystem;
import com.web.template.security.CustomUserDetails;
import com.web.template.service.BoardService;
import com.web.template.validator.BoardValidator;
import com.web.template.vo.BoardVO;
import com.web.template.vo.MenuFirVO;
import com.web.template.vo.MenuSecVO;
import com.web.template.vo.PhotoVO;
import com.web.template.vo.SearchVO;

@Controller
@RequestMapping("board")
public class BoardController {
	
	private static final Logger LOGGER = Logger.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;
	

	@Autowired
	private SubSystem subSystem;
	
	
	@InitBinder("boardVo")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new BoardValidator());
	}
	
	
	
	@RequestMapping(value = "{menu_fir_seq}/{menu_sec_seq}", method = RequestMethod.GET)
	public String getList(@PathVariable("menu_fir_seq") String menu_fir_seq,
									@PathVariable("menu_sec_seq") String menu_sec_seq,
									@AuthenticationPrincipal CustomUserDetails details,
									SearchVO searchVo, Model model)  throws Exception {
		

		
		LOGGER.info(searchVo.toString());	
		List<MenuFirVO> menuList = boardService.getMenuList();
		
		for (MenuFirVO firVo : menuList) {
			if (firVo.getMenu_fir_seq().equals(menu_fir_seq)) {
				model.addAttribute("menuFirVo", firVo);
				for (MenuSecVO secVo : firVo.getMenuSecList()) {
					if (secVo.getMenu_sec_seq().equals(menu_sec_seq)) {
						model.addAttribute("menuSecVo", secVo);
						break;
					}
				}
			}
		}
		
		
		String skey = searchVo.getSkey();
		
		if (skey != null) {
			subSystem.publishMyEvent(skey, details);			
		}
		
		
		
		searchVo.setMenu_fir_seq(menu_fir_seq);
		searchVo.setMenu_sec_seq(menu_sec_seq);
		
		
		model.addAttribute("device", subSystem.checkDevice());
		model.addAttribute("bestList", boardService.getBestAll());
		model.addAttribute("recentList", boardService.getRecentAll());
		model.addAttribute("menuList", menuList);
		model.addAttribute("menu_fir_seq", menu_fir_seq);
		model.addAttribute("menu_sec_seq", menu_sec_seq);
		model.addAttribute("homeVo", boardService.getHomeInfo());
		model.addAttribute("boardVoList", boardService.getList(searchVo));
		return "board/list";
	}

	
	
	
	
	
	@RequestMapping(value = "{menu_fir_seq}/{menu_sec_seq}/{board_seq}", method = RequestMethod.GET)
	public String getDetail(@PathVariable("menu_fir_seq") String menu_fir_seq,
										@PathVariable("menu_sec_seq") String menu_sec_seq, 
										@PathVariable("board_seq") String board_seq, Model model)  throws Exception {
		
		LOGGER.info("getDetail method : board_seq = " + board_seq);
		
		model.addAttribute("homeVo", boardService.getHomeInfo());
		model.addAttribute("menuList", boardService.getMenuList());
		model.addAttribute("menu_fir_seq", menu_fir_seq);
		model.addAttribute("menu_sec_seq", menu_sec_seq);
		model.addAttribute("boardVo", boardService.getDetail(board_seq));
		return "board/detail";
	}

	
	
	
	
	
	@Secured(value = { Const.ROLE_USER, Const.ROLE_ADMIN })
	@RequestMapping(value = "{menu_fir_seq}/{menu_sec_seq}/add", method = RequestMethod.GET)
	public String addView(@PathVariable("menu_fir_seq") String menu_fir_seq,
										@PathVariable("menu_sec_seq") String menu_sec_seq, Model model)  throws Exception {
	
		model.addAttribute("homeVo", boardService.getHomeInfo());
		model.addAttribute("menuList", boardService.getMenuList());
		model.addAttribute("menu_fir_seq", menu_fir_seq);
		model.addAttribute("menu_sec_seq", menu_sec_seq);
		model.addAttribute("boardVo", new BoardVO());
		return "board/add";
	}
	

	
	
	
	@Secured(value = { Const.ROLE_USER, Const.ROLE_ADMIN })
	@RequestMapping(value = "{menu_fir_seq}/{menu_sec_seq}/add", method = RequestMethod.POST)
	public String add(@PathVariable("menu_fir_seq") String menu_fir_seq,
								@PathVariable("menu_sec_seq") String menu_sec_seq, 
								@AuthenticationPrincipal CustomUserDetails user,
								@ModelAttribute("boardVo") @Valid BoardVO boardVo, BindingResult br) throws Exception {
		
		LOGGER.info("add method : " + boardVo.toString());
		
		if (br.hasErrors()) { return "board/add"; }
		
		boardVo.setUser_email(user.getUser_email());
		boardVo.setMenu_fir_seq(menu_fir_seq);
		boardVo.setMenu_sec_seq(menu_sec_seq);
		return "redirect:/board/"+menu_fir_seq+ "/" + menu_sec_seq+ "/" + boardService.addBoard(boardVo).getBoard_seq();

	}

	
	
	
	
	
	@Secured(value={Const.ROLE_USER, Const.ROLE_ADMIN})
	@RequestMapping(value = "{menu_fir_seq}/{menu_sec_seq}/{board_seq}/edit", method = RequestMethod.GET)
	public String editView(@PathVariable("menu_fir_seq") String menu_fir_seq,
										@PathVariable("menu_sec_seq") String menu_sec_seq, 
										@PathVariable("board_seq") String board_seq, Model model) throws Exception {
		
		LOGGER.info("editView method : board_seq = " + board_seq);
		
		model.addAttribute("homeVo", boardService.getHomeInfo());
		model.addAttribute("menuList", boardService.getMenuList());
		model.addAttribute("menu_fir_seq", menu_fir_seq);
		model.addAttribute("menu_sec_seq", menu_sec_seq);
		model.addAttribute("boardVo", boardService.getEditDetail(board_seq));
		return "board/edit";
	}
	
	
	
	
	
	
	
	@Secured(value={Const.ROLE_USER, Const.ROLE_ADMIN})
	@RequestMapping(value = "{menu_fir_seq}/{menu_sec_seq}/{board_seq}/edit", method = RequestMethod.POST)
	public String edit(@PathVariable("menu_fir_seq") String menu_fir_seq,
								@PathVariable("menu_sec_seq") String menu_sec_seq, 
								@ModelAttribute("boardVo") @Valid BoardVO boardVo, BindingResult br,
								@PathVariable("board_seq") String board_seq) throws Exception {
		
		LOGGER.info("edit method :" + boardVo.toString());
		
		if (br.hasErrors()) { return "board/edit"; }
		
		boardVo.setBoard_seq(board_seq);
		boardVo.setMenu_fir_seq(menu_fir_seq);
		boardVo.setMenu_sec_seq(menu_sec_seq);
		boardService.editBoard(boardVo);
		return "redirect:/board/" +menu_fir_seq + "/" + menu_sec_seq + "/" + board_seq;
	}
	
	
	
	
	
	
	
	
	@Secured(value={Const.ROLE_USER, Const.ROLE_ADMIN})
	@RequestMapping(value="{menu_fir_seq}/{menu_sec_seq}/{board_seq}/delete", method=RequestMethod.POST)
	public String delete(@PathVariable("menu_fir_seq") String menu_fir_seq,
									@PathVariable("menu_sec_seq") String menu_sec_seq, 	
									@PathVariable("board_seq") String board_seq, 
							    	@RequestParam("user_email") String user_email)  throws Exception {
		
		boardService.deleteBoard(board_seq, user_email);
		return "redirect:/board/" +menu_fir_seq + "/" + menu_sec_seq;
	}
	
	
	
	
	
	
	
	
	@Secured(value={Const.ROLE_USER, Const.ROLE_ADMIN})
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public String fileUpload(PhotoVO photoVo, HttpSession session) throws Exception {
		
		LOGGER.info(photoVo.toString());

		String file_result = "";
		
		MultipartFile file = photoVo.getFiledata();
		
		if (file != null) {
			
			String[] nameArr = subSystem.uploadImg(file);
			
			String newName = nameArr[0];
			String originalFileName = nameArr[1];
			
			String contextUrl = session.getServletContext().getContextPath();
			file_result += "&bNewLine=true&sFileName=" + URLEncoder.encode(originalFileName, "UTF-8")
					+ "&sFileURL="+contextUrl+"/uploads/" + URLEncoder.encode(newName, "UTF-8");
	
		} else {
			file_result += "&errstr=error";
		}
		return "redirect:" + photoVo.getCallback() + "?callback_func=" + photoVo.getCallback_func() + file_result;
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value="reply", method=RequestMethod.GET)
	public @ResponseBody List<Map<String, String>> getReplyList(@RequestParam("board_seq") String board_seq) {
		
		LOGGER.info("getReplyList method : board_seq = " + board_seq);
		return boardService.getReplyList(board_seq);
	}
	
	
	
	
	
	
	
	
	@Secured(value={Const.ROLE_USER, Const.ROLE_ADMIN})
	@RequestMapping(value="reply",method=RequestMethod.POST)
	public String addReply(@RequestParam Map<String, String> map,
										@AuthenticationPrincipal CustomUserDetails user) {
		
		LOGGER.info("addReply method : " + map.toString());
		
		map.put("user_email", user.getUser_email());
		boardService.addReply(map);
		return "redirect:/board/reply?board_seq=" + map.get("board_seq");
	}
	
	
	
	
	
	@Secured(value={Const.ROLE_USER, Const.ROLE_ADMIN})
	@RequestMapping(value="reply/{board_seq}", method=RequestMethod.POST)
	public String deleteReply(@PathVariable("board_seq") String board_seq,
											@RequestParam Map<String, String> map) {
		
		LOGGER.info("deleteReply method : " + map.toString());
		
		map.put("board_seq", board_seq);
		boardService.deleteReply(map);
		return "redirect:/board/reply?board_seq=" + board_seq;
	}
	

}
