package com.web.template.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.template.sub.Const;

@RequestMapping("captcha")
@Controller
public class NaverCaptchaController {
	
	
	
	private static final Logger LOGGER = Logger.getLogger(NaverCaptchaController.class);
	
	
	private static final String NAVER_CLIENT_ID = "9ukoh9TDWU815iwLmDoH";
	
	private static final String NAVER_CLIENT_SECRET = "XGtBlwUAim";
	
	
	
	@RequestMapping(value="key", method=RequestMethod.GET)
	public @ResponseBody String getKey() throws Exception {
		
		String code = "0";
		String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code;
		
		HttpURLConnection conn = getURLConnection(apiURL);
		
		int responseCode = conn.getResponseCode();
		
		BufferedReader br;
		
		if (responseCode == 200) {
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		
		String intputLine;
		StringBuffer response = new StringBuffer();
		while((intputLine  = br.readLine()) != null) {
			response.append(intputLine);
		}
		br.close();
		return response.toString();
	}
	
	
	
	
	@RequestMapping(value="image", method=RequestMethod.GET)
	public void getImage(@RequestParam("key") String key, HttpServletResponse res) throws Exception {
		
		LOGGER.info("getImageMethod : key=" + key);
		
		String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;
		HttpURLConnection conn = getURLConnection(apiURL);
		
		int responseCode = conn.getResponseCode();
		BufferedReader br;
		
		if (responseCode == 200) {
			
			InputStream is = conn.getInputStream();
			
			int read = 0;
			byte[] bytes = new byte[1024];
			

			
			ServletOutputStream outputStream = res.getOutputStream();
			res.setContentType(MediaType.IMAGE_JPEG_VALUE);
			
			while((read = is.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			
			is.close();
			outputStream.flush();
			
		} else {
			br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            LOGGER.info(response.toString());
		}
		
	}

	
	
	
	@RequestMapping(value="compare", method=RequestMethod.POST)
	public @ResponseBody String compareValue(@RequestBody Map<String, String> map) throws Exception {
	
		LOGGER.info("compareValue Method : " + map.toString());
		
		String key = map.get("key");
		String value = map.get("value");
		
		String code = "1";
		String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code + "&key=" + key + "&value=" + value;
	
		HttpURLConnection conn = getURLConnection(apiURL);
		int responseCode = conn.getResponseCode();
		
		BufferedReader br;
		if (responseCode == 200) {
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		
		String intputLine;
		StringBuffer response = new StringBuffer();
		while((intputLine  = br.readLine()) != null) {
			response.append(intputLine);
		}
		br.close();
		return response.toString();
	}
	
	
	
	
	private HttpURLConnection getURLConnection(String apiURL) throws Exception {
		
		URL url = new URL(apiURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("GET");
		conn.setRequestProperty("X-Naver-Client-Id", NAVER_CLIENT_ID);
		conn.setRequestProperty("X-Naver-Client-Secret", NAVER_CLIENT_SECRET);
		
		return conn;
	}
	
	
	
	
	
	
	
	
	
	
}
