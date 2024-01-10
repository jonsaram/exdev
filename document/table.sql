-- DROP TABLE ATS_MEMBER;
-- 
-- CREATE TABLE ATS_MEMBER
-- (
--      USER_ID      		varchar(20)  CHARACTER SET utf8 COLLATE utf8_general_ci
--     ,USER_PW      		varchar(20)  CHARACTER SET utf8 COLLATE utf8_general_ci
--     ,ACCOUNT_NUM  		varchar(20)  CHARACTER SET utf8 COLLATE utf8_general_ci
--     ,ACCOUNT_PW   		varchar(20)  CHARACTER SET utf8 COLLATE utf8_general_ci
--     ,ROLLUP_YN    		varchar(20)  DEFAULT 'N'
--     ,MAKE_GAP_GET_CNT	varchar(20)  CHARACTER SET utf8 COLLATE utf8_general_ci	
--     ,START_DELAY_TIME	varchar(20)  CHARACTER SET utf8 COLLATE utf8_general_ci	
--     ,ROLLUP_TIME		varchar(20)  CHARACTER SET utf8 COLLATE utf8_general_ci	
--     ,PRIMARY KEY (`USER_ID`)
-- );
-- 
-- INSERT INTO ATS_MEMBER VALUES('jonsaram', '932152'	, '57386978', '0708', 'Y', '202', '2' ,	'1510');
-- INSERT INTO ATS_MEMBER VALUES('kongju'	, '932152'	, '60901459', '0708', 'Y', '201', '4' ,	'1512');
-- INSERT INTO ATS_MEMBER VALUES('park'	, 'park'	, '61141812', '9070', 'Y', '199', '6' ,	'1514');
-- INSERT INTO ATS_MEMBER VALUES('taewon'	, 'taewon'	, '60962436', '0114', 'Y', '198', '10',	'1516');
-- INSERT INTO ATS_MEMBER VALUES('wsh'		, 'wsh'		, '61248352', '0301', 'Y', '200', '8' ,	'1518');
-- INSERT INTO ATS_MEMBER VALUES('wsy'		, 'wsy'		, '61234156', '9619', 'Y', '197', '12',	'1450');
-- 
-- 
-- 
-- 
-- 
-- 
-- 
-- DROP TABLE ATS_COMMON_CODE;
-- 
-- CREATE TABLE ATS_COMMON_CODE
-- (
--      `CODE`      	varchar(50)  CHARACTER SET utf8 COLLATE utf8_general_ci
--     ,`CODE_NM`       varchar(200)  CHARACTER SET utf8 COLLATE utf8_general_ci
--     ,PRIMARY KEY (`CODE`)
-- );
-- 
-- INSERT INTO ATS_COMMON_CODE VALUES("BASE_KP200", "201");
-- INSERT INTO ATS_COMMON_CODE VALUES("BASE_KD150", "150");
-- 
-- INSERT INTO ATS_COMMON_CODE VALUES("KP200", "101SC000");
-- INSERT INTO ATS_COMMON_CODE VALUES("KD150", "106SC000");
-- INSERT INTO ATS_COMMON_CODE VALUES("KPMIN", "105SC000");
-- INSERT INTO ATS_COMMON_CODE VALUES("BUY" , "2"     );
-- INSERT INTO ATS_COMMON_CODE VALUES("SELL" , "1"     );
-- 
-- INSERT INTO ATS_COMMON_CODE VALUES("KP200_CNT", "1");
-- INSERT INTO ATS_COMMON_CODE VALUES("KD150_CNT", "7");
-- INSERT INTO ATS_COMMON_CODE VALUES("MINI_KP200_CNT", "2");
-- INSERT INTO ATS_COMMON_CODE VALUES("MINI_KD150_CNT", "3");
-- 
-- INSERT INTO ATS_COMMON_CODE VALUES("RU_KP200",  "101T3000");
-- INSERT INTO ATS_COMMON_CODE VALUES("RU_KD150",  "106T3000");
-- INSERT INTO ATS_COMMON_CODE VALUES("RU_KPMIN",  "105T1000");
-- 
-- INSERT INTO ATS_COMMON_CODE VALUES("MIN_BUY_MONEY"				,  "19000000");
-- INSERT INTO ATS_COMMON_CODE VALUES("MIN_BUY_MONEY_RUNTIME"		,  "3500000" );
-- INSERT INTO ATS_COMMON_CODE VALUES("MINI_MIN_BUY_MONEY"			,  "12000000");
-- INSERT INTO ATS_COMMON_CODE VALUES("MINI_MIN_BUY_MONEY_RUNTIME"	,  "2000000" );
-- 
-- 
-- 
-- 
-- 
-- DROP TABLE ATS_GAP_PLAN;
-- 
-- CREATE TABLE ATS_GAP_PLAN
-- (
--      USER_ID      varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci
--     ,TOP	      DECIMAL(4,2)
--     ,BOTTOM	      DECIMAL(4,2)
--     ,STATE	      varchar(1)  CHARACTER SET utf8 COLLATE utf8_general_ci
--     ,STOCK_TYPE	  varchar(10)  CHARACTER SET utf8 COLLATE utf8_general_ci
--     ,IVST_CODE	  varchar(10)  CHARACTER SET utf8 COLLATE utf8_general_ci
--     ,DUMMY		  varchar(1)  CHARACTER SET utf8 COLLATE utf8_general_ci
--     ,PRIMARY KEY (USER_ID, TOP, BOTTOM)
-- );
-- 
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "1.0", "0.30 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "1.1", "0.40 ", "0", "MINI"		, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "1.2", "0.50 ", "0", "MINI"		, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "1.3", "0.60 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "1.4", "0.70 ", "0", "MINI"		, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "1.5", "0.80 ", "0", "MINI"		, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "1.6", "0.85 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "1.7", "0.90 ", "0", "MINI"		, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "1.8", "0.95 ", "0", "MINI"		, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "1.9", "1.00 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "2.0", "1.30 ", "0", "MINI"		, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "2.1", "1.10 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "2.2", "1.20 ", "0", "MINI"		, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "2.3", "1.30 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "2.4", "1.40 ", "0", "MINI"		, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "2.5", "1.50 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "2.6", "1.60 ", "0", "MINI"		, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "2.7", "1.70 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "2.8", "1.75 ", "0", "MINI"		, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "2.9", "1.80 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "3.0", "2.30 ", "0", "MINI"		, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "3.1", "1.90 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "3.2", "1.95 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "3.3", "2.00 ", "0", "MINI"		, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "3.4", "2.05 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "3.5", "2.70 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "3.6", "2.15 ", "0", "MINI"		, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "3.7", "2.20 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "3.8", "2.25 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "3.9", "2.30 ", "0", "MINI"		, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "4.0", "3.00 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "4.1", "2.40 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "4.2", "2.50 ", "0", "MINI"		, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "4.3", "2.60 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "4.4", "2.70 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "4.5", "2.80 ", "0", "MINI"		, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "5.0", "2.90 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "5.5", "3.00 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "6.0", "3.10 ", "0", "MINI"		, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "6.5", "3.20 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "7.0", "3.30 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "7.5", "3.40 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "8.0", "3.50 ", "0", "NORMAL"	, "", "0");
-- 
-- 
-- 
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-1.0", "-0.30 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-1.1", "-0.40 ", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-1.2", "-0.50 ", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-1.3", "-0.60 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-1.4", "-0.70 ", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-1.5", "-0.80 ", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-1.6", "-0.85 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-1.7", "-0.90 ", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-1.8", "-0.95 ", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-1.9", "-1.00 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-2.0", "-1.30 ", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-2.1", "-1.10 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-2.2", "-1.20 ", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-2.3", "-1.30 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-2.4", "-1.40 ", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-2.5", "-1.50 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-2.6", "-1.60 ", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-2.7", "-1.70 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-2.8", "-1.75 ", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-2.9", "-1.80 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-3.0", "-2.30 ", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-3.1", "-1.90 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-3.2", "-1.95 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-3.3", "-2.00 ", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-3.4", "-2.05 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-3.5", "-2.70 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-3.6", "-2.15 ", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-3.7", "-2.20 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-3.8", "-2.25 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-3.9", "-2.30 ", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-4.0", "-3.00 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-4.1", "-2.40 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-4.2", "-2.50 ", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-4.3", "-2.60 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-4.4", "-2.70 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-4.5", "-2.80 ", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-5.0", "-2.90 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-5.5", "-3.00 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-6.0", "-3.10 ", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-6.5", "-3.20 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-7.0", "-3.30 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-7.5", "-3.40 ", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("jonsaram", "-8.0", "-3.50 ", "0", "NORMAL"	, "", "0");
-- 
-- 
-- 
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "1.0", "0.3", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "1.3", "0.5", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "1.6", "0.7", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "1.9", "0.9", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "2.2", "1.1", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "2.4", "1.2", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "2.6", "1.4", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "2.8", "1.7", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "3.0", "1.9", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "3.2", "2.0", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "3.3", "2.1", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "3.5", "2.2", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "3.6", "2.3", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "3.8", "2.4", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "3.9", "2.5", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "4.0", "2.6", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "4.1", "2.7", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "4.2", "2.8", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "4.3", "2.9", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "4.4", "3.0", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "4.5", "3.1", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "5.0", "3.4", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "5.5", "3.7", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "6.0", "4.0", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "6.5", "4.4", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "7.0", "4.8", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "7.5", "5.2", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "8.0", "5.5", "0", "NORMAL"	, "", "0");
-- 
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-1.0", "-0.3", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-1.3", "-0.5", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-1.6", "-0.7", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-1.9", "-0.9", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-2.2", "-1.1", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-2.4", "-1.2", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-2.6", "-1.4", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-2.8", "-1.7", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-3.0", "-1.9", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-3.2", "-2.0", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-3.3", "-2.1", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-3.5", "-2.2", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-3.6", "-2.3", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-3.8", "-2.4", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-3.9", "-2.5", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-4.0", "-2.6", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-4.1", "-2.7", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-4.2", "-2.8", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-4.3", "-2.9", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-4.4", "-3.0", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-4.5", "-3.1", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-5.0", "-3.4", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-5.5", "-3.7", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-6.0", "-4.0", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-6.5", "-4.4", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-7.0", "-4.8", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-7.5", "-5.2", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("kongju", "-8.0", "-5.5", "0", "NORMAL"	, "", "0");
-- 
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "1.0", "0.3", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "1.2", "0.5", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "1.4", "0.7", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "1.6", "0.9", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "1.8", "1.0", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "2.0", "1.1", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "2.2", "1.2", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "2.4", "1.3", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "2.6", "1.4", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "2.8", "1.6", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "3.0", "1.8", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "3.2", "2.0", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "3.4", "2.1", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "3.6", "2.2", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "3.8", "2.3", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "4.0", "2.4", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "4.5", "2.8", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "5.0", "3.2", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "5.5", "3.6", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "6.0", "4.0", "0", "MINI"	, "", "0");
-- 
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "-1.0", "-0.3", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "-1.2", "-0.5", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "-1.4", "-0.7", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "-1.6", "-0.9", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "-1.8", "-1.0", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "-2.0", "-1.1", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "-2.2", "-1.2", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "-2.4", "-1.3", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "-2.6", "-1.4", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "-2.8", "-1.6", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "-3.0", "-1.8", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "-3.2", "-2.0", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "-3.4", "-2.1", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "-3.6", "-2.2", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "-3.8", "-2.3", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "-4.0", "-2.4", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "-4.5", "-2.8", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "-5.0", "-3.2", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "-5.5", "-3.6", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("taewon", "-6.0", "-4.0", "0", "MINI"	, "", "0");
-- 
-- 
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "1.0", "0.3", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "1.2", "0.5", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "1.4", "0.7", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "1.6", "0.9", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "1.8", "1.0", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "2.0", "1.1", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "2.2", "1.2", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "2.4", "1.3", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "2.6", "1.4", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "2.8", "1.6", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "3.0", "1.8", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "3.2", "2.0", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "3.4", "2.1", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "3.6", "2.2", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "3.8", "2.3", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "4.0", "2.4", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "4.5", "2.8", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "5.0", "3.2", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "5.5", "3.6", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "6.0", "4.0", "0", "MINI"	, "", "0");
-- 
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "-1.0", "-0.3", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "-1.2", "-0.5", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "-1.4", "-0.7", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "-1.6", "-0.9", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "-1.8", "-1.0", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "-2.0", "-1.1", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "-2.2", "-1.2", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "-2.4", "-1.3", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "-2.6", "-1.4", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "-2.8", "-1.6", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "-3.0", "-1.8", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "-3.2", "-2.0", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "-3.4", "-2.1", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "-3.6", "-2.2", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "-3.8", "-2.3", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "-4.0", "-2.4", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "-4.5", "-2.8", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "-5.0", "-3.2", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "-5.5", "-3.6", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsy", "-6.0", "-4.0", "0", "MINI"	, "", "0");
-- 
-- /*
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "1.0", "0.3", "0", "NORMAL"	, "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "1.4", "0.6", "0", "NORMAL"	, "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "1.8", "0.9", "0", "NORMAL"	, "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "2.2", "1.2", "0", "NORMAL"	, "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "2.5", "1.4", "0", "NORMAL"	, "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "2.8", "1.6", "0", "NORMAL"	, "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "3.0", "1.8", "0", "NORMAL"	, "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "3.2", "2.0", "0", "NORMAL"	, "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "3.4", "2.1", "0", "NORMAL"	, "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "3.6", "2.2", "0", "NORMAL"	, "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "3.8", "2.3", "0", "NORMAL"	, "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "4.0", "2.4", "0", "NORMAL"	, "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "4.5", "2.8", "0", "NORMAL"	, "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "5.0", "3.2", "0", "NORMAL"	, "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "5.5", "3.6", "0", "NORMAL"	, "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "6.0", "4.0", "0", "NORMAL"	, "", "1");
-- 
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-1.0", "-0.3", "0", "NORMAL", "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-1.4", "-0.6", "0", "NORMAL", "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-1.8", "-0.9", "0", "NORMAL", "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-2.2", "-1.2", "0", "NORMAL", "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-2.5", "-1.4", "0", "NORMAL", "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-2.8", "-1.6", "0", "NORMAL", "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-3.0", "-1.8", "0", "NORMAL", "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-3.2", "-2.0", "0", "NORMAL", "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-3.4", "-2.1", "0", "NORMAL", "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-3.6", "-2.2", "0", "NORMAL", "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-3.8", "-2.3", "0", "NORMAL", "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-4.0", "-2.4", "0", "NORMAL", "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-4.5", "-2.8", "0", "NORMAL", "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-5.0", "-3.2", "0", "NORMAL", "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-5.5", "-3.6", "0", "NORMAL", "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-6.0", "-4.0", "0", "NORMAL", "", "1");
-- */
-- 
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "1.0", "0.3", "0", "NORMAL"	, "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "1.4", "0.6", "0", "NORMAL"	, "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "1.8", "0.9", "0", "NORMAL"	, "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "2.2", "1.2", "0", "NORMAL"	, "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "2.5", "1.4", "0", "NORMAL"	, "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "2.8", "1.6", "0", "NORMAL"	, "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "3.2", "2.0", "0", "NORMAL"	, "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "3.6", "2.2", "0", "NORMAL"	, "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "4.0", "2.4", "0", "NORMAL"	, "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "4.5", "2.8", "0", "NORMAL"	, "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "5.5", "3.6", "0", "NORMAL"	, "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "6.0", "4.0", "0", "NORMAL"	, "", "1");
-- 
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-1.0", "-0.3", "0", "NORMAL", "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-1.4", "-0.6", "0", "NORMAL", "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-1.8", "-0.9", "0", "NORMAL", "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-2.2", "-1.2", "0", "NORMAL", "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-2.5", "-1.4", "0", "NORMAL", "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-2.8", "-1.6", "0", "NORMAL", "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-3.2", "-2.0", "0", "NORMAL", "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-3.6", "-2.2", "0", "NORMAL", "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-4.0", "-2.4", "0", "NORMAL", "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-4.5", "-2.8", "0", "NORMAL", "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-5.5", "-3.6", "0", "NORMAL", "", "1");
-- INSERT INTO ATS_GAP_PLAN VALUES("wsh", "-6.0", "-4.0", "0", "NORMAL", "", "1");
-- 
-- 
-- 
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "1.0", "0.3", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "1.3", "0.5", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "1.5", "0.7", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "1.7", "1.0", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "1.9", "1.1", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "2.2", "1.2", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "2.4", "1.4", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "2.5", "1.7", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "2.6", "1.3", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "2.8", "1.7", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "3.0", "1.8", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "3.2", "1.9", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "3.3", "1.8", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "3.4", "2.6", "0", "MINI"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "3.5", "2.1", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "3.6", "2.2", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "3.8", "2.3", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "3.9", "2.4", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "4.0", "2.5", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "4.1", "2.6", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "4.2", "2.7", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "4.3", "2.8", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "4.4", "2.9", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "4.5", "3.0", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "5.0", "3.3", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "5.5", "3.6", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "6.0", "3.9", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "6.5", "4.2", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "7.0", "4.5", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "7.5", "4.8", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "8.0", "5.0", "0", "NORMAL"	, "", "0");
-- 
-- 
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-1.0", "-0.3", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-1.3", "-0.5", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-1.5", "-0.7", "0", "MINI"		, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-1.7", "-1.0", "0", "MINI"		, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-1.9", "-1.1", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-2.2", "-1.2", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-2.4", "-1.4", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-2.5", "-1.7", "0", "MINI"		, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-2.6", "-1.3", "0", "MINI"		, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-2.8", "-1.7", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-3.0", "-1.8", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-3.2", "-1.9", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-3.3", "-1.8", "0", "MINI"		, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-3.4", "-2.6", "0", "MINI"		, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-3.5", "-2.1", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-3.6", "-2.2", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-3.8", "-2.3", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-3.9", "-2.4", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-4.0", "-2.5", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-4.1", "-2.6", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-4.2", "-2.7", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-4.3", "-2.8", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-4.4", "-2.9", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-4.5", "-3.0", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-5.0", "-3.3", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-5.5", "-3.6", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-6.0", "-3.9", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-6.5", "-4.2", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-7.0", "-4.5", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-7.5", "-4.8", "0", "NORMAL"	, "", "0");
-- INSERT INTO ATS_GAP_PLAN VALUES("park", "-8.0", "-5.0", "0", "NORMAL"	, "", "0");
-- 
-- 
-- 
-- 
-- 
-- 
-- DROP TABLE ATS_LOG;
-- 
-- CREATE TABLE ATS_LOG
-- (
--      USER_ID      	varchar(20)  CHARACTER SET utf8 COLLATE utf8_general_ci
--     ,REG_DATE       datetime
--     ,LOG_TYPE       varchar(10)  CHARACTER SET utf8 COLLATE utf8_general_ci
--     ,LOG_DATA       varchar(2000)  CHARACTER SET utf8 COLLATE utf8_general_ci
--     ,SHOW_YN		varchar(1) DEFAULT 'N'
-- );
-- 
-- 
-- 
-- 
-- DROP TABLE ATS_REALTIME_CMD;
-- 
-- CREATE TABLE ATS_REALTIME_CMD
-- (
-- 	 SEQNUM			int(10) 	  NOT NULL AUTO_INCREMENT PRIMARY KEY
--     ,USER_ID      	varchar(20)   CHARACTER SET utf8 COLLATE utf8_general_ci
--     ,REG_DATE       datetime
--     ,COMMAND        varchar(200)  CHARACTER SET utf8 COLLATE utf8_general_ci
--     ,EXEC_STATE     varchar(3)    CHARACTER SET utf8 COLLATE utf8_general_ci /* REG / CMP */
-- );
-- 
-- 
-- INSERT INTO ATS_REALTIME_CMD(USER_ID, REG_DATE, COMMAND, EXEC_STATE ) VALUES ('jonsaram', CURRENT_TIMESTAMP, 'SELL/-1.0/-0.3', 'REG');





CREATE TABLE `jonsaram93`.`ATS_COMMON_CODE`
(
    `CODE`     varchar(50)   CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `CODE_NM`  varchar(200)  CHARACTER SET utf8 COLLATE utf8_general_ci,
    PRIMARY KEY (`CODE`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci ROW_FORMAT=Compact;
CREATE TABLE `jonsaram93`.`ATS_GAP_PLAN`
(
    `USER_ID`     varchar(20)   CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `TOP`         decimal(4,2)  NOT NULL,
    `BOTTOM`      decimal(4,2)  NOT NULL,
    `STATE`       varchar(1)    CHARACTER SET utf8 COLLATE utf8_general_ci,
    `STOCK_TYPE`  varchar(10)   CHARACTER SET utf8 COLLATE utf8_general_ci,
    `IVST_CODE`   varchar(10)   CHARACTER SET utf8 COLLATE utf8_general_ci,
    `DUMMY`       varchar(1)    CHARACTER SET utf8 COLLATE utf8_general_ci,
    `USE_YN`      varchar(1)    CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'Y',
    `GAP_GROUP`   varchar(50)   CHARACTER SET utf8 COLLATE utf8_general_ci,
    `AFTER_DAY`   int(11)       NOT NULL DEFAULT 0,
    PRIMARY KEY (`USER_ID`, `TOP`, `BOTTOM`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci ROW_FORMAT=Compact;
CREATE TABLE `jonsaram93`.`ATS_GAP_PLAN_BACKUP`
(
    `USER_ID`     varchar(20)   CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `TOP`         decimal(4,2)  NOT NULL,
    `BOTTOM`      decimal(4,2)  NOT NULL,
    `STATE`       varchar(1)    CHARACTER SET utf8 COLLATE utf8_general_ci,
    `STOCK_TYPE`  varchar(10)   CHARACTER SET utf8 COLLATE utf8_general_ci,
    `IVST_CODE`   varchar(10)   CHARACTER SET utf8 COLLATE utf8_general_ci,
    `DUMMY`       varchar(1)    CHARACTER SET utf8 COLLATE utf8_general_ci,
    `USE_YN`      varchar(1)    CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'Y',
    `REG_DATE`    datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `GAP_GROUP`   varchar(50)   CHARACTER SET utf8 COLLATE utf8_general_ci,
    `AFTER_DAY`   int(11),
    PRIMARY KEY (`USER_ID`, `TOP`, `BOTTOM`, `REG_DATE`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci ROW_FORMAT=Compact;
CREATE TABLE `jonsaram93`.`ATS_INDEX_DATA`
(
    `REG_DATE`  datetime,
    `KP200`     varchar(10)  CHARACTER SET utf8 COLLATE utf8_general_ci,
    `KD150`     varchar(10)  CHARACTER SET utf8 COLLATE utf8_general_ci
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci ROW_FORMAT=Compact;
CREATE TABLE `jonsaram93`.`ATS_LOG`
(
    `USER_ID`   varchar(20)    CHARACTER SET utf8 COLLATE utf8_general_ci,
    `REG_DATE`  datetime,
    `LOG_TYPE`  varchar(10)    CHARACTER SET utf8 COLLATE utf8_general_ci,
    `LOG_DATA`  varchar(2000)  CHARACTER SET utf8 COLLATE utf8_general_ci,
    `SHOW_YN`   varchar(1)     CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'N'
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci ROW_FORMAT=Compact;
CREATE TABLE `jonsaram93`.`ATS_MEMBER`
(
    `USER_ID`           varchar(20)   CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `USER_PW`           varchar(20)   CHARACTER SET utf8 COLLATE utf8_general_ci,
    `ACCOUNT_NUM`       varchar(20)   CHARACTER SET utf8 COLLATE utf8_general_ci,
    `ACCOUNT_PW`        varchar(20)   CHARACTER SET utf8 COLLATE utf8_general_ci,
    `ROLLUP_YN`         varchar(20)   CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'N',
    `MAKE_GAP_GET_CNT`  varchar(20)   CHARACTER SET utf8 COLLATE utf8_general_ci,
    `START_DELAY_TIME`  varchar(20)   CHARACTER SET utf8 COLLATE utf8_general_ci,
    `ROLLUP_TIME`       varchar(20)   CHARACTER SET utf8 COLLATE utf8_general_ci,
    `ADD_RATE`          varchar(5)    CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT 'Ãß°¡¼öÀÍ·ü',
    `IVST_START_DATE`   date,
    `USE_YN`            varchar(255)  CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'Y',
    PRIMARY KEY (`USER_ID`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci ROW_FORMAT=Compact;
CREATE TABLE `jonsaram93`.`ATS_REALTIME_CMD`
(
    `SEQNUM`      int(10)       NOT NULL AUTO_INCREMENT,
    `USER_ID`     varchar(20)   CHARACTER SET utf8 COLLATE utf8_general_ci,
    `REG_DATE`    datetime,
    `COMMAND`     varchar(200)  CHARACTER SET utf8 COLLATE utf8_general_ci,
    `EXEC_STATE`  varchar(3)    CHARACTER SET utf8 COLLATE utf8_general_ci,
    PRIMARY KEY (`SEQNUM`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci ROW_FORMAT=Compact AUTO_INCREMENT=202;
CREATE TABLE `jonsaram93`.`ATS_TEST_LOG`
(
    `GROUP_NUM`   varchar(10)    CHARACTER SET utf8 COLLATE utf8_general_ci,
    `IVST_TYPE`   varchar(20)    CHARACTER SET utf8 COLLATE utf8_general_ci,
    `TOP`         decimal(4,2)   NOT NULL,
    `BOTTOM`      decimal(4,2)   NOT NULL,
    `KP200_S`     varchar(10)    CHARACTER SET utf8 COLLATE utf8_general_ci,
    `KP200_E`     varchar(10)    CHARACTER SET utf8 COLLATE utf8_general_ci,
    `KD150_S`     varchar(10)    CHARACTER SET utf8 COLLATE utf8_general_ci,
    `KD150_E`     varchar(10)    CHARACTER SET utf8 COLLATE utf8_general_ci,
    `INCOME`      decimal(14,2)  NOT NULL,
    `START_DATE`  varchar(12)    CHARACTER SET utf8 COLLATE utf8_general_ci,
    `END_DATE`    varchar(12)    CHARACTER SET utf8 COLLATE utf8_general_ci,
    `REG_DATE`    datetime
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci ROW_FORMAT=Compact;
