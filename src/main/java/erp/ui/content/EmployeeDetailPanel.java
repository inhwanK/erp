package erp.ui.content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import erp.dto.EmpDetail;
import erp.dto.Employee;
import erp.ui.exception.InvalidCheckException;

@SuppressWarnings("serial")
public class EmployeeDetailPanel extends AbstractContentPanel<EmpDetail> implements ActionListener {
	private JPasswordField pfPass1;
	private JPasswordField pfPass2;
	
	private String imgPath = System.getProperty("user.dir") + File.separator + "images" + File.separator;
    private JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));

	private JLabel lblPic;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnAddPic;
	private JDateChooser dateHire;
	private JRadioButton rdbtnFemale;
	private JLabel lblPassConfirm;
	private JTextField tfEmpNo;
	private JRadioButton rdbtnMale;
	
	public EmployeeDetailPanel() {
		initialize();
		loadPic(null);
	}
	
	private void loadPic(String imgFilePath) {
		Image chanagImage = null;
		if (imgFilePath == null) {
			ImageIcon icon = new ImageIcon(imgPath + "noImg.jpg");
			chanagImage = icon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
		}else {
			ImageIcon icon = new ImageIcon(imgFilePath);
			chanagImage = icon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
		}
		ImageIcon changIcon = new ImageIcon(chanagImage);
		lblPic.setIcon(changIcon);
	}

	private void initialize() {
		setBorder(new TitledBorder(null, "사원 세부 정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pTop = new JPanel();
		add(pTop, BorderLayout.NORTH);
		
		JPanel pPic = new JPanel();
		pTop.add(pPic);
		pPic.setLayout(new BorderLayout(0, 0));
		
		lblPic = new JLabel();
		lblPic.setPreferredSize(new Dimension(100, 150));
		pPic.add(lblPic, BorderLayout.CENTER);
		
		btnAddPic = new JButton("사진 추가");
		btnAddPic.addActionListener(this);
		pPic.add(btnAddPic, BorderLayout.SOUTH);
		
		JPanel pItem = new JPanel();
		add(pItem, BorderLayout.CENTER);
		pItem.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pContent = new JPanel();
		pItem.add(pContent);
		pContent.setLayout(new GridLayout(0, 2, 10, 0));
		
		JLabel lblEmpNo = new JLabel("사원 번호");
		lblEmpNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblEmpNo);
		
		tfEmpNo = new JTextField();
		tfEmpNo.setEditable(false);
		pContent.add(tfEmpNo);
		tfEmpNo.setColumns(10);
		
		JLabel lblHireDate = new JLabel("입사일");
		lblHireDate.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblHireDate);
		
		dateHire = new JDateChooser(new Date());
		pContent.add(dateHire);
		
		JLabel lblGender = new JLabel("성별");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblGender);
		
		JPanel pGender = new JPanel();
		pContent.add(pGender);
		
		rdbtnFemale = new JRadioButton("여");
		rdbtnFemale.setSelected(true);
		buttonGroup.add(rdbtnFemale);
		pGender.add(rdbtnFemale);
		
		rdbtnMale = new JRadioButton("남");
		buttonGroup.add(rdbtnMale);
		pGender.add(rdbtnMale);
		
		JLabel lblPass1 = new JLabel("비밀번호");
		lblPass1.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblPass1);
		
		pfPass1 = new JPasswordField();
		pfPass1.getDocument().addDocumentListener(listener);
		pContent.add(pfPass1);
		
		JLabel lblPass2 = new JLabel("비밀번호 확인");
		lblPass2.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblPass2);
		
		pfPass2 = new JPasswordField();
		pfPass2.getDocument().addDocumentListener(listener);
		pContent.add(pfPass2);
		
		JPanel pSpace = new JPanel();
		pContent.add(pSpace);
		
		lblPassConfirm = new JLabel();
		lblPassConfirm.setFont(new Font("굴림", Font.BOLD, 20));
		lblPassConfirm.setForeground(Color.RED);
		lblPassConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		pContent.add(lblPassConfirm);
	}

	public void setTfEmpno(Employee empNo) {
		tfEmpNo.setText(String.valueOf(empNo.getEmpNo()));
	}
	
	@Override
	public void setItem(EmpDetail item) {
		tfEmpNo.setText(String.valueOf(item.getEmpNo()));
		byte[] iconBytes = item.getPic();
		System.out.println(item);
		ImageIcon icon = new ImageIcon(iconBytes);
		lblPic.setIcon(icon);
		dateHire.setDate(item.getHireDate());
		if (item.isGender()) {
			rdbtnFemale.setSelected(true);
		}else {
			rdbtnMale.setSelected(true);
		}
	}

	@Override
	public EmpDetail getItem() {
		validCheck();
		int empNo = Integer.parseInt(tfEmpNo.getText().trim());
		boolean gender = rdbtnFemale.isSelected()?true:false;
		Date hireDate = dateHire.getDate();
		String pass = String.valueOf(pfPass1.getPassword());
		byte[] pic = getImage();
		return new EmpDetail(empNo, gender, hireDate, pass, pic);
	}

	private byte[] getImage() {
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
			ImageIcon icon = (ImageIcon) lblPic.getIcon();
			BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
			
			//icon -> image
			Graphics2D g2 = bi.createGraphics();
			g2.drawImage(icon.getImage(), 0, 0, null);
			g2.dispose();
			
			ImageIO.write(bi, "png", baos);
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void validCheck() {
		if (lblPassConfirm.getText().equals("불일치")) {
			throw new InvalidCheckException("비밀번호 불일치");
		}
	}

	@Override
	public void clearTf() {
		loadPic(null);
		dateHire.setDate(new Date());
		rdbtnFemale.setSelected(true);
		pfPass1.setText("");
		pfPass2.setText("");
		lblPassConfirm.setText("");
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAddPic) {
			actionPerformedBtnAddPic(e);
		}
	}
	
	protected void actionPerformedBtnAddPic(ActionEvent e) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG & GIF images", "jpg", "png", "gif");
        chooser.setFileFilter(filter);
        
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int res = chooser.showOpenDialog(null);
        if (res != JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다", "경고", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String chooseFilePath = chooser.getSelectedFile().getPath();
        loadPic(chooseFilePath);
	}
	
	DocumentListener listener = new DocumentListener() {
		@Override
		public void removeUpdate(DocumentEvent e) {
			getMessage();
		}
		@Override
		public void insertUpdate(DocumentEvent e) {
			getMessage();
		}
		@Override
		public void changedUpdate(DocumentEvent e) {
			getMessage();
		}
		
		private void getMessage() {
			String pw1 = new String(pfPass1.getPassword());
			String pw2 = String.valueOf(pfPass2.getPassword());
			if (pw1.equals(pw2)) {
				lblPassConfirm.setText("일치");
			}else {
				lblPassConfirm.setText("불일치");
			}
		}
	};

}
