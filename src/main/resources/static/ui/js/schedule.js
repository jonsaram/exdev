var C_CALENDAR = {
    isNotRepeat: false,
    isTeamNotRepeat: false,
    IS_RETURN: false,
    CONTRACT_ID: "",
    resultList: [],
    
    getScheduleList: async function(parm, callback) {
        
        var startDate1 = parm["startDate"];
        let date = new Date(startDate1);
        date.setDate(date.getDate() - 1); // 하루를 빼기
        let startDate = date.toISOString().split('T')[0]; // 새로운 날짜를 "YYYY-MM-DD" 형식으로 변환
        
        var endDate = parm["endDate"];
        C_CALENDAR.CONTRACT_ID = parm["contractId"];
        
        var userListYn = "Y";
        var userIdList = parm["userIdList"];
        if (!userIdList) {
            userListYn = "N";
        }
        
        let requestParm = {};            
        requestParm["minusDate"] = startDate; 
        requestParm["plusDate"] = endDate; 
        requestParm["startDate"] = startDate; 
        requestParm["endDate"] = endDate;
        requestParm["contractId"] = C_CALENDAR.CONTRACT_ID; 
        requestParm["userIdList"] = userIdList; 
        requestParm["userListYn"] = userListYn;
        requestParm["periodYn"] = "Y";
        requestParm["searchData"] = parm["searchData"];
        if (C_CALENDAR.CONTRACT_ID) {
            // 두 서비스 호출을 병렬로 처리하고 결과가 모두 완료될 때까지 기다림
            await Promise.all([
                C_CALENDAR.serviceCall(requestParm, "ScheduleService.getTeamNotRepeatSchedule"),
                C_CALENDAR.serviceCall(requestParm, "ScheduleService.getTeamWeekRepeatSchedule"),
                C_CALENDAR.serviceCall(requestParm, "ScheduleService.getTeamMonthRepeatSchedule"),
                C_CALENDAR.serviceCall(requestParm, "ScheduleService.getTeamYearRepeatSchedule"),
                C_CALENDAR.serviceCall(requestParm, "ScheduleService.getNotRepeatSchedule"),
                C_CALENDAR.serviceCall(requestParm, "ScheduleService.getDayRepeatSchedule"),
                C_CALENDAR.serviceCall(requestParm, "ScheduleService.getWeekRepeatSchedule"),
                C_CALENDAR.serviceCall(requestParm, "ScheduleService.getMonthRepeatSchedule"),
                C_CALENDAR.serviceCall(requestParm, "ScheduleService.getYearRepeatSchedule")
            ]);
        }else{
            // 두 서비스 호출을 병렬로 처리하고 결과가 모두 완료될 때까지 기다림
            await Promise.all([
                C_CALENDAR.serviceCall(requestParm, "ScheduleService.getNotRepeatSchedule"),
                C_CALENDAR.serviceCall(requestParm, "ScheduleService.getDayRepeatSchedule"),
                C_CALENDAR.serviceCall(requestParm, "ScheduleService.getWeekRepeatSchedule"),
                C_CALENDAR.serviceCall(requestParm, "ScheduleService.getMonthRepeatSchedule"),
                C_CALENDAR.serviceCall(requestParm, "ScheduleService.getYearRepeatSchedule")
            ]);
        }
        
        // 두 서비스가 완료된 후 callback 호출
        if (typeof callback == "function") {
            callback(C_CALENDAR.resultList);
        }

        // 초기화
        C_CALENDAR.resultList.length = 0;
        C_CALENDAR.isTeamNotRepeat = false;
        C_CALENDAR.isNotRepeat = false;
    },
    
    serviceCall: function(requestParm, serviceId) {
        return new Promise((resolve) => {
            var parm = {
                serviceId: serviceId,
                requestParm: requestParm
            };
            
            C_COM.requestService(parm, function(resultData) {
                $.each(resultData.data.list, function() {
                    let item = this;
                    item.START_DATETIME = `${item.SCHEDULE_START_DATE} ${item.START_TIME_MINUTE}:${item.START_TIME_MINUTE}`;
                    C_CALENDAR.resultList.push(item);
                });
        
                // resultList를 SCHEDULE_START_DATE 기준으로 정렬
                C_CALENDAR.resultList.sort(function(a, b) {
                    let dateA = new Date(a.SCHEDULE_START_DATE);
                    let dateB = new Date(b.SCHEDULE_START_DATE);
                    return dateA - dateB;
                });
                resolve(); // 작업이 완료되었음을 알림
            });
        });
    }
}
