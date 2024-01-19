
--------- 테이블 삭제

DROP TABLE ADMIN.TBL_EXP_CODE;
DROP TABLE ADMIN.TBL_EXP_GRPCODE;

DROP TABLE ADMIN.TBL_EXP_ROLEMENU;
DROP TABLE ADMIN.TBL_EXP_AUTH;
DROP TABLE ADMIN.TBL_EXP_USER;
DROP TABLE ADMIN.TBL_EXP_ROLE;
DROP TABLE ADMIN.TBL_EXP_MENU;
DROP TABLE ADMIN.TBL_SP_CSTM;

----------------- GRP CODE 테이블
    SELECT A.CONSTRAINT_NAME, A.* FROM ALL_CONSTRAINTS A WHERE OWNER='ADMIN' AND TABLE_NAME = 'TBL_EXP_GRPCODE';
    SELECT * FROM ADMIN.TBL_EXP_GRPCODE;
--    DROP TABLE ADMIN.TBL_EXP_GRPCODE;
    
    CREATE TABLE "ADMIN"."TBL_EXP_GRPCODE" (
        "GRP_CODE_ID" VARCHAR2(16 BYTE),
        "GRP_CODE_NM"  VARCHAR2(30 BYTE),
        "CODE_DESC" VARCHAR2(30 BYTE),
        "USE_YN" VARCHAR2(1 BYTE) DEFAULT 'Y',
        "DEL_YN"  VARCHAR2(1 BYTE) DEFAULT 'N',
        "SORT_ORDER" NUMBER,
        "UPDATE_USER" VARCHAR2(30 BYTE),
        "UPDATE_DATE" DATE,
        CONSTRAINT "PK_TBL_EXP_GRPCODE" PRIMARY KEY("GRP_CODE_ID")
    )
   DEFAULT COLLATION "USING_NLS_COMP" SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 10 MAXTRANS 255 
 COLUMN STORE COMPRESS FOR QUERY HIGH ROW LEVEL LOCKING LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "DATA" ;
  
  INSERT INTO TBL_EXP_GRPCODE ( GRP_CODE_ID, GRP_CODE_NM, CODE_DESC, USE_YN, DEL_YN, SORT_ORDER, UPDATE_USER, UPDATE_DATE ) VALUES ('18d10a6919b1835a' ,'CD_GRP_FIRST','ù��° �׷�','Y','N', 100,'admin',to_date('202401010910','YYYYMMDDHH24MISS') ) ;
  commit;
  
   SELECT * FROM ADMIN.TBL_EXP_GRPCODE;
   
  -----------------  CODE 테이블
    SELECT A.CONSTRAINT_NAME, A.* FROM ALL_CONSTRAINTS A WHERE OWNER='ADMIN' AND TABLE_NAME = 'TBL_EXP_CODE';
    SELECT * FROM ADMIN.TBL_EXP_CODE;
