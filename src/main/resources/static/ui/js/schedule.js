var C_CALENDAR = {
	getScheduleList : function(parm, callback) {
		
		dalert(parm);
		
		let resultList = [];
		
		if(typeof callback == "function") callback(resultList);
	}
}