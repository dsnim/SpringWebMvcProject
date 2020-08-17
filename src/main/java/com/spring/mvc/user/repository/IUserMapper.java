package com.spring.mvc.user.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.spring.mvc.user.model.UserVO;

public interface IUserMapper {

	//회원가입 기능
	void register(UserVO user);
	
	//아이디 중복체크 기능
	Integer checkId(String account);
	
	//회원탈퇴 기능
	void delete(String account);
	
	//회원정보 조회기능
	UserVO selectOne(String account);
	
	//회원정보 전체 조회기능
	List<UserVO> selectAll();
	
	//자동로그인 쿠키값 DB저장 처리
	void keepLogin(Map<String, Object> datas);
	
	//세션아이디를 통한 회원정보 조회기능
	UserVO getUserWithSessionId(String sessionId);
}









