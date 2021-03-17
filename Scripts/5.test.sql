select * from title;

-- 해당 직책을 가지고 있는 사원목록을 검색
select empname, empno
  from employee e 
  join title t
    on e.title  = t.tno
 where tno = 5;
 
-- 해당 부서 소속된 사원목록을 검색
select empname, empno, deptno
  from employee e 
  join department d
    on e.dept = d.deptNo 
 where dept = 2;

-- 해당 직책별 사원수
select tname, count(*) as 사원수
  from title t left join employee e on t.tno = e.title 
 group by tno;
 
create or replace view vw_full_employee 
as
select e.empno,e.empname
       ,t.tno as title_no
       ,t.tname as title_name
       ,e.manager as manager_no
       , m.empname as manager_name
       ,e.salary
       ,d.deptNo,d.deptname
       ,floor
  from employee e join title t on e.title = t.tno
        left join employee m on e.manager = m.empno
        join department d on e.dept = d.deptno;

select *
  from emp_detail;
insert into emp_detail values(1003,null,true,);
delete
  from emp_detail where empno = 1003;
  
 select empno,pic,gender,hiredate,pass from emp_detail where empno = ?;
 

select *
from emp_detail;

drop database erp;
create database erp;

INSERT INTO emp_detail(empno, pic, gender, hiredate) 
	               VALUES (1003, null, false, 1111 );
	               
	              
SELECT empno, pic, gender, hiredate FROM emp_detail WHERE empno = 1003;   

select *
  from employee;
 
 
