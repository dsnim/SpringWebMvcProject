package com.spring.mvc.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.repository.IUserMapper;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserMapper mapper;
	
	@Override
	public void register(UserVO user) {
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









