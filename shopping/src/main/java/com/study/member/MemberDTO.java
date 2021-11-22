package com.study.member;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.study.orders.OrdersDTO;

import lombok.Data;

@Data // lombok을 사용하는 annotation
public class MemberDTO {
	private String id;
	private String password;
	private String mname;
	private String tel;
	private String email;
	private String postcode;
	private String address1;
	private String address2;
	private String job;
	private String fname;
	private MultipartFile fnameMF; // MultipartFile : 파일 업로드 형태
	private String grade;

	private List<OrdersDTO> list;
}
