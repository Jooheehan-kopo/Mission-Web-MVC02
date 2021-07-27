package kr.ac.kopo.framework;


import java.lang.reflect.Method; //reflection 할것임
import java.util.HashMap;
import java.util.Map;

import kr.ac.kopo.framework.annotation.RequestMapping;

public class HandlerMapping {

 private Map<String, CtrlAndMethod> mappings;
 
 public HandlerMapping(String ctrlNames) throws Exception{
	 //ctrlNames => kr.ac.kopo.board.controller.BoardController|
	 
	 mappings = new HashMap<>();
	 
	 System.out.println(ctrlNames);
	 
	 String[] ctrls = ctrlNames.split("\\|");
	 
	 for(String ctrl: ctrls) {
		// System.out.println(ctrl.trim());
		 Class<?> clz = Class.forName(ctrl.trim());  //컨트롤러이름
		 
		 //Object target = clz.newInstance();
		 Object target = clz.getDeclaredConstructor().newInstance();  //
		// System.out.println(target);
		
		 
		 //Method[] methods = clz.getDeclareMethods()  --내가 만든것만 나옴
		 Method[] methods = clz.getMethods();//controller 내클래서에 정의되어잇는 모든 메소드를 알려달라는것(public만)
		 for(Method method : methods) {
			 System.out.println(method);
			RequestMapping reqAnno =  method.getAnnotation(RequestMapping.class);
			System.out.println("reqAnno: "+ reqAnno);
			
			if(reqAnno != null) {
				//System.out.println(method);
				String uri = reqAnno.value();
				//System.out.println("uri: "+ uri);
				
				CtrlAndMethod cam = new CtrlAndMethod(target,method);
				mappings.put(uri, cam);
			}
		 
		 }
		 
		 System.out.println();
	 }
	 
 }
 public CtrlAndMethod getCtrlAndMethod(String uri) {
	 return mappings.get(uri);
 }
}





















