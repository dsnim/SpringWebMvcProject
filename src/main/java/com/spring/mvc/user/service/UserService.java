package com.spring.mvc.user.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.repository.IUserMapper;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserMapper mapper;
	
	@Override
	public void register(UserVO user) {
		
		//회원 비밀번호를 암호화 인코딩
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println("암호화 전: " + user.getPassword());
		
		//비밀번호를 암호화하여 다시 user객체에 저장.
		String securePw = encoder.encode(user.getPassword());
		user.setPassword(securePw);
		System.out.println("암호화 후: " + securePw);
		
		mapper.register(user);
	}
	
	@Override
	public Integer checkId(String account) {
		return mapper.checkId(account);
	}
	
	@Override
	public void delete(String account) {
		mapper.delete(account);
	}
	
	@Override
	public UserVO selectOne(String account) {
		return mapper.selectOne(account);
	}
	
	@Override
	public List<UserVO> selectAll() {
		return mapper.selectAll();
	}
	
	@Override
	public void keepLogin(String sessionId, Date limitDate, String account) {
		
		Map<String, Object> datas = new HashMap<>();
		datas.put("sessionId", sessionId);
		datas.put("limitDate", limitDate);
		datas.put("account", account);
		
		mapper.keepLogin(datas);
	}

	@Override
	public UserVO getUserWithSessionId(String sessionId) {
		return mapper.getUserWithSessionId(sessionId);
	}
	
	
}









