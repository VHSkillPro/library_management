package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.Account;
import dao.Database;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Cursor;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormHome extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel labelUsername;
	private JLabel labelRole;
	private JButton buttonMenu4;
	private JButton buttonSignOut;
	
	private Account account;
	private FormSignIn formSignIn;
	private FormHome thisForm = this;
	private JPanel panelContent;
	private PanelBook panelBook;
	private PanelPhieuMuon panelPhieuMuon;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Database.getInstance("35.240.220.181", "library_mangement", "sqlserver", "123123");
					FormHome frame = new FormHome();
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
	public FormHome() {
		createContents();
		signOut();
	}
	
	public void signOut() {
		buttonSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				account = null;
				formSignIn = new FormSignIn(thisForm);
				formSignIn.setVisible(true);
				thisForm.setVisible(false);
			}
		});
	}
	
	private void createContents() {
		setResizable(false);
		setTitle("Phần mềm quản lý thư viện");
		this.formSignIn = new FormSignIn(this);
		this.formSignIn.setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		buttonSignOut = new JButton("Đăng xuất");
		buttonSignOut.setBounds(55, 625, 120, 40);
		contentPane.add(buttonSignOut);
		buttonSignOut.setFont(new Font("Segoe UI", Font.BOLD, 14));
		
		labelUsername = new JLabel("Ngô Văn Hải");
		labelUsername.setFont(new Font("Segoe UI", Font.BOLD, 18));
		labelUsername.setBounds(100, 20, 120, 25);
		contentPane.add(labelUsername);
		
		JLabel labelAvatar = new JLabel("");
		labelAvatar.setIcon(new ImageIcon(FormHome.class.getResource("/icons/user64.png")));
		labelAvatar.setBounds(20, 20, 64, 64);
		contentPane.add(labelAvatar);
		
		labelRole = new JLabel("Quản trị viên");
		labelRole.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		labelRole.setBounds(100, 50, 100, 15);
		contentPane.add(labelRole);
		
		JLabel labelEditAccount = new JLabel("Cài đặt tài khoản");
		labelEditAccount.setForeground(SystemColor.textHighlight);
		labelEditAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		labelEditAccount.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		labelEditAccount.setBounds(100, 72, 100, 16);
		contentPane.add(labelEditAccount);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBorder(new EmptyBorder(0, 0, 0, 2));
		panelMenu.setBounds(0, 0, 230, 680);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		JButton buttonMenu1 = new JButton("Sách");
		buttonMenu1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBook.setVisible(true);
			}
		});
		buttonMenu1.setBorderPainted(false);
		buttonMenu1.setBackground(new Color(240, 240, 240));
		buttonMenu1.setFont(new Font("Segoe UI", Font.BOLD, 15));
		buttonMenu1.setBounds(0, 100, 230, 50);
		panelMenu.add(buttonMenu1);
		
		JButton buttonMenu2 = new JButton("Phiếu mượn");
		buttonMenu2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelPhieuMuon.setVisible(true);
			}
		});
//		buttonMenu2.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				panelPhieuMuon.setVisible(true);
//			}
//		});
		buttonMenu2.setBorderPainted(false);
		buttonMenu2.setBackground(new Color(240, 240, 240));
		buttonMenu2.setFont(new Font("Segoe UI", Font.BOLD, 15));
		buttonMenu2.setBounds(0, 150, 230, 50);
		panelMenu.add(buttonMenu2);
		
		JButton buttonMenu3 = new JButton("Độc giả");
		buttonMenu3.setBorderPainted(false);
		buttonMenu3.setBackground(new Color(240, 240, 240));
		buttonMenu3.setFont(new Font("Segoe UI", Font.BOLD, 15));
		buttonMenu3.setBounds(0, 200, 230, 50);
		panelMenu.add(buttonMenu3);
		
		buttonMenu4 = new JButton("Tài khoản");
		buttonMenu4.setBorderPainted(false);
		buttonMenu4.setBackground(new Color(240, 240, 240));
		buttonMenu4.setFont(new Font("Segoe UI", Font.BOLD, 15));
		buttonMenu4.setBounds(0, 250, 230, 50);
		panelMenu.add(buttonMenu4);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(160, 160, 160));
		separator.setBackground(new Color(255, 255, 255));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(230, 0, 2, 1000);
		contentPane.add(separator);
		
		panelBook = new PanelBook();
		panelBook.setBounds(230, 0, 1035, 680);
		panelBook.setVisible(false);
		contentPane.add(panelBook);
		
		//panelPhieuMuon
		panelPhieuMuon = new PanelPhieuMuon();
		panelPhieuMuon.setBounds(230, 0, 1035, 680);
		panelPhieuMuon.setVisible(false);
		contentPane.add(panelPhieuMuon);
	}
	
	public void setAccount(Account account) {
		this.account = account;
		labelUsername.setText(account.getUsername());
		if (account.getRole() == 1) {
			labelRole.setText("Thủ thư");
			buttonMenu4.setVisible(false);
		}
		else if (account.getRole() == 2) {
			labelRole.setText("Quản trị viên");
			buttonMenu4.setVisible(true);
		}
	}
	
	public Account getAccount() {
		return this.account;
	}
}
