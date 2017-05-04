package com.web.template.mapper;

import java.util.List;
import java.util.Map;

import com.web.template.vo.BoardVO;
import com.web.template.vo.HomeVO;
import com.web.template.vo.MenuFirVO;
import com.web.template.vo.MenuSecVO;
import com.web.template.vo.SearchVO;

public interface BoardMapper {
	
	public int getTotalDataNum(SearchVO searchVo);
	
	public List<BoardVO> getList(SearchVO searchVo);
	
	public List<BoardVO> getMainList(SearchVO searchVo);
	
	
	public List<BoardVO> getRecentAll();
	
	public List<BoardVO> getBestAll();

	public BoardVO getDetail(String board_seq);

	public int addBoard(BoardVO boardVo) ;
	
	public int editBoard(BoardVO boardVo);
	
	public int deleteBoard(String board_seq);
	
	public int hitCount(String board_seq);
	
	
	public List<MenuFirVO> getMenuFir();
	
	public List<MenuSecVO> getMenuSec(String menu_fir_seq);

	public HomeVO getHomeInfo();

	
	public List<Map<String, String>> getReplyList(String board_seq);

	public int addReply(Map<String, String> map);

	public int deleteReply(String reply_seq);
	
	public List<String> getBoardAll();
	
	
	public void addReferer(String refererURL);
	

}
