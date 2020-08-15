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

	@Override
	public List<BoardVO> getArticleList(SearchVO search) {
		List<BoardVO> list = mapper.getArticleList(search);
		return list;
	}

	@Override
	public Integer countArticles(SearchVO search) {
		return mapper.countArticles(search);
	}

}
