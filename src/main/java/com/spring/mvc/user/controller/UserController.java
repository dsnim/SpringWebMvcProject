package com.spring.mvc.user.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.mysql.cj.x.protobuf.MysqlxCrud.Limit;
import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService service;
	
	//회원가입 요청 처리
	//Rest-api에서 Insert-> POST
	@PostMapping("/")
	public String register(@RequestBody UserVO user) {
		System.out.println("/user/ POST 요청 발생!");
		System.out.println("param: " + user);
		
		service.register(user);
		return "joinSuccess";
	}
	
	//아이디 중복확인 요청 처리
	@PostMapping("/checkId") 
	public String checkId(@RequestBody String account) {
		
		System.out.println("/user/checkId: POST요청 발생!");
		System.out.println("parameter: " + account);
		String result = null;
		
		Integer checkNum = service.checkId(account);
		if(checkNum == 1) {
			System.out.println("아이디가 중복됨!");
			result = "NO";
		} else {
			System.out.println("아이디 사용가능!");
			result = "OK";
		}
		
		return result;
	}
	
	//로그인 요청 처리
	@PostMapping("/loginCheck")
	public String loginCheck(@RequestBody UserVO inputData, 
							/*HttpServletRequest request*/
							HttpSession session,
							HttpServletResponse response) {
		
		//서버에서 세션객체를 얻는 방법.
		//1. HttpServletRequest객체 사용
//		HttpSession session = request.getSession();
		//2. HttpSession객체 사용.
		
		String result = null;
		
		/*
		 # 클라이언트가 전송한 id값과 pw값을 가지고 DB에서 회원의 정보를
		   조회해서 불러온다음 값 비교를 통해
		   1. 아이디가 없을 경우 클라이언트측으로 문자열 "idFail" 전송
		   2. 비밀번호가 틀렸을 경우 문자열 "pwFail"전송
		   3. 로그인 성공시 문자열 "loginSuccess" 전송
		 */
		System.out.println("/user/loginCheck 요청! : POST");
		System.out.println("Parameter: " + inputData);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		UserVO dbData = service.selectOne(inputData.getAccount());
		
		if(dbData != null) {
			if(encoder.matches(inputData.getPassword(), dbData.getPassword())) {
				session.setAttribute("login", dbData);
				result = "loginSuccess";
				
				long limitTime = 60 * 60 * 24 * 90;
				
				//자동로그인 체크시 처리
				if(inputData.isAutoLogin()) {
					System.out.println("자동 로그인 쿠키 생성중...");
					Cookie loginCookie = new Cookie("loginCookie", session.getId());
					loginCookie.setPath("/");
					loginCookie.setMaxAge((int)limitTime);
					
					response.addCookie(loginCookie);
					
					//자동로그인 유지시간을 날자객체로 변환
					long expiredDate = System.currentTimeMillis() + (limitTime * 1000);
					Date limitDate = new Date(expiredDate);
					
					service.keepLogin(session.getId(), limitDate, inputData.getAccount());
				}
				
			} else {
				result = "pwFail";
			}
		} else {
			result = "idFail";
		}
		
		return result;
	}
	
	//로그아웃 요청 처리
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session,
							HttpServletRequest request,
							HttpServletResponse response) {
		
		System.out.println("/user/logout 요청!");
		
		UserVO user = (UserVO)session.getAttribute("login");
		
		if(user != null) {
			session.removeAttribute("login");
			session.invalidate();
			
			//로그아웃 시 자동로그인 쿠키 삭제 및 해당 회원 정보에서 session_id제거
			/*
			 1. loginCookie를 읽어온 뒤 해당 쿠키가 존재하는지 여부 확인
			 2. 쿠키가 존재한다면 쿠키의 수명을 0초로 다시 설정한 후(setMaxAge사용)
			 3. 응답객체를 통해 로컬에 0초짜리 쿠키 재전송 -> 쿠키 삭제
			 4. service를 통해 keepLogin을 호출하여 DB컬럼 레코드 재설정
			 	(session_id -> "none", limit_time -> 현재시간으로)
			 */
			Cookie loginCookie = WebUtils.getCookie(request, "LoginCookie");
			if(loginCookie != null) {
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				service.keepLogin("none", new Date(), user.getAccount());
			}
			
		}
		
		return new ModelAndView("redirect:/");
	}
	
	
	//회원탈퇴 요청 처리
//	@RequestMapping(value="/", method=RequestMethod.DELETE)
	@DeleteMapping("/{account}")
	public String delete(@PathVariable String account) {
		System.out.println("/user/" + account + ": DELETE 요청 발생!");
		
		service.delete(account);
		return "delSuccess";
	}
	
	//회원정보 조회 요청 처리
	@GetMapping("/{account}")
	public UserVO selectOne(@PathVariable String account) {
		System.out.println("/user/" + account + ": GET 요청 발생!");
		
		return service.selectOne(account);
	}
	
	//회원정보 전체조회 요청 처리
	@GetMapping("/")
	public List<UserVO> selectOne() {
		System.out.println("/user/ : GET 요청 발생!");
		
		return service.selectAll();
	}
	
}







