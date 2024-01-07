package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import bean.PhieuMuon;
import bo.PhieuMuonBo;
import bo.ThuThuBo;
import utils.Validate;
import utils.ValidateElement;
import utils.ValidateForm;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import bo.DocGiaBo;
import javax.swing.SwingConstants;

public class FormTaoPhieuMuon extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FormTaoPhieuMuon thisFrm = this;
	private JPanel contentPane;
	private PanelPhieuMuon parent;
	private ArrayList<String> lableMess = new ArrayList<String>();
	private ArrayList<JLabel> lableError = new ArrayList<JLabel> ();
	private ArrayList<ValidateForm<String>> validateForm = new ArrayList<ValidateForm<String>>();
	
	JLabel lblErrorMaTT;
	JLabel lblErrorMaDocGia;

	/**
	 * Create the frame.
	 */
	
	public FormTaoPhieuMuon(PanelPhieuMuon pr) {
		this.parent = pr;
		setTitle("Tạo hóa đơn - Phần mềm quản lý thư viện");
		setFont(new Font("Segoe UI", Font.PLAIN, 12));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbTaoHD = new JLabel("Tạo hóa đơn");
		lbTaoHD.setHorizontalAlignment(SwingConstants.CENTER);
		lbTaoHD.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lbTaoHD.setBounds(105, 15, 140, 30);
		contentPane.add(lbTaoHD);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(160, 160, 160));
		separator.setBounds(20, 60, 300, 2);
		contentPane.add(separator);
		
		JLabel lbMaTT = new JLabel("Mã thủ thư");
		lbMaTT.setLabelFor(lbMaTT);
		lbMaTT.setIcon(new ImageIcon(FormSignIn.class.getResource("/icons/user.png")));
		lbMaTT.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lbMaTT.setBounds(20, 80, 100, 16);
		contentPane.add(lbMaTT);
		
		JTextField txtMaTT = new JTextField();
		txtMaTT.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtMaTT.setBounds(20, 100, 300, 25);
		contentPane.add(txtMaTT);
		txtMaTT.setColumns(10);
		
		JLabel lbMaKH = new JLabel("Mã đọc giả");
		lbMaKH.setIcon(new ImageIcon(FormSignIn.class.getResource("/icons/user.png")));
		lbMaKH.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lbMaKH.setBounds(20, 140, 100, 16);
		contentPane.add(lbMaKH);
		
		
		JTextField txtMaKH = new JTextField();
		lbMaKH.setLabelFor(txtMaKH);
		txtMaKH.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtMaKH.setBounds(20, 160, 300, 25);
		contentPane.add(txtMaKH);
	
		JButton buttonSignIn = new JButton("Thêm");
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
						parent.loadTableListPhieuMuon(PhieuMuonBo.getAllPhieuMuon());
						new FormChinhSuaPhieuMuon(PhieuMuonBo.getLastestInsert(), pr).setVisible(true);
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		buttonSignIn.setIcon(new ImageIcon(FormTaoPhieuMuon.class.getResource("/icons/plus-symbol-button.png")));
		buttonSignIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonSignIn.setFont(new Font("Segoe UI", Font.BOLD, 16));
		buttonSignIn.setBounds(120, 200, 110, 35);
		contentPane.add(buttonSignIn);
		
		lblErrorMaTT = new JLabel("");
		lblErrorMaTT.setHorizontalAlignment(SwingConstants.RIGHT);
		lblErrorMaTT.setBounds(120, 80, 200, 16);
		lblErrorMaTT.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblErrorMaTT.setVisible(false);
		lblErrorMaTT.setForeground(Color.RED);
		contentPane.add(lblErrorMaTT);
		
		lblErrorMaDocGia = new JLabel("");
		lblErrorMaDocGia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblErrorMaDocGia.setBounds(120, 140, 200, 16);
		lblErrorMaDocGia.setFont(new Font("Segoe UI", Font.PLAIN, 11));
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
		
		validateForm.get(0).addElement(new ValidateElement<String>("Vui lòng nhập mã thủ thư", str -> Validate.isExist(str)));
		validateForm.get(0).addElement(new ValidateElement<String>("Mã thủ thư chỉ gồm các số từ [0..9]", str-> Validate.isNumber(str)));
		validateForm.get(0).addElement(new ValidateElement<String>("Mã thủ thư không tồn tại", str -> Validate.isNumber(str) && ThuThuBo.getThuThuByMaThuThu(Integer.valueOf(str)) != null));
		validateForm.get(1).addElement(new ValidateElement<String>("Vui lòng nhập mã đọc giả", str -> Validate.isExist(str)));
		validateForm.get(1).addElement(new ValidateElement<String>("Mã đọc giả chỉ gồm các số từ [0..9]", str-> Validate.isNumber(str)));
		validateForm.get(1).addElement(new ValidateElement<String>("Mã đọc giả không tồn tại", str -> Validate.isNumber(str) && DocGiaBo.getDocGiaByMaDocGia(Integer.valueOf(str)) != null));
	}
}