--    DROP TABLE ADMIN.TBL_EXP_CODE;
    
    CREATE TABLE "ADMIN"."TBL_EXP_CODE" (
        "GRP_CODE_ID" VARCHAR2(18 BYTE),
        "CODE_ID" VARCHAR2(18 BYTE),
        "CODE_NM" VARCHAR2(30 BYTE),
        "CODE_DESC" VARCHAR2(30 BYTE),
        "USE_YN" VARCHAR2(1 BYTE) DEFAULT 'Y',
        "DEL_YN" VARCHAR2(1 BYTE) DEFAULT 'N',
        "SORT_ORDER" NUMBER,
        "UPDATE_USER" VARCHAR2(30 BYTE),
        "UPDATE_DATE" DATE,
        CONSTRAINT "FK_GRP_CODE_ID" FOREIGN KEY ("GRP_CODE_ID") REFERENCES "ADMIN"."TBL_EXP_GRPCODE"("GRP_CODE_ID")
    )    
    DEFAULT COLLATION "USING_NLS_COMP" SEGMENT CREATION IMMEDIATE 
    PCTFREE 10 PCTUSED 40 INITRANS 10 MAXTRANS 255 
    COLUMN STORE COMPRESS FOR QUERY HIGH ROW LEVEL LOCKING LOGGING
    STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
    PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
    BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
    TABLESPACE "DATA" ;
  
    INSERT INTO TBL_EXP_CODE (GRP_CODE_ID, CODE_ID, CODE_NM, CODE_DESC, USE_YN, DEL_YN, SORT_ORDER, UPDATE_USER, UPDATE_DATE ) VALUES ('18d10a6919b1835a' ,'99d10a6919b1835a' ,'CD_TEST_1','�׽�Ʈ1','Y','N', 100,'admin',to_date('202401010910','YYYYMMDDHH24MISS') ) ;
    INSERT INTO TBL_EXP_CODE (GRP_CODE_ID, CODE_ID, CODE_NM, CODE_DESC, USE_YN, DEL_YN, SORT_ORDER, UPDATE_USER, UPDATE_DATE ) VALUES ('18d10a6919b1835a' ,'89d10a6919b1835a' ,'CD_TEST_2','�׽�Ʈ2','Y','N', 100,'admin',to_date('202401010910','YYYYMMDDHH24MISS') ) ;
    commit;
    
    SELECT * FROM ADMIN.TBL_EXP_CODE;
    
 ---------------- CUSTOMER 테이블 
    SELECT A.CONSTRAINT_NAME, A.* FROM ALL_CONSTRAINTS A WHERE OWNER='ADMIN' AND TABLE_NAME = 'TBL_SP_CSTM';
    SELECT * FROM ADMIN.TBL_SP_CSTM;
--    DROP TABLE ADMIN.TBL_SP_CSTM;

    CREATE TABLE "ADMIN"."TBL_SP_CSTM" (
        "SP_CSTM_ID" VARCHAR2(16 BYTE) ,-- PK
        "SP_CSTM_NM"  VARCHAR2(30 BYTE),
        "SP_CSTM_DESC" VARCHAR2(30 BYTE),
        "USE_YN" VARCHAR2(1 BYTE) DEFAULT 'Y',
        "DEL_YN"  VARCHAR2(1 BYTE) DEFAULT 'N',
        "UPDATE_USER" VARCHAR2(30 BYTE),
        "UPDATE_DATE" DATE,
        CONSTRAINT "PK_TBL_SP_CSTM" PRIMARY KEY("SP_CSTM_ID")
   ) 
    DEFAULT COLLATION "USING_NLS_COMP" SEGMENT CREATION IMMEDIATE 
    PCTFREE 10 PCTUSED 40 INITRANS 10 MAXTRANS 255 
    COLUMN STORE COMPRESS FOR QUERY HIGH ROW LEVEL LOCKING LOGGING
    STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
    PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
    BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
    TABLESPACE "DATA" ;
  
    Insert into ADMIN.TBL_SP_CSTM (SP_CSTM_ID,SP_CSTM_NM,SP_CSTM_DESC,USE_YN,DEL_YN,UPDATE_USER,UPDATE_DATE) values ('1234567890123456','A','Aȸ��','Y','N','admin',to_date('202401010910','YYYYMMDDHH24MISS'));
    Insert into ADMIN.TBL_SP_CSTM (SP_CSTM_ID,SP_CSTM_NM,SP_CSTM_DESC,USE_YN,DEL_YN,UPDATE_USER,UPDATE_DATE) values ('0987654321098765','B','Bȸ��','Y','N','admin',to_date('202401010910','YYYYMMDDHH24MISS'));
commit;
    SELECT * FROM ADMIN.TBL_SP_CSTM;
    
 ----------------- MENU 테이블
    SELECT A.CONSTRAINT_NAME, A.* FROM ALL_CONSTRAINTS A WHERE OWNER = 'ADMIN' AND TABLE_NAME = 'TBL_EXP_MENU';
    SELECT * FROM ADMIN.TBL_EXP_MENU;
