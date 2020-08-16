package com.spring.mvc.user.service;

import java.util.List;

import com.spring.mvc.user.model.UserVO;

public interface IUserService {

	//회원가입 기능
	void register(UserVO user);

	//회원탈퇴 기능
	void delete(String account);

	//회원정보 조회기능
	UserVO selectOne(String account);
	
	//회원정보 전체 조회기능
	List<UserVO> selectAll();

}
