package exdev.com.common.controller;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import exdev.com.common.ExdevConstants;
import exdev.com.common.vo.SessionVO;

/**
 * @author 위성열
 */
@Controller("ExdevSessionController")
public class ExdevSessionController {
	
	@RequestMapping("setSession.do")
	public @ResponseBody Map setSession(@RequestBody Map map, HttpSession session) throws Exception {
		
		SessionVO sessionVO = new SessionVO();
		
		String spCstmId = (String)map.get("spCstmId");
		String userNm 	= (String)map.get("userId"	);
		String userId 	= (String)map.get("userNm"	);
		
		sessionVO.setSpCstmId	(spCstmId	);
		sessionVO.setUserId		(userNm		);
		sessionVO.setUserNm		(userId		);
		
		session.setAttribute(ExdevConstants.SESSION_ID, sessionVO);
		
		return null;
	}
}
