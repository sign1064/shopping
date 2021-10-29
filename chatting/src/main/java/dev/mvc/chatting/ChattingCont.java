package dev.mvc.chatting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChattingCont {
    @RequestMapping("/chatting")  // http://localhost:9092/chatting
    public ModelAndView chat() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("chatting"); // /src/main/webapp/WEB-INF/views/chatting.jsp
        
        return mav;
    }
}