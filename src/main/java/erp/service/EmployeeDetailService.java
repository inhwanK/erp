package erp.service;

import java.util.List;

import erp.dao.EmployeeDetailDao;
import erp.dao.impl.EmployeeDetailDaoImpl;
import erp.dto.EmpDetail;
import erp.dto.Employee;

public class EmployeeDetailService {
	private EmployeeDetailDao empDetailDao = EmployeeDetailDaoImpl.getInstance();
	
	public EmpDetail showEmployeeDetail(Employee employee){
		return empDetailDao.selectEmployeeDetailByNo(employee);
	}
	
	public void modifyEmployeeDetail(EmpDetail empDetail) {
		empDetailDao.updateEmployeeDetail(empDetail);
	}
	
	public void addEmployeeDetail(EmpDetail empDetail) {
		empDetailDao.insertEmployeeDetail(empDetail);
	}
	
	public void removeEmployeeDetail(Employee employee) {
		empDetailDao.deleteEmployeeDetail(employee);
	}
}
