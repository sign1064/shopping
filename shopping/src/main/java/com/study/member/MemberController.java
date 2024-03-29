package com.study.member;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.contents.Contents;
import com.study.utility.Utility;

@Controller
public class MemberController {
	
	@Autowired
	@Qualifier("com.study.member.MemberServiceImpl") //이 타입(서비스 인터페이스를 구현한 클래스)의 객체를 서비스 인터페이스에 주입하겠다
	private MemberService service; //컨트롤러는 서비스 인터페이스를 사용하기 때문에 연결하기 위해(@Autowired로 미리 생성된 서비스를 가져옴)
	
	@GetMapping("/")
	public String home() {

		return "/home";  //tiles(jsp) 
	}
	
	@GetMapping("/member/agree")
	public String agree() {
		return "/member/agree";
	}
	
	@PostMapping("/member/createForm")
	public String create() {
			
		return "/member/create";
	}
	
	@PostMapping("/member/create")
	public String create(MemberDTO dto) throws IOException {
		
		//String upDir = new ClassPathResource("/static/member/storage").getFile().getAbsolutePath();
		
		String upDir = Contents.getUploadDir();
		
		String fname = Utility.saveFileSpring(dto.getFnameMF(), upDir);
		
		int size = (int)dto.getFnameMF().getSize();
		
		if(size>0) {
			dto.setFname(fname);
			
		}else {
			dto.setFname("member.jpg");
		}
		
		if(service.create(dto)>0) {
			return "redirect:/";
		}else {
			return "error";
		}
	}
	
	@GetMapping(value="/member/idcheck",produces="application/json;charset=utf-8")
	@ResponseBody
	public Map<String,String> idcheck(String id){
		
		int cnt = service.duplicatedId(id); 
		
		Map<String,String> map = new HashMap<String,String>();
		if(cnt>0) {
			map.put("str", id+"는 중복되어서 사용할 수 없습니다.");	
		}else {
			map.put("str", id+"는 사용 가능한 ID입니다.");
		}
		return map;
	}
	
	@GetMapping(value="/member/emailcheck", produces="application/json;charset=utf-8")  //url, parameter
	@ResponseBody
	public Map<String, String> emailcheck(String email){   //Map<String, String>  --> JSON return
		
		int cnt = service.duplicatedEmail(email);
		
		Map<String, String> map = new HashMap<String, String>();
		
		if(cnt>0) {
			map.put("str", email+"는 중복되어 사용할 수 없습니다.");
		}else {
			map.put("str", email+"는 사용 가능한 Email입니다.");
		}
		
		return map;
	}
	
	
	
	@GetMapping("/member/login") //클라이언트에서 쿠키를 받아 저장
    public String login(HttpServletRequest request) {
            /*----쿠키설정 내용시작----------------------------*/ 
            String c_id = "";     // ID 저장 여부를 저장하는 변수, Y
            String c_id_val = ""; // ID 값
             
            Cookie[] cookies = request.getCookies(); 
            Cookie cookie=null; 
             
            if (cookies != null){ 
             for (int i = 0; i < cookies.length; i++) { 
               cookie = cookies[i]; 
             
               if (cookie.getName().equals("c_id")){ 
                 c_id = cookie.getValue();     // Y 
               }else if(cookie.getName().equals("c_id_val")){ 
                 c_id_val = cookie.getValue(); // user1... 
               } 
             } 
            } 
            /*----쿠키설정 내용 끝----------------------------*/
            
            request.setAttribute("c_id", c_id);
            request.setAttribute("c_id_val", c_id_val);
            
            return "/member/login";
    }
	
	
	@PostMapping("/member/login")
	public String login(@RequestParam Map<String,String> map,  //id, 비번을 파라미터인 map으로 받는다
            			HttpSession session, //세션 저장
            			HttpServletResponse response, //remember id --> 쿠키 저장
            			HttpServletRequest request, //c_id 받아오기 위해
            			Model model) { 
		
		 int cnt = service.loginCheck(map);  //id, 비번으로 로그인 확인
         if(cnt > 0) {//회원이다
                 String grade = service.getGrade(map.get("id"));
                 session.setAttribute("id", map.get("id"));
                 session.setAttribute("grade", grade);
                 //Cookie 저장,id저장 여부 및 id
                 Cookie cookie = null;
                 String c_id = request.getParameter("c_id");  //체크한 값을 가져온다
                 if(c_id != null) {  //c_id=> Y
                         cookie = new Cookie("c_id",c_id ); 
                         cookie.setMaxAge(60 * 60 * 24 * 365);//1년
                         response.addCookie(cookie);//client 쿠키 전송
                         
                         cookie = new Cookie("c_id_val",map.get("id")); //id로 쿠키 생성
                         cookie.setMaxAge(60 * 60 * 24 * 365);//1년
                         response.addCookie(cookie);//client에 쿠키 전송
                 }else { //c_id=> 체크 X
                         cookie = new Cookie("c_id",""); //쿠키 삭제
                         cookie.setMaxAge(0);
                         response.addCookie(cookie);
                         
                         cookie = new Cookie("c_id_val","");//쿠키 삭제
                         cookie.setMaxAge(0);
                         response.addCookie(cookie);                  
                 }
         }//if(cnt>0) end
                 
         if(cnt>0) {
                     return "redirect:/";
             }else {
            	 	model.addAttribute("msg","아이디 또는 비밀번호를 잘못 입력 했거나 <br>회원이 아닙니다. 회원가입 하세요");
            	 	return "/member/errorMsg";
     }
	}
	
//	@PostMapping("/member/logout/kakao/callback")
//	public String logoutKakaoCallback() {
//		return "/member/logout";
//	}
	
	@RequestMapping("/member/logout")
    public String logout(HttpSession session) {
		
			String grade = (String) session.getAttribute("grade");
			String token = (String) session.getAttribute("token");
			
			System.out.println(grade);
			System.out.println(token);
			
			if(grade == "KH") {
				System.out.println("로그아웃 준비");
				String reqURL ="https://kapi.kakao.com/v1/user/unlink";
		        try {
		            URL url = new URL(reqURL);
		    		
		            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		            conn.setRequestMethod("POST");
		    		conn.setRequestProperty("Authorization","Bearer " + token);
		            
		            int responseCode = conn.getResponseCode();
		            System.out.println("responseCode : " + responseCode);
		 
		            if(responseCode ==400)
		                throw new RuntimeException("카카오 로그아웃 도중 오류 발생");
		            
		            
		            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		            
		            String br_line = "";
		            String result = "";
		            while ((br_line = br.readLine()) != null) {
		                result += br_line;
		            }
		            System.out.println("결과"+result);
		        }catch(IOException e) {
		            
		        }
				
				
//		        try {
//		        	String reqURL ="https://kauth.kakao.com/oauth/logout?client_id=50749d73349bbd242c92152b3bde6677&logout_redirect_uri=http://localhost8000";
//		            URL url = new URL(reqURL);
//		    		
//		            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		            conn.setRequestMethod("GET");
//		    		conn.setRequestProperty("Authorization","Bearer " + token);
//		            
//		            int responseCode = conn.getResponseCode();
//		            System.out.println("responseCode : " + responseCode);
//		 
//		            if(responseCode ==400)
//		                throw new RuntimeException("카카오 로그아웃 도중 오류 발생");
//		            
//		            
//		            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//		            
//		            String br_line = "";
//		            String result = "";
//		            while ((br_line = br.readLine()) != null) {
//		                result += br_line;
//		            }
//		            System.out.println("결과"+result);
//		        }catch(IOException e) {
//		            
//		        }

			}
            session.invalidate();  //세션 삭제
            
            return "redirect:/";
    }
	
	
	
	@GetMapping("/member/update")
	public String update(String id, HttpSession session, Model model) {

		if (id == null) {
			id = (String) session.getAttribute("id");
		}

		MemberDTO dto = service.read(id);

		model.addAttribute("dto", dto);

		return "/member/update";
	}

	@PostMapping("/member/update")
	public String update(MemberDTO dto, Model model) {
		int cnt = service.update(dto);

		if (cnt == 1) {
			model.addAttribute("id", dto.getId());
			return "redirect:./read";
		} else {
			return "error";
		}
	}
	
