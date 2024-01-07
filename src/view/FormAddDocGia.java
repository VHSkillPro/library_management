package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.Account;
import bean.DocGia;
import bo.AccountBo;
import bo.DocGiaBo;
import utils.SHA256;
import utils.Validate;
import utils.ValidateElement;
import utils.ValidateForm;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.SwingConstants;

public class FormAddDocGia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputUsername;
	private JPasswordField inputPassword;
	private JTextField inputFullName;
	private JTextField inputEmail;
	private JTextField inputPhone;
	private JTextField inputAddress;
	private JButton buttonSignUp;
	private JComboBox<String> comboGender;
	private JPasswordField inputRepassword;
	private JLabel labelErrorUsername;
	private JLabel labelErrorPassword;
	private JLabel labelErrorRepassword;
	private JLabel labelErrorFullname;
	private JLabel labelErrorEmail;
	private JLabel labelErrorPhone;
	private JLabel labelErrorAddress;

	private ArrayList<JLabel> labelErrors = new ArrayList<JLabel>();
	private ArrayList<String> labelMessage = new ArrayList<String>();
	private ArrayList<ValidateForm<String>> validateForm = new ArrayList<ValidateForm<String>>();
	
	private PanelDocGia parent;
	private FormAddDocGia thisForm = this;
	private JDateChooser dateChooserBirthday;
	private JLabel labelErrorBirthday;
	
	/**
	 * Create the frame.
	 */
	public FormAddDocGia(PanelDocGia parent) {
		this.parent = parent;
		closeForm();
		createContents();
		signUp();
		createValidateForm();
	}
	
	public void closeForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				parent.setVisible(true);
			}
		});
	}
	
	public void createValidateForm() {
		labelErrors.add(labelErrorUsername);
		labelErrors.add(labelErrorPassword);
		labelErrors.add(labelErrorRepassword);
		labelErrors.add(labelErrorFullname);
		labelErrors.add(labelErrorEmail);
		labelErrors.add(labelErrorPhone);
		labelErrors.add(labelErrorAddress);
		
		for (int i = 0; i < 7; i++) {
			labelMessage.add(null);
			validateForm.add(new ValidateForm<String>());
		}
		
		// Username
		validateForm.get(0).addElement(new ValidateElement<String>("Vui lòng nhập username", str -> Validate.isExist(str)));
		validateForm.get(0).addElement(new ValidateElement<String>("Username phải dài hơn 5 ký tự", str -> str.length() >= 5));
		validateForm.get(0).addElement(new ValidateElement<String>("Username đã tồn tại", str -> AccountBo.getAccountByUsername(str) == null));
		
		// Password
		validateForm.get(1).addElement(new ValidateElement<String>("Vui lòng nhập mật khẩu", str -> Validate.isExist(str)));
		validateForm.get(1).addElement(new ValidateElement<String>("Mật khẩu phải dài hơn 6 ký tự", str -> str.length() >= 6));
		
		// Repassword
		validateForm.get(2).addElement(new ValidateElement<String>("Vui lòng nhập lại mật khẩu", str -> Validate.isExist(str)));
		
		// Fullname
		validateForm.get(3).addElement(new ValidateElement<String>("Vui lòng nhập họ tên", str -> Validate.isExist(str)));
		
		// Email
		validateForm.get(4).addElement(new ValidateElement<String>("Vui lòng nhập email", str -> Validate.isExist(str)));
		validateForm.get(4).addElement(new ValidateElement<String>("Email không hợp lệ", str -> Validate.isEmail(str)));
		
		// Phone
		validateForm.get(5).addElement(new ValidateElement<String>("Vui lòng nhập SĐT", str -> Validate.isExist(str)));
		validateForm.get(5).addElement(new ValidateElement<String>("SĐT không hợp lệ", str -> Validate.isNumberPhone(str)));
		
		// Address
		validateForm.get(6).addElement(new ValidateElement<String>("Vui lòng nhập địa chỉ", str -> Validate.isExist(str)));
	}
	
	public void signUp() {
		buttonSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String username = inputUsername.getText().strip();
					String password = String.valueOf(inputPassword.getPassword()).strip();
					String repassword = String.valueOf(inputRepassword.getPassword()).strip();
					String hoTen = inputFullName.getText().strip();
					boolean gioiTinh = String.valueOf(comboGender.getSelectedItem()).equals("Nam") ? true : false;
					Date ngaySinh = dateChooserBirthday.getDate();
					String email = inputEmail.getText().strip();
					String soDienThoai = inputPhone.getText().strip();
					String diaChi = inputAddress.getText().strip();
					
					labelMessage.set(0, validateForm.get(0).validate(username));
					labelMessage.set(1, validateForm.get(1).validate(password));
					labelMessage.set(2, validateForm.get(2).validate(repassword));
					labelMessage.set(3, validateForm.get(3).validate(hoTen));
					labelMessage.set(4, validateForm.get(4).validate(email));
					labelMessage.set(5, validateForm.get(5).validate(soDienThoai));
					labelMessage.set(6, validateForm.get(6).validate(diaChi));
					
					if (!password.equals(repassword)) {
						labelMessage.set(2, "Mật khẩu không trùng khớp");
					}
					
					boolean haveError = false;
					for (int i = 0; i < 7; i++) {
						if (labelMessage.get(i) != null) {
							haveError = true;
							labelErrors.get(i).setText(labelMessage.get(i));
							labelErrors.get(i).setVisible(true);
						}
						else {
							labelErrors.get(i).setVisible(false);
						}
					}
					
					if (ngaySinh == null) {
						haveError = true;
						labelErrorBirthday.setText("Nhập trường này");
					}
					else {
						labelErrorBirthday.setText("");
					}
					
					if (!haveError) {
						AccountBo.insertAccount(new Account(username, SHA256.getString(password), 0, new Date()));
						DocGiaBo.insertDocGia(new DocGia(0, hoTen, gioiTinh, ngaySinh, email, soDienThoai, diaChi, username));
						JOptionPane.showMessageDialog(null, "Đăng ký độc giả thành công");
						parent.loadTable(DocGiaBo.getAllDocGia());
						thisForm.dispatchEvent(new WindowEvent(thisForm, WindowEvent.WINDOW_CLOSING));
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void createContents() {
		setResizable(false);
		setTitle("Thêm độc giả - Phần mềm quản lý thư viện");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelSignUp = new JLabel("THÊM ĐỘC GIẢ");
		labelSignUp.setFont(new Font("Segoe UI", Font.BOLD, 21));
		labelSignUp.setBounds(220, 15, 160, 40);
		contentPane.add(labelSignUp);
		
		JLabel labelUsername = new JLabel("Username");
		labelUsername.setIcon(new ImageIcon(FormAddDocGia.class.getResource("/icons/user.png")));
		labelUsername.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelUsername.setBounds(20, 90, 100, 16);
		contentPane.add(labelUsername);
		
		inputUsername = new JTextField();
		labelUsername.setLabelFor(inputUsername);
		inputUsername.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		inputUsername.setBounds(20, 110, 250, 25);
		contentPane.add(inputUsername);
		inputUsername.setColumns(10);
		
		JLabel labelPassword = new JLabel("Password");
		labelPassword.setIcon(new ImageIcon(FormAddDocGia.class.getResource("/icons/key.png")));
		labelPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelPassword.setBounds(20, 150, 100, 16);
		contentPane.add(labelPassword);
		
		inputPassword = new JPasswordField();
		labelPassword.setLabelFor(inputPassword);
		inputPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		inputPassword.setBounds(20, 170, 250, 25);
		contentPane.add(inputPassword);
		
		inputFullName = new JTextField();
		inputFullName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		inputFullName.setColumns(10);
		inputFullName.setBounds(20, 290, 250, 25);
		contentPane.add(inputFullName);
		
		JLabel labelFullName = new JLabel("Họ và tên");
		labelFullName.setIcon(new ImageIcon(FormAddDocGia.class.getResource("/icons/id-card.png")));
		labelFullName.setLabelFor(inputFullName);
		labelFullName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelFullName.setBounds(20, 270, 100, 16);
		contentPane.add(labelFullName);
		
		comboGender = new JComboBox<String>();
		comboGender.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		comboGender.setModel(new DefaultComboBoxModel<String>(new String[] {"Nam", "Nữ"}));
		comboGender.setBounds(485, 110, 75, 25);
		contentPane.add(comboGender);
		
		JLabel labelBirthday = new JLabel("Ngày sinh");
		labelBirthday.setIcon(new ImageIcon(FormAddDocGia.class.getResource("/icons/date-of-birth.png")));
		labelBirthday.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelBirthday.setBounds(310, 90, 100, 16);
		contentPane.add(labelBirthday);
		
		JLabel labelGender = new JLabel("Giới tính");
		labelGender.setIcon(new ImageIcon(FormAddDocGia.class.getResource("/icons/gender.png")));
		labelGender.setLabelFor(comboGender);
		labelGender.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelGender.setBounds(485, 90, 75, 16);
		contentPane.add(labelGender);
		
		inputEmail = new JTextField();
		inputEmail.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		inputEmail.setColumns(10);
		inputEmail.setBounds(310, 170, 250, 25);
		contentPane.add(inputEmail);
		
		JLabel labelEmail = new JLabel("Email");
		labelEmail.setIcon(new ImageIcon(FormAddDocGia.class.getResource("/icons/mail.png")));
		labelEmail.setLabelFor(inputEmail);
		labelEmail.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelEmail.setBounds(310, 150, 100, 16);
		contentPane.add(labelEmail);
		
		JLabel labelPhone = new JLabel("Số điện thoại");
		labelPhone.setIcon(new ImageIcon(FormAddDocGia.class.getResource("/icons/phone-call.png")));
		labelPhone.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelPhone.setBounds(310, 210, 110, 16);
		contentPane.add(labelPhone);
		
		inputPhone = new JTextField();
		labelPhone.setLabelFor(inputPhone);
		inputPhone.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		inputPhone.setColumns(10);
		inputPhone.setBounds(310, 230, 250, 25);
		contentPane.add(inputPhone);
		
		inputAddress = new JTextField();
		inputAddress.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		inputAddress.setColumns(10);
		inputAddress.setBounds(310, 290, 250, 25);
		contentPane.add(inputAddress);
		
		JLabel labelAddress = new JLabel("Địa chỉ");
		labelAddress.setIcon(new ImageIcon(FormAddDocGia.class.getResource("/icons/pin.png")));
		labelAddress.setLabelFor(inputAddress);
		labelAddress.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelAddress.setBounds(310, 270, 100, 16);
		contentPane.add(labelAddress);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(160, 160, 160));
		separator.setBounds(20, 70, 540, 2);
		contentPane.add(separator);
		
		buttonSignUp = new JButton("Thêm");
		buttonSignUp.setIcon(new ImageIcon(FormAddDocGia.class.getResource("/icons/sign-up.png")));
		buttonSignUp.setFont(new Font("Segoe UI", Font.BOLD, 17));
		buttonSignUp.setBounds(220, 335, 150, 40);
		contentPane.add(buttonSignUp);
		
		labelErrorUsername = new JLabel("");
		labelErrorUsername.setVisible(false);
		labelErrorUsername.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		labelErrorUsername.setForeground(Color.RED);
		labelErrorUsername.setBounds(120, 90, 150, 16);
		contentPane.add(labelErrorUsername);
		
		labelErrorEmail = new JLabel("");
		labelErrorEmail.setVisible(false);
		labelErrorEmail.setForeground(Color.RED);
		labelErrorEmail.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		labelErrorEmail.setBounds(410, 150, 150, 16);
		contentPane.add(labelErrorEmail);
		
		labelErrorPhone = new JLabel("");
		labelErrorPhone.setVisible(false);
		labelErrorPhone.setForeground(Color.RED);
		labelErrorPhone.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		labelErrorPhone.setBounds(420, 210, 140, 16);
		contentPane.add(labelErrorPhone);
		
		inputRepassword = new JPasswordField();
		inputRepassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		inputRepassword.setBounds(20, 230, 250, 25);
		contentPane.add(inputRepassword);
		
		JLabel labelRepassword = new JLabel("Re-Password");
		labelRepassword.setLabelFor(labelRepassword);
		labelRepassword.setIcon(new ImageIcon(FormAddDocGia.class.getResource("/icons/key.png")));
		labelRepassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelRepassword.setBounds(20, 210, 100, 16);
		contentPane.add(labelRepassword);
		
		labelErrorPassword = new JLabel("");
		labelErrorPassword.setForeground(Color.RED);
		labelErrorPassword.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		labelErrorPassword.setBounds(120, 150, 150, 16);
		contentPane.add(labelErrorPassword);
		
		labelErrorRepassword = new JLabel("");
		labelErrorRepassword.setForeground(Color.RED);
		labelErrorRepassword.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		labelErrorRepassword.setBounds(120, 210, 150, 16);
		contentPane.add(labelErrorRepassword);
		
		labelErrorFullname = new JLabel("");
		labelErrorFullname.setForeground(Color.RED);
		labelErrorFullname.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		labelErrorFullname.setBounds(120, 270, 150, 16);
		contentPane.add(labelErrorFullname);
		
		labelErrorAddress = new JLabel("");
		labelErrorAddress.setForeground(Color.RED);
		labelErrorAddress.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		labelErrorAddress.setBounds(410, 270, 150, 16);
		contentPane.add(labelErrorAddress);
		
		dateChooserBirthday = new JDateChooser();
		dateChooserBirthday.setDateFormatString("dd/MM/yyyy");
		dateChooserBirthday.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		dateChooserBirthday.setBounds(310, 110, 160, 25);
		contentPane.add(dateChooserBirthday);
		
		labelErrorBirthday = new JLabel("");
		labelErrorBirthday.setForeground(Color.RED);
		labelErrorBirthday.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		labelErrorBirthday.setBounds(391, 90, 84, 16);
		contentPane.add(labelErrorBirthday);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(160, 160, 160));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(290, 70, 2, 245);
		contentPane.add(separator_1);
	}
}
