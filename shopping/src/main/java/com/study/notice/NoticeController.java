package com.study.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.utility.Utility;

@Controller
public class NoticeController {

	@Autowired
	@Qualifier("com.study.notice.NoticeServiceImpl")
	private NoticeService service;

	@RequestMapping("/notice/list")
	public String list(HttpServletRequest request) {

		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));
		if (col.equals("total")) {
			word = "";
		}

		int nowPage = 1;
		if (request.getParameter("nowpage") != null) {
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
		int recordPerPage = 5;

		int sno = ((nowPage - 1) * recordPerPage);
		int eno = nowPage * recordPerPage;

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);

		int total = service.total(map);

		List<NoticeDTO> list = service.list(map);

		String paging = Utility.paging(total, nowPage, recordPerPage, col, word);

		request.setAttribute("list", list);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("paging", paging);

		return "/notice/list";
	}

	@GetMapping("/notice/read")
	public String read(int noticeno, Model model) {

		service.upCnt(noticeno);

		NoticeDTO dto = service.read(noticeno);

		String content = dto.getContent().replaceAll("\r\n", "<br>");

		dto.setContent(content);
		model.addAttribute("dto", dto);

		return "/notice/read";
	}

	@GetMapping("/admin/notice/create")
	public String create() {
		return "/notice/create";
	}

	@PostMapping("/admin/notice/create")
	public String create(NoticeDTO dto) {
		if (service.create(dto) == 1) {
			return "redirect:list";
		} else {
			return "/error";
		}
	}

	@GetMapping("/admin/notice/update")
	public String update(int noticeno, Model model) {
		model.addAttribute("dto", service.read(noticeno));
		return "/notice/update";
	}

	@PostMapping("/admin/notice/update")
	public String update(NoticeDTO dto) {
		Map map = new HashMap();
		map.put("noticeno", dto.getNoticeno());
		map.put("passwd", dto.getPasswd());
		int pcnt = service.passwd(map);

		int cnt = 0;
		if (pcnt == 1) {
			cnt = service.update(dto);
		}

		if (pcnt != 1) {
			return "./passwdError";
		} else if (cnt == 1) {
			return "redirect:./list";
		} else {
			return "./error";
		}
	}

	@GetMapping("/admin/notice/delete")
	public String delete() {
		return "/notice/delete";
	}

	@PostMapping("/admin/notice/delete")
	public String delete(int noticeno, String passwd) {

		Map map = new HashMap();
		map.put("noticeno", noticeno);
		map.put("passwd", passwd);
		int pcnt = service.passwd(map);

		int cnt = 0;
		if (pcnt == 1) {
			cnt = service.delete(noticeno);
		}
		if (pcnt != 1) {
			return "./passwdError";
		} else if (cnt == 1) {
			return "redirect:./list";
		} else {
			return "./error";
		}
	}

}
