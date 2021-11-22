package com.study.orders;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.study.orders.OrderMapper;

public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper mapper;

	@Override
	public int order(Map map) {
		return mapper.order(map);
	}

}
