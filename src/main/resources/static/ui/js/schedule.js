var C_CALENDAR = {
	getScheduleList : function(parm, callback) {
		
        var startDate = parm["startDate"];
        var endDate   = parm["endDate"];
        
        console.log("=== startDate ==>"+startDate);
        console.log("=== endDate ==>"+endDate);
        
        let requestParm = {};            
        requestParm["minusDate"]   = startDate; 
        requestParm["plusDate"]    = endDate; 
        requestParm["startDate"]   = startDate; 
        requestParm["endDate"]     = endDate; 
        requestParm["userIdList"]  = parm["userIdList"];
        requestParm["periodYn"]    = "Y";
        
        /*====================================================================================== */
        
        
        var serviceId = "ScheduleService.getTeamSchedule";
        var parm = {
             serviceId              : serviceId
            ,requestParm            : requestParm
        }
        let resultList = [];
        C_COM.requestService(parm, function(resultData) {
            
            for (var i=0; i<resultData.data.list.length; i++){
            
                let title             = resultData.data.list[i].TITLE;
                let scheduleId        = resultData.data.list[i].SCHEDULE_ID
                let scheduleStartDate = resultData.data.list[i].SCHEDULE_START_DATE
                let startTimeHour     = resultData.data.list[i].START_TIME_HOUR
                let startTimeMinute   = resultData.data.list[i].START_TIME_MINUTE
                let scheduleEndDate   = resultData.data.list[i].SCHEDULE_END_DATE
                let endTimeHour       = resultData.data.list[i].END_TIME_HOUR
                let endTimeMinute     = resultData.data.list[i].END_TIME_MINUTE
                let position          = resultData.data.list[i].POSITION
                let workType          = resultData.data.list[i].WORK_TYPE
                let workTypeNm        = resultData.data.list[i].WORK_TYPE_NM
                let textColor         = resultData.data.list[i].TEXT_COLOR;
                let workColor         = resultData.data.list[i].WORK_COLOR;
                console.log("=== title             =="+title              );
                console.log("=== scheduleId        =="+scheduleId         );
                console.log("=== scheduleStartDate =="+scheduleStartDate  );
                console.log("=== startTimeHour     =="+startTimeHour      );
                console.log("=== startTimeMinute   =="+startTimeMinute    );
                console.log("=== scheduleEndDate   =="+scheduleEndDate    );
                console.log("=== endTimeHour       =="+endTimeHour        );
                console.log("=== endTimeMinute     =="+endTimeMinute      );
                console.log("=== position          =="+position           );
                console.log("=== workType          =="+workType           );
                console.log("=== workTypeNm        =="+workTypeNm         );
                console.log("=== textColor         =="+textColor          );
                console.log("=== workColor         =="+workColor          );
                
                resultList = resultData.data.list;
                
                
            }
            if(typeof callback == "function") callback(resultList);
        });
        
        /*====================================================================================== */      
		
		
	}
}