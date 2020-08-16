package com.spring.mvc.user.service;

import java.util.List;

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

}









