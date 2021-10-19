package com.study.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;

public class OrderController {
	
	@Autowired
	@Qualifier("com.study.order.OrderServiceImpl")
	private OrderService service;
	
	@GetMapping("/order")
	public String order() {
		return "/order";
	}
}
