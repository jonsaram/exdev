var C_CALENDAR = {
	getScheduleList : function(parm, callback) {
		
		var type = parm["type"];
        var startDate = parm["startDate"];
        var endDate = parm["endDate"];
        
        console.log("=== type ==>"+type);
        console.log("=== startDate ==>"+startDate);
        console.log("=== endDate ==>"+endDate);
        
        
        let requestParm = {};            
        requestParm["minusDate"]   = startDate; 
        requestParm["plusDate"]    = endDate; 
        requestParm["startDate"]   = startDate; 
        requestParm["endDate"]    = endDate; 
        requestParm["userIdList"]  = parm["userIdList"];
        
        /*====================================================================================== */
        
        var serviceId = "ScheduleService.getUserSchedule";
        var parm = {
             serviceId              : serviceId
            ,requestParm            : requestParm
        }
        C_COM.requestService(parm, function(resultData) {
            console.log("=== requestService ==");
            for (var i=0; i<resultData.data.list.length; i++){
                
                console.log("=== requestService =="+i);
            
                let length = 24; // 표시할 글자수 기준
                let title = resultData.data.list[i].TITLE;
                
            }
           
        });
        /**/
        /*====================================================================================== */      
		let resultList = [];
		
		if(typeof callback == "function") callback(resultList);
	}
}