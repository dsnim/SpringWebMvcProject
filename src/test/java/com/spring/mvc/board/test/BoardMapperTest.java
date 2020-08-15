package com.spring.mvc.board.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.repository.IBoardMapper;
import com.spring.mvc.commons.PageVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/mvc-config.xml"})
public class BoardMapperTest {

	@Autowired
	private IBoardMapper mapper;
	
	//게시글 등록 단위 테스트
	@Test
	public void insertTest() {
		
		for(int i=1; i<=320; i++) {
		
			BoardVO article = new BoardVO();
			article.setTitle("테스트 제목입니다."+i);
			article.setWriter("김테스트" + i);
			article.setContent("테스트 중이니까 말시키지 마시오!" + i);
			mapper.insert(article);
		
		}
		
		System.out.println("게시물 등록 성공!");
	}
	
	//게시글 목록 조회 테스트
	@Test
	public void getListTest() {
		
//		List<BoardVO> list = mapper.getArticleList();
//		for(BoardVO vo : list) {
//			System.out.println(vo);
//		}	
		
		//mapper.getArticleList().forEach(vo -> System.out.println(vo));
		
	}
	
	//게시글 단일 조회 테스트
	@Test
	public void getArticleTest() {
		
		BoardVO article = mapper.getArticle(44);
		System.out.println(article);
		
	}
	
	//게시물 수정 테스트 : 
	//VO의 세터를 사용하여 수정 내용(글제목, 글내용)을 입력하고
	//수정을 테스트해보세요.
	@Test
	public void updateTest() {
		BoardVO article = new BoardVO();
		article.setBoardNo(1);
		article.setTitle("수정된 제목 메롱~");
		article.setContent("수정 테스트중이지롱~~~");
		mapper.update(article);
		System.out.println("수정 후 정보: " + mapper.getArticle(1));
	}
	
	
	//게시물 삭제 테스트 : 게시글 번호를 통한 삭제를 확인하세요
	@Test
	public void deleteTest() {
		mapper.delete(3);
		BoardVO vo = mapper.getArticle(3);
		if(vo == null) {
			System.out.println("# 게시물이 없습니다!");
		} else {
			System.out.println("# 게시물 정보: " + vo);
		}
	}
	
	//페이징 단위 테스트
	@Test
	public void pagingTest() {
		System.out.println("===================================");
		PageVO paging = new PageVO();
		paging.setPage(0);
		paging.setCountPerPage(20);
		
		//mapper.getArticleListPaging(paging)
		//      .forEach(vo -> System.out.println(vo));
		System.out.println("===================================");
		
	}
}












