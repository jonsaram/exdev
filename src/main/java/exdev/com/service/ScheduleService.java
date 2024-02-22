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
     * 내용        : 일정관리 입력
     *               일정관리(TBL_EXP_SCHEDULE)
     * @생 성 자   : 이응규
     * @생 성 일자 : 2024. 02. 21 : 최초 생성
     * @수 정 자   : 
     * @수 정 일자 :
     * @수 정 자
     */
    public Map<String,Object>  scheduleSave(Map map ) throws Exception {
        
        System.out.println("================================= scheduleSave =================================");
        
        int result = 0;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        String strDate = dateFormat.format(Calendar.getInstance().getTime());

        String scheduleId = (String)map.get("scheduleId");
        String title = (String)map.get("title");
        String date = (String)map.get("date");
        String position = (String)map.get("position");
        String description = (String)map.get("description");
        String workType = (String)map.get("workType");
        String loopType = (String)map.get("loopType");
        String startTimeH = (String)map.get("startTimeH");
        String startTimeM = (String)map.get("startTimeM");
        String endTimeH = (String)map.get("endTimeH");
        String endTimeM = (String)map.get("endTimeM");
        String secretYn = (String)map.get("secretYn");
        String userId = (String)map.get("userId");
        
        String json = (String)map.get("users");
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> apprUserList = mapper.readValue(json, new TypeReference<ArrayList<Map<String, Object>>>(){});
        
        Map<String, String> saveMap = new HashMap<String, String>();
        saveMap.put("title", title);
        saveMap.put("position", position);
        saveMap.put("description", description);
        saveMap.put("loopType", loopType);
        saveMap.put("workType", workType);
        saveMap.put("startTimeH", startTimeH);
        saveMap.put("startTimeM", startTimeM);
        saveMap.put("endTimeH", endTimeH);
        saveMap.put("endTimeM", endTimeM);
        saveMap.put("secretYn", secretYn);
        saveMap.put("userId", userId);
        
        
        ArrayList<String> scheduleIdArry = new ArrayList<>();
        scheduleIdArry = (ArrayList<String>)map.get("scheduleIdArry");
        
        String[] scheduleIds = new String[scheduleIdArry.size()];
        
        for (int i=0; i<scheduleIdArry.size(); i++) {
            scheduleIds[i] = scheduleIdArry.get(i);
        }
        
        //공통코드 조회 ============
        Map<String, String> requestParm = new HashMap<String, String>();
        requestParm.put("GRP_CODE_ID", "SCHEDULE_LOOP_TYPE");
        requestParm.put("CODE_ID", loopType);
        
        Map<String, String> resultMap = (Map<String, String>)commonDao.getObject("system.getCodeList", requestParm);
        int addCnt = Integer.parseInt(resultMap.get("ATTR1"));
    
        
        String NOT_REPEAT = ExdevBaseService.SCHEDULE_LOOP_TYPE.NOT_REPEAT.name();
        String DAY = ExdevBaseService.SCHEDULE_LOOP_TYPE.DAY.name();
        String WEEK = ExdevBaseService.SCHEDULE_LOOP_TYPE.WEEK.name();
        
        System.out.println("addCnt["+addCnt+"]  NOT_REPEAT["+NOT_REPEAT+"] DAY["+DAY+"] WEEK["+WEEK+"] =");
        
        if( ExdevBaseService.SCHEDULE_LOOP_TYPE.NOT_REPEAT.name().equals(loopType) ) {
            // 당일 입력
            
            saveMap.put("scheduleId", scheduleIds[0]);
            saveMap.put("date", date);
            result += commonDao.insert("schedule.insertSchedule", saveMap);
            result += scheduleShareSave(saveMap, apprUserList );
        }else if( ExdevBaseService.SCHEDULE_LOOP_TYPE.DAY.name().equals(loopType) ){
            for(int i=0; i < addCnt; i++) {
                String addWeek  = DateUtil.AddDate(date, 0, 0, i);
                saveMap.put("scheduleId", scheduleIds[i]);
                saveMap.put("date", addWeek);
                result += commonDao.insert("schedule.insertSchedule", saveMap);
                result += scheduleShareSave(saveMap, apprUserList );
            }
        }else if( ExdevBaseService.SCHEDULE_LOOP_TYPE.WEEK.name().equals(loopType) ){
            int initNum = 7;
            for(int i=0; i < addCnt; i++) {
                String addWeek  = DateUtil.AddDate(date, 0, 0, initNum*i);
                saveMap.put("scheduleId", scheduleIds[i]);
                saveMap.put("date", addWeek);
                result += commonDao.insert("schedule.insertSchedule", saveMap);
                result += scheduleShareSave(saveMap, apprUserList );
            }
        }else if( ExdevBaseService.SCHEDULE_LOOP_TYPE.MONTH.name().equals(loopType) ){
            for(int i=0; i < addCnt; i++) {
                String addWeek  = DateUtil.AddDate(date, 0, i, 0);
                saveMap.put("scheduleId", scheduleIds[i]);
                saveMap.put("date", addWeek);
                result += commonDao.insert("schedule.insertSchedule", saveMap);
                result += scheduleShareSave(saveMap, apprUserList );
            }
        }else if( ExdevBaseService.SCHEDULE_LOOP_TYPE.QUARTER.name().equals(loopType) ){
            int initNum = 3;
            for(int i=0; i < addCnt; i++) {
                String addWeek  = DateUtil.AddDate(date, 0, initNum*i, 0);
                saveMap.put("scheduleId", scheduleIds[i]);
                saveMap.put("date", addWeek);
                result += commonDao.insert("schedule.insertSchedule", saveMap);
                result += scheduleShareSave(saveMap, apprUserList );
            }
            
        }else if( ExdevBaseService.SCHEDULE_LOOP_TYPE.YEAR.name().equals(loopType) ){
            for(int i=0; i < addCnt; i++) {
                String addWeek  = DateUtil.AddDate(date, i, 0, 0);
                saveMap.put("scheduleId", scheduleIds[i]);
                saveMap.put("date", addWeek);
                result += commonDao.insert("schedule.insertSchedule", saveMap);
                result += scheduleShareSave(saveMap, apprUserList );
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
    public int scheduleShareSave(Map map, List<Map<String, Object>> userList ) throws Exception {
        
        System.out.println("================================= scheduleShareSave =================================");
        int result = 0;
        
        for(Map<String, Object> userMap : userList){
            
            Map<String, String> saveMap = new HashMap<String, String>();
            
            saveMap.put("scheduleId", (String)map.get("scheduleId"));
            saveMap.put("userId", (String)userMap.get("user_id"));
            
            result += commonDao.insert("schedule.insertScheduleShare", saveMap);
            
        }
        
        return result;
    }
	
	
}
