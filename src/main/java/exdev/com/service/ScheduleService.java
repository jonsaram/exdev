package exdev.com.service;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
     * 내용        : 일정관리 수정(addSchedulePopup.html)
     *               일정관리(TBL_EXP_SCHEDULE)
     * @생 성 자   : 이응규
     * @생 성 일자 : 2024. 02. 27 : 최초 생성
     * @수 정 자   : 
     * @수 정 일자 :
     * @수 정 자
     */
    public Map<String,Object>  updateSchedule(Map map ) throws Exception {
        
        int result = 0;
        
        String json = (String)map.get("users");
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> apprUserList = mapper.readValue(json, new TypeReference<ArrayList<Map<String, Object>>>(){});
        
        result += commonDao.update("schedule.updateSchedule", map);
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
        
        int result = 0;
        result += commonDao.delete("schedule.deleteScheduleShare", map);
        
        return result;
    }
    
    /** 
     * 내용        : 일정관리삭제(addSchedulePopup.html)
     *               일정관리(TBL_EXP_SCHEDULE)
     * @생 성 자   : 이응규
     * @생 성 일자 : 2024. 02. 26 : 최초 생성
     * @수 정 자   : 
     * @수 정 일자 :
     * @수 정 자
     */
    public Map<String,Object>  deleteSchedule(Map map ) throws Exception {
        
        int result = 0;
        
        
        String scheduleGrpId = (String)map.get("scheduleGrpId");
        String scheduleId    = (String)map.get("scheduleId");
        String allApplyYn    = (String)map.get("allApplyYn");
        
        if( "Y".equals(allApplyYn)) {
            //result += commonDao.delete("schedule.deleteScheduleMsater", map);
            
            Map<String, String> scheduleMap = new HashMap<String, String>();
            scheduleMap.put("scheduleGrpId", (String)map.get("scheduleGrpId"));
            
            // TBL_EXP_SCHEDULE_MASTER 에서 scheduleId 리스트를 조회한다.
            List<Map> list = commonDao.getList("schedule.getScheduleList", scheduleMap);
            for(Map getMap : list) {
                
                Map<String, String> delScheduleMap = new HashMap<String, String>();
                delScheduleMap.put("scheduleId", (String)getMap.get("SCHEDULE_ID"));
                result += commonDao.delete("schedule.deleteSchedule", delScheduleMap);
                result += commonDao.delete("schedule.deleteScheduleShare", delScheduleMap);
            }
        }else {
            
            Map<String, String> scheduleMap = new HashMap<String, String>();
            scheduleMap.put("scheduleId", (String)map.get("scheduleId"));
            result += commonDao.delete("schedule.deleteSchedule", scheduleMap);
            result += commonDao.delete("schedule.deleteScheduleShare", scheduleMap);
        }
        
        
        if( result > 0  ) {
            resultInfo = makeResult(ExdevBaseService.REQUEST_SUCCESS, "", null);
        }else {
            resultInfo = makeResult(ExdevBaseService.REQUEST_FAIL, "", null);
        }
               
        
        
        return resultInfo;
    }

    /** 
     * 내용        : 일정관리 입력(addSchedulePopup.html)
     *               일정관리(TBL_EXP_SCHEDULE)
     * @생 성 자   : 이응규
     * @생 성 일자 : 2024. 02. 21 : 최초 생성
     * @수 정 자   : 
     * @수 정 일자 :
     * @수 정 자
     */
    public Map<String,Object>  saveSchedule(Map map ) throws Exception {
        
        int result = 0;
        
        ArrayList<String> scheduleIdArry = new ArrayList<>();
        scheduleIdArry = (ArrayList<String>)map.get("scheduleIdArry");
        
        String[] scheduleIds = new String[scheduleIdArry.size()];
        
        for (int i=0; i<scheduleIdArry.size(); i++) {
            scheduleIds[i] = scheduleIdArry.get(i);
        }
        
        //result += commonDao.insert("schedule.insertScheduleMsater", map);
        Map<String,Object> resultMap = saveScheduleAndShare( map );
        
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
    public Map<String,Object>  saveScheduleAndShare(Map map ) throws Exception {
        
        int result = 0;
        
        String json              = (String)map.get("users");
        
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> apprUserList = mapper.readValue(json, new TypeReference<ArrayList<Map<String, Object>>>(){});
        
        result += commonDao.insert("schedule.insertSchedule", map);
        result += saveScheduleShare(map, apprUserList );
        
        
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
    public Map<String,Object>  saveScheduleAndShare_bak(Map map ) throws Exception {
        
        int result = 0;
        
        String date = (String)map.get("date");
        String loopType = (String)map.get("loopType");
        String limitDate = (String)map.get("limitDate");
        String scheduleStartDate = (String)map.get("scheduleStartDate");
        String scheduleEndDate = (String)map.get("scheduleEndDate");
        String json = (String)map.get("users");
        
        
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> apprUserList = mapper.readValue(json, new TypeReference<ArrayList<Map<String, Object>>>(){});

        ArrayList<String> scheduleIdArry = new ArrayList<>();
        scheduleIdArry = (ArrayList<String>)map.get("scheduleIdArry");
        
        String[] scheduleIds = new String[scheduleIdArry.size()];
        
        for (int i=0; i<scheduleIdArry.size(); i++) {
            scheduleIds[i] = scheduleIdArry.get(i);
        }
        
        if( ExdevBaseService.SCHEDULE_LOOP_TYPE.NOT_REPEAT.name().equals(loopType) ) {
            // 당일 입력
            
            map.put("scheduleId", scheduleIds[0]);
            map.put("date", date);
            
            result += commonDao.insert("schedule.insertSchedule", map);
            result += saveScheduleShare(map, apprUserList );
        }else if( ExdevBaseService.SCHEDULE_LOOP_TYPE.DAY.name().equals(loopType) ){

            int startDateNum = Integer.parseInt(scheduleStartDate.replace("-", ""));
            int endDateNum = Integer.parseInt(scheduleEndDate.replace("-", ""));
            
            for( int i =0; startDateNum < endDateNum; i++ ) {
                String addWeek  = DateUtil.AddDate(date, 0, 0, i);
                startDateNum = Integer.parseInt(addWeek.replace("-", ""));
                map.put("scheduleId", scheduleIds[i]);
                map.put("date", addWeek);
                result += commonDao.insert("schedule.insertSchedule", map);
                result += saveScheduleShare(map, apprUserList );
                
            }
        }else if( ExdevBaseService.SCHEDULE_LOOP_TYPE.WEEK.name().equals(loopType) ){
            int initNum = 7;
            int startDateNum = Integer.parseInt(scheduleStartDate.replace("-", ""));
            int endDateNum = Integer.parseInt(scheduleEndDate.replace("-", ""));

            for( int i =0; startDateNum < endDateNum; i++ ) {
                String addWeek  = DateUtil.AddDate(date, 0, 0, initNum*i);
                startDateNum = Integer.parseInt(addWeek.replace("-", ""));
                map.put("scheduleId", scheduleIds[i]);
                map.put("date", addWeek);
                result += commonDao.insert("schedule.insertSchedule", map);
                result += saveScheduleShare(map, apprUserList );
                
            }
        }else if( ExdevBaseService.SCHEDULE_LOOP_TYPE.MONTH.name().equals(loopType) ){
            int startDateNum = Integer.parseInt(scheduleStartDate.replace("-", ""));
            int endDateNum = Integer.parseInt(scheduleEndDate.replace("-", ""));

            for( int i =0; startDateNum < endDateNum; i++ ) {
                String addWeek  = DateUtil.AddDate(date, 0, i, 0);
                startDateNum = Integer.parseInt(addWeek.replace("-", ""));
                map.put("scheduleId", scheduleIds[i]);
                map.put("date", addWeek);
                result += commonDao.insert("schedule.insertSchedule", map);
                result += saveScheduleShare(map, apprUserList );
                
            }
        }else if( ExdevBaseService.SCHEDULE_LOOP_TYPE.QUARTER.name().equals(loopType) ){
            int initNum = 3;
            
            int startDateNum = Integer.parseInt(scheduleStartDate.replace("-", ""));
            int endDateNum = Integer.parseInt(scheduleEndDate.replace("-", ""));

            for( int i =0; startDateNum < endDateNum; i++ ) {
                String addWeek  = DateUtil.AddDate(date, 0, initNum*i, 0);
                startDateNum = Integer.parseInt(addWeek.replace("-", ""));
                map.put("scheduleId", scheduleIds[i]);
                map.put("date", addWeek);
                result += commonDao.insert("schedule.insertSchedule", map);
                result += saveScheduleShare(map, apprUserList );
                
            }
            
        }else if( ExdevBaseService.SCHEDULE_LOOP_TYPE.YEAR.name().equals(loopType) ){

            int startDateNum = Integer.parseInt(scheduleStartDate.replace("-", ""));
            int endDateNum = Integer.parseInt(scheduleEndDate.replace("-", ""));

            for( int i =0; startDateNum < endDateNum; i++ ) {
                String addWeek  = DateUtil.AddDate(date, i, 0, 0);
                startDateNum = Integer.parseInt(addWeek.replace("-", ""));
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

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object getNotRepeatSchedule(Map map) throws Exception {
        
        List<Map> listMap = new ArrayList<Map>();
        List<Map> list = commonDao.getList("schedule.getNotRepeat", map);
        for(Map resultMap : list) {
            listMap.add(resultMap);
        }
        
        map.put("list", listMap);
        return map;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object getDayRepeatSchedule(Map map) throws Exception {
        
        List<Map> listMap = new ArrayList<Map>();
        
        /* 매일반복 */
        List<Map> weeklist = commonDao.getList("schedule.getDayRepeat", map);
        for(Map resultMap : weeklist) {
            listMap.add(resultMap); 
        }
        
        map.put("list", listMap);
        return map;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object getWeekRepeatSchedule(Map map) throws Exception {
        
        List<Map> listMap = new ArrayList<Map>();
        
        /* 매주반복 */
        /**/
        List<Map> weeklist = commonDao.getList("schedule.getWeekRepeat", map);
        for(Map resultMap : weeklist) {
            listMap.add(resultMap); 
        }
        
        map.put("list", listMap);
        return map;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object getMonthRepeatSchedule(Map map) throws Exception {
        
        List<Map> listMap = new ArrayList<Map>();
        
        /* 매월반복 말일선택 */
        List<Map> monthListSelectDay = commonDao.getList("schedule.getMonthRepeatSelectDay", map);
        for(Map resultMap : monthListSelectDay) {
          listMap.add(resultMap); 
        } 
        
        /* 매월반복 */  
        List<Map> monthList = commonDao.getList("schedule.getMonthRepeat", map);
        for(Map resultMap : monthList) {
          listMap.add(resultMap); 
        }
        
        map.put("list", listMap);
        return map;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object getQuarterRepeatSchedule(Map map) throws Exception {
                
        List<Map> listMap = new ArrayList<Map>();
        
        /* 분기반복 */  
        List<Map> monthList = commonDao.getList("schedule.getQuarterRepeat", map);

        for(Map resultMap : monthList) {
          listMap.add(resultMap); 
        }
        
        map.put("list", listMap);
        return map;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object getYearRepeatSchedule(Map map) throws Exception {
                
        List<Map> listMap = new ArrayList<Map>();
        
        /* 매년반복 */
        List<Map> yearList = commonDao.getList("schedule.getYearRepeat", map);
    
        for(Map resultMap : yearList) {
          listMap.add(resultMap);
        }
        
        map.put("list", listMap);
        return map;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object getTeamSchedule(Map map) throws Exception {
        
        List<Map> listMap = new ArrayList<Map>();
        
        /* 매일반복 */
        /**/
        List<Map> daylist = commonDao.getList("schedule.getTeamDayRepeat", map);
        for(Map resultMap : daylist) {
            listMap.add(resultMap); 
        }
        
        /* 반복안함 */
        /**/
        List<Map> notRepeatlist = commonDao.getList("schedule.getTeamNotRepeat", map);
        for(Map resultMap : notRepeatlist) {
            listMap.add(resultMap); 
        }
        
        /* 매주 반복 */
        /**/
        List<Map> weekRepeatlist = commonDao.getList("schedule.getTeamWeekRepeat", map);
        for(Map resultMap : weekRepeatlist) {
            listMap.add(resultMap); 
        }

        /* 매월 말일선택 반복 */
        /* */
        List<Map> monthRepeatlistSelectDay = commonDao.getList("schedule.getTeamMonthRepeatSelectDay", map);
        for(Map resultMap : monthRepeatlistSelectDay) {
            listMap.add(resultMap);  
        }
        
        /* 매월 반복 */
        /* */
        List<Map> monthRepeatlist = commonDao.getList("schedule.getTeamMonthRepeat", map);
        for(Map resultMap : monthRepeatlist) {
            listMap.add(resultMap);  
        }
        
        /* 매년 반복 */
        /**/
        List<Map> yearRepeatlist = commonDao.getList("schedule.getTeamYearRepeat", map);
        for(Map resultMap : yearRepeatlist) {
            listMap.add(resultMap); 
        }
        
        map.put("list", listMap);
        return map;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object getTeamDayRepeatSchedule(Map map) throws Exception {
        
        List<Map> listMap = new ArrayList<Map>();
        
        /* 매일반복 */
        /**/
        List<Map> daylist = commonDao.getList("schedule.getTeamDayRepeat", map);
        for(Map resultMap : daylist) {
            listMap.add(resultMap); 
        }
        
        map.put("list", listMap);
        return map;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object getTeamNotRepeatSchedule(Map map) throws Exception {
        
        List<Map> listMap = new ArrayList<Map>();

        /* 반복안함 */
        /**/
        List<Map> notRepeatlist = commonDao.getList("schedule.getTeamNotRepeat", map);
        for(Map resultMap : notRepeatlist) {
            listMap.add(resultMap); 
        }
        
        map.put("list", listMap);
        return map;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object getTeamWeekRepeatSchedule(Map map) throws Exception {
        
        List<Map> listMap = new ArrayList<Map>();

        /* 매주 반복 */
        /**/
        List<Map> weekRepeatlist = commonDao.getList("schedule.getTeamWeekRepeat", map);
        for(Map resultMap : weekRepeatlist) {
            listMap.add(resultMap); 
        }

        
        map.put("list", listMap);
        return map;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object getTeamMonthRepeatSchedule(Map map) throws Exception {
        
        List<Map> listMap = new ArrayList<Map>();

        /* 매월 말일선택 반복 */
        /* */
        List<Map> monthRepeatlistSelectDay = commonDao.getList("schedule.getTeamMonthRepeatSelectDay", map);
        for(Map resultMap : monthRepeatlistSelectDay) {
            listMap.add(resultMap);  
        }
        
        /* 매월 반복 */
        /* */
        List<Map> monthRepeatlist = commonDao.getList("schedule.getTeamMonthRepeat", map);
        for(Map resultMap : monthRepeatlist) {
            listMap.add(resultMap);  
        }

        
        map.put("list", listMap);
        return map;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object getTeamYearRepeatSchedule(Map map) throws Exception {
        
        List<Map> listMap = new ArrayList<Map>();

        /* 매년 반복 */
        /**/
        List<Map> yearRepeatlist = commonDao.getList("schedule.getTeamYearRepeat", map);
        for(Map resultMap : yearRepeatlist) {
            listMap.add(resultMap); 
        }

        
        map.put("list", listMap);
        return map;
    }
	
}