--    DROP TABLE ADMIN.TBL_EXP_MENU;
    
    CREATE TABLE "ADMIN"."TBL_EXP_MENU" 
    (
        "SP_CSTM_ID" VARCHAR2(16 BYTE), --pk
        "MENU_ID" VARCHAR2(16 BYTE), -- pk 
        "MENU_NM" VARCHAR2(30 BYTE), 
        "PARENT_MENU_ID" VARCHAR2(16 BYTE), 
        "MENU_DEPTH" VARCHAR2(2 BYTE), 
        "MENU_DESC" VARCHAR2(100 BYTE),  
        "PAGE_ID" VARCHAR2(30 BYTE), 
        "USE_YN" VARCHAR2(2 BYTE) DEFAULT 'Y',
        "SORT_ORDER" NUMBER,
        "UPDATE_USER" VARCHAR2(30 BYTE), 
        "UPDATE_DATE" DATE,
        CONSTRAINT "PK_TBL_EXP_MENU" PRIMARY KEY("SP_CSTM_ID","MENU_ID"),
        CONSTRAINT "FK_MENU_SP_CSTM_ID" FOREIGN KEY ("SP_CSTM_ID") REFERENCES "ADMIN"."TBL_SP_CSTM"("SP_CSTM_ID")
    )  
    DEFAULT COLLATION "USING_NLS_COMP" SEGMENT CREATION IMMEDIATE 
    PCTFREE 10 PCTUSED 40 INITRANS 10 MAXTRANS 255 
    COLUMN STORE COMPRESS FOR QUERY HIGH ROW LEVEL LOCKING LOGGING
    STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
    PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
    BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
    TABLESPACE "DATA" ;
   
  INSERT INTO TBL_EXP_MENU (SP_CSTM_ID, MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_DEPTH, MENU_DESC, PAGE_ID, USE_YN, SORT_ORDER, UPDATE_USER, UPDATE_DATE ) VALUES ('1234567890123456', '18d19a6919b1835a' ,'MN_DASH_BOARD',null, '0','��ú���','admin_dashboard.html', 'Y', 100,'admin',to_date('202401010910','YYYYMMDDHH24MISS') ) ;
  INSERT INTO TBL_EXP_MENU (SP_CSTM_ID, MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_DEPTH, MENU_DESC, PAGE_ID, USE_YN, SORT_ORDER, UPDATE_USER, UPDATE_DATE ) VALUES ('1234567890123456', '19d19a6919b1835a' ,'MN_SCHED_MANAGE',null, '0','��������','admin_schedManage.html' , 'Y',200, 'admin',to_date('202401010910','YYYYMMDDHH24MISS') ) ;
  INSERT INTO TBL_EXP_MENU (SP_CSTM_ID, MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_DEPTH, MENU_DESC, PAGE_ID, USE_YN, SORT_ORDER, UPDATE_USER, UPDATE_DATE ) VALUES ('1234567890123456', '20d19a6919b1835a' ,'MN_BIZ_MANAGE',null, '0','��������','admin_bizManage.html', 'Y',300, 'admin',to_date('202401010910','YYYYMMDDHH24MISS') ) ;
  INSERT INTO TBL_EXP_MENU (SP_CSTM_ID, MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_DEPTH, MENU_DESC, PAGE_ID, USE_YN, SORT_ORDER, UPDATE_USER, UPDATE_DATE ) VALUES ('1234567890123456', '21d19a6919b1835a' ,'MN_RESULT_MANAGE',null, '0','��������','admin_resultManage.html', 'Y',400, 'admin',to_date('202401010910','YYYYMMDDHH24MISS') ) ;
  INSERT INTO TBL_EXP_MENU (SP_CSTM_ID, MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_DEPTH, MENU_DESC, PAGE_ID, USE_YN, SORT_ORDER, UPDATE_USER, UPDATE_DATE ) VALUES ('1234567890123456', '22d19a6919b1835a' ,'MN_BULLETIN',null, '0','�Խ���','admin_bulletin.html', 'Y',500, 'admin',to_date('202401010910','YYYYMMDDHH24MISS') ) ;
  INSERT INTO TBL_EXP_MENU (SP_CSTM_ID, MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_DEPTH, MENU_DESC, PAGE_ID, USE_YN, SORT_ORDER, UPDATE_USER, UPDATE_DATE ) VALUES ('1234567890123456', '23d19a6919b1835a' ,'MN_OPERATION_MANAGE',null, '0','�����','admin_oprManage.html', 'Y', 600, 'admin',to_date('202401010910','YYYYMMDDHH24MISS') ) ;
  
  commit;
  SELECT * FROM ADMIN.TBL_EXP_MENU;
  
 ---------------- 롤 테이블 
    SELECT A.CONSTRAINT_NAME, A.* FROM ALL_CONSTRAINTS A WHERE OWNER='ADMIN' AND TABLE_NAME = 'TBL_EXP_ROLE';
    SELECT * FROM ADMIN.TBL_EXP_ROLE;
