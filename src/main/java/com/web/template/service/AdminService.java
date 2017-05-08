package com.web.template.service;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.template.mapper.AdminMapper;
import com.web.template.mapper.BoardMapper;
import com.web.template.sub.Const;
import com.web.template.vo.HomeVO;

@Service
public class AdminService {
	
	

	@Autowired
	private AdminMapper adminMapper;
	
	
	@Autowired
	private BoardMapper boardService;


	
	
	public List<Map<String, Object>> getMenuList() {

		List<Map<String, Object>> menuFir = adminMapper.getMenuFir();

		for (Map<String, Object> fir : menuFir) {
			String menu_fir_seq = fir.get("menu_fir_seq").toString();
			fir.put("menu_sec", adminMapper.getMenuSec(menu_fir_seq));
		}

		return menuFir;
	}

	
	
	
	@SuppressWarnings("unchecked")
	@CacheEvict(value = "getMenuList", key = "1")
	@Transactional
	public void editMenuList(Map<String, List<Map<String, Object>>> menuList) {
		List<Map<String, Object>> firArr = menuList.get("menu_fir");
		List<Map<String, Object>> deleteFir = menuList.get("del_menu_fir");
		List<Map<String, Object>> deleteSec = menuList.get("del_menu_sec");

		for (Map<String, Object> map : firArr) {
			String menu_fir_seq = map.get("menu_fir_seq").toString();
			// 대분류 추가.
			if (menu_fir_seq.contains("new")) {
				adminMapper.addMenuFir(map);
				
				// 새로 추가된 대분류의 중분류 추가.
				List<Map<String, String>> menu_sec_list = (List<Map<String, String>>) map.get("menu_sec");
				
				for (Map<String, String> menu_sec : menu_sec_list) {
					// menu_fir_seq에 맞게 추가.
					if (menu_fir_seq.equals(menu_sec.get("menu_fir_seq"))) {
						menu_sec.put("menu_fir_seq", map.get("menu_fir_seq").toString());
						adminMapper.addMenuSec(menu_sec);
					}
				}
				
				// 기존 대분류 수정.
			} else {
				
				adminMapper.editMenuFir(map);
				// 중분류 꺼내기.
				List<Map<String, String>> menu_sec_list = (List<Map<String, String>>) map.get("menu_sec");
				
				for (Map<String, String> menu_sec : menu_sec_list) {
					// 기존 대분류의 중분류 추가.
					if ((menu_sec.get("menu_sec_seq").toString()).equals("new")) {
						adminMapper.addMenuSec(menu_sec);
						// 기존 대분류의 기존 중분류 수정.
					} else {
						adminMapper.editMenuSec(menu_sec);
					}
				}
			}
		}

		for (Map<String, Object> map : deleteSec) {
			// 중분류 삭제, CASCADE로 글 삭제.
			adminMapper.deleteMenuSec(map);
		}

		for (Map<String, Object> map : deleteFir) {
			// 대분류 삭제, CASCADE로 하위 메뉴 및 글 삭제.
			adminMapper.deleteMenuFir(map);
		}
	}

	
	
	
	@CacheEvict(value = "getHomeInfo", key = "0")
	public void editHome(HomeVO homeVo) {
		adminMapper.editHome(homeVo);
	}
	
	
	
	
	
	/*@Scheduled(cron="")*/
	public void deleteImgs() {
		
		//따로 이미지를 관리하는 테이블이 없으므로 content에서 img를 추출.
		List<String> contents = boardService.getBoardAll();

		//삽입, 검색이 빠른 HashSet 사용.
		Set<String> namesInContent = new HashSet<>();
		
		Pattern pattern  =  Pattern.compile("src=[\"']?([^>\"']+)[\"']?[^>]");	

		for (String content : contents) {
			Matcher match = pattern.matcher(content);
		
			while (match.find()) {
				String imgName = match.group(0).replaceAll("src=", "").replaceAll("/uploads/", "").replaceAll("\"", "");
				namesInContent.add(imgName);
			}
		
		}
		
		
		HomeVO homeVo = boardService.getHomeInfo();

		namesInContent.add(homeVo.getHome_img());
		namesInContent.add(homeVo.getHome_logo());
		
		
		
		File dir = new File(Const.UPLOAD_PATH);
		
		if (dir.exists()) {
		
			String[] removeImgNames = dir.list(new FilenameFilter() {
				
				@Override
				public boolean accept(File dir, String name) {
					//DB에서 가져온 img 파일 이름들과 폴더에 있는 파일 이름을 비교.
					if (namesInContent.contains(name)) {					
						return false;
					} else {
						return true;	
					}
				}
				
			});
			
			for (String imgName : removeImgNames) {
				new File(Const.UPLOAD_PATH, imgName).delete();
			}
									
		}
		
	}

}
