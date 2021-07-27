package kr.ac.kopo.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
// type.field 면 변수
// type : class 위에 붙임
// method : method 위에 붙임. 컨트롤러에서.

//언제까지 인식시킬껀가를 정할 수 있음. 실행중에 어노테이션을 읽어서 .do를 어디컨트롤러에서 할건지를 찾을것. 유지가 되어야함
@Retention(RetentionPolicy.RUNTIME) // 프로그램 실행때까지 유지.
public @interface RequestMapping {

	String value(); //@RequestMapping(value="<얘를 추출할수있음>") 이것과 같은 뜻. 
//	String uri(); //이런식으로도 만들수있음.  @RequestMapping(uri="list.do")
	
	
	
}
