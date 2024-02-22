package exdev.com.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DateUtil {

    /** 
     * 내용        : 날짜 더하기
     * @생 성 자   : 이응규
     * @생 성 일자 : 2024. 02. 22 : 최초 생성
     * @수 정 자   : 
     * @수 정 일자 :
     * @수 정 자   :
     * String addDay   = DateUtil.AddDate("2024-03-21", 0, 0, 1); 1일후 날짜
     * String addWeek  = DateUtil.AddDate("2024-03-21", 0, 0, 8); 1주 후 날짜
     * String addMonth = DateUtil.AddDate("2024-03-21", 0, 1, 1); 1달 후 날짜
     * String addYear  = DateUtil.AddDate("2024-03-21", 1, 0, 1); 1년 후 날짜
     */  
    public static String AddDate(String strDate, int year, int month, int day) throws Exception {
        

        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date dt = dtFormat.parse(strDate);
        
        cal.setTime(dt);
        
        cal.add(Calendar.YEAR,  year);
        cal.add(Calendar.MONTH, month);
        cal.add(Calendar.DATE,  day);
        
        return dtFormat.format(cal.getTime());
    }

}
