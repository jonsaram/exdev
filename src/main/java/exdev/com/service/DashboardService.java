package exdev.com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exdev.com.common.dao.ExdevCommonDao;
import exdev.com.common.service.ExdevBaseService;
import exdev.com.util.DateUtil;

/**
 * This MovieServiceImpl class is an Implementation class to provide movie crud
 * functionality.
 * 
 * @author 이응규
 */
@Service("DashboardService")
public class DashboardService extends ExdevBaseService{
	
	@Autowired
	private ExdevCommonDao commonDao;
    
	

    /** 
     * 내용        : 가맹점 리스트및 현황
     *               compPerformanceTab4.html
     * @생 성 자   : 이응규
     * @생 성 일자 : 2024. 02. 27 : 최초 생성
     * @수 정 자   : 
     * @수 정 일자 :
     * @수 정 자
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public Object getOpenCloseCnt(Map map) throws Exception {

	    /*=====================================================*/
	    String startDate = (String)map.get("startDate");
        String endDate = (String)map.get("endDate");
        String region = (String)map.get("region");
        String supervisor = (String)map.get("supervisor");
        String buyerId = (String)map.get("buyerId");
        String spCstmId = (String)map.get("spCstmId");
        
	    int startDateNum = Integer.parseInt(startDate.replace("-", ""));
        int endDateNum = Integer.parseInt(endDate.replace("-", ""));
        
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        
        for( int i =0; startDateNum < endDateNum; i++ ) {
           
            String addMonth  = DateUtil.AddDate(startDate, 0, i, 0);
            startDateNum = Integer.parseInt(addMonth.replace("-", ""));
            
            Map<String, String> searchMap = new HashMap<String, String>();
            searchMap.put("spCstmId", spCstmId);
            searchMap.put("buyerId", buyerId);
            searchMap.put("seartDate", addMonth.substring(0, 7));
            searchMap.put("region", region);
            searchMap.put("supervisor", supervisor);
        
            Map<String, Object> addMap = new HashMap<String, Object>();
            addMap.put("YYYYMM", addMonth.substring(0, 7));
            
            List<Map> resultList = commonDao.getList("dashboardCorPerformance.getOpenCloseCnt", searchMap);
            for(Map resultMap : resultList) {
                String type = (String)resultMap.get("TYPE");
                String cnt  = String.valueOf(resultMap.get("CNT"));
                
                if( "OPEN".equals( type )) {
                    addMap.put("OPEN", cnt);
                }else if( "CLOSE".equals( type )) {
                    addMap.put("CLOSE", cnt);
                }else if( "ALL".equals( type )) { 
                    addMap.put("ALL", cnt);
                }
            }
            
            list.add(addMap);
        }
        map.put("list", list);
        return map;
    }

    /** 
     * 내용        : 매장별 메뉴 매출
     * @생 성 자   : 이응규
     * @생 성 일자 : 2024. 05. 22 : 최초 생성
     * @수 정 자   : 
     * @수 정 일자 :
     * @수 정 자
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object getMenuMonthSales(Map map) throws Exception {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<Map> resultList = commonDao.getList("dashboardCorPerformance.getmenuSalesMonthTop5", map);
        
        for(Map resultMap : resultList) {
            
            
        }
        map.put("list", list);
        return map;
    }
	
}
/*
 
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object getUserList1(Map map) throws Exception {
        
        ArrayList<String> userIdList = new ArrayList<>();
        userIdList = (ArrayList<String>)map.get("userIdList");
        
        String[] userIds = new String[userIdList.size()];
        
        for (int i=0; i<userIdList.size(); i++) {
            userIds[i] = userIdList.get(i);
        }
        
        Map<String, Object> searchMap = new HashMap<String, Object>();
        
        
        searchMap.put("userIdList", userIds);
        
        List<Map> list = commonDao.getList("schedule.getUserList1", searchMap);
       
        map.put("list", list);
        System.out.println("ScheduleService.getUserList1 11");
        return map;
    }
 
 */