--    DROP TABLE ADMIN.TBL_EXP_ROLE;
    
    CREATE TABLE "ADMIN"."TBL_EXP_ROLE" (
        "SP_CSTM_ID" VARCHAR2(16 BYTE), --pk
        "ROLE_ID" VARCHAR2(16 BYTE), -- pk
        "ROLE_NM"  VARCHAR2(30 BYTE),
        "ROLE_DESC" VARCHAR2(30 BYTE),
        "USE_YN" VARCHAR2(1 BYTE) DEFAULT 'Y',
        "DEL_YN"  VARCHAR2(1 BYTE) DEFAULT 'N',
        "SORT_ORDER" NUMBER,
        "UPDATE_USER" VARCHAR2(30 BYTE),
        "UPDATE_DATE" DATE,
        CONSTRAINT "PK_TBL_EXP_ROLE" PRIMARY KEY("SP_CSTM_ID","ROLE_ID"), 
        CONSTRAINT "FK_ROLE_SP_CSTM_ID" FOREIGN KEY ("SP_CSTM_ID") REFERENCES "ADMIN"."TBL_SP_CSTM"("SP_CSTM_ID")
    ) 
    DEFAULT COLLATION "USING_NLS_COMP" SEGMENT CREATION IMMEDIATE 
    PCTFREE 10 PCTUSED 40 INITRANS 10 MAXTRANS 255 
    COLUMN STORE COMPRESS FOR QUERY HIGH ROW LEVEL LOCKING LOGGING
    STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
    PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
    BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
    TABLESPACE "DATA" ;
  
    DELETE FROM  "ADMIN"."TBL_EXP_ROLE"; 
    Insert into ADMIN.TBL_EXP_ROLE (SP_CSTM_ID,ROLE_ID,ROLE_NM,ROLE_DESC,USE_YN,DEL_YN,SORT_ORDER,UPDATE_USER,UPDATE_DATE) values ('1234567890123456','r8d19a6919b1835a','RL_SYSADMIN','�ý��� ����','Y','N',100,'admin',to_date('202401010910','YYYYMMDDHH24MISS'));
    Insert into ADMIN.TBL_EXP_ROLE (SP_CSTM_ID,ROLE_ID,ROLE_NM,ROLE_DESC,USE_YN,DEL_YN,SORT_ORDER,UPDATE_USER,UPDATE_DATE) values ('1234567890123456','r7d19a6919b1835a','RL_ADMIN','������','Y','N',200,'admin',to_date('202401010910','YYYYMMDDHH24MISS'));
    Insert into ADMIN.TBL_EXP_ROLE (SP_CSTM_ID,ROLE_ID,ROLE_NM,ROLE_DESC,USE_YN,DEL_YN,SORT_ORDER,UPDATE_USER,UPDATE_DATE) values ('1234567890123456','r6d19a6919b1835a','RL_NORMAL','�Ϲݻ����','Y','N',300,'admin',to_date('202401010910','YYYYMMDDHH24MISS'));
