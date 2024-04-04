package exdev.com.common.controller;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import exdev.com.ExdevCommonAPI;
import exdev.com.common.ExdevConstants;
import exdev.com.common.dao.ExdevCommonDao;
import exdev.com.common.vo.SessionVO;

/**
 * @author 위성열
 */
@Controller("ExdevSessionController")
public class ExdevSessionController {
	
	@Autowired
	private ExdevCommonDao commonDao;

	@RequestMapping("setSession.do")
	public @ResponseBody Map setSession(@RequestBody Map map, HttpSession session) throws Exception {
		
		SessionVO sessionVO = new SessionVO();
		
		Map userInfo = (Map)commonDao.getObject("system.getUserInfo", map);
		
		if(!ExdevCommonAPI.isValid(userInfo)) {
			map.put("state", "E");
			return map;
		}
		
		String spCstmId = (String)userInfo.get("SP_CSTM_ID"	);
		String userNm 	= (String)userInfo.get("USER_NM"	);
		String userId 	= (String)userInfo.get("USER_ID"	);
		String grade 	= (String)userInfo.get("GRADE"		);
		String email	= (String)userInfo.get("EMAIL"		);
		
		sessionVO.setSpCstmId	(spCstmId	);
		sessionVO.setUserId		(userId		);
		sessionVO.setUserNm		(userNm		);
		sessionVO.setGrade		(grade		);
		sessionVO.setEmail		(email		);
		
		session.setAttribute(ExdevConstants.SESSION_ID, sessionVO);
		
		return userInfo;
	}
}
