package com.study.cart;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("com.study.cart.CartServiceImpl")
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartMapper mapper;
	
	@Override
	public int total(String id) {
		// TODO Auto-generated method stub
		return mapper.total(id);
	}

	@Override
	public List<CartDTO> list(Map map) {
		// TODO Auto-generated method stub
		return mapper.list(map);
	}

}
