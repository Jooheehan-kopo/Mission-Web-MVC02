package kr.ac.kopo.framework;

import java.util.HashMap;
import java.util.Map;

/*
 *model : 화면단(jsp)에서 사용할 데이터를 저장하는 객체(request공유영역에 등록할 객체)   : map 형태
 *view : forward 시켜서 응답화면(jsp)의 정보 저장. 주소가져오기
 *
 */
public class ModelAndView {
	
	private String View;
	private Map<String, Object> model; //""로 받았으니 String, int string list 모르니까 object
	
	public ModelAndView() {
		model = new HashMap<>();
		
	}

	public String getView() {
		return View;
	}

	public void setView(String view) {
		View = view;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}
	
	//model에 key, value 집어넣기
	public void addAttribute(String key, Object value) {  //묵시적 형변환을 위해 object 인것임.
		model.put(key, value);
	}
	//boardcont 로 가서 타입 바꿔줘
}
