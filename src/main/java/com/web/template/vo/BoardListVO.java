package com.web.template.vo;

import java.util.List;

public class BoardListVO {

	private int index;
	private int totalPage;

	private int countNum;
	private int startBlock;
	private int endBlock;
	private int blockPerPage;
	private int previous;
	private int next;
	
	

	private String menu_fir_seq;
	private String menu_fir_name;

	private List<BoardVO> boardList;

	
	
	public int getPrevious() {
		return previous;
	}

	public void setPrevious(int previous) {
		this.previous = previous;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

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

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCountNum() {
		return countNum;
	}

	public void setCountNum(int countNum) {
		this.countNum = countNum;
	}

	public int getStartBlock() {
		return startBlock;
	}

	public void setStartBlock(int startBlock) {
		this.startBlock = startBlock;
	}

	public int getEndBlock() {
		return endBlock;
	}

	public void setEndBlock(int endBlock) {
		this.endBlock = endBlock;
	}

	public int getBlockPerPage() {
		return blockPerPage;
	}

	public void setBlockPerPage(int blockPerPage) {
		this.blockPerPage = blockPerPage;
	}

	public List<BoardVO> getBoardList() {
		return boardList;
	}

	public void setBoardList(List<BoardVO> boardList) {
		this.boardList = boardList;
	}

}
