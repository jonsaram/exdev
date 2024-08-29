package exdev.com.service;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import exdev.com.ExdevCommonAPI;
import exdev.com.common.dao.ExdevCommonDao;
import exdev.com.common.service.ExdevBaseService;
import exdev.com.common.service.ExdevCommonService;

/**
 * This MovieServiceImpl class is an Implementation class to provide movie crud
 * functionality.
 * 
 * @author 위성열
 */
@Service("ApprovalAfterService")
public class ApprovalAfterService extends ExdevBaseService{
	
	
	@Autowired
	private ExdevCommonService	exdevCommonService;
	
	@Autowired
	private EmailService 		emailService;
	
	@Autowired
    private ApprovalService 	approvalService;

    @Autowired
    private ScheduleService 	scheduleService;
	
	@Autowired
	private ExdevCommonDao		commonDao;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map approvalAfterProcess(Map map) throws Exception {
		
    	Map appInfo = (Map)commonDao.getObject("approval.getApproval", map);
    	
    	String AFTER_SERVICE 	= (String)appInfo.get("AFTER_SERVICE");
    	String AFTER_PARM 		= (String)appInfo.get("AFTER_PARM");
    	
    	if(!ExdevCommonAPI.isValid(AFTER_SERVICE)) {
    		return map;
    	}
    	
    	if(!ExdevCommonAPI.isValid(AFTER_PARM)) {
    		AFTER_PARM = "{}";
    	}
    	
    	 // ObjectMapper 객체 생성
        ObjectMapper objectMapper = new ObjectMapper();

        // JSON 문자열을 Map으로 변환
        Map<String, Object> afterParmMap = objectMapper.readValue(AFTER_PARM, Map.class);
        
        appInfo.putAll(afterParmMap);
        map.putAll(appInfo);
        
		String [] token = AFTER_SERVICE.split("\\.");
		
		String classId 		= token[0];
		String methodId 	= token[1];
		
		Method targetMethod = null;
		Object targetService = null;
        
		if("ApprovalAfterService".equals(classId)) {
			targetService = this;
			targetMethod = ApprovalAfterService.class.getMethod(methodId, Map.class);
		} else if("ExdevCommonService".equals(classId)) {
			targetService = exdevCommonService;
			targetMethod = ExdevCommonService.class.getMethod(methodId, Map.class);
		} else if("EmailService".equals(classId)) {
			targetService = emailService;
			targetMethod = EmailService.class.getMethod(methodId, Map.class);
		} else if("ApprovalService".equals(classId)) {
            targetService = approvalService;
            targetMethod = ApprovalService.class.getMethod(methodId, Map.class);
        } else if("ScheduleService".equals(classId)) {
            targetService = scheduleService;
            targetMethod = ScheduleService.class.getMethod(methodId, Map.class);
        }
			
		Map resultObject = (Map)targetMethod.invoke(targetService, map);
		
		return resultObject;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map updateProjectProcess(Map map) throws Exception {
		
		map.put("CONTRACT_ID", map.get("RELATION_ID"));
		
		String state = (String)map.get("STATE");
		
		if("COMPLETE".equals(state)) {
	        int result = commonDao.update("contract.updateProject", map);
			
	        int result2 = commonDao.update("contract.updateTeamState", map);
			
	        int result3 = commonDao.update("contract.updateContractMemberFromTeam", map);
	        
		} else if("REJECT".equals(state)) {
			
			map.put("PROCESS_STATE", "PRE_CONTRACT");
			
	        int result = commonDao.update("contract.updateProject", map);
	        
	        int result2 = commonDao.update("contract.deleteTeam", map);
	        
	        int result3 = commonDao.update("contract.deleteTeamMember", map);
	        
		}
        return map;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map updateContractProcess(Map map) throws Exception {
		
		map.put("CONTRACT_ID", map.get("RELATION_ID"));
		
		String state = (String)map.get("STATE");
		String processState = (String)map.get("PROCESS_STATE");
		
		if("COMPLETE".equals(state)) {
			
			int result = commonDao.update("contract.updateContractProcessState", map);
	        
		} else if("REJECT".equals(state)) {
			
			if( processState.equals("CONTRACTED")) {
				
				map.put("PROCESS_STATE", "CONTRACTING");
				map.put("PRE_PROCESS_STATE", "ON_CONTRACTED_APPR");
				
			}else if( processState.equals("COMPLETED")) {
				
				map.put("PROCESS_STATE", "CONTRACTED");
				map.put("PRE_PROCESS_STATE", "ON_COMPLETED_APPR");
				
			}
			
			int result = commonDao.update("contract.updateContractProcessState", map);
	        
		}		
		return map;
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map updateBuyerConsultingProcess(Map map) throws Exception {
		
		map.put("BUYER_CONSULTING_ID", map.get("RELATION_ID"));
		
        int result = commonDao.update("custDashboard.updateBuyerConsultingAppState", map);
		
		return map;
	}
	
	
}
