package kr.ac.kopo.framework;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
//@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
	private HandlerMapping mappings;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {  //controller 이름을 가져옴(board,list)
		String ctrlNames= config.getInitParameter("controllers");
		//System.out.println(ctrlNames);
		try {
			
		mappings=	new HandlerMapping(ctrlNames);
		}catch(Exception e){
			throw new ServletException();
		}
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		uri = uri.substring(request.getContextPath().length());
		System.out.println("요청 uri: " + uri);
		
		String view ="";
		try {
		CtrlAndMethod cam = mappings.getCtrlAndMethod(uri);
		//.out.println("ctrl:" + cam.getTarget());
		//System.out.println("method: "+ cam.getMethod()); //null임
		
		if(cam == null){//dh옳바르게 uri 를 받아오지못했을때{
			throw new ServletException("해당 uri는 존재하지 않습니다");
		}
		
		//어떤 컨트롤러의 메소드
		Object target = cam.getTarget(); // = kr.ac.kopo.controller.boardcontroller()를 알고 있음.
		Method method = cam.getMethod(); //Method method = public String list(HttpServlet Request request,,,) 의 정보가 들어있음. => invoke가 실행
		
		
		/*
		 * 
		 * board/list.do 요청
		 * target = new boardController()
		 * Method method = public String list(HttpServlet Request request,,,) 의 정보가 들어있음.
		 * String callPage = target.list(request, response)
		 * 요청객체ㅔ와 response 넘겨ㅕ줘야했었음
		 */
		
//		target.method() // boardcontroller 안에 있는 method라는 method를 가리킴. 동적으로 실행하게 만들어야함.=> invoke
		
//		method.invoke(target, request, response) ;// target의 어떤 method 실행 - request랑 response 받아옴. /try catch로 묶어줌.
		
		ModelAndView mav = (ModelAndView)method.invoke(target, request, response);
		view = mav.getView();
		
		//request 공유영역에 등록 -데이터 불러오기
			Map<String, Object> model = mav.getModel();
			Set<String> keys = model.keySet(); // 순서가 지맘대로라서 다 뽑아오고,  get으로 key에 대한 값을 넣준 것임.
			for(String key : keys) {
				request.setAttribute(key, model.get(key));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//3응답을 위한 jsp 이동 sendredirect 요청. redirect:/login.do
		if (view.startsWith("redirect:")) {
			view = view.substring("redirect:".length()); // "/login.do"
			response.sendRedirect(request.getContextPath()+ view);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(view); //response.sendRedirect("Mission-web-mvc")
			dispatcher.forward(request, response);
		}
	}
	//1실행하면 *.do로 됨. url을 바꿔줘야함. /board/list.do로!
	
	//2공유영역에 올리는 행동을 Dispatcher가 하게. 원래는 forword만 했음. controller가 올리고.
	
}
