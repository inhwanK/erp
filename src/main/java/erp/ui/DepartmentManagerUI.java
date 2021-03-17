package erp.ui;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import erp.dto.Department;
import erp.dto.Employee;
import erp.service.DepartmentService;
import erp.ui.content.AbstractContentPanel;
import erp.ui.content.DepartmentPanel;
import erp.ui.list.AbstractCustomTablePanel;
import erp.ui.list.DepartmentTablePanel;

@SuppressWarnings("serial")
public class DepartmentManagerUI extends AbstractManagerUi<Department> {
	private DepartmentService service;
	

	@Override
	protected void setService() {
		service = new DepartmentService();		
	}

	@Override
	protected void tableLoadData() {
		((DepartmentTablePanel)pList).setService(service);
		pList.loadData();		
	}

	@Override
	protected AbstractContentPanel<Department> createContentPanel() {
		return new DepartmentPanel();
	}

	@Override
	protected AbstractCustomTablePanel<Department> createTablePanel() {
		return new DepartmentTablePanel();
	}

	@Override
	protected void actionPerformedMenuGubun() {
		Department dept = pList.getItem();
		List<Employee> list = service.showEmployeeGroupByDepartment(dept);

		if (list == null) {
			JOptionPane.showMessageDialog(null, "해당 사원이 없음", "동일 부서 사원", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		List<String> strList = list.parallelStream().map(s -> {
			return String.format("%s(%d)", s.getEmpName(), s.getEmpNo());
		}).collect(Collectors.toList());

		JOptionPane.showMessageDialog(null, strList, "동일 부서 사원", JOptionPane.INFORMATION_MESSAGE);		
	}

	@Override
	protected void actionPerformedMenuUpdate() {
		Department updateDept = pList.getItem();
		pContent.setItem(updateDept);
		btnAdd.setText("수정");		
	}

	@Override
	protected void actionPerformedMenuDelete() {
		Department delDept = pList.getItem();
		service.removeDepartment(delDept);
		pList.loadData();
		JOptionPane.showMessageDialog(null, delDept + "삭제 되었습니다.");		
	}

	@Override
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		Department updateDept = pContent.getItem();
		service.modifyDepartment(updateDept);
		pList.loadData();
		pContent.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, updateDept.getDeptName() + "정보가 수정되었습니다.");		
	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Department addDept = pContent.getItem();
		service.addDepartment(addDept);
		pList.loadData();
		pContent.clearTf();
		JOptionPane.showMessageDialog(null, addDept + " 추가했습니다.");		
	}

	public DepartmentManagerUI() {
		empListByTitleItem.setText(AbstractManagerUi.DEPT_MENU);
	}

	

}