package exdev.com.common.controller;
import java.util.HashMap;
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
		
		String spCstmId 	= (String)userInfo.get("SP_CSTM_ID"	);
		String userNm 		= (String)userInfo.get("USER_NM"	);
		String userId 		= (String)userInfo.get("USER_ID"	);
		String grade 		= (String)userInfo.get("GRADE"		);
		String email		= (String)userInfo.get("EMAIL"		);
		
		sessionVO.setSpCstmId	(spCstmId	);
		sessionVO.setUserId		(userId		);
		sessionVO.setUserNm		(userNm		);
		sessionVO.setGrade		(grade		);
		sessionVO.setEmail		(email		);
		sessionVO.setLoginType	("USER"		);
		
		session.setAttribute(ExdevConstants.SESSION_ID, sessionVO);
		
		return userInfo;
	}

	@RequestMapping("setSessionForBuyer.do")
	public @ResponseBody Map setSessionForBuyer(@RequestBody Map map, HttpSession session) throws Exception {
		
		SessionVO sessionVO = new SessionVO();
		
		Map userInfo = (Map)commonDao.getObject("system.getBuyerInfoForLogin", map);
		
		if(!ExdevCommonAPI.isValid(userInfo)) {
			map.put("state", "E");
			return map;
		}
		
		String spCstmId 	= (String)userInfo.get("SP_CSTM_ID"	);
		String userNm 		= (String)userInfo.get("BUYER_NM"	);
		String userId 		= (String)userInfo.get("BUYER_ID"	);
		
		sessionVO.setSpCstmId	(spCstmId	);
		sessionVO.setUserId		(userId		);
		sessionVO.setUserNm		(userNm		);
		sessionVO.setLoginType	("BUYER"	);
		
		session.setAttribute(ExdevConstants.SESSION_ID, sessionVO);
		
		return userInfo;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("getSession.do")
	public @ResponseBody Map getSession(@RequestBody Map map, HttpSession session) throws Exception {
		
		SessionVO sessionVO = (SessionVO)session.getAttribute(ExdevConstants.SESSION_ID);
		
		HashMap userInfo = new HashMap();

		userInfo.put("SP_CSTM_ID"	,  sessionVO.getSpCstmId	());
		userInfo.put("USER_NM"		,  sessionVO.getUserNm		());
		userInfo.put("USER_ID"		,  sessionVO.getUserId		());
		userInfo.put("GRADE"		,  sessionVO.getGrade		());
		userInfo.put("EMAIL"		,  sessionVO.getEmail		());
		userInfo.put("LOGIN_TYPE"	,  sessionVO.getLoginType	());
		
		return userInfo;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping("logout.do")
	public @ResponseBody Map logout(HttpSession session) {

	    session.invalidate();
	    
	    Map<String, String> result = new HashMap<>();
	    result.put("status", "S");
	    return result;
	}	
}
