package exdev.com.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import exdev.com.common.ExdevConstants;
import exdev.com.common.dao.ExdevCommonDao;
import exdev.com.common.service.ExdevBaseService;
import exdev.com.util.DateUtil;

/**
 * This MovieServiceImpl class is an Implementation class to provide movie crud
 * functionality.
 * 
 * @author 이응규
 */
@Service("ScheduleService")
public class ScheduleService extends ExdevBaseService{
	
	@Autowired
	private ExdevCommonDao commonDao;
    
	
	

    /** 
     * 내용        : 일정관리 수정
     *               일정관리(TBL_EXP_SCHEDULE)
     * @생 성 자   : 이응규
     * @생 성 일자 : 2024. 02. 27 : 최초 생성
     * @수 정 자   : 
     * @수 정 일자 :
     * @수 정 자
     */
    public Map<String,Object>  updateSchedule(Map map ) throws Exception {
        
        System.out.println("================================= updateSchedule =================================");
        
        int result = 0;
        
        String json = (String)map.get("users");
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> apprUserList = mapper.readValue(json, new TypeReference<ArrayList<Map<String, Object>>>(){});
        
        result += commonDao.insert("schedule.updateSchedule", map);
        
        result += deleteScheduleShare(map);
        result += saveScheduleShare(map, apprUserList );
        
        
        if( result > 0  ) {
            resultInfo = makeResult(ExdevBaseService.REQUEST_SUCCESS, "", null);
        }else {
            resultInfo = makeResult(ExdevBaseService.REQUEST_FAIL, "", null);
        }
               
        
        
        return resultInfo;
    }

    /** 
     * 내용        : 일정공유 삭제
     *               일정공유(TBL_EXP_SCHEDULE_SHARE)
     * @생 성 자   : 이응규
     * @생 성 일자 : 2024. 02. 27 : 최초 생성
     * @수 정 자   : 
     * @수 정 일자 :
     * @수 정 자
     */
    public int deleteScheduleShare(Map map) throws Exception {
        
        System.out.println("================================= deleteScheduleShare =================================");
        int result = 0;
        Map<String, String> deleteMap = new HashMap<String, String>();
        deleteMap.put("scheduleId", (String)map.get("scheduleId"));
        result += commonDao.insert("schedule.deleteScheduleShare", deleteMap);
        
        return result;
    }
    
    /** 
     * 내용        : 일정공유 삭제
     *               일정공유(TBL_EXP_SCHEDULE_SHARE)
     * @생 성 자   : 이응규
     * @생 성 일자 : 2024. 02. 27 : 최초 생성
     * @수 정 자   : 
     * @수 정 일자 :
     * @수 정 자
     */
    public int deleteScheduleShare(Map map, List<Map<String, Object>> userList ) throws Exception {
        
        System.out.println("================================= deleteScheduleShare =================================");
        int result = 0;
        
        for(Map<String, Object> userMap : userList){
            
            Map<String, String> saveMap = new HashMap<String, String>();
            
            saveMap.put("scheduleId", (String)map.get("scheduleId"));
            saveMap.put("userId", (String)userMap.get("user_id"));
            
            result += commonDao.insert("schedule.deleteScheduleShare", saveMap);
            
        }
        
        return result;
    }
    
    /** 
     * 내용        : 일정관리삭제
     *               일정관리(TBL_EXP_SCHEDULE)
     * @생 성 자   : 이응규
     * @생 성 일자 : 2024. 02. 26 : 최초 생성
     * @수 정 자   : 
     * @수 정 일자 :
     * @수 정 자
     */
    public Map<String,Object>  deleteSchedule(Map map ) throws Exception {
        
        System.out.println("================================= deleteSchedule =================================");
        
        int result = 0;

        String scheduleId = (String)map.get("scheduleId");
        System.out.println("scheduleId["+scheduleId+"]  ");
        
        result += commonDao.delete("schedule.deleteSchedule", map);
        result += commonDao.delete("schedule.deleteScheduleShare", map);
        
        
        if( result > 0  ) {
            resultInfo = makeResult(ExdevBaseService.REQUEST_SUCCESS, "", null);
        }else {
            resultInfo = makeResult(ExdevBaseService.REQUEST_FAIL, "", null);
        }
               
        
        
        return resultInfo;
    }
    /** 
     * 내용        : 일정관리 입력
     *               일정관리(TBL_EXP_SCHEDULE)
     * @생 성 자   : 이응규
     * @생 성 일자 : 2024. 02. 21 : 최초 생성
     * @수 정 자   : 
     * @수 정 일자 :
     * @수 정 자
     */
    public Map<String,Object>  saveSchedule(Map map ) throws Exception {
        
        System.out.println("================================= saveSchedule =================================");
        
        int result = 0;
        
        String date = (String)map.get("date");
        String loopType = (String)map.get("loopType");
        String limitDate = (String)map.get("limitDate");
        System.out.println("=== limitDate ===>"+limitDate);
        String json = (String)map.get("users");
        
        
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> apprUserList = mapper.readValue(json, new TypeReference<ArrayList<Map<String, Object>>>(){});

        ArrayList<String> scheduleIdArry = new ArrayList<>();
        scheduleIdArry = (ArrayList<String>)map.get("scheduleIdArry");
        
        String[] scheduleIds = new String[scheduleIdArry.size()];
        
        for (int i=0; i<scheduleIdArry.size(); i++) {
            scheduleIds[i] = scheduleIdArry.get(i);
        }
        
        //공통코드 조회 ============
        Map<String, String> requestParm = new HashMap<String, String>();
        requestParm.put("GRP_CODE_ID", "SCHEDULE_LOOP_TYPE");
        requestParm.put("CODE_ID", (String)map.get("loopType"));
        
        Map<String, String> resultMap = (Map<String, String>)commonDao.getObject("system.getCodeList", requestParm);
        int addCnt = Integer.parseInt(resultMap.get("ATTR1"));
    
        
        String NOT_REPEAT = ExdevBaseService.SCHEDULE_LOOP_TYPE.NOT_REPEAT.name();
        String DAY = ExdevBaseService.SCHEDULE_LOOP_TYPE.DAY.name();
        String WEEK = ExdevBaseService.SCHEDULE_LOOP_TYPE.WEEK.name();
        
        System.out.println("addCnt["+addCnt+"]  NOT_REPEAT["+NOT_REPEAT+"] DAY["+DAY+"] WEEK["+WEEK+"] =");
        
        result += commonDao.insert("schedule.insertScheduleMsater", map);
        
        if( ExdevBaseService.SCHEDULE_LOOP_TYPE.NOT_REPEAT.name().equals(loopType) ) {
            // 당일 입력
            
            map.put("scheduleId", scheduleIds[0]);
            map.put("date", date);
            
            result += commonDao.insert("schedule.insertSchedule", map);
            result += saveScheduleShare(map, apprUserList );
        }else if( ExdevBaseService.SCHEDULE_LOOP_TYPE.DAY.name().equals(loopType) ){
            for(int i=0; i < addCnt; i++) {
                String addWeek  = DateUtil.AddDate(date, 0, 0, i);
                map.put("scheduleId", scheduleIds[i]);
                map.put("date", addWeek);
                result += commonDao.insert("schedule.insertSchedule", map);
                result += saveScheduleShare(map, apprUserList );
            }
        }else if( ExdevBaseService.SCHEDULE_LOOP_TYPE.WEEK.name().equals(loopType) ){
            int initNum = 7;
            for(int i=0; i < addCnt; i++) {
                String addWeek  = DateUtil.AddDate(date, 0, 0, initNum*i);
                map.put("scheduleId", scheduleIds[i]);
                map.put("date", addWeek);
                result += commonDao.insert("schedule.insertSchedule", map);
                result += saveScheduleShare(map, apprUserList );
            }
        }else if( ExdevBaseService.SCHEDULE_LOOP_TYPE.MONTH.name().equals(loopType) ){
            for(int i=0; i < addCnt; i++) {
                String addWeek  = DateUtil.AddDate(date, 0, i, 0);
                map.put("scheduleId", scheduleIds[i]);
                map.put("date", addWeek);
                result += commonDao.insert("schedule.insertSchedule", map);
                result += saveScheduleShare(map, apprUserList );
            }
        }else if( ExdevBaseService.SCHEDULE_LOOP_TYPE.QUARTER.name().equals(loopType) ){
            int initNum = 3;
            for(int i=0; i < addCnt; i++) {
                String addWeek  = DateUtil.AddDate(date, 0, initNum*i, 0);
                map.put("scheduleId", scheduleIds[i]);
                map.put("date", addWeek);
                result += commonDao.insert("schedule.insertSchedule", map);
                result += saveScheduleShare(map, apprUserList );
            }
            
        }else if( ExdevBaseService.SCHEDULE_LOOP_TYPE.YEAR.name().equals(loopType) ){
            for(int i=0; i < addCnt; i++) {
                String addWeek  = DateUtil.AddDate(date, i, 0, 0);
                map.put("scheduleId", scheduleIds[i]);
                map.put("date", addWeek);
                result += commonDao.insert("schedule.insertSchedule", map);
                result += saveScheduleShare(map, apprUserList );
            }
        }else {}
        
        
        if( result > 0  ) {
            resultInfo = makeResult(ExdevBaseService.REQUEST_SUCCESS, "", null);
        }else {
            resultInfo = makeResult(ExdevBaseService.REQUEST_FAIL, "", null);
        }
               
        
        
        return resultInfo;
    }

    /** 
     * 내용        : 일정공유 입력
     *               일정공유(TBL_EXP_SCHEDULE_SHARE)
     * @생 성 자   : 이응규
     * @생 성 일자 : 2024. 02. 21 : 최초 생성
     * @수 정 자   : 
     * @수 정 일자 :
     * @수 정 자
     */
    public int saveScheduleShare(Map map, List<Map<String, Object>> userList ) throws Exception {
        
        System.out.println("================================= scheduleShareSave =================================");
        int result = 0;
        
        for(Map<String, Object> userMap : userList){
            
            Map<String, String> saveMap = new HashMap<String, String>();
            
            saveMap.put("scheduleId", (String)map.get("scheduleId"));
            saveMap.put("userId", (String)userMap.get("USER_ID"));
            saveMap.put("spCstmId", (String)userMap.get("SP_CSTM_ID"));
            result += commonDao.insert("schedule.insertScheduleShare", saveMap);
            
        }
        
        return result;
    }
	
	
}
