package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.Database;
import utils.Validate;
import utils.ValidateElement;
import utils.ValidateForm;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class FormChangePasswordDocGia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField inputPassword;
	private JPasswordField inputRepassword;
	private JButton buttonChangePassword;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormChangePasswordDocGia frame = new FormChangePasswordDocGia();
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
	public FormChangePasswordDocGia() {
		createContents();
		eventChangePassword();
	}
	
	public void eventChangePassword() {
		buttonChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ValidateForm<String> validatePassword = new ValidateForm<String>();
				validatePassword.addElement(new ValidateElement<String>("Vui lòng nhập trường này", str -> Validate.isExist(str)));
				validatePassword.addElement(new ValidateElement<String>("Mật khẩu phải dài hơn 6 ký tự", str -> str.length() >= 6));
			
				String password = String.valueOf(inputPassword.getPassword());
				String repassword = String.valueOf(inputRepassword.getPassword());
				
				boolean haveError = false;
				
				
				if (password.equals(repassword)) {
					
				}
			}
		});
	}
	
	private void createContents() {
		setResizable(false);
		setTitle("Đổi mật khẩu - Phần mềm quản lý thư viện");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbliMtKhu = new JLabel("Đổi mật khẩu");
		lbliMtKhu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lbliMtKhu.setBounds(105, 15, 140, 30);
		contentPane.add(lbliMtKhu);
		
		JLabel labelMaDocGia = new JLabel("Mã độc giả:");
		labelMaDocGia.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMaDocGia.setFont(new Font("Segoe UI", Font.BOLD, 14));
		labelMaDocGia.setBounds(20, 70, 90, 25);
		contentPane.add(labelMaDocGia);
		
		JLabel labelMaDocGiaValue = new JLabel("0");
		labelMaDocGiaValue.setHorizontalAlignment(SwingConstants.LEFT);
		labelMaDocGiaValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		labelMaDocGiaValue.setBounds(120, 70, 200, 25);
		contentPane.add(labelMaDocGiaValue);
		
		JLabel labelUsername = new JLabel("Username:");
		labelUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		labelUsername.setFont(new Font("Segoe UI", Font.BOLD, 14));
		labelUsername.setBounds(20, 110, 90, 25);
		contentPane.add(labelUsername);
		
		JLabel labelUsernameValue = new JLabel("0");
		labelUsernameValue.setHorizontalAlignment(SwingConstants.LEFT);
		labelUsernameValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		labelUsernameValue.setBounds(120, 110, 200, 25);
		contentPane.add(labelUsernameValue);
		
		JLabel labelPassword = new JLabel("Password");
		labelPassword.setIcon(new ImageIcon(FormChangePasswordDocGia.class.getResource("/icons/key.png")));
		labelPassword.setHorizontalAlignment(SwingConstants.LEFT);
		labelPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelPassword.setBounds(21, 150, 100, 16);
		contentPane.add(labelPassword);
		
		inputPassword = new JPasswordField();
		inputPassword.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		inputPassword.setBounds(20, 170, 270, 25);
		contentPane.add(inputPassword);
		
		JLabel labelRepassword = new JLabel("Repassword");
		labelRepassword.setIcon(new ImageIcon(FormChangePasswordDocGia.class.getResource("/icons/key.png")));
		labelRepassword.setHorizontalAlignment(SwingConstants.LEFT);
		labelRepassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelRepassword.setBounds(20, 210, 100, 16);
		contentPane.add(labelRepassword);
		
		inputRepassword = new JPasswordField();
		inputRepassword.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		inputRepassword.setBounds(20, 230, 270, 25);
		contentPane.add(inputRepassword);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(160, 160, 160));
		separator.setBounds(20, 60, 300, 2);
		contentPane.add(separator);
		
		buttonChangePassword = new JButton("Đổi mật khẩu");
		buttonChangePassword.setFont(new Font("Segoe UI", Font.BOLD, 14));
		buttonChangePassword.setBounds(115, 293, 130, 30);
		contentPane.add(buttonChangePassword);
	}
}
