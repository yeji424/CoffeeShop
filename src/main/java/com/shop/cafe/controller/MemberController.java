package com.shop.cafe.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shop.cafe.dto.Member;
import com.shop.cafe.service.MemberService;

@RestController
@CrossOrigin("http://127.0.0.1:5500/")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@PostMapping("login")
	public Map<String, String> login(@RequestBody Member m) {
		System.out.println(m);
		Map<String, String> responseMap = new HashMap<>();
		
		try {
			memberService.login(m);
			String nickname = m.getNickname(); // 한 번만 수행하도록
			if(m != null && nickname != null && !nickname.trim().equals("")) {
				responseMap.put("nickname", m.getNickname());
			} else {
				responseMap.put("msg", "다시 로그인하셈");
			}
		} catch(Exception e) {
			e.printStackTrace();
			responseMap.put("msg", "다시 로그인하셈");
		}
		return responseMap;
	}
	
	@PostMapping("insertMember")
	public String insertMember(@RequestBody Member m) {
		System.out.println(m);
		try {
			memberService.insertMember(m);
			return "ok";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return " nickname or email 중복";
		}
	}
}
