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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.utility.Utility;

@Controller
public class CartController {

	@Autowired
	@Qualifier("com.study.cart.CartServiceImpl")
	private CartService service;

	@RequestMapping("/cart/addCart")
	public String orderFrom(HttpServletRequest request, HttpSession session) {

		int contentsno = Integer.parseInt(request.getParameter("contentsno"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		String id = (String) session.getAttribute("id");

		Map map = new HashMap();
		map.put("id", id);
		map.put("contentsno", contentsno);
		map.put("amount", amount);

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

	@GetMapping("/cart/delete")
	public String deleteCart(HttpServletRequest request) {
		int cartno = Integer.parseInt(request.getParameter("cartno"));

		int cnt = service.delete(cartno);

		if (cnt > 0) {
			return "redirect:/cart/list";
		} else {
			return "error";
		}
	}

}
