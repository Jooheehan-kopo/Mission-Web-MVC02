package kr.ac.kopo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter{
	private String charset;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("필터처리...");
		
		//request.setCharacterEncoding("utf-8");
		request.setCharacterEncoding(charset);
		chain.doFilter(request, response);
		//페이지로 넘어가지않게함. 인코딩이 되지 않았으면.
		//filter class를 가지고 진행을 시킴. 까먹어도 알아서 처리해줌
		//ex) 로그인이 안되어있으면 넌 안됌
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException{
		charset = filterConfig.getInitParameter("encoding");
	}

}
