package com.study.orders;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;

public class OrderController {

	@Autowired
	@Qualifier("com.study.order.OrderServiceImpl")
	private OrderService service;

	@RequestMapping("/cart/order")
	public String order(HttpServletRequest request, HttpSession session) {

		int cartno = Integer.parseInt(request.getParameter("cartno"));
		String id = (String) session.getAttribute("id");

		Map map = new HashMap();
		map.put("id", id);
		map.put("cartno", cartno);

		service.order(map);

		return "redirect:/member/mypage";
	}
}
