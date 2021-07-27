package kr.ac.kopo.board.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.board.service.BoardService;
import kr.ac.kopo.board.vo.BoardVO;
import kr.ac.kopo.framework.ModelAndView;
import kr.ac.kopo.framework.annotation.RequestMapping;

public class BoardController {

	private BoardService service;

	/*
	 * public BoardController() { service = new BoardService();
	 * 
	 * }
	 */

	// /board/list.do가 들어왔을때 요청시, 실행 메소드

	@RequestMapping(value = "/board/list.do")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// request.setAttribute("abc", "abc"); //이렇게했지만 model view class 를 만들꺼임

		System.out.println("게시판 조회 서비스");
		ServletContext sc = request.getServletContext();
		service = (BoardService) sc.getAttribute("boardService");
		List<BoardVO> boardlist = service.listAll();

//		List<BoardVO> boardList = new ArrayList<>();
		List<BoardVO> boardList = service.listAll(); // 전체객체를 가져옴

//		boardList.add(new BoardVO(5, "aaa","bbb","ccc",0, "2021-07-27"));
//		boardList.add(new BoardVO(4, "ddd","eee","fff",0, "2021-07-26"));
//		boardList.add(new BoardVO(2, "ggg","hhh","iii",0, "2021-07-23"));

		ModelAndView mav = new ModelAndView();
		mav.setView("/board/list.jsp");
		mav.addAttribute("list", boardList);  //공유영역에 넣어주라고 mav에 넣어줌.

		// request공유영역에 등록
		Map<String, Object> model = mav.getModel();
		// object는 리턴형을 모르니까 묵시적 형변환으로 Object리턴 (VO,,,)명시적 형변환 필수
		// Generic 명시적 형변환으로 설정된 것. 시점별로 내가 넣은 형의 데이터가 날아가게됨

		// model에 몇개가 있을 지 모른다.
		Set<String> keys = model.keySet();
		for (String key : keys) {
			request.setAttribute(key, model.get(key));
		}
		return mav;
	}
	// return "list.jsp";

	// /board/detail.do 요청시 실행 메소드
	@RequestMapping(value = "/board/detail.do")
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("게시판 상세 서비스");
		ModelAndView mav = new ModelAndView();
		mav.setView("/board/detail.jsp");
		return mav;

	}

	@RequestMapping("/board/writeForm.do")
	public ModelAndView write(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setView("/board/writeForm.jsp");
		return mav;
	}

	@RequestMapping("/board/write.do")
	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");

		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		System.out.println("title: " + title);
		System.out.println("writer: " + writer);
		System.out.println("content: " + content);

		ModelAndView mav = new ModelAndView();
		mav.setView("redirect:/board/list.do");
		return mav;
	}
}
