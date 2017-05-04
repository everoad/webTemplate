package com.web.template.vo;

import org.springframework.web.multipart.MultipartFile;

public class HomeVO {

	private String home_name;
	private String home_logo;
	private String home_img;
	private MultipartFile home_img_file;
	private MultipartFile home_logo_file;

	public MultipartFile getHome_img_file() {
		return home_img_file;
	}

	public void setHome_img_file(MultipartFile home_img_file) {
		this.home_img_file = home_img_file;
	}

	public MultipartFile getHome_logo_file() {
		return home_logo_file;
	}

	public void setHome_logo_file(MultipartFile home_logo_file) {
		this.home_logo_file = home_logo_file;
	}

	public String getHome_name() {
		return home_name;
	}

	public void setHome_name(String home_name) {
		this.home_name = home_name;
	}

	public String getHome_logo() {
		return home_logo;
	}

	public void setHome_logo(String home_logo) {
		this.home_logo = home_logo;
	}

	public String getHome_img() {
		return home_img;
	}

	public void setHome_img(String home_img) {
		this.home_img = home_img;
	}

	@Override
	public String toString() {
		return "HomeVO [home_name=" + home_name + ", home_logo=" + home_logo + ", home_img=" + home_img
				+ ", home_img_file=" + home_img_file + ", home_logo_file=" + home_logo_file + "]";
	}
	
	

}