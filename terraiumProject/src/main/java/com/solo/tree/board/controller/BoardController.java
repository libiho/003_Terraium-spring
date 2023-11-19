package com.solo.tree.board.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.solo.tree.board.model.service.BoardService;
import com.solo.tree.board.model.vo.Board;
import com.solo.tree.common.model.vo.PageInfo;
import com.solo.tree.common.template.Pagination;

@Controller
public class BoardController {
	
	
	@Autowired
	private BoardService bService;
	
	

	// 작동 잘되는지 테스트용
	@RequestMapping(value="list.bo")
	public ModelAndView selsectList(@RequestParam(value="cpage", defaultValue="1") int currentPage, ModelAndView mv) {
		
		int listCount = bService.selectListCount();
		
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, 10, 5);
		
		ArrayList<Board> list = bService.selectList(pi);
		
		mv.addObject("pi",pi).addObject("list",list).setViewName("board/boardListView");
		
		
		
		return mv;
	}
	
	
}
