create or replace view vw_full_employee
as
select e.empno
     , e.empname
     , t.tno as title_no
     , t.tname as title_name
     , e.manager as manager_no
     , m.empname as manager_name
     , e.salary
     , d.deptNo
     , d.deptName
     , d.floor
  from employee e join title t on e.title = t.tno
       left join employee m on e.manager = m.empno 
       join department d on e.dept = d.deptNo ;