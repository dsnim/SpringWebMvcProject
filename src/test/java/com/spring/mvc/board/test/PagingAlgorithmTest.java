package com.spring.mvc.board.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.mvc.board.repository.IBoardMapper;
import com.spring.mvc.commons.PageVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/mvc-config.xml"})
public class PagingAlgorithmTest {
	/*
	 *** 페이징 알고리즘 테스트
	 
	 # 1. 사용자가 보게 될 페이지 화면
	 - 한 화면에 페이지를 10개씩 끊어서 
	 ex) 1 2 3 4 ... 9 10 [다음] // [이전] 31 32 33 ... 39 40 [다음]
	 
	 - 총 게시물의 수가 67개라면
	 ex) 1 2 3 4 5 6 7 //이전, 다음 비활성화
	 
	 - 총 게시물 수가 142개이고 현재 12페이지에 사용자가 머물러있다면
	 ex) [이전] 11 12 13 14 15
	 
	 # 2. 우선 총 게시물의 수를 조회
	 - 총 게시물 수는 DB로부터 수를 조회하는 SQL을 작성
	 
	 # 3. 사용자가 현재 위치한 페이지를 기준으로 
	      끝 페이지 번호를 계산하는 로직 작성
	 
	 - 만약 현재 사용자가 보고 있는 페이지가 3페이지고 한 화면에
	      보여줄 페이지가 10페이지씩이라면
	 	-> 끝 페이지 번호는 10페이지
	 - 만약 현재 페이지가 37페이지고 한 화면에 보여줄 페이지가
	   20페이지씩이라면
	 	-> 끝페이지 번호는 40페이지
	 
	 - 공식: Math.ceil(올림)(현재 위치한 페이지 번호 / 한 화면당 보여질 페이지의 수) * 한 화면당 보여질 페이지의 수
	 
	 # 4. 시작 페이지 번호 계산하기
	 - 현재 위치한 페이지가 15페이지고, 한 화면에 보여줄 페이지가 10페이지씩이라면??
	 	-> 시작페이지 번호는 11페이지
	 	
	 - 현재 위치한 페이지가 73페이지고, 한 화면에 20페이지씩 보여준다면??
	 	-> 시작페이지 번호는 61 페이지
	 	
	 - 공식: (끝 페이지 번호 - 한 화면에 보여줄 페이지의 수) + 1
	 
	 # 5. 끝 페이지 보정
	 
	 - 총 게시물 수가 324개이고 한 페이지당 10개의 게시물을 보여준다.
	 - 그리고 현재 이사람은 31페이지를 보고 있다.
	 - 한 화면당 10개씩의 페이지를 보여주고 있다.
	 - 위 공식에 의한 끝페이지는 어떻게 계산되는가? (40페이지)
	 - 하지만 실제 끝페이지는 몇번이어야 하는가? (33페이지)
	 
	 # 5-1. 이전버튼 활성 여부 설정
	 - 언제 이전버튼을 비활서화 할 것인가? 시작페이지가 1인 부분에서 비활성.
	 - 공식: 시작페이지 번호가 1로 구해진 시점에서 비활성 처리, 나머지는 활성.
	 
	 # 5-2. 다음버튼 활성 여부 설정
	 - 언제 다음버튼을 비활성화 할 것인가?
	 - 공식: 보정전 끝페이지번호 * 한 페이지에 들어갈 게시물 수 >= 총 게시물수 ->비활성
	 */
	
	@Autowired
	private IBoardMapper mapper;
	
	@Test
	public void pagingAlgorithmTest() {
		
		//총 게시물 수 구하는 테스트
		System.out.println("===================");
		System.out.println("# 총 게시물 수: " + mapper.countArticles()+ "개");
		System.out.println("===================");
		
		//끝 페이지 번호 계산 테스트
		PageVO paging = new PageVO();
		paging.setPage(21);
		int displayPage = 10;
		
		int endPage = (int)Math.ceil(paging.getPage() / (double)displayPage) * displayPage;
		System.out.println("끝 페이지 번호: " + endPage + "번");
		
		//시작 페이지 번호 계산 테스트
		int beginPage = (endPage - displayPage) + 1;
		System.out.println("시작페이지 번호: " + beginPage + "번");
		
		boolean isPrev = (beginPage == 1) ? false : true;
		System.out.println("이전 버튼 활성화 여부: " + isPrev);
		
		boolean isNext = (mapper.countArticles() <= (endPage * paging.getCountPerPage())) ? false : true;
		System.out.println("다음 버튼 활성화 여부: " + isNext);
		
		
		System.out.println("===================");
		
	}

}
