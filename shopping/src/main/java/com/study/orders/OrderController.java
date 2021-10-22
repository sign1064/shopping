package com.study.orders;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;

public class OrderController {
	
	@Autowired
	@Qualifier("com.study.order.OrderServiceImpl")
	private OrderService service;
	
	@PostMapping("/cart/orderForm")
	public String orderForm(HttpServletRequest request, OrdersDTO dto) {
		
		
		return "/order/orderForm";
	}
}
