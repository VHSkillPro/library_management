package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.DocGia;
import bo.DocGiaBo;
import utils.Validate;
import utils.ValidateElement;
import utils.ValidateForm;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSeparator;
import java.awt.Color;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class FormEditDocGia extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField inputHoTen;
	private JTextField inputEmail;
	private JTextField inputSoDienThoai;
	private JTextField inputDiaChi;
	private JComboBox<String> comboGioiTinh;
	private JDateChooser dateChooserNgaySinh;
	private JButton buttonEditEnable;
	private JButton buttonExist;
	
	private DocGia docGia;
	private boolean enableEditor = false;
	
	private PanelDocGia parent;
	private FormEditDocGia thisForm = this;

	private JLabel labelErrorFullname;
	private JLabel labelErrorPhone;
	private JLabel labelErrorEmail;
	private JLabel labelErrorAddress;
	
	/**
	 * Create the frame.
	 */
	public FormEditDocGia(int maDocGia, PanelDocGia parent) {
		setResizable(false);
		this.parent = parent;
		this.docGia = DocGiaBo.getDocGiaByMaDocGia(maDocGia);
		createContents();
		changeModeEditor();
	}
	
	public void changeModeEditor() {
		buttonEditEnable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (enableEditor == false) {
					enableEditor = true;
					inputHoTen.setEditable(true);
					comboGioiTinh.setEnabled(true);
					dateChooserNgaySinh.setEnabled(true);
					inputEmail.setEditable(true);
					inputSoDienThoai.setEditable(true);
					inputDiaChi.setEditable(true);
					buttonExist.setEnabled(false);
					buttonEditEnable.setText("Lưu");
				}
				else {
					boolean haveError = false;
					labelErrorFullname.setText("");
					labelErrorPhone.setText("");
					labelErrorEmail.setText("");
					labelErrorAddress.setText("");
					
					String hoTen = inputHoTen.getText();
					boolean gioiTinh = comboGioiTinh.getSelectedIndex() == 0 ? true : false;
					Date ngaySinh = dateChooserNgaySinh.getDate();
					String soDienThoai = inputSoDienThoai.getText();
					String email = inputEmail.getText();
					String diaChi = inputDiaChi.getText();
					
					ValidateForm<String> validateFullname = new ValidateForm<String>();
					validateFullname.addElement(new ValidateElement<String>("Vui lòng nhập trường này", str -> Validate.isExist(str)));
					String messageErrorFullname = validateFullname.validate(hoTen);
					if (messageErrorFullname != null) {
						haveError = true;
						labelErrorFullname.setText(messageErrorFullname);
					}
					
					ValidateForm<String> validatePhone = new ValidateForm<String>();
					validatePhone.addElement(new ValidateElement<String>("Vui lòng nhập trường này", str -> Validate.isExist(str)));
					validatePhone.addElement(new ValidateElement<String>("Số điện thoại không hợp lệ", str -> Validate.isNumberPhone(str)));
					String messageErrorPhone = validatePhone.validate(soDienThoai);
					if (messageErrorPhone != null) {
						haveError = true;
						labelErrorPhone.setText(messageErrorPhone);
					}
					
					ValidateForm<String> validateEmail = new ValidateForm<String>();
					validateEmail.addElement(new ValidateElement<String>("Vui lòng nhập trường này", str -> Validate.isExist(str)));
					validateEmail.addElement(new ValidateElement<String>("Email không hợp lệ", str -> Validate.isEmail(str)));
					String messageErrorEmail = validateEmail.validate(email);
					if (messageErrorEmail != null) {
						haveError = true;
						labelErrorEmail.setText(messageErrorEmail);
					}
					
					ValidateForm<String> validateAddress = new ValidateForm<String>();
					validateAddress.addElement(new ValidateElement<String>("Vui lòng nhập trường này", str -> Validate.isExist(str)));
					String messageErrorAddress = validateAddress.validate(diaChi);
					if (messageErrorAddress != null) {
						haveError = true;
						labelErrorAddress.setText(messageErrorAddress);
					}
					
					if (!haveError) {
						int decision = JOptionPane.showConfirmDialog(null, "Bạn có muốn lưu ?");
						if (decision == 0) {
							if (DocGiaBo.updateDocGia(docGia.getMaDocGia(), hoTen, gioiTinh, ngaySinh, email, soDienThoai, diaChi)) {
								JOptionPane.showMessageDialog(null, "Lưu thành công");
								parent.loadTable(DocGiaBo.getAllDocGia());
							}
							else {
								JOptionPane.showMessageDialog(null, "Lưu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
							}
						}
						
						if (decision != 2) {
							enableEditor = false;
							inputHoTen.setEditable(false);
							comboGioiTinh.setEnabled(false);
							dateChooserNgaySinh.setEnabled(false);
							inputEmail.setEditable(false);
							inputSoDienThoai.setEditable(false);
							inputDiaChi.setEditable(false);
							buttonExist.setEnabled(true);
							buttonEditEnable.setText("Chỉnh sửa");
						}
						
						docGia = DocGiaBo.getDocGiaByMaDocGia(docGia.getMaDocGia());
						inputHoTen.setText(docGia.getHoTen());
						comboGioiTinh.setSelectedIndex(docGia.getGioiTinh() ? 0 : 1);
						dateChooserNgaySinh.setDate(docGia.getNgaySinh());
						inputEmail.setText(docGia.getEmail());
						inputSoDienThoai.setText(docGia.getSoDienThoai());
						inputDiaChi.setText(docGia.getDiaChi());
					}
				}
			}
		});
	}
	
	private void createContents() {
		setTitle("Thông tin độc giả - Phần mềm quản lý thư viện");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 500);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelTitle = new JLabel("Thông tin độc giả");
		labelTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
		labelTitle.setBounds(110, 15, 180, 30);
		contentPane.add(labelTitle);
		
		JLabel labelMaDocGia = new JLabel("Mã độc giả:");
		labelMaDocGia.setIcon(new ImageIcon(FormEditDocGia.class.getResource("/icons/user.png")));
		labelMaDocGia.setHorizontalAlignment(SwingConstants.LEFT);
		labelMaDocGia.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelMaDocGia.setBounds(20, 80, 100, 16);
		contentPane.add(labelMaDocGia);
		
		JLabel labelMaDocGiaValue = new JLabel(String.valueOf(docGia.getMaDocGia()));
		labelMaDocGiaValue.setHorizontalAlignment(SwingConstants.LEFT);
		labelMaDocGiaValue.setFont(new Font("Segoe UI", Font.BOLD, 14));
		labelMaDocGiaValue.setBounds(120, 80, 250, 16);
		contentPane.add(labelMaDocGiaValue);
		
		JLabel labelFullname = new JLabel("Họ và tên");
		labelFullname.setIcon(new ImageIcon(FormEditDocGia.class.getResource("/icons/id-card.png")));
		labelFullname.setHorizontalAlignment(SwingConstants.LEFT);
		labelFullname.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelFullname.setBounds(20, 110, 100, 16);
		contentPane.add(labelFullname);
		
		inputHoTen = new JTextField();
		inputHoTen.setEditable(false);
		inputHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		inputHoTen.setBounds(20, 130, 350, 25);
		inputHoTen.setText(docGia.getHoTen());
		contentPane.add(inputHoTen);
		inputHoTen.setColumns(10);
		
		JLabel labelGender = new JLabel("Giới tính");
		labelGender.setIcon(new ImageIcon(FormEditDocGia.class.getResource("/icons/gender.png")));
		labelGender.setHorizontalAlignment(SwingConstants.LEFT);
		labelGender.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelGender.setBounds(290, 170, 80, 16);
		contentPane.add(labelGender);
		
		comboGioiTinh = new JComboBox<String>();
		comboGioiTinh.setEnabled(false);
		comboGioiTinh.setModel(new DefaultComboBoxModel<String>(new String[] {"Nam", "Nữ"}));
		comboGioiTinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboGioiTinh.setBounds(290, 190, 80, 25);
		comboGioiTinh.setSelectedIndex(docGia.getGioiTinh() ? 0 : 1);
		contentPane.add(comboGioiTinh);
		
		JLabel labelBirthdate = new JLabel("Ngày sinh");
		labelBirthdate.setIcon(new ImageIcon(FormEditDocGia.class.getResource("/icons/date-of-birth.png")));
		labelBirthdate.setHorizontalAlignment(SwingConstants.LEFT);
		labelBirthdate.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelBirthdate.setBounds(20, 170, 100, 16);
		contentPane.add(labelBirthdate);
		
		dateChooserNgaySinh = new JDateChooser(docGia.getNgaySinh(), "dd/MM/yyyy");
		dateChooserNgaySinh.setEnabled(false);
		dateChooserNgaySinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		dateChooserNgaySinh.setBounds(20, 190, 260, 25);
		contentPane.add(dateChooserNgaySinh);
		
		JLabel labelEmail = new JLabel("Email");
		labelEmail.setIcon(new ImageIcon(FormEditDocGia.class.getResource("/icons/mail.png")));
		labelEmail.setHorizontalAlignment(SwingConstants.LEFT);
		labelEmail.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelEmail.setBounds(20, 290, 100, 16);
		contentPane.add(labelEmail);
		
		inputEmail = new JTextField();
		inputEmail.setEditable(false);
		inputEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		inputEmail.setColumns(10);
		inputEmail.setBounds(20, 310, 350, 25);
		inputEmail.setText(docGia.getEmail());
		contentPane.add(inputEmail);
		
		JLabel labelSoDienThoai = new JLabel("Số điện thoại");
		labelSoDienThoai.setIcon(new ImageIcon(FormEditDocGia.class.getResource("/icons/phone-call.png")));
		labelSoDienThoai.setHorizontalAlignment(SwingConstants.LEFT);
		labelSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelSoDienThoai.setBounds(20, 230, 100, 16);
		contentPane.add(labelSoDienThoai);
		
		inputSoDienThoai = new JTextField();
		inputSoDienThoai.setEditable(false);
		inputSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		inputSoDienThoai.setColumns(10);
		inputSoDienThoai.setBounds(20, 250, 350, 25);
		inputSoDienThoai.setText(docGia.getSoDienThoai());
		contentPane.add(inputSoDienThoai);
		
		JLabel labelDiaChi = new JLabel("Địa chỉ");
		labelDiaChi.setIcon(new ImageIcon(FormEditDocGia.class.getResource("/icons/pin.png")));
		labelDiaChi.setHorizontalAlignment(SwingConstants.LEFT);
		labelDiaChi.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelDiaChi.setBounds(20, 350, 100, 16);
		contentPane.add(labelDiaChi);
		
		inputDiaChi = new JTextField();
		inputDiaChi.setEditable(false);
		inputDiaChi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		inputDiaChi.setColumns(10);
		inputDiaChi.setBounds(20, 370, 350, 25);
		inputDiaChi.setText(docGia.getDiaChi());
		contentPane.add(inputDiaChi);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(160, 160, 160));
		separator.setBackground(new Color(255, 255, 255));
		separator.setBounds(20, 60, 350, 2);
		contentPane.add(separator);
		
		buttonEditEnable = new JButton("Chỉnh sửa");
		buttonEditEnable.setFont(new Font("Segoe UI", Font.BOLD, 14));
		buttonEditEnable.setBounds(190, 410, 120, 30);
		contentPane.add(buttonEditEnable);
		
		buttonExist = new JButton("Quay lại");
		buttonExist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisForm.dispatchEvent(new WindowEvent(thisForm, WindowEvent.WINDOW_CLOSING));
			}
		});
		buttonExist.setFont(new Font("Segoe UI", Font.BOLD, 14));
		buttonExist.setBounds(80, 410, 100, 30);
		contentPane.add(buttonExist);
		
		labelErrorFullname = new JLabel("");
		labelErrorFullname.setForeground(Color.RED);
		labelErrorFullname.setHorizontalAlignment(SwingConstants.RIGHT);
		labelErrorFullname.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		labelErrorFullname.setBounds(120, 110, 250, 16);
		contentPane.add(labelErrorFullname);
		
		labelErrorPhone = new JLabel("");
		labelErrorPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		labelErrorPhone.setForeground(Color.RED);
		labelErrorPhone.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		labelErrorPhone.setBounds(120, 230, 250, 16);
		contentPane.add(labelErrorPhone);
		
		labelErrorEmail = new JLabel("");
		labelErrorEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		labelErrorEmail.setForeground(Color.RED);
		labelErrorEmail.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		labelErrorEmail.setBounds(120, 290, 250, 16);
		contentPane.add(labelErrorEmail);
		
		labelErrorAddress = new JLabel("");
		labelErrorAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		labelErrorAddress.setForeground(Color.RED);
		labelErrorAddress.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		labelErrorAddress.setBounds(120, 350, 250, 16);
		contentPane.add(labelErrorAddress);
	}
}
