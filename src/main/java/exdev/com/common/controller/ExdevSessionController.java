package exdev.com.common.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import exdev.com.common.ExdevConstants;
import exdev.com.common.vo.SessionVO;

/**
 * @author 위성열
 */
@Controller("ExdevSessionController")
public class ExdevSessionController {
	
	@RequestMapping("setSession.do")
	public ModelAndView setSession(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		SessionVO sessionVO = new SessionVO();
		
		sessionVO.setUserId("testUserId");
		sessionVO.setUserName("테스트");
		
		session.setAttribute(ExdevConstants.SESSION_ID, sessionVO);
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:/start.html");
		
		return mv;
	}
}
