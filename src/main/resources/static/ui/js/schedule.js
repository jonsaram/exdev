var C_CALENDAR = {
     isDayRepeat   : false
    ,isNotRepeat   : false
    ,isWeekRepeat  : false
    ,isMonthRepeat : false
    ,isYearRepeat  : false
    ,resultList    : []
    ,getScheduleList : function(parm, callback) {
        
        var startDate = parm["startDate"];
        var endDate   = parm["endDate"];
        
        let requestParm = {};            
        requestParm["minusDate"]   = startDate; 
        requestParm["plusDate"]    = endDate; 
        requestParm["startDate"]   = startDate; 
        requestParm["endDate"]     = endDate;
        requestParm["contractId"]  = parm["contractId"]; 
        requestParm["userIdList"]  = parm["userIdList"];
        requestParm["periodYn"]    = "Y";
        requestParm["searchData"]  = parm["searchData"];
        
        C_CALENDAR.serviceCall(callback, requestParm, "ScheduleService.getTeamDayRepeatSchedule");
        C_CALENDAR.serviceCall(callback, requestParm, "ScheduleService.getTeamNotRepeatSchedule");
        C_CALENDAR.serviceCall(callback, requestParm, "ScheduleService.getTeamWeekRepeatSchedule");
        C_CALENDAR.serviceCall(callback, requestParm, "ScheduleService.getTeamMonthRepeatSchedule");
        C_CALENDAR.serviceCall(callback, requestParm, "ScheduleService.getTeamYearRepeatSchedule");
        
    }
    ,serviceCall : function( callback, requestParm, serviceId ) {
      
        var parm = {
             serviceId              : serviceId
            ,requestParm            : requestParm
        }
        
        C_COM.requestService(parm, function(resultData) {
            $.each(resultData.data.list, function() {
                let item = this;
                item.START_DATETIME = `${item.SCHEDULE_START_DATE} ${item.START_TIME_MINUTE}:${item.START_TIME_MINUTE}`;
                C_CALENDAR.resultList.push(item);
            });
                      
            if( serviceId == "ScheduleService.getTeamDayRepeatSchedule"){   C_CALENDAR.isDayRepeat   = true; }
            if( serviceId == "ScheduleService.getTeamNotRepeatSchedule"){   C_CALENDAR.isNotRepeat   = true; }
            if( serviceId == "ScheduleService.getTeamWeekRepeatSchedule"){  C_CALENDAR.isWeekRepeat  = true; }
            if( serviceId == "ScheduleService.getTeamMonthRepeatSchedule"){ C_CALENDAR.isMonthRepeat = true; }
            if( serviceId == "ScheduleService.getTeamYearRepeatSchedule"){  C_CALENDAR.isYearRepeat  = true; }
            
            if( C_CALENDAR.isDayRepeat && C_CALENDAR.isNotRepeat && C_CALENDAR.isWeekRepeat && C_CALENDAR.isMonthRepeat && C_CALENDAR.isYearRepeat ){
                if(typeof callback == "function") callback(C_CALENDAR.resultList);
                C_CALENDAR.resultList.length = 0;
                C_CALENDAR.isDayRepeat   = false;
                C_CALENDAR.isNotRepeat   = false;
                C_CALENDAR.isWeekRepeat  = false;
                C_CALENDAR.isMonthRepeat = false;
                C_CALENDAR.isYearRepeat  = false;
            }
        });
    }
}