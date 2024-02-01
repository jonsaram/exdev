package exdev.com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exdev.com.common.ExdevConstants;
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
    

    /** 
     * 내용        : 결재상신 입력
     *               결재테이블(TBL_EXP_APPROVAL)
     *               결재자 테이블(TBL_EXP_APPROVAL_USER)
     * @생 성 자   : 이응규
     * @생 성 일자 : 2024. 01. 16 : 최초 생성
     * @수 정 자   : 
     * @수 정 일자 :
     * @수 정 자
     */
    public Map<String, String> approvalComplete(Map map ) throws Exception {
        
        Map<String, String> returnMap = new HashMap<String, String>();
        
        String uuid = (String)map.get("uuid");
        
        System.out.println("uuid =>"+uuid);
        
        returnMap.put("msg", ExdevConstants.REQUEST_SUCCESS);       
        
        
        return returnMap;
    }


    /** 
     * 내용        : 결재결완료
     * @생 성 자   : 이응규
     * @생 성 일자 : 2024. 02. 01 : 최초 생성
     * @수 정 자   : 
     * @수 정 일자 :
     * @수 정 자
     */
    public Map<String, String> insertApproval(Map<String, String> apprMap, List<Map<String, Object>> apprUserList ) throws Exception {
        
        Map<String, String> returnMap = new HashMap<String, String>();
        
        int result1 = 0;
        int result = commonDao.insert("approval.insertApproval", apprMap);
        System.out.println("result =>"+result);
        if( result == 1 ) {
          
            
            for(Map<String, Object> apprUserMap : apprUserList){
                
                String apprUserUuid = (String)apprUserMap.get("appr_user_uuid");
                String userId       = (String)apprUserMap.get("user_id");
                String apprType     = (String)apprUserMap.get("apprType");
                
                Map<String, String> apprUseInsertMap = new HashMap<String, String>();
                
                apprUseInsertMap.put("uuid", apprUserUuid);
                apprUseInsertMap.put("approvalUuid", apprMap.get("uuid"));
                apprUseInsertMap.put("approvalUserId", userId);
                apprUseInsertMap.put("apprType", apprType);
                apprUseInsertMap.put("state", "REQUEST");
                apprUseInsertMap.put("approvalComment", "");
                apprUseInsertMap.put("createUser", apprMap.get("createUser"));
                apprUseInsertMap.put("createDate", apprMap.get("createDate"));
                
                System.out.println("apprUserUuid ==>"+apprUserUuid+"   userId ==>"+userId);
                result1 += commonDao.insert("approval.insertApprovalUser", apprUseInsertMap);
                System.out.println("result1 =>"+result1);
            }
            
            if( result1 == apprUserList.size() ) {
                returnMap.put("msg", ExdevConstants.REQUEST_SUCCESS);   
            }else {
                returnMap.put("msg", ExdevConstants.REQUEST_FAIL);
            }   
            
            
        }else {
            returnMap.put("msg", ExdevConstants.REQUEST_FAIL);       
        }
        
        return returnMap;
    }
	
	
	
}
