package com.spring.mvc.commons.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.service.IUserService;

public class AutoLoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private IUserService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		//1. 자동 로그인 쿠키가 있는지 여부 확인
		// - 쿠키(loginCookie)의 존재 여부 확인
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
		if(loginCookie != null) { //자동로그인 했다면~
			
			//2. DB에서 쿠키값과 일치하는 세션아이디를 가진 회원의 정보를 조회
			UserVO user = service.getUserWithSessionId(loginCookie.getValue());
			if(user != null) {
				session.setAttribute("login", user);
			}
		}
		
		return true;
	}

}
