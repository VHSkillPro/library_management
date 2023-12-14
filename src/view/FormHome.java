package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.Database;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Cursor;
import java.awt.SystemColor;

public class FormHome extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private FormSignIn formSignIn;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Database.getInstance("35.240.220.181", "library_mangement", "sqlserver", "123123");
					FormHome frame = new FormHome();
					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormHome() {
		createContents();
	}
	private void createContents() {
		setResizable(false);
		setTitle("Phần mềm quản lý thư viện");
		this.formSignIn = new FormSignIn(this);
		this.formSignIn.setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
				setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelUsername = new JLabel("Ngô Văn Hải");
		labelUsername.setFont(new Font("Segoe UI", Font.BOLD, 18));
		labelUsername.setBounds(100, 20, 120, 25);
		contentPane.add(labelUsername);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FormHome.class.getResource("/icons/user64.png")));
		lblNewLabel.setBounds(20, 20, 64, 64);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Quản trị viên");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(100, 50, 100, 15);
		contentPane.add(lblNewLabel_1);
		
		JButton buttonSignOut = new JButton("Đăng xuất");
		buttonSignOut.setFont(new Font("Segoe UI", Font.BOLD, 14));
		buttonSignOut.setBounds(250, 30, 120, 40);
		contentPane.add(buttonSignOut);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(25, 100, 350, 2);
		contentPane.add(separator);
		
		JLabel labelEditAccount = new JLabel("Cài đặt tài khoản");
		labelEditAccount.setForeground(SystemColor.textHighlight);
		labelEditAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		labelEditAccount.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		labelEditAccount.setBounds(100, 72, 100, 16);
		contentPane.add(labelEditAccount);
	}

}
