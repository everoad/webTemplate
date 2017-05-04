package com.web.template.vo;

import java.util.List;



public class SearchVO {

	
	private String index;

	private String main_flag;
	private String menu_fir_seq;
	private String menu_sec_seq;

	private int start;
	private int dataPerPage;

	private String skey;
	private List<String> skeyList;

	
	public String getMain_flag() {
		return main_flag;
	}

	public void setMain_flag(String main_flag) {
		this.main_flag = main_flag;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getMenu_fir_seq() {
		return menu_fir_seq;
	}

	public void setMenu_fir_seq(String menu_fir_seq) {
		this.menu_fir_seq = menu_fir_seq;
	}

	public String getMenu_sec_seq() {
		return menu_sec_seq;
	}

	public void setMenu_sec_seq(String menu_sec_seq) {
		this.menu_sec_seq = menu_sec_seq;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getDataPerPage() {
		return dataPerPage;
	}

	public void setDataPerPage(int dataPerPage) {
		this.dataPerPage = dataPerPage;
	}


	public String getSkey() {
		return skey;
	}

	public void setSkey(String skey) {
		this.skey = skey;
	}

	public List<String> getSkeyList() {
		return skeyList;
	}

	public void setSkeyList(List<String> skeyList) {
		this.skeyList = skeyList;
	}

	
	@Override
	public String toString() {
		return "SearchVO [index=" + index + ", main_flag=" + main_flag + ", menu_fir_seq=" + menu_fir_seq
				+ ", menu_sec_seq=" + menu_sec_seq + ", start=" + start + ", dataPerPage=" + dataPerPage + ", skey=" + skey + ", skeyList=" + skeyList + "]";
	}
	
	
}
