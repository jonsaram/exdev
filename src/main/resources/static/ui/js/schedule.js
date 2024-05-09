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
        
        var serviceId = "ScheduleService.getTeamSchedule";
        var parm = {
             serviceId              : serviceId
            ,requestParm            : requestParm
        }
        let resultList = [];
        C_COM.requestService(parm, function(resultData) {
            
            for (var i=0; i<resultData.data.list.length; i++){
                resultList = resultData.data.list;
            }
            if(typeof callback == "function") callback(resultList);
        });
        
	}
}