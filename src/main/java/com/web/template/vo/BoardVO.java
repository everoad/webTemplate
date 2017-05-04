package com.web.template.vo;

public class BoardVO {

	private String board_seq;
	private String title;
	private String content;
	private String reg_date;
	private String hit_count;
	private String reply_count;
	private String front_img;
	private String user_nick;
	private String user_email;

	private String menu_fir_seq;
	private String menu_fir_name;
	private String menu_sec_seq;
	private String menu_sec_name;

	public String getMenu_fir_name() {
		return menu_fir_name;
	}

	public void setMenu_fir_name(String menu_fir_name) {
		this.menu_fir_name = menu_fir_name;
	}

	public String getMenu_sec_name() {
		return menu_sec_name;
	}

	public void setMenu_sec_name(String menu_sec_name) {
		this.menu_sec_name = menu_sec_name;
	}

	public String getUser_nick() {
		return user_nick;
	}

	public void setUser_nick(String user_nick) {
		this.user_nick = user_nick;
	}

	public String getBoard_seq() {
		return board_seq;
	}

	public void setBoard_seq(String board_seq) {
		this.board_seq = board_seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getHit_count() {
		return hit_count;
	}

	public void setHit_count(String hit_count) {
		this.hit_count = hit_count;
	}

	public String getReply_count() {
		return reply_count;
	}

	public void setReply_count(String reply_count) {
		this.reply_count = reply_count;
	}

	public String getFront_img() {
		return front_img;
	}

	public void setFront_img(String front_img) {
		this.front_img = front_img;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
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

	
	@Override
	public String toString() {
		return "BoardVO [board_seq=" + board_seq + ", title=" + title + ", content=" + content + ", reg_date="
				+ reg_date + ", hit_count=" + hit_count + ", reply_count=" + reply_count + ", front_img=" + front_img
				+ ", user_nick=" + user_nick + ", user_email=" + user_email + ", menu_fir_seq=" + menu_fir_seq
				+ ", menu_fir_name=" + menu_fir_name + ", menu_sec_seq=" + menu_sec_seq + ", menu_sec_name="
				+ menu_sec_name + "]";
	}
	

}