commit;

    SELECT * FROM ADMIN.TBL_EXP_ROLE;
----------------- 사용자 테이블  
    SELECT A.CONSTRAINT_NAME, A.*  FROM ALL_CONSTRAINTS A WHERE OWNER = 'ADMIN' AND TABLE_NAME = 'TBL_EXP_USER';
    SELECT * FROM ADMIN.TBL_EXP_USER;
--    DROP TABLE ADMIN.TBL_EXP_USER;

    CREATE TABLE "ADMIN"."TBL_EXP_USER" (
        "SP_CSTM_ID" VARCHAR2(16 BYTE), --pk
        "USER_ID" VARCHAR2(16 BYTE), -- pk
        "USER_NM"  VARCHAR2(30 BYTE),
        "USER_DESC" VARCHAR2(30 BYTE),
        "E_MAIL" VARCHAR2(50 BYTE),
        "PHONE_NUM" VARCHAR2(50 BYTE),
        "USE_YN" VARCHAR2(1 BYTE) DEFAULT 'Y',
        "DEL_YN"  VARCHAR2(1 BYTE) DEFAULT 'N',
        "UPDATE_USER" VARCHAR2(30 BYTE),
        "UPDATE_DATE" DATE,
        CONSTRAINT "PK_TBL_EXP_USER" PRIMARY KEY("SP_CSTM_ID","USER_ID"),
        CONSTRAINT "FK_USER_SP_CSTM_ID" FOREIGN KEY ("SP_CSTM_ID") REFERENCES "ADMIN"."TBL_SP_CSTM"("SP_CSTM_ID")
    ) 
    DEFAULT COLLATION "USING_NLS_COMP" SEGMENT CREATION IMMEDIATE 
    PCTFREE 10 PCTUSED 40 INITRANS 10 MAXTRANS 255 
    COLUMN STORE COMPRESS FOR QUERY HIGH ROW LEVEL LOCKING LOGGING
    STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
    PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
    BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
    TABLESPACE "DATA" ;

DELETE FROM  "ADMIN"."TBL_EXP_USER"; 
INSERT INTO "ADMIN"."TBL_EXP_USER" (SP_CSTM_ID, USER_ID, USER_NM, USER_DESC,E_MAIL, PHONE_NUM, USE_YN, DEL_YN, UPDATE_USER,UPDATE_DATE) VALUES ('1234567890123456', 'trigger7kr', '���¿�', 'Ʈ����','trigger7kr@gmail.com','010-9522-5440', 'Y', 'N', 'admin', to_date('202401010910','YYYYMMDDHH24MISS'));
INSERT INTO "ADMIN"."TBL_EXP_USER" (SP_CSTM_ID, USER_ID, USER_NM, USER_DESC,E_MAIL, PHONE_NUM, USE_YN, DEL_YN, UPDATE_USER,UPDATE_DATE) VALUES ('1234567890123456', 'jonsaram74', '������', '�����','jonsaram74@gmail.com','010-xxx-yyyy', 'Y', 'N', 'admin', to_date('202401010910','YYYYMMDDHH24MISS'));
INSERT INTO "ADMIN"."TBL_EXP_USER" (SP_CSTM_ID, USER_ID, USER_NM, USER_DESC,E_MAIL, PHONE_NUM, USE_YN, DEL_YN, UPDATE_USER,UPDATE_DATE) VALUES ('1234567890123456', 'manetogame', '����', '���̸�','trigger7kr@gmail.com','010-game-gggyyy', 'Y', 'N', 'admin', to_date('202401010910','YYYYMMDDHH24MISS'));
INSERT INTO "ADMIN"."TBL_EXP_USER" (SP_CSTM_ID, USER_ID, USER_NM, USER_DESC,E_MAIL, PHONE_NUM, USE_YN, DEL_YN, UPDATE_USER,UPDATE_DATE) VALUES ('1234567890123456', 'windrider', '������', '���̴�','trigger7kr@gmail.com','010-rider-yyyy', 'Y', 'N', 'admin', to_date('202401010910','YYYYMMDDHH24MISS'));
commit;

 SELECT * FROM ADMIN.TBL_EXP_USER;
 
 ----------------- 권한  매핑 테이블 ( 사용자 - 권한 )
    SELECT A.CONSTRAINT_NAME, A.*  FROM ALL_CONSTRAINTS A WHERE OWNER = 'ADMIN' AND TABLE_NAME = 'TBL_EXP_AUTH' ;
    SELECT * FROM ADMIN.TBL_EXP_AUTH;
