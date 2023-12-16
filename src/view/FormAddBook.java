package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.Book;
import bo.BookBo;
import utils.ValidateForm;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class FormAddBook extends JFrame {

	private JPanel contentPane;
	private JTextField txtmaSach;
	private JTextField txttenSach;
	private JTextField txttacGia;
	private JTextField txtNXB;
	private JTextField txtdonGia;
	private JTextField txtsoLuong;
	private JTextField txttheLoai;
	private JTextField txtMaThuThu;
	
	private PanelBook parent;
	private ArrayList<ValidateForm<String>> validateForm = new ArrayList<ValidateForm<String>>();

	/**
	 * Create the frame.
	 */
	public FormAddBook(PanelBook parent) {
		this.parent = parent;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 358, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã sách");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNewLabel.setBounds(20, 11, 80, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblTnSch = new JLabel("Tên sách");
		lblTnSch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblTnSch.setBounds(20, 47, 80, 26);
		contentPane.add(lblTnSch);
		
		JLabel lblTcGi = new JLabel("Tác giả");
		lblTcGi.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblTcGi.setBounds(20, 84, 80, 26);
		contentPane.add(lblTcGi);
		
		JLabel lblNhXutBn = new JLabel("Nhà Xuất Bản");
		lblNhXutBn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNhXutBn.setBounds(20, 121, 80, 26);
		contentPane.add(lblNhXutBn);
		
		JLabel lblnGi = new JLabel("Đơn giá");
		lblnGi.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblnGi.setBounds(20, 158, 80, 26);
		contentPane.add(lblnGi);
		
		JLabel lblSLng = new JLabel("Số lượng");
		lblSLng.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblSLng.setBounds(20, 195, 80, 26);
		contentPane.add(lblSLng);
		
		JLabel lblThLoi = new JLabel("Thể loại");
		lblThLoi.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblThLoi.setBounds(20, 232, 80, 26);
		contentPane.add(lblThLoi);
		
		JLabel lblMThTh = new JLabel("Mã thủ thư");
		lblMThTh.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblMThTh.setBounds(20, 269, 80, 26);
		contentPane.add(lblMThTh);
		
		txtmaSach = new JTextField();
		txtmaSach.setBounds(113, 15, 189, 20);
		contentPane.add(txtmaSach);
		txtmaSach.setColumns(10);
		
		txttenSach = new JTextField();
		txttenSach.setColumns(10);
		txttenSach.setBounds(113, 51, 189, 20);
		contentPane.add(txttenSach);
		
		txttacGia = new JTextField();
		txttacGia.setColumns(10);
		txttacGia.setBounds(113, 88, 189, 20);
		contentPane.add(txttacGia);
		
		txtNXB = new JTextField();
		txtNXB.setColumns(10);
		txtNXB.setBounds(113, 125, 189, 20);
		contentPane.add(txtNXB);
		
		txtdonGia = new JTextField();
		txtdonGia.setColumns(10);
		txtdonGia.setBounds(113, 162, 189, 20);
		contentPane.add(txtdonGia);
		
		txtsoLuong = new JTextField();
		txtsoLuong.setColumns(10);
		txtsoLuong.setBounds(113, 199, 189, 20);
		contentPane.add(txtsoLuong);
		
		txttheLoai = new JTextField();
		txttheLoai.setColumns(10);
		txttheLoai.setBounds(113, 236, 189, 20);
		contentPane.add(txttheLoai);
		
		txtMaThuThu = new JTextField();
		txtMaThuThu.setColumns(10);
		txtMaThuThu.setBounds(113, 273, 189, 20);
		contentPane.add(txtMaThuThu);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer maSach = Integer.parseInt(txtmaSach.getText());
				String tenSach = txttenSach.getText();
				String tacGia = txttacGia.getText();
				String nhaXuatBan = txtNXB.getText();
				Double donGia = Double.parseDouble(txtdonGia.getText());
				Integer soLuong = Integer.parseInt(txtsoLuong.getText());
				String theLoai = txttheLoai.getText();
				Integer maThuThu = Integer.parseInt(txtMaThuThu.getText());
//				
//				for (int i = 0; i < 8; i++) {
//					validateForm.add(new ValidateForm<String>());
//				}
				
				Book books = new Book(maSach, tenSach, tacGia, nhaXuatBan, donGia, soLuong, theLoai, maThuThu);
				BookBo.insertBook(books);
				try {
					ArrayList<Book> lst = BookBo.getAllBook();
					parent.loadTable(lst);
				} catch (Exception e2) {
					e2.getStackTrace();
				}
			}
		});
		btnThem.setBounds(162, 304, 89, 23);
		contentPane.add(btnThem);
	}
}
