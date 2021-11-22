package com.study.cart;

import lombok.Data;

@Data
public class CartDTO {
	private int cartno;
	private String id;
	private int contentsno;
	private String pname;
	private int amount;
	private int price;
	private int total;
}
