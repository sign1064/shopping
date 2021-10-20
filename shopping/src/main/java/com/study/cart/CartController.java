package com.study.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.contents.ContentsDTO;
import com.study.utility.Utility;

@Controller
public class CartController {

	@Autowired
	@Qualifier("com.study.cart.CartServiceImpl")
	private CartService service;
	
	
	@RequestMapping("/cart/delete")
	public String delete(HttpServletRequest request) {
		int cartno = Integer.parseInt(request.getParameter("cartno"));
		int contentsno = Integer.parseInt(request.getParameter("contentsno"));
		
		Map map = new HashMap();
		map.put("cartno", cartno);
		map.put("contentsno", contentsno);
		
		int cnt = service.delete(map);
		
		if(cnt > 0) {
			return "redirect:/cart/list";
		}else {
			return "error";
		}
	}
	
	
	@GetMapping("/cart/addCart")
	public String orderFrom(HttpServletRequest request, HttpSession session) {
		
		String id = session.getId();
		int contentsno = Integer.parseInt(request.getParameter("contentsno"));
		String pname = request.getParameter("pname");
		int amount = Integer.parseInt(request.getParameter("amount"));
		int price = Integer.parseInt(request.getParameter("price"));
		
		
		Map map = new HashMap();
		
		map.put("id", id);
		map.put("contentsno", contentsno);
		map.put("pname", pname);
		map.put("amount", amount);
		map.put("price", price);
		
		int cnt = service.addCart(map);
		
		
		return "redirect:/cart/list";
	}
	
	
	@GetMapping("/cart/list")
	public String cart(HttpSession session, HttpServletRequest request) {
		String id = (String) session.getAttribute("id");

		if (id != null) {
			int nowPage = 1;
			if (request.getParameter("nowPage") != null) {
				nowPage = Integer.parseInt(request.getParameter("nowPage"));
				}
			int recordPerPage = 3;

			int sno = ((nowPage - 1) * recordPerPage) + 1;
			int eno = nowPage * recordPerPage;

			Map map = new HashMap();
			map.put("sno", sno);
			map.put("eno", eno);
			map.put("id", id);
			
			List<CartDTO> list = service.list(map);
			
			int total = service.total(id);

			String paging = Utility.paging3(total, nowPage, recordPerPage);

			request.setAttribute("list", list);
			request.setAttribute("nowPage", nowPage);
			request.setAttribute("paging", paging);

			return "/cart/list";

		} else {

			return "/member/login";
		}
	}
}
