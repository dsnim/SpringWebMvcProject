package com.spring.mvc.commons.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//인터셉터 클래스를 만드려면 HandlerInterceptorAdapter클래스를 상속.
public class TestInterceptor extends HandlerInterceptorAdapter {
	
	//preHandle은 특정 컨트롤러에 진입하기 전에 공통처리할 내용을 작성.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("테스트 인터셉터의  preHandle이 작동!!");
		
		return true;//true가 리턴되면 컨트롤러로 진입, false가 리턴되면 진입실패
	}
	
	//postHandle은 컨트롤러를 나갈때 공통처리해야할 내용을 작성.
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("테스트 인터셉터의 postHandle 작동!!");
		
		Object data = modelAndView.getModel().get("data");
		
		if(data != null) {
			request.getSession().setAttribute("data", data);
			response.sendRedirect("/test1");
		}
	}
	
}









