package com.study.cart;

import java.util.List;
import java.util.Map;

public interface CartService {

	int total(String id);

	List<CartDTO> list(Map map);

	int delete(Map map);

	int addCart(Map map);

}
