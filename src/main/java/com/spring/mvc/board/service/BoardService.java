package com.spring.mvc.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.repository.IBoardMapper;
import com.spring.mvc.commons.PageVO;
import com.spring.mvc.commons.SearchVO;

@Service
public class BoardService implements IBoardService {
	
	@Inject
	private IBoardMapper mapper;

	@Override
	public void insert(BoardVO article) {
		mapper.insert(article);
	}

	@Override
	public List<BoardVO> getArticleList() {
		return mapper.getArticleList();
	}
	
	@Override
	public List<BoardVO> getArticleListPaging(PageVO paging) {
		//page = (page - 1) * 10;
		return mapper.getArticleListPaging(paging);
	}
	
	@Override
	public List<BoardVO> getArticleListByTitle(SearchVO search) {
		
		return mapper.getArticleListByTitle(search);
	}
	
	@Override
	public Integer countArticlesByTitle(SearchVO search) {
		return mapper.countArticlesByTitle(search);
	}
	
	@Override
	public List<BoardVO> getArticleListByWriter(SearchVO search) {
		return mapper.getArticleListByWriter(search);
	}
	
	@Override
	public Integer countArticlesByWriter(SearchVO search) {
		return mapper.countArticlesByWriter(search);
	}
	
	@Override
	public Integer countArticles() {
		return mapper.countArticles();
	}

	@Override
	public BoardVO getArticle(Integer boardNo) {
		return mapper.getArticle(boardNo);
	}

	@Override
	public void update(BoardVO article) {
		mapper.update(article);
	}

	@Override
	public void delete(Integer boardNo) {
		mapper.delete(boardNo);
	}

}
