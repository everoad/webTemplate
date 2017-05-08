package com.web.template.sub;

import java.io.File;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.web.template.event.MyEvent;
import com.web.template.security.CustomUserDetails;
import com.web.template.service.SearchService;


public class SubSystem {

	@Autowired
	private SearchService searchService;

	@Autowired
	private ApplicationEventPublisher publisher;

	
	
	
	public Boolean checkDevice() {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		
		String userAgent = req.getHeader("user-Agent");
		String[] mobileos = { "iPhone", "iPad", "Android", "Blackbarray", "windows CE", "Nokia", "Webos", "Opera Mini",
				"SonyEricssion", "Oprea Mobi", "IEMobile" };
		
		int j = -1;
		Boolean check = false;

		if (userAgent != null && !userAgent.equals("")) {
			for (String os : mobileos) {
				j = userAgent.indexOf(os);
				if (j > -1) {
					check = true;
					break;
				}
			}
		}

		return check;
	}

	
	
	
	
	public void publishMyEvent(String skey, CustomUserDetails details) throws Exception {

		Map<String, String> map = new HashMap<String, String>();
		
		map.put("skey", skey);
		
		if (details != null) {
			map.put("user_email", details.getUser_email());
		} else {
			map.put("user_email", "anonymous");
		}

		searchService.setLog(map);
		publisher.publishEvent(new MyEvent());

	}

	
	
	
	
	public String[] uploadImg(MultipartFile file) throws Exception {

		String newName = null;

		String[] array = null;
		if (file != null) {
			String originalFileName = URLDecoder.decode(file.getOriginalFilename(), "UTF-8");
			originalFileName = originalFileName.replaceAll(" ", "_");
			newName = System.currentTimeMillis() + "_" + originalFileName;

			array = new String[] { newName, originalFileName };


			File f = new File(Const.UPLOAD_PATH, newName);

			
			if (!f.exists()) {
				f.mkdirs();
			}

			file.transferTo(f);

		}
		
		return array;
	}
	
	
	
	
	
	public void getUserInfo(HttpServletRequest req) {
		//이전 페이지
		String referer = req.getHeader("referer");
		
		req.getHeader("host");
		req.getHeader("user-Agent");
		String ip = req.getHeader("X-FORWARDED-FOR");
    	if (ip == null) {
    		ip = req.getRemoteAddr();

    	}
	}
}
