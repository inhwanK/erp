package erp.dao;

import java.util.List;

import erp.dto.Department;
import erp.dto.Employee;
import erp.dto.Title;

public interface EmployeeDao {
	List<Employee> selectEmployeeByAll();
	Employee selectEmployeeByNo(Employee employee);
	
	int insertEmployee(Employee employee);
	int updateEmployee(Employee employee);
	int deleteEmployee(Employee employee);
	
	List<Employee> selectEmployeeByTitle(Title title);
	List<Employee> selectEmployeeByDept(Department dept);
}






