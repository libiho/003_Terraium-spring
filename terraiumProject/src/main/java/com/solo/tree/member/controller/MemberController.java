package com.solo.tree.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.solo.tree.member.model.service.KakaoService;
import com.solo.tree.member.model.vo.Member;

@Controller
public class MemberController {

	
	@Autowired
	private KakaoService kakaoSerive;
	
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	
	private String token = "";
	
	
	
	@RequestMapping("enrollForm.me")
	public String enrollForm() {
		return "member/memberEnrollForm";
	}
	
	
	@RequestMapping("login.me")
	public String login() {
		return "member/login";
	}
	
	
	@RequestMapping(value="/kakao", method=RequestMethod.GET)
	public String kakaoLogin(@RequestParam(value="code", required = false) String code, HttpSession session) throws Throwable {
		
		if(token == "") {
			String access_Token = kakaoSerive.getAccessToken(code);
			token = access_Token;
			}
			Member userInfo = kakaoSerive.getUserInfo(token);
			session.setAttribute("loginUser", userInfo);

		
		return "main";
	}
	
	
	
}
