-- 계정 권한 부여
grant all 
   on erp_teacher.* 
   to 'user_erp_teacher'@'localhost' identified by 'rootroot';

-- file 권한(backup, load) -- root만 권한 부여 가능
GRANT File 
   ON *.* 
   TO 'user_erp'@'localhost';
   
  grant all 
   on erp.* 
   to 'user_erp'@'localhost' identified by 'rootroot';