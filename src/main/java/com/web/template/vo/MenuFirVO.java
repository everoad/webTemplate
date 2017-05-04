package com.web.template.vo;

import java.util.List;

public class MenuFirVO {

	private String menu_fir_seq;
	private String menu_fir_name;
	private String menu_fir_type;

	private List<MenuSecVO> menuSecList;

	public String getMenu_fir_seq() {
		return menu_fir_seq;
	}

	public void setMenu_fir_seq(String menu_fir_seq) {
		this.menu_fir_seq = menu_fir_seq;
	}

	public String getMenu_fir_name() {
		return menu_fir_name;
	}

	public void setMenu_fir_name(String menu_fir_name) {
		this.menu_fir_name = menu_fir_name;
	}

	public String getMenu_fir_type() {
		return menu_fir_type;
	}

	public void setMenu_fir_type(String menu_fir_type) {
		this.menu_fir_type = menu_fir_type;
	}

	public List<MenuSecVO> getMenuSecList() {
		return menuSecList;
	}

	public void setMenuSecList(List<MenuSecVO> menuSecList) {
		this.menuSecList = menuSecList;
	}

}
