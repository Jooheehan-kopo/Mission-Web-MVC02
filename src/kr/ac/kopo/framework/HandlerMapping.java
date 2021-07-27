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
		 Object target = clz.getDeclaredConstructor().newInstance();  //얘가 controller 알고있음.
		// System.out.println(target);
		
		 
		 //Method[] methods = clz.getDeclareMethods()  --내가 만든것만 나옴
		 Method[] methods = clz.getMethods();//controller 내클래서에 정의되어잇는 모든 메소드를 알려달라는것(public만)
		 for(Method method : methods) {
			 System.out.println(method); //이런 메소드 중에서 requestMappingAnno가 붙은 것을 찾고싶은것임.
			 
			 
			RequestMapping reqAnno =  method.getAnnotation(RequestMapping.class); //request anno가 있는 애만 가져와 = board, list
			System.out.println("reqAnno: "+ reqAnno);
			
			if(reqAnno != null) {
				//System.out.println(method);
				String uri = reqAnno.value();
				//System.out.println("uri: "+ uri);
				
				CtrlAndMethod cam = new CtrlAndMethod(target,method);
				mappings.put(uri, cam);  //위에서 받은 값을 mappings에 넣어줌. target은 컨트롤러, method
			}
		 
		 }
		 
		 System.out.println();
	 }
	 
 }
 
 //key와 value를 주면 값을 줘라. 다 받아논 mappins에서 uri 값만 받아오면 됨.
 public CtrlAndMethod getCtrlAndMethod(String uri) {
	 return mappings.get(uri);
 }
}





















