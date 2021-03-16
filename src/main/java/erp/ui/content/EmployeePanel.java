package erp.ui.content;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import erp.dto.Department;
import erp.dto.Employee;
import erp.dto.Title;
import erp.service.EmployeeService;
import erp.ui.exception.InvalidCheckException;

@SuppressWarnings("serial")
public class EmployeePanel extends AbstractContentPanel<Employee> implements ItemListener {
	private JTextField tfNo;
	private JTextField tfName;
	private JComboBox<Title> cmbTitle;
	private JComboBox<Employee> cmbManager;
	private JSpinner spinSalary;
	private JComboBox<Department> cmbDept;

	private EmployeeService service;
	
	public EmployeePanel() {
		initialize();
	}
	
	public void setService(EmployeeService service) {
		this.service = service;
		
		List<Department> deptList = service.showDeptList();
		DefaultComboBoxModel<Department> deptModel = new DefaultComboBoxModel<>(new Vector<>(deptList));
		cmbDept.setModel(deptModel);
		
		List<Title> titleList = service.showTitleList();
		DefaultComboBoxModel<Title> titleModel = new DefaultComboBoxModel<>(new Vector<>(titleList));
		cmbTitle.setModel(titleModel);
		
		cmbDept.setSelectedIndex(-1);
		cmbTitle.setSelectedIndex(-1);
	}

	private void initialize() {
		setBorder(new TitledBorder(null, "사원 정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pItem = new JPanel();
		add(pItem);
		pItem.setLayout(new GridLayout(0, 2, 10, 0));
		
		JLabel lblNo = new JLabel("사원번호");
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblNo);
		
		tfNo = new JTextField();
		tfNo.setColumns(10);
		pItem.add(tfNo);
		
		JLabel lblName = new JLabel("사원명");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblName);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		pItem.add(tfName);
		
		JLabel lblDept = new JLabel("부서");
		lblDept.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblDept);
		
		cmbDept = new JComboBox<>();
		cmbDept.addItemListener(this);
		pItem.add(cmbDept);
		
		JLabel lblManager = new JLabel("직속상사");
		lblManager.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblManager);
		
		cmbManager = new JComboBox<>();
		pItem.add(cmbManager);
		
		JLabel lblTitle = new JLabel("직책");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblTitle);
		
		cmbTitle = new JComboBox<>();
		pItem.add(cmbTitle);
		
		JLabel lblSalary = new JLabel("급여");
		lblSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblSalary);
		
		spinSalary = new JSpinner();
		spinSalary.setModel(new SpinnerNumberModel(2000000, 1500000, 5000000, 100000));
		pItem.add(spinSalary);
	}

	@Override
	public void clearTf() {
		tfNo.setText("");
		tfName.setText("");
		cmbTitle.setSelectedIndex(-1);
		cmbDept.setSelectedIndex(-1);
		cmbManager.setSelectedIndex(-1);
		spinSalary.setValue(2000000);
	}
	
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cmbDept) {
			itemStateChangedCmbDept(e);
		}
	}
	
	protected void itemStateChangedCmbDept(ItemEvent e) {
		if (e.getStateChange()==ItemEvent.SELECTED) {
			Department dept = (Department) cmbDept.getSelectedItem();
			List<Employee> empList = service.showEmployeeByDept(dept);
			//직속 상사가 없는 경우 추가
			if (empList == null) {
				empList = new ArrayList<>();
			}
			DefaultComboBoxModel<Employee> model = new DefaultComboBoxModel<>(new Vector<>(empList));
			cmbManager.setModel(model);
			cmbManager.setSelectedIndex(-1);
		}
	}

	@Override
	public void setItem(Employee item) {
		tfNo.setText(item.getEmpNo() + "");
		tfName.setText(item.getEmpName());
		cmbTitle.setSelectedItem(item.getTitle());
		cmbDept.setSelectedItem(item.getDept());
		cmbManager.setSelectedItem(item.getManager());
		spinSalary.setValue(item.getSalary());		
	}

	@Override
	public Employee getItem() {
		validCheck();
		int empNo = Integer.parseInt(tfNo.getText().trim());
		String empName = tfName.getText().trim();
		Title title = (Title) cmbTitle.getSelectedItem();
		Employee manager = (Employee) cmbManager.getSelectedItem();
		int salary = (int) spinSalary.getValue();
		Department dept = (Department) cmbDept.getSelectedItem();
		return new Employee(empNo, empName, title, manager, salary, dept);
	}

	@Override
	public void validCheck() {
		if (tfNo.getText().contentEquals("") || tfName.getText().equals("") 
				|| cmbTitle.getSelectedIndex() == -1 
				|| cmbDept.getSelectedIndex() == -1
				|| cmbManager.getSelectedIndex() == -1) {
			throw new InvalidCheckException();
		}		
	}
}
