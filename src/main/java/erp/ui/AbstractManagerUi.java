package erp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import erp.ui.content.AbstractContentPanel;
import erp.ui.exception.InvalidCheckException;
import erp.ui.exception.SqlConstraintException;
import erp.ui.list.AbstractCustomTablePanel;

@SuppressWarnings("serial")
public abstract class AbstractManagerUi<T> extends JFrame implements ActionListener {

	private JPanel contentPane;
	protected JButton btnAdd;
	private JButton btnClear;
	
	protected AbstractContentPanel<T> pContent;
	protected AbstractCustomTablePanel<T> pList;
	protected JMenuItem empListByTitleItem;
	
	protected static final String TITLE_MENU ="동일 직책 사원 보기";
	protected static final String DEPT_MENU ="동일 부서 사원 보기";
	protected static final String EMP_MENU ="동일 세부정보 보기";
	
	public AbstractManagerUi() {
		setService();//service 연결
		
		initialize();
		
		tableLoadData();//component load data
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pContent = createContentPanel();
		contentPane.add(pContent);
		
		JPanel pBtns = new JPanel();
		contentPane.add(pBtns);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
		
		btnClear = new JButton("취소");
		btnClear.addActionListener(this);
		pBtns.add(btnClear);
		
		pList = createTablePanel();
		contentPane.add(pList);
		
		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(this);
		popMenu.add(updateItem);

		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(this);
		popMenu.add(deleteItem);
		
		empListByTitleItem = new JMenuItem("동일 직책 사원 보기");
		empListByTitleItem.addActionListener(this);
		popMenu.add(empListByTitleItem);
		
		return popMenu;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() instanceof JMenuItem) {
				if (e.getActionCommand().equals("삭제")) {
					actionPerformedMenuDelete();
				}
				
				if (e.getActionCommand().equals("수정")) {
					actionPerformedMenuUpdate();
				}
				
				if (e.getActionCommand().contentEquals(AbstractManagerUi.TITLE_MENU)
						|| e.getActionCommand().contentEquals(AbstractManagerUi.DEPT_MENU) 
						|| e.getActionCommand().contentEquals(AbstractManagerUi.EMP_MENU)){
					actionPerformedMenuGubun();
				}
			}else {
				if (e.getSource() == btnClear) {
					actionPerformedBtnClear(e);
				}
	
				if (e.getSource() == btnAdd) {
					if (e.getActionCommand().contentEquals("추가")) {
						actionPerformedBtnAdd(e);
					}else {
						actionPerformedBtnUpdate(e);
					}
				}
			}
		}catch (InvalidCheckException | SqlConstraintException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
//			pContent.clearTf();
		}catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	protected abstract void setService();
	protected abstract void tableLoadData();
	protected abstract AbstractContentPanel<T> createContentPanel();
	protected abstract AbstractCustomTablePanel<T> createTablePanel();
	
	protected abstract void actionPerformedMenuGubun();
	protected abstract void actionPerformedMenuUpdate();
	protected abstract void actionPerformedMenuDelete();
	protected abstract void actionPerformedBtnUpdate(ActionEvent e);
	protected abstract void actionPerformedBtnAdd(ActionEvent e);
	
	protected void actionPerformedBtnClear(ActionEvent e) {
		pContent.clearTf();
		
		if (btnAdd.getText().contentEquals("수정")) {
			btnAdd.setText("추가");
		}
	}
}