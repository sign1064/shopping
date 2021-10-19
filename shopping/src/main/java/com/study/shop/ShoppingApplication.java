package com.study.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication  //원래는 ComponentScan이 포함되어 있는데 default는 같은 패키지 안에서만 scan
@ComponentScan(basePackages= {"com.study.*"}) 
public class ShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingApplication.class, args);
	}

}
