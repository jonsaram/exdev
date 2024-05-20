var C_CALENDAR = {
	getScheduleList : function(parm, callback) {
		
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
        
        
        var serviceId = "ScheduleService.getTeamSchedule";
        var parm = {
             serviceId              : serviceId
            ,requestParm            : requestParm
        }

        let resultList = [];

        C_COM.requestService(parm, function(resultData) {
            
			$.each(resultData.data.list, function() {
				let item = this;
				item.START_DATETIME = `${item.SCHEDULE_START_DATE} ${item.START_TIME_MINUTE}:${item.START_TIME_MINUTE}`;
				resultList.push(item);
			});
            if(typeof callback == "function") callback(resultList);
        });
        
	}
}