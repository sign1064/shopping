package com.study.member;

import java.util.List;
import java.util.Map;

import com.study.orders.OrdersDTO;

public interface MemberMapper {

	int duplicatedId(String id);
	
	int duplicatedEmail(String email);

	int create(MemberDTO dto);

	int loginCheck(Map<String, String> map);

	String getGrade(String id);

	MemberDTO read(String id);

	int update(MemberDTO dto);

	int updateFile(Map map);

	int total(Map map);

	List<MemberDTO> list(Map map);
	
	MemberDTO mypage(String id);

	int ototal(Map map);

	List<OrdersDTO> olist(Map map);

	int kakaoCreate(MemberDTO dto);

	int googleCreate(MemberDTO dto);
}