--    DROP TABLE ADMIN.TBL_EXP_AUTH;
    
    CREATE TABLE "ADMIN"."TBL_EXP_AUTH" (
        "SP_CSTM_ID" VARCHAR2(16 BYTE), --pk
        "USER_ID" VARCHAR2(30 BYTE), --pk
        "ROLE_ID" VARCHAR2(30 BYTE),
        
        CONSTRAINT "PK_TBL_EXP_AUTH" PRIMARY KEY ("SP_CSTM_ID","USER_ID"),    
        CONSTRAINT "FK_USER_ID" FOREIGN KEY ("SP_CSTM_ID","USER_ID") REFERENCES "ADMIN"."TBL_EXP_USER"("SP_CSTM_ID","USER_ID"),
        CONSTRAINT "FK_ROLE_ID" FOREIGN KEY ("SP_CSTM_ID","ROLE_ID") REFERENCES "ADMIN"."TBL_EXP_ROLE"("SP_CSTM_ID","ROLE_ID")
    ) 
    DEFAULT COLLATION "USING_NLS_COMP" SEGMENT CREATION IMMEDIATE 
    PCTFREE 10 PCTUSED 40 INITRANS 10 MAXTRANS 255 
    COLUMN STORE COMPRESS FOR QUERY HIGH ROW LEVEL LOCKING LOGGING
    STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
    PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
    BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
    TABLESPACE "DATA" ;
  
    Insert into ADMIN.TBL_EXP_AUTH (SP_CSTM_ID,USER_ID,ROLE_ID) values ('1234567890123456','trigger7kr','r8d19a6919b1835a');
    commit;

    SELECT * FROM ADMIN.TBL_EXP_AUTH;

 ----------------- 롤/ 메뉴   매핑 테이블 
 
    SELECT A.CONSTRAINT_NAME, A.*  FROM ALL_CONSTRAINTS A WHERE OWNER = 'ADMIN' AND TABLE_NAME = 'TBL_EXP_ROLEMENU' ;
    SELECT * FROM ADMIN.TBL_EXP_ROLEMENU;
--    DROP TABLE ADMIN.TBL_EXP_ROLEMENU;   
    CREATE TABLE "ADMIN"."TBL_EXP_ROLEMENU" (
        "SP_CSTM_ID" VARCHAR2(16 BYTE), --pk
        "ROLE_ID" VARCHAR2(30 BYTE), -- pk
        "MENU_ID" VARCHAR2(30 BYTE), --pk 
        "AUTH_TYPE" VARCHAR2(2 BYTE),
        CONSTRAINT "PK_TBL_EXP_ROLEMENU" PRIMARY KEY ("ROLE_ID", "MENU_ID"),
        CONSTRAINT "FK_ROLEMENU_ROLE_ID" FOREIGN KEY ("SP_CSTM_ID","ROLE_ID") REFERENCES "ADMIN"."TBL_EXP_ROLE"("SP_CSTM_ID","ROLE_ID"),
        CONSTRAINT "FK_ROLEMENU_MENU_ID" FOREIGN KEY ("SP_CSTM_ID","MENU_ID") REFERENCES "ADMIN"."TBL_EXP_MENU"("SP_CSTM_ID","MENU_ID")
    ) 
   DEFAULT COLLATION "USING_NLS_COMP" SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 10 MAXTRANS 255 
 COLUMN STORE COMPRESS FOR QUERY HIGH ROW LEVEL LOCKING LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "DATA" ;
 

