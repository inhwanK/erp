package erp.dao;

import erp.dto.EmpDetail;
import erp.dto.Employee;

public interface EmployeeDetailDao {

	
	EmpDetail selectEmployeeDetailByNo(Employee employee);
	
	int insertEmployeeDetail(EmpDetail employee);
	int updateEmployeeDetail(EmpDetail employee);
	int deleteEmployeeDetail(Employee employee);
}
