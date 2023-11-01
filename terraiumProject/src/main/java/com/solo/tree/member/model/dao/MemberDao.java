package com.solo.tree.member.model.dao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.solo.tree.member.model.vo.Member;

@Repository
public class MemberDao {
	
	
	public void insertKakao(SqlSessionTemplate sqlSession, HashMap<String, Object> userInfo) {
		sqlSession.insert("memberMapper.insertKakao", userInfo);
	}

	public Member findKakao(SqlSessionTemplate sqlSession, HashMap<String, Object> userInfo) {
		return sqlSession.selectOne("memberMapper.findKakao", userInfo);
	}
	

}
