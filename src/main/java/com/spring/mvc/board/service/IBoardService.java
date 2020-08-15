package com.spring.mvc.board.service;

import java.util.List;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.commons.PageVO;
import com.spring.mvc.commons.SearchVO;

public interface IBoardService {

	//게시글 등록 기능
	void insert(BoardVO article);

	//게시글 상세 조회기능
	BoardVO getArticle(Integer boardNo);

	//게시글 수정 기능
	void update(BoardVO article);

	//게시글 삭제 기능
	void delete(Integer boardNo);
	
	//게시글 목록 조회 기능(검색, 페이징 통합)
	List<BoardVO> getArticleList(SearchVO search);
	Integer countArticles(SearchVO search);
	
	//////////////////////////////////////////////////////////////////

	//게시글 목록 조회기능
	//List<BoardVO> getArticleList();

	//게시글 페이징 목록 조회기능
	//List<BoardVO> getArticleListPaging(PageVO paging);

	//제목으로 검색기능
	//List<BoardVO> getArticleListByTitle(SearchVO search);

	//제목으로 검색 이후 게시물 수 조회기능
	//Integer countArticlesByTitle(SearchVO search);

	//작성자로 검색기능
	//List<BoardVO> getArticleListByWriter(SearchVO search);

	//작성자로 검색 이후 게시물 수 조회기능
	//Integer countArticlesByWriter(SearchVO search);

	//총 게시물의 수 조회기능
	//Integer countArticles();

	

}
