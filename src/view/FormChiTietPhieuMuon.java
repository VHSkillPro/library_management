package view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormChiTietPhieuMuon extends JFrame {

	private JPanel contentPane;
	private JFrame parent;
	private Integer maPhieuMuon;
	private JTextField txtMaKhachHang;
	private JTextField txtTenKhachHang;
	private JTextField txtPhone;
	private JTable table;
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public void content() {
		setTitle("Chi Tiết Phiếu Mượn");
		setFont(new Font("Segoe UI", Font.PLAIN, 12));
		setResizable(false);
		setBounds(100, 100, 830, 493);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelPhieuMuon = new JLabel("Phiếu mượn #" + maPhieuMuon.toString());
		labelPhieuMuon.setFont(new Font("Segoe UI", Font.BOLD, 21));
		labelPhieuMuon.setBounds(134, 11, 204, 50);
		contentPane.add(labelPhieuMuon);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 70, 784, 2);
		contentPane.add(separator);
		
		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setEditable(false);
		txtMaKhachHang.setColumns(10);
		txtMaKhachHang.setBounds(20, 123, 250, 25);
		contentPane.add(txtMaKhachHang);
		
		JLabel lblmaKhachHang = new JLabel("Mã khách hàng");
		lblmaKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblmaKhachHang.setBounds(20, 103, 118, 16);
		lblmaKhachHang.setIcon(new ImageIcon(FormChiTietPhieuMuon.class.getResource("/icons/user.png")));
		contentPane.add(lblmaKhachHang);
		
		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setEditable(false);
//		txtTenKhachHang.setColumns(10);
		txtTenKhachHang.setBounds(20, 201, 250, 25);
		contentPane.add(txtTenKhachHang);
		
		JLabel lblTenKhachHang = new JLabel("Tên khách hàng");
		lblTenKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblTenKhachHang.setIcon(new ImageIcon(FormChiTietPhieuMuon.class.getResource("/icons/id-card.png")));
		lblTenKhachHang.setBounds(20, 181, 118, 16);
		contentPane.add(lblTenKhachHang);
		
		JLabel lblPhone = new JLabel("Số điện thoại");
		lblPhone.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblPhone.setBounds(20, 264, 118, 16);
		lblPhone.setIcon(new ImageIcon(FormChiTietPhieuMuon.class.getResource("/icons/phone-call.png")));
		contentPane.add(lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setEditable(false);
		txtPhone.setBounds(20, 284, 250, 25);
		contentPane.add(txtPhone);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(319, 72, 9, 422);
		contentPane.add(separator_1);
		
		JLabel lbllistBook = new JLabel("Danh sách mượn");
		lbllistBook.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lbllistBook.setBounds(338, 83, 204, 16);
		lbllistBook.setIcon(new ImageIcon(FormChiTietPhieuMuon.class.getResource("/icons/ballot.png")));
		contentPane.add(lbllistBook);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(338, 423, 443, -296);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBounds(338, 110, 466, 320);
		contentPane.add(table);
		
		JButton btnInHoaDon = new JButton("In Hóa Đơn");
		btnInHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnInHoaDon.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnInHoaDon.setBounds(51, 349, 167, 36);
		btnInHoaDon.setIcon(new ImageIcon(FormChiTietPhieuMuon.class.getResource("/icons/printer.png")));
		contentPane.add(btnInHoaDon);
	}
	public FormChiTietPhieuMuon(Integer ma) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.maPhieuMuon = ma;
		content();
	}
}
