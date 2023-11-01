package com.solo.tree.member.model.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.solo.tree.member.model.dao.MemberDao;
import com.solo.tree.member.model.vo.Member;

@Service
public class KakaoService {
	
	
	@Autowired
	private MemberDao mDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	public String getAccessToken (String authorize_code) {
		
		  String access_Token = "";
          String refresh_Token = "";
          String reqURL = "https://kauth.kakao.com/oauth/token";

          try {
              URL url = new URL(reqURL);

              HttpURLConnection conn = (HttpURLConnection) url.openConnection();

              conn.setRequestMethod("POST");
              conn.setDoOutput(true);

              BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
              StringBuilder sb = new StringBuilder();
              sb.append("grant_type=authorization_code");
              sb.append("&client_id=3b117227ae449f5a74e1d81dda8cfc66"); 
              sb.append("&redirect_uri=http://localhost:8088/tree/kakao");     
              sb.append("&code=" + authorize_code);
              bw.write(sb.toString());
              bw.flush();

              int responseCode = conn.getResponseCode();

              BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
              String line = "";
              String result = "";

              while ((line = br.readLine()) != null) {
                  result += line;
              }
 

              JsonParser parser = new JsonParser();
              JsonElement element = parser.parse(result);

              access_Token = element.getAsJsonObject().get("access_token").getAsString();
              refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

              br.close();
              bw.close();
          } catch (IOException e) {
              e.printStackTrace();
          }
		
		
		
		return access_Token;
	}
	
	
	
	
	  public Member getUserInfo (String access_Token) {
		
		
		
		   HashMap<String, Object> userInfo = new HashMap<String, Object>();
	          String reqURL = "https://kapi.kakao.com/v2/user/me";
	          try {
	              URL url = new URL(reqURL);
	              HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	              conn.setRequestMethod("POST");     
	              conn.setRequestProperty("Authorization", "Bearer " + access_Token);
	              conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
	              conn.setRequestProperty("charset", "utf-8");
	              conn.setDoOutput(true);

	              int responseCode = conn.getResponseCode();
	              BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));

	              String line = "";
	              String result = "";

	              while ((line = br.readLine()) != null) {
	                  result += line;
	              }

	              JsonParser parser = new JsonParser();
	              JsonElement element = parser.parse(result);
	              

	              JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
	              JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
	              

	              String nickname = properties.getAsJsonObject().get("nickname").getAsString();
	              String profile_image = properties.getAsJsonObject().get("profile_image").getAsString();
	              String email = kakao_account.getAsJsonObject().get("email").getAsString();

	              userInfo.put("nickname", nickname);
	              userInfo.put("email", email);
	              userInfo.put("profile_image", profile_image);

	          } catch (IOException e) {
	              e.printStackTrace();
	          }
	          
	          Member result = mDao.findKakao(sqlSession,userInfo);
	          
	          if(result == null) {
	        	  mDao.insertKakao(sqlSession,userInfo);
	        	  
	        	  return mDao.findKakao(sqlSession,userInfo);
	          }else {
	        	  return result;
	          }
	
	
	
	}
	
	
	

}
