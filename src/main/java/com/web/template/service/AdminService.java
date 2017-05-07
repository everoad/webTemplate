package com.web.template.service;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
			String menu_fir_seq = fir.get("menu_fir_seq") + "";
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
			String menu_fir_seq = map.get("menu_fir_seq") + "";
			// 대분류 추가.
			if (menu_fir_seq.contains("new")) {
				adminMapper.addMenuFir(map);
				// 새로 추가된 대분류의 중분류 추가.
				for (Map<String, String> menu_sec : (List<Map<String, String>>) map.get("menu_sec")) {
					// menu_fir_seq에 맞게 추가.
					if (menu_fir_seq.equals(menu_sec.get("menu_fir_seq"))) {
						menu_sec.put("menu_fir_seq", map.get("menu_fir_seq") + "");
						adminMapper.addMenuSec(menu_sec);
					}
				}
				// 기존 대분류 수정.
			} else {
				adminMapper.editMenuFir(map);
				// 중분류 꺼내기.
				for (Map<String, String> menu_sec : (List<Map<String, String>>) map.get("menu_sec")) {
					// 기존 대분류의 중분류 추가.
					if ((menu_sec.get("menu_sec_seq") + "").equals("new")) {
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
		//삽입에 유리한 LinkedList
		List<String> list = new LinkedList<>();
		String imgName = null;
		Matcher match = null;
		Pattern pattern  =  Pattern.compile("src=[\"']?([^>\"']+)[\"']?[^>]");	

		for (String content : contents) {
			match = pattern.matcher(content);
		
			while(match.find()) {
				imgName = match.group(0).replaceAll("src=", "").replaceAll("/uploads/", "").replaceAll("\"", "");
				list.add(imgName);
			}
		
		}
		
		
		HomeVO homeVo = boardService.getHomeInfo();
		list.add(homeVo.getHome_img());
		list.add(homeVo.getHome_logo());
		

		
		//upload 폴더에서 이미지 파일 목록 가져오기
		String path = Const.UPLOAD_PATH;
		//삽입, 삭제가 용이한 LinkedList로 이루어진 LinkedHashMap 사용.
		Map<String, File> map = new LinkedHashMap<>();
		
		File dir = new File(path);
		
		if (dir.exists()) {
			//upload 폴더에서 파일 목록을 가져온다.
			File[] files = dir.listFiles();
			
			//파일 이름과 파일을 LinkedHashMap에 저장
			for (File file : files) {
				map.put(file.getName(), file);
			}
			
			//만약 DB 데이터와 일치하는 이름이 있다면 Map에서 삭제
			for (String dbName : list) {
				if (map.containsKey(dbName)) {
					map.remove(dbName);
				}
			}
		
			//Map에 남아있는 파일들은 더이상 사용되는 파일이 아니므로 삭제.
			for (String key : map.keySet()) {
				map.get(key).delete();
			}
			
		}
		
	}
	
	
	
	

}
