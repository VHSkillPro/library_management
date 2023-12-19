package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bean.PhieuMuon;
import bo.PhieuMuonBo;
import bo.ThuThuBo;
import utils.Validate;
import utils.ValidateElement;
import utils.ValidateForm;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import bo.DocGiaBo;

public class FormTaoPhieuMuon extends JFrame {
	private FormTaoPhieuMuon thisFrm = this;
	private JPanel contentPane;
	private PanelPhieuMuon parent;
	private ArrayList<String> lableMess = new ArrayList<String>();
	private ArrayList<JLabel> lableError = new ArrayList<JLabel> ();
	private ArrayList<ValidateForm<String>> validateForm = new ArrayList<ValidateForm<String>>();
	JLabel lblErrorMaTT, lblErrorMaDocGia;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	
	public FormTaoPhieuMuon(PanelPhieuMuon pr) {
		this.parent = pr;
		setTitle("Tạo hóa đơn - Phần mềm quản lý thư viện");
		setFont(new Font("Segoe UI", Font.PLAIN, 12));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 453, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbTaoHD = new JLabel("Tạo hóa đơn");
		lbTaoHD.setFont(new Font("Segoe UI", Font.BOLD, 21));
		lbTaoHD.setBounds(150, 11, 140, 50);
		contentPane.add(lbTaoHD);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 70, 417, 2);
		contentPane.add(separator);
		
		JLabel lbMaTT = new JLabel("Nhập mã thủ thư");
		lbMaTT.setLabelFor(lbMaTT);
		lbMaTT.setIcon(new ImageIcon(FormSignIn.class.getResource("/icons/user.png")));
		lbMaTT.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lbMaTT.setBounds(50, 90, 126, 16);
		contentPane.add(lbMaTT);
		
		JTextField txtMaTT = new JTextField();
		txtMaTT.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtMaTT.setBounds(50, 110, 334, 25);
		contentPane.add(txtMaTT);
		txtMaTT.setColumns(10);
		
		JLabel lbMaKH = new JLabel("Nhập mã đọc giả");
		lbMaKH.setIcon(new ImageIcon(FormSignIn.class.getResource("/icons/user.png")));
		lbMaKH.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lbMaKH.setBounds(50, 150, 132, 16);
		contentPane.add(lbMaKH);
		
		
		JTextField txtMaKH = new JTextField();
		lbMaKH.setLabelFor(txtMaKH);
		txtMaKH.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtMaKH.setBounds(50, 170, 334, 25);
		contentPane.add(txtMaKH);
	
		JButton buttonSignIn = new JButton("Xác nhận");
		buttonSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lableMess.set(0, validateForm.get(0).validate(txtMaTT.getText()));
					lableMess.set(1, validateForm.get(1).validate(txtMaKH.getText()));
					boolean ok = true;
					for (int i = 0; i < 2; i++) {
						if (lableMess.get(i) != null) {
							ok = false;
							lableError.get(i).setText(lableMess.get(i));
							lableError.get(i).setVisible(true);
						} else {
							lableError.get(i).setVisible(false);
						}
					}
					if (ok) {
						thisFrm.setVisible(false);
						int maTT = Integer.parseInt(txtMaTT.getText().toString());
						int maKH = Integer.parseInt(txtMaKH.getText().toString());
						//				labelMess.get(0)buttonSignIn;
						PhieuMuonBo.insertPhieuMuon(new PhieuMuon(0, null, null, false, maKH, maTT));
						pr.loadTableListPhieuMuon();
						new FormChinhSuaPhieuMuon(PhieuMuonBo.getLastestInsert(), pr).setVisible(true);
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		buttonSignIn.setIcon(new ImageIcon(FormSignIn.class.getResource("/icons/log-in.png")));
		buttonSignIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonSignIn.setFont(new Font("Segoe UI", Font.BOLD, 17));
		buttonSignIn.setBounds(129, 217, 175, 40);
		contentPane.add(buttonSignIn);
		
		lblErrorMaTT = new JLabel("");
		lblErrorMaTT.setBounds(190, 92, 194, 16);
		lblErrorMaTT.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		lblErrorMaTT.setVisible(false);
		lblErrorMaTT.setForeground(Color.RED);
		contentPane.add(lblErrorMaTT);
		
		lblErrorMaDocGia = new JLabel("");
		lblErrorMaDocGia.setBounds(192, 152, 194, 16);
		lblErrorMaDocGia.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		lblErrorMaDocGia.setVisible(false);
		lblErrorMaDocGia.setForeground(Color.RED);
		contentPane.add(lblErrorMaDocGia);
		createLabelError();
	}
	
	public void createLabelError() {
		lableError.add(lblErrorMaTT);
		lableError.add(lblErrorMaDocGia);
		for (int i = 0; i < 2; i++) {
			validateForm.add(new ValidateForm<String>());
			lableMess.add(null);
		}
		validateForm.get(0).addElement(new ValidateElement<String>("Vui lòng nhập mã thủ thư !", str -> Validate.isExist(str)));
		validateForm.get(0).addElement(new ValidateElement<String>("Mã thủ thư chỉ bao gồm các số từ [0..9]", str-> Validate.isNumber(str)));
		validateForm.get(0).addElement(new ValidateElement<String>("Mã thủ thư không tồn tại !", str -> Validate.isNumber(str) && ThuThuBo.getThuThuByMaThuThu(Integer.valueOf(str)) != null));
		validateForm.get(1).addElement(new ValidateElement<String>("Vui lòng nhập mã đọc giả !", str -> Validate.isExist(str)));
		validateForm.get(1).addElement(new ValidateElement<String>("Mã đọc giả chỉ bao gồm các số từ [0..9]", str-> Validate.isNumber(str)));
		validateForm.get(1).addElement(new ValidateElement<String>("Mã đọc giả không tồn tại !", str -> Validate.isNumber(str) && DocGiaBo.getDocGiaByMaDocGia(Integer.valueOf(str)) != null));
	}
}
