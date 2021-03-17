package erp.ui.list;

import javax.swing.SwingConstants;

import erp.dto.Department;
import erp.service.DepartmentService;
import erp.ui.exception.NotSelectedException;

@SuppressWarnings("serial")
public class DepartmentTablePanel extends AbstractCustomTablePanel<Department> {
	private DepartmentService service;

	@Override
	public void initList() {
		list = service.showDeptList();
	}

	@Override
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2);
		// 컬럼별 너비 조정
		setTableCellWidth(100, 250, 100);
	}

	@Override
	public Object[] toArray(Department t) {
		return new Object[] { t.getDeptNo(), t.getDeptName(), t.getFloor() };
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "부서번호", "부서명", "위치" };
	}

	public void setService(DepartmentService service) {
		this.service = service;
	}

	@Override
	public Department getItem() {
		int row = table.getSelectedRow();
		int DeptNo = (int) table.getValueAt(row, 0);
		
		if (row == -1) {
			throw new NotSelectedException();
		}
		System.out.println(list.indexOf("영업(1)"));
		System.out.println(list.indexOf(new Department(DeptNo)));
		return list.get(list.indexOf(new Department(DeptNo)));
	}
}