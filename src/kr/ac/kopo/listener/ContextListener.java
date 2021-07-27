package kr.ac.kopo.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import kr.ac.kopo.board.dao.BoardDAO;
import kr.ac.kopo.board.service.BoardService;

public class ContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {  //servletContext가 만들어짐. 공유하기우ㅣ해서
		System.out.println("리스너 시작,,");
		
		ServletContext context = sce.getServletContext();
		
		BoardDAO boardDAO = new BoardDAO();
		BoardService boardService = new BoardService(boardDAO);
		
		context.setAttribute("boardService", boardService);
		//context.setAttribute("boardDao", boardService); 할필요없음. 위에서 이미 만들어서 들어가있응ㅁ.
	}
	
	

}
