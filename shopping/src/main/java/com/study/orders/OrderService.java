package com.study.orders;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service("com.study.order.OrderServiceImpl")
public interface OrderService {

	int order(Map map);

}
