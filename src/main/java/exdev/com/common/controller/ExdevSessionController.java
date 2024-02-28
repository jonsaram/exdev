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
		
		String spCstmId = (String)map.get("spCstmId"	);
		String userNm 	= (String)map.get("userNm"		);
		String userId 	= (String)map.get("userId"		);
		String grade 	= (String)map.get("grade"		);
		String email	= (String)map.get("email"		);
		
		sessionVO.setSpCstmId	(spCstmId	);
		sessionVO.setUserId		(userId		);
		sessionVO.setUserNm		(userNm		);
		sessionVO.setGrade		(grade		);
		sessionVO.setEmail		(email		);
		
		session.setAttribute(ExdevConstants.SESSION_ID, sessionVO);
		
		return null;
	}
}
