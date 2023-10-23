package com.solo.tree.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {

	// 작동 잘되는지 테스트용
	@RequestMapping(value="list.bo")
	public String selsectList() {
		
		
		return "board/boardListView";
	}
	
	
}
