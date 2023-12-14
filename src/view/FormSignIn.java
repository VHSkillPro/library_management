package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.AccountDao;
import utils.SHA256;
import utils.Validate;
import utils.ValidateElement;
import utils.ValidateForm;
import bean.Account;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FormSignIn extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputUsername;
	private JLabel labelSignUp;
	private JButton buttonSignIn;
	private JPasswordField inputPassword;
	private JLabel labelErrorUsername;
	private JLabel labelErrorPassword;
	
	private JFrame parent;
	private FormSignUp formSignUp = new FormSignUp(this);
	private FormSignIn thisForm = this;

	private ValidateForm<String> validateUsername;
	private ValidateForm<String> validatePassword;
	
	/**
	 * Create the frame.
	 */
	public FormSignIn(JFrame parent) {
		this.parent = parent;
		buildValidateForm();
		createContents();
		openFormSignUp();
		signIn();
		enterSignIn();
	}
	
	public void openFormSignUp() {
		labelSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				formSignUp.setVisible(true);
				thisForm.setVisible(false);
			}
		});
	}
	
	public void enterSignIn() {
		inputPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					buttonSignIn.doClick();
				}
			}
		});
	}
	
	public void buildValidateForm() {
		validateUsername = new ValidateForm<String>();
		validateUsername.addElement(new ValidateElement<String>("Vui lòng nhập username", str -> Validate.isExist(str)));
		
		validatePassword = new ValidateForm<String>();
		validatePassword.addElement(new ValidateElement<String>("Vui lòng nhập password", str -> Validate.isExist(str)));
	}
	
	public void signIn() {
		buttonSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = inputUsername.getText().strip();
				String password = String.valueOf(inputPassword.getPassword()).strip();
				
				String usernameError = validateUsername.validate(username);
				String passwordError = validatePassword.validate(password);
				
				boolean haveError = false;
				if (usernameError != null) {
					haveError = true;
					labelErrorUsername.setText(usernameError);
					labelErrorUsername.setVisible(true);
				}
				else {
					labelErrorUsername.setVisible(false);
				}
				
				if (passwordError != null) {
					haveError = true;
					labelErrorPassword.setText(passwordError);
					labelErrorPassword.setVisible(true);
				}
				else {
					labelErrorPassword.setVisible(false);
				}
				
				if (!haveError) {
					Account account = AccountDao.getAccountByUsername(username);
					if (account != null && account.getPassword().equals(SHA256.getString(password))) {
						JOptionPane.showMessageDialog(null, "Đăng nhập thành công", "Thông báo đăng nhập", JOptionPane.INFORMATION_MESSAGE);
						parent.setVisible(true);
						thisForm.setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null, "Đăng nhập thất bại", "Thông báo đăng nhập", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
	
	private void createContents() {
		setTitle("Đăng nhập - Phần mềm quản lý thư viện");
		setFont(new Font("Segoe UI", Font.PLAIN, 12));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelSignIn = new JLabel("ĐĂNG NHẬP");
		labelSignIn.setFont(new Font("Segoe UI", Font.BOLD, 21));
		labelSignIn.setBounds(125, 10, 140, 50);
		contentPane.add(labelSignIn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 70, 350, 2);
		contentPane.add(separator);
		
		JLabel labelUsername = new JLabel("Username");
		labelUsername.setLabelFor(labelUsername);
		labelUsername.setIcon(new ImageIcon(FormSignIn.class.getResource("/icons/user.png")));
		labelUsername.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelUsername.setBounds(50, 90, 100, 16);
		contentPane.add(labelUsername);
		
		inputUsername = new JTextField();
		inputUsername.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		inputUsername.setBounds(50, 110, 300, 25);
		contentPane.add(inputUsername);
		inputUsername.setColumns(10);
		
		JLabel labelPassword = new JLabel("Password");
		labelPassword.setIcon(new ImageIcon(FormSignIn.class.getResource("/icons/key.png")));
		labelPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelPassword.setBounds(50, 150, 100, 16);
		contentPane.add(labelPassword);
		
		buttonSignIn = new JButton("Đăng nhập");
		buttonSignIn.setIcon(new ImageIcon(FormSignIn.class.getResource("/icons/log-in.png")));
		buttonSignIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonSignIn.setFont(new Font("Segoe UI", Font.BOLD, 17));
		buttonSignIn.setBounds(115, 220, 175, 40);
		contentPane.add(buttonSignIn);
		
		labelSignUp = new JLabel("Nếu bạn chưa có tài khoản, hãy đăng ký tại đây");
		labelSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		labelSignUp.setForeground(SystemColor.textHighlight);
		labelSignUp.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		labelSignUp.setBounds(80, 280, 250, 15);
		contentPane.add(labelSignUp);
		
		inputPassword = new JPasswordField();
		labelPassword.setLabelFor(inputPassword);
		inputPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		inputPassword.setBounds(50, 170, 300, 25);
		contentPane.add(inputPassword);
		
		labelErrorUsername = new JLabel("");
		labelErrorUsername.setVisible(false);
		labelErrorUsername.setForeground(Color.RED);
		labelErrorUsername.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		labelErrorUsername.setBounds(150, 90, 200, 16);
		contentPane.add(labelErrorUsername);
		
		labelErrorPassword = new JLabel("");
		labelErrorPassword.setVisible(false);
		labelErrorPassword.setForeground(Color.RED);
		labelErrorPassword.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		labelErrorPassword.setBounds(150, 150, 200, 16);
		contentPane.add(labelErrorPassword);
	}
}
