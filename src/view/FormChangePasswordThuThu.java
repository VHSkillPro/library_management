package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.Account;
import bo.AccountBo;
import utils.SHA256;
import utils.Validate;
import utils.ValidateElement;
import utils.ValidateForm;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import java.awt.Color;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class FormChangePasswordThuThu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField inputPassword;
	private JPasswordField inputRepassword;
	private JButton buttonChangePassword;
	private JLabel labelErrorPassword;
	private JLabel labelErrorRepassword;
	
	private Account account;
	
	private FormChangePasswordThuThu thisForm = this;
	
	/**
	 * Create the frame.
	 */
	public FormChangePasswordThuThu(String username) {
		account = AccountBo.getAccountByUsername(username);
		createContents();
		eventChangePassword();
	}
	
	public void eventChangePassword() {
		buttonChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelErrorPassword.setText("");
				labelErrorRepassword.setText("");
				
				ValidateForm<String> validatePassword = new ValidateForm<String>();
				validatePassword.addElement(new ValidateElement<String>("Vui lòng nhập trường này", str -> Validate.isExist(str)));
				validatePassword.addElement(new ValidateElement<String>("Mật khẩu phải dài hơn 6 ký tự", str -> str.length() >= 6));
			
				boolean haveError = false;
				String password = String.valueOf(inputPassword.getPassword());
				String repassword = String.valueOf(inputRepassword.getPassword());
				
				if (!password.equals(repassword)) {
					haveError = true;
					labelErrorRepassword.setText("Mật khẩu không trùng khớp");
				}
				
				String messageErrorPassword = validatePassword.validate(password);
				if (messageErrorPassword != null) {
					haveError = true;
					labelErrorPassword.setText(messageErrorPassword);
				}
				
				String messageErrorRepassword = validatePassword.validate(repassword);
				if (messageErrorRepassword != null) {
					haveError = true;
					labelErrorRepassword.setText(messageErrorRepassword);
				}
				
				if (!haveError) {
					if (AccountBo.updatePassword(account.getUsername(), SHA256.getString(password))) {
						JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						thisForm.dispatchEvent(new WindowEvent(thisForm, WindowEvent.WINDOW_CLOSING));
					}
					else {
						JOptionPane.showMessageDialog(null, "Đổi mật khẩu thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
	
	private void createContents() {
		setResizable(false);
		setTitle("Đổi mật khẩu - Phần mềm quản lý thư viện");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbliMtKhu = new JLabel("Đổi mật khẩu");
		lbliMtKhu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lbliMtKhu.setBounds(90, 15, 140, 30);
		contentPane.add(lbliMtKhu);
		
		JLabel labelUsername = new JLabel("Username:");
		labelUsername.setIcon(new ImageIcon(FormChangePasswordThuThu.class.getResource("/icons/user.png")));
		labelUsername.setHorizontalAlignment(SwingConstants.LEFT);
		labelUsername.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelUsername.setBounds(20, 80, 100, 25);
		contentPane.add(labelUsername);
		
		JLabel labelUsernameValue = new JLabel(account.getUsername());
		labelUsernameValue.setHorizontalAlignment(SwingConstants.LEFT);
		labelUsernameValue.setFont(new Font("Segoe UI", Font.BOLD, 14));
		labelUsernameValue.setBounds(120, 80, 170, 25);
		contentPane.add(labelUsernameValue);
		
		JLabel labelPassword = new JLabel("Password");
		labelPassword.setIcon(new ImageIcon(FormChangePasswordThuThu.class.getResource("/icons/key.png")));
		labelPassword.setHorizontalAlignment(SwingConstants.LEFT);
		labelPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelPassword.setBounds(20, 120, 100, 16);
		contentPane.add(labelPassword);
		
		inputPassword = new JPasswordField();
		inputPassword.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		inputPassword.setBounds(20, 140, 270, 25);
		contentPane.add(inputPassword);
		
		JLabel labelRepassword = new JLabel("Repassword");
		labelRepassword.setIcon(new ImageIcon(FormChangePasswordThuThu.class.getResource("/icons/key.png")));
		labelRepassword.setHorizontalAlignment(SwingConstants.LEFT);
		labelRepassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelRepassword.setBounds(20, 180, 100, 16);
		contentPane.add(labelRepassword);
		
		inputRepassword = new JPasswordField();
		inputRepassword.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		inputRepassword.setBounds(20, 200, 270, 25);
		contentPane.add(inputRepassword);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(160, 160, 160));
		separator.setBounds(20, 56, 270, 6);
		contentPane.add(separator);
		
		buttonChangePassword = new JButton("Đổi mật khẩu");
		buttonChangePassword.setFont(new Font("Segoe UI", Font.BOLD, 14));
		buttonChangePassword.setBounds(90, 240, 130, 30);
		contentPane.add(buttonChangePassword);
		
		labelErrorPassword = new JLabel("");
		labelErrorPassword.setForeground(Color.RED);
		labelErrorPassword.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		labelErrorPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		labelErrorPassword.setBounds(120, 120, 170, 16);
		contentPane.add(labelErrorPassword);
		
		labelErrorRepassword = new JLabel("");
		labelErrorRepassword.setHorizontalAlignment(SwingConstants.RIGHT);
		labelErrorRepassword.setForeground(Color.RED);
		labelErrorRepassword.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		labelErrorRepassword.setBounds(120, 180, 170, 16);
		contentPane.add(labelErrorRepassword);
	}
}
