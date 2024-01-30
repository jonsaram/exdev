package exdev.com.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exdev.com.common.dao.ExdevCommonDao;
import exdev.com.common.service.ExdevBaseService;

/**
 * This MovieServiceImpl class is an Implementation class to provide movie crud
 * functionality.
 * 
 * @author 이응규
 */
@Service("ApprovalService")
public class ApprovalService extends ExdevBaseService{
	
	@Autowired
	private ExdevCommonDao commonDao;
    
	public static final String REQUEST_SUCCESS  = "S";
    public static final String REQUEST_FAIL     = "F";
    
    public Map<String, String> insertApproval(Map<String, String> apprMap, Map<String, String[]> apprUserMap ) throws Exception {
        
        Map<String, String> returnMap = new HashMap<String, String>();
        

        System.out.println("uuid  =>"+apprMap.get("uuid"));
        System.out.println("requestUser  =>"+apprMap.get("requestUser"));
        System.out.println("title  =>"+apprMap.get("title"));
        System.out.println("contents  =>"+apprMap.get("contents"));
        System.out.println("state  =>"+apprMap.get("state"));
        System.out.println("approvalDate  =>"+apprMap.get("approvalDate"));
        System.out.println("createUser  =>"+apprMap.get("createUser"));
        System.out.println("createDate  =>"+apprMap.get("createDate"));

        int result1 = 0;
        int result = commonDao.insert("approval.insertApproval", apprMap);
        System.out.println("result =>"+result);
        if( result == 1 ) {
          
            String[] apprUserUuids = apprUserMap.get("apprUserUuids");
            String[] userIds       = apprUserMap.get("userIds");
            
            for (int i = 0; i < apprUserUuids.length; i++) {
                String apprUserUuid = apprUserUuids[i];
                String userId       = userIds[i];
                
                Map<String, String> apprUseInsertMap = new HashMap<String, String>();
                
                apprUseInsertMap.put("uuid", apprUserUuid);
                apprUseInsertMap.put("approvalUuid", apprMap.get("uuid"));
                apprUseInsertMap.put("approvalUserId", userId);
                apprUseInsertMap.put("state", "REQUESR");
                apprUseInsertMap.put("approvalComment", "");
                apprUseInsertMap.put("createUser", apprMap.get("createUser"));
                apprUseInsertMap.put("createDate", apprMap.get("createDate"));
                
                System.out.println("apprUserUuid ==>"+apprUserUuid+"   userId ==>"+userId);
                result1 += commonDao.insert("approval.insertApprovalUser", apprUseInsertMap);
                System.out.println("result1 =>"+result1);
            }
            if( result1 == apprUserUuids.length) {
                returnMap.put("msg", REQUEST_SUCCESS);   
            }else {
                returnMap.put("msg", REQUEST_FAIL);
            }   
            
            
        }else {
            returnMap.put("msg", REQUEST_FAIL);       
        }
        
        return returnMap;
    }
    
	
	
	
}
