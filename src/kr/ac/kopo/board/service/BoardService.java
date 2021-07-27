package kr.ac.kopo.board.service;

import java.util.List;

import kr.ac.kopo.board.dao.BoardDAO;
import kr.ac.kopo.board.vo.BoardVO;

public class BoardService {
	
	private BoardDAO dao;
	
	/* 리스너에서 만들어줌.0
	 * public BoardService() { dao = new BoardDAO(); }
	 *serlvet이 서로 값을 공유하기위해 만들어진 공간 : servletContext
	 */
	
	public BoardService(BoardDAO dao) {
		this.dao = dao;
	}
	
	public List<BoardVO> listAll(){
		List<BoardVO> list = dao.selectAll();
		
		return list;
		
	}
}
