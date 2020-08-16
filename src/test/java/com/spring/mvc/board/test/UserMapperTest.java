package com.spring.mvc.board.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.repository.IUserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/mvc-config.xml"})
public class UserMapperTest {

	@Autowired
	private IUserMapper mapper;
	
	@Test
	public void registerTest() {
	
		UserVO user = new UserVO();
		user.setAccount("abc1234");
		user.setPassword("def1234!");
		user.setName("박영희");
		
		mapper.register(user);
		System.out.println("회원가입 성공!");
	
	}
	
	@Test
	public void deleteTest() {
		String account = "abc1234";
		mapper.delete(account);
		System.out.println("탈퇴 성공!");
	}
	
	
	@Test
	public void loginTest() {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String inputId = "abc1234";
		String inputPw = "aaa1234!";
		
		UserVO dbData = mapper.selectOne(inputId);
		String dbPw = dbData.getPassword();
		
		System.out.println("입력된 비밀번호: " + inputPw);
		System.out.println("DB에 등록된 비밀번호: " + dbPw);
		
//		System.out.println("비밀번호 일치?? " + inputPw.equals(dbPw));
		System.out.println("비밀번호 일치?? " + encoder.matches(inputPw, dbPw));
		
	}
	
}












