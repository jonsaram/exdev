package exdev.com.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import exdev.com.common.dao.ExdevCommonDao;
import exdev.com.common.service.ExdevBaseService;

/**
 * This MovieServiceImpl class is an Implementation class to provide movie crud
 * functionality.
 * 
 * @author 위성열
 */
@Service("ExdevContractService")
public class ExdevContractService extends ExdevBaseService{
	
	@Autowired
	private ExdevCommonDao commonDao;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public Object changeMember(Map map) throws Exception {
		List<Map> 		teamMemberList 	= (List<Map>)map.get("teamMemberList");
		String	 		contractId 		= (String)map.get("CONTRACT_ID");
		String	 		userId 			= (String)map.get("USER_ID");
		Object			sessionVo		= (Object)map.get("sessionVo");
		
		map.put("CONTRACT_ID"	,  contractId);
		map.put("USER_ID"		,  userId);
		map.put("ALLOWED"		,  "Y");
		
		commonDao.update("contract.updateProjectUserId"	, map);
		commonDao.update("contract.insertTeam"			, map);
		commonDao.update("contract.deleteTeamMember"	, map);
		commonDao.update("contract.deleteContractMember", map);

		return map;
	}
	public Object changeMember2(Map map) throws Exception {
		List<Map> 		teamMemberList 	= (List<Map>)map.get("teamMemberList");
		String	 		contractId 		= (String)map.get("CONTRACT_ID");
		String	 		userId 			= (String)map.get("USER_ID");
		Object			sessionVo		= (Object)map.get("sessionVo");
		
		map.put("CONTRACT_ID"	,  contractId);
		map.put("USER_ID"		,  userId);
		map.put("ALLOWED"		,  "Y");
		
		// Data Update에만 사용한다.
		if(teamMemberList != null) {
			for (Map teamMember : teamMemberList) {
				// Session이 필요하다면 여기서 넣어준다.
				teamMember.put("sessionVo", sessionVo);
				teamMember.put("CONTRACT_ID", contractId);
				commonDao.update("contract.insertTeamMember", teamMember);
			}
		}

		commonDao.update("contract.updateContractMemberFromTeam", map);
		
		return map;
	}
}
