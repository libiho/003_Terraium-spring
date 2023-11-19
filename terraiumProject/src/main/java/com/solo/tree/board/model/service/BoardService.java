package com.solo.tree.board.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solo.tree.board.model.dao.BoardDao;
import com.solo.tree.board.model.vo.Board;
import com.solo.tree.common.model.vo.PageInfo;

@Service
public class BoardService {

	@Autowired
	private BoardDao bDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	
	public int selectListCount() {
		return bDao.selectListCount(sqlSession);
	}



	public ArrayList<Board> selectList(PageInfo pi) {
		
		return bDao.selsectList(sqlSession,pi);
	}
	
	
	
	
}
