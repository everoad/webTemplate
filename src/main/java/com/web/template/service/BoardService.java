package com.web.template.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.web.template.mapper.BoardMapper;
import com.web.template.vo.BoardListVO;
import com.web.template.vo.BoardVO;
import com.web.template.vo.HomeVO;
import com.web.template.vo.MenuFirVO;
import com.web.template.vo.SearchVO;

@Service
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;
		

	
	public BoardListVO getMainList(SearchVO searchVo) throws Exception {
		String skey = searchVo.getSkey();
		
		if (skey != null) {
			List<String> skeyList = Arrays.asList(skey.trim().split(" "));
			for (int i = 0; i < skeyList.size(); i++) {
				if (skeyList.get(i).equals(""))
					skeyList.remove(i);
			}
			searchVo.setSkeyList(skeyList);
		}
		
		BoardListVO boardListVo = new BoardListVO();
		boardListVo.setBoardList(boardMapper.getMainList(searchVo));
		
		return boardListVo;
	}
	
	
	
	public BoardListVO getList(SearchVO searchVo) throws Exception {

		String skey = searchVo.getSkey();
		
		if (skey != null) {
			List<String> skeyList = Arrays.asList(skey.trim().split(" "));
			for (int i = 0; i < skeyList.size(); i++) {
				if (skeyList.get(i).equals(""))
					skeyList.remove(i);
			}
			searchVo.setSkeyList(skeyList);
		}

		String indexStr = searchVo.getIndex();
		int index = (indexStr == null || !Pattern.matches("^[0-9]*$", indexStr)) ? 1 : Integer.parseInt(indexStr);
		
		int dataPerPage = 12;
		int totalDataNum = boardMapper.getTotalDataNum(searchVo);
		int totalPage = (int) Math.ceil((double) totalDataNum / (double) dataPerPage);
		
		totalPage = (totalPage == 0) ? 1 : totalPage;
		index = (index < 1) ? 1 : index;
		index = (index > totalPage) ? totalPage : index;
		
		int start = (index - 1) * dataPerPage;
		searchVo.setStart(start);
		
		if(searchVo.getDataPerPage() == 0) {
			searchVo.setDataPerPage(dataPerPage);
		}
		
		List<BoardVO> boardList = boardMapper.getList(searchVo);
		
		int countNum = (totalDataNum - (index-1) * dataPerPage);
		int blockPerPage = 5;
		int startBlock = (index - 1) / blockPerPage * blockPerPage + 1;
		int endBlock = ((index - 1) / blockPerPage) * blockPerPage + blockPerPage;
		endBlock = (endBlock > totalPage) ? totalPage : endBlock;
	
		int previous = (startBlock - blockPerPage < 0) ? startBlock : startBlock - blockPerPage;
		int next = (startBlock + blockPerPage > totalPage) ? totalPage : startBlock + blockPerPage;
		
		BoardListVO boardListVo = new BoardListVO();

		boardListVo.setPrevious(previous);
		boardListVo.setNext(next);
		boardListVo.setIndex(index);
		boardListVo.setTotalPage(totalPage);
		boardListVo.setStartBlock(startBlock);
		boardListVo.setEndBlock(endBlock);
		boardListVo.setCountNum(countNum);
		boardListVo.setBlockPerPage(blockPerPage);
		boardListVo.setBoardList(boardList);
		return boardListVo;
	}

	
	
	
	
	public BoardVO getDetail(String board_seq) {
	    boardMapper.hitCount(board_seq);
		return boardMapper.getDetail(board_seq);
	}

	
	
	
	
	public BoardVO addBoard(BoardVO boardVo) throws Exception {
		String front_img = findFrontImg(boardVo.getContent());
		boardVo.setFront_img(front_img);
	
		boardMapper.addBoard(boardVo);
		
		return boardVo;
	}

	
	
	
	

	@PostAuthorize("(principal.user_email == returnObject.user_email) or hasRole('ROLE_ADMIN')")
	public BoardVO getEditDetail(String board_seq) throws Exception {
		return boardMapper.getDetail(board_seq);
	}

	
	
	
	

	@PreAuthorize("(principal.user_email == #boardVo.user_email) or hasRole('ROLE_ADMIN')")
	public BoardVO editBoard(BoardVO boardVo) throws Exception {
		String front_img = findFrontImg(boardVo.getContent());
		boardVo.setFront_img(front_img);
		
		boardMapper.editBoard(boardVo);
		
		return boardVo;
	}

	
	
	
	
	@PreAuthorize("(principal.user_email == #user_email) or hasRole('ROLE_ADMIN')")
	public void deleteBoard(String board_seq, String user_email) throws Exception {
		boardMapper.deleteBoard(board_seq);
	}
	
	
	

	public List<BoardVO> getRecentAll() {	
		return boardMapper.getRecentAll();
	}
	
	
	public List<BoardVO> getBestAll() {
		return boardMapper.getBestAll();
	}
 	
	
	
	@Cacheable(value="getMenuList", key="1")
	public List<MenuFirVO> getMenuList() {
		List<MenuFirVO> menuFirList = boardMapper.getMenuFir();
		for(MenuFirVO vo : menuFirList) {
			vo.setMenuSecList(boardMapper.getMenuSec(vo.getMenu_fir_seq()));
		}
		return menuFirList;
	}
	
	
	
	
	
	public List<Map<String, String>> getReplyList(String board_seq) {
		return boardMapper.getReplyList(board_seq);
	}

	
	

	public void addReply(Map<String, String> map) {
		//Trigger로 reply_count up
		boardMapper.addReply(map);
	}

	
	
	
	public void deleteReply(Map<String, String> map) {
		//Trigger로 reply_count down
		boardMapper.deleteReply(map.get("reply_seq"));
	}
	
	
	
	
	@Cacheable(value="getHomeInfo", key="0")
	public HomeVO getHomeInfo() {
		return boardMapper.getHomeInfo();
	}
	
	
	
	
	private String findFrontImg(String content) {
		Pattern pattern  =  Pattern.compile("src=[\"']?([^>\"']+)[\"']?[^>]");	
		// 내용 중에서 이미지 태그를 찾아라!
		Matcher match = pattern.matcher(content);
		String imgName = null;
		if(match.find()) { // 이미지 태그를 찾았다면,,
			imgName = match.group(0).replaceAll("src=", "").replaceAll("\"", "");
		}
		return imgName;
	}
	
	public void addReferer(String refererURL) {
		boardMapper.addReferer(refererURL);
	}
	
}
