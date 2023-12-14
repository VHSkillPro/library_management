package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;

public class FormSignUp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputUsername;
	private JPasswordField inputPassword;
	private JTextField inputFullName;
	private JTextField inputEmail;
	private JTextField inputPhone;
	private JTextField inputAddress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormSignUp frame = new FormSignUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormSignUp() {
		createContents();
	}
	
	private void createContents() {
		setResizable(false);
		setTitle("Đăng ký độc giả - Phần mềm quản lý thư viện");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
				setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelSignUp = new JLabel("ĐĂNG KÝ ĐỘC GIẢ");
		labelSignUp.setFont(new Font("Segoe UI", Font.BOLD, 21));
		labelSignUp.setBounds(200, 15, 200, 50);
		contentPane.add(labelSignUp);
		
		JLabel labelUsername = new JLabel("Username");
		labelUsername.setIcon(new ImageIcon(FormSignUp.class.getResource("/icons/user.png")));
		labelUsername.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelUsername.setBounds(20, 95, 100, 16);
		contentPane.add(labelUsername);
		
		inputUsername = new JTextField();
		labelUsername.setLabelFor(inputUsername);
		inputUsername.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		inputUsername.setBounds(20, 115, 250, 25);
		contentPane.add(inputUsername);
		inputUsername.setColumns(10);
		
		JLabel labelPassword = new JLabel("Password");
		labelPassword.setIcon(new ImageIcon(FormSignUp.class.getResource("/icons/key.png")));
		labelPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelPassword.setBounds(20, 155, 100, 16);
		contentPane.add(labelPassword);
		
		inputPassword = new JPasswordField();
		labelPassword.setLabelFor(inputPassword);
		inputPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		inputPassword.setBounds(20, 175, 250, 25);
		contentPane.add(inputPassword);
		
		inputFullName = new JTextField();
		inputFullName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		inputFullName.setColumns(10);
		inputFullName.setBounds(20, 235, 250, 25);
		contentPane.add(inputFullName);
		
		JLabel labelFullName = new JLabel("Họ và tên");
		labelFullName.setIcon(new ImageIcon(FormSignUp.class.getResource("/icons/id-card.png")));
		labelFullName.setLabelFor(inputFullName);
		labelFullName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelFullName.setBounds(20, 215, 100, 16);
		contentPane.add(labelFullName);
		
		JComboBox<String> comboGender = new JComboBox<String>();
		comboGender.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		comboGender.setModel(new DefaultComboBoxModel<String>(new String[] {"Nam", "Nữ"}));
		comboGender.setBounds(485, 115, 75, 25);
		contentPane.add(comboGender);
		
		JLabel labelBirthday = new JLabel("Ngày sinh");
		labelBirthday.setIcon(new ImageIcon(FormSignUp.class.getResource("/icons/date-of-birth.png")));
		labelBirthday.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelBirthday.setBounds(310, 95, 100, 16);
		contentPane.add(labelBirthday);
		
		JFormattedTextField inputBirthday = new JFormattedTextField(new TimeFormatter());
		labelBirthday.setLabelFor(inputBirthday);
		inputBirthday.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		inputBirthday.setBounds(310, 115, 165, 25);
		contentPane.add(inputBirthday);
		
		JLabel labelGender = new JLabel("Giới tính");
		labelGender.setIcon(new ImageIcon(FormSignUp.class.getResource("/icons/gender.png")));
		labelGender.setLabelFor(comboGender);
		labelGender.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelGender.setBounds(485, 97, 75, 16);
		contentPane.add(labelGender);
		
		inputEmail = new JTextField();
		inputEmail.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		inputEmail.setColumns(10);
		inputEmail.setBounds(310, 175, 250, 25);
		contentPane.add(inputEmail);
		
		JLabel labelEmail = new JLabel("Email");
		labelEmail.setIcon(new ImageIcon(FormSignUp.class.getResource("/icons/mail.png")));
		labelEmail.setLabelFor(inputEmail);
		labelEmail.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelEmail.setBounds(310, 155, 100, 16);
		contentPane.add(labelEmail);
		
		JLabel labelPhone = new JLabel("Số điện thoại");
		labelPhone.setIcon(new ImageIcon(FormSignUp.class.getResource("/icons/phone-call.png")));
		labelPhone.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelPhone.setBounds(310, 215, 100, 16);
		contentPane.add(labelPhone);
		
		inputPhone = new JTextField();
		labelPhone.setLabelFor(inputPhone);
		inputPhone.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		inputPhone.setColumns(10);
		inputPhone.setBounds(310, 235, 250, 25);
		contentPane.add(inputPhone);
		
		inputAddress = new JTextField();
		inputAddress.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		inputAddress.setColumns(10);
		inputAddress.setBounds(310, 295, 250, 25);
		contentPane.add(inputAddress);
		
		JLabel labelAddress = new JLabel("Địa chỉ");
		labelAddress.setIcon(new ImageIcon(FormSignUp.class.getResource("/icons/pin.png")));
		labelAddress.setLabelFor(inputAddress);
		labelAddress.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelAddress.setBounds(310, 275, 100, 16);
		contentPane.add(labelAddress);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 75, 540, 2);
		contentPane.add(separator);
		
		JButton buttonSignUp = new JButton("Đăng ký");
		buttonSignUp.setIcon(new ImageIcon(FormSignUp.class.getResource("/icons/sign-up.png")));
		buttonSignUp.setFont(new Font("Segoe UI", Font.BOLD, 17));
		buttonSignUp.setBounds(220, 340, 150, 40);
		contentPane.add(buttonSignUp);
	}
}

class TimeFormatter extends MaskFormatter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TimeFormatter() {
		try {
		    setMask("##/##/####");
		    setPlaceholderCharacter('0');
		    setAllowsInvalid(false);
		    setOverwriteMode(true);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
	}
	  
	@Override
	public Object stringToValue(String string) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (string == null) {
			string = "00/00/0000";
		}
		return df.parse(string);
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (value == null) {
			value = new Date();
		}
		return df.format((Date) value);
	}
}