	@GetMapping("/member/read")
	public String read(String id,HttpSession session,Model model) {
		if (id == null) {
			id = (String) session.getAttribute("id");
		}

		MemberDTO dto = service.read(id);

		model.addAttribute("dto", dto);
		
		return "/member/read";
	}
	
	
	@PostMapping("/member/updateFile")
    public String updateFile(MultipartFile fnameMF,
                    String oldfile,
                    HttpSession session,
                    HttpServletRequest request) throws IOException{
            String basePath = new ClassPathResource("/static/member/storage").getFile().getAbsolutePath();
            
            if(oldfile !=null  && !oldfile.equals("member.jpg")) { //원본파일 삭제
                    Utility.deleteFile(basePath, oldfile);
            }
            
            //storage에 변경 파일 저장
            Map map = new HashMap();
            map.put("id", session.getAttribute("id"));
            map.put("fname", Utility.saveFileSpring(fnameMF, basePath));
            
            //디비에 파일명 변경
            int cnt = service.updateFile(map);
            
            if(cnt==1) {
                    return "redirect:./read";
            }else {
                    return "./error";
            }
    }
	
	
	@GetMapping("/member/updateFile")
    public String updateFile() {
            
            return "/member/updateFile";
    }
	
	
	@GetMapping("/member/download")
    public void download(HttpServletRequest request,
                    HttpServletResponse response) throws IOException {

            // 절대경로
            //String dir = new ClassPathResource("/static/member/storage").getFile().getAbsolutePath();
			String dir = Member.getUploadDir();
            String filename = request.getParameter("filename");
            byte[] files = FileUtils.readFileToByteArray(new File(dir, filename));
            response.setHeader("Content-disposition",
                            "attachment; fileName=\"" + URLEncoder.encode(filename, "UTF-8") + "\";");
            // Content-Transfer-Encoding : 전송 데이타의 body를 인코딩한 방법을 표시함.
            response.setHeader("Content-Transfer-Encoding", "binary");
            /**
             * Content-Disposition가 attachment와 함게 설정되었다면 'Save As'로 파일을 제안하는지 여부에 따라 브라우저가
             * 실행한다.
             */
            response.setContentType("application/octet-stream");
            response.setContentLength(files.length);
            response.getOutputStream().write(files);
            response.getOutputStream().flush();
            response.getOutputStream().close();
    } 
	
	@RequestMapping("/admin/list")
    public String list(HttpServletRequest request) {
            // 검색관련------------------------
            String col = Utility.checkNull(request.getParameter("col"));
            String word = Utility.checkNull(request.getParameter("word"));

            if (col.equals("total")) {
                    word = "";
            }

            // 페이지관련-----------------------
            int nowPage = 1;// 현재 보고있는 페이지
            if (request.getParameter("nowPage") != null) {
                    nowPage = Integer.parseInt(request.getParameter("nowPage"));
            }
            int recordPerPage = 3;// 한페이지당 보여줄 레코드갯수

            // DB에서 가져올 순번-----------------
            int sno = ((nowPage - 1) * recordPerPage) + 1;
            int eno = nowPage * recordPerPage;

            Map map = new HashMap();
            map.put("col", col);
            map.put("word", word);
            map.put("sno", sno);
            map.put("eno", eno);

            int total = service.total(map);

            List<MemberDTO> list = service.list(map);

            String paging = Utility.paging(total, nowPage, recordPerPage, col, word);

            // request에 Model사용 결과 담는다
            request.setAttribute("list", list);
            request.setAttribute("nowPage", nowPage);
            request.setAttribute("col", col);
            request.setAttribute("word", word);
            request.setAttribute("paging", paging);
            
            return "/admin/list";
            
    }
	
	@GetMapping("/member/mypage")
	public String mypage(HttpSession session, Model model) {
	   String id = (String)session.getAttribute("id");
	 
	  if(id==null) {
	       return "redirect: ./login/";
	  }else {
	  
	       MemberDTO dto = service.mypage(id);
	      
	       model.addAttribute("dto", dto);
	      
	   return "/member/mypage";
	  }
	}

}