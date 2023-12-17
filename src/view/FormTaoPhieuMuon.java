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

import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class FormTaoPhieuMuon extends JFrame {
	private FormTaoPhieuMuon thisFrm = this;
	private JPanel contentPane;
	private PanelPhieuMuon parent;
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
		setBounds(100, 100, 400, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbTaoHD = new JLabel("Tạo hóa đơn");
		lbTaoHD.setFont(new Font("Segoe UI", Font.BOLD, 21));
		lbTaoHD.setBounds(125, 10, 140, 50);
		contentPane.add(lbTaoHD);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 70, 350, 2);
		contentPane.add(separator);
		
		JLabel lbMaTT = new JLabel("Nhập mã thủ thư");
		lbMaTT.setLabelFor(lbMaTT);
		lbMaTT.setIcon(new ImageIcon(FormSignIn.class.getResource("/icons/user.png")));
		lbMaTT.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lbMaTT.setBounds(50, 90, 126, 16);
		contentPane.add(lbMaTT);
		
		JTextField txtMaTT = new JTextField();
		txtMaTT.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtMaTT.setBounds(50, 110, 300, 25);
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
		txtMaKH.setBounds(50, 170, 300, 25);
		contentPane.add(txtMaKH);
		
		JButton buttonSignIn = new JButton("Xác nhận");
		buttonSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrm.setVisible(false);
				int maTT = Integer.parseInt(txtMaTT.getText().toString());
				int maKH = Integer.parseInt(txtMaKH.getText().toString());
				PhieuMuonBo.insertPhieuMuon(new PhieuMuon(0, null, null, 0, maKH, maTT));
				pr.loadTableListPhieuMuon();
				new FormChinhSuaPhieuMuon(PhieuMuonBo.getLastestInsert(), pr).setVisible(true);
			}
		});
		buttonSignIn.setIcon(new ImageIcon(FormSignIn.class.getResource("/icons/log-in.png")));
		buttonSignIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonSignIn.setFont(new Font("Segoe UI", Font.BOLD, 17));
		buttonSignIn.setBounds(115, 220, 175, 40);
		contentPane.add(buttonSignIn);
	}

}
