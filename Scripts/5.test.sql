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