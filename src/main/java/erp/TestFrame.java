package erp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp.dto.Department;
import erp.dto.Employee;
import erp.dto.Title;
import erp.service.EmployeeService;
import erp.ui.content.EmployeePanel;
import erp.ui.list.EmployeeTablePanel;
import erp.ui.content.EmployeeDetailPanel;
import java.awt.FlowLayout;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class TestFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnAdd;
	private JButton btnSet;
	private JButton btnCancel;
	private EmployeeTablePanel pList;
	private JPanel panel;
	private EmployeeDetailPanel panel_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TestFrame() {
		initialize();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		EmployeeService service = new EmployeeService();
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pBtns = new JPanel();
		contentPane.add(pBtns);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
		
		btnSet = new JButton("Set");
		btnSet.addActionListener(this);
		pBtns.add(btnSet);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtns.add(btnCancel);
		
		pList = new EmployeeTablePanel();
		pList.setService(service);
		pList.loadData();
		contentPane.add(pList);
		
		panel = new JPanel();
		pList.add(panel, BorderLayout.SOUTH);
		
		panel_1 = new EmployeeDetailPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnCancel) {
				actionPerformedBtnCancel(e);
			}
			if (e.getSource() == btnSet) {
				actionPerformedBtnSet(e);
			}
			if (e.getSource() == btnAdd) {
				actionPerformedBtnAdd(e);
			}
		}catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
	
	}
	
	protected void actionPerformedBtnSet(ActionEvent e) {
		
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		
	}
}