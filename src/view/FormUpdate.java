package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.Book;
import bo.BookBo;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class FormUpdate extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7146415031877207122L;
	
	private JPanel contentPane;
	private JTextField txtTenSach;
	private JTextField txtTacGia;
	private JTextField txtNhaXuatBan;
	private JTextField txtDonGia;
	private JTextField txtSoLuong;
	private JTextField txtTheLoai;
	private JTextField txtMaThuThu;
	private JLabel labelMaSach;
	private Book books;

	/**
	 * Create the frame.
	 */
	public FormUpdate(int maSach, PanelBook parent) {
		this.books = BookBo.findByBookId(maSach); 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 430, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã sách:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 11, 61, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblHTn = new JLabel("Tên sách");
		lblHTn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblHTn.setBounds(10, 41, 61, 28);
		contentPane.add(lblHTn);
		
		JLabel lblNgySinh = new JLabel("Tác giả:");
		lblNgySinh.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNgySinh.setBounds(10, 74, 61, 28);
		contentPane.add(lblNgySinh);
		
		txtTenSach = new JTextField();
		txtTenSach.setColumns(10);
		txtTenSach.setBounds(108, 46, 286, 20);
		contentPane.add(txtTenSach);
		
		txtTacGia = new JTextField();
		txtTacGia.setColumns(10);
		txtTacGia.setBounds(108, 79, 286, 20);
		contentPane.add(txtTacGia);
		
		JLabel lblEmail = new JLabel("Nhà xuất bản");
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblEmail.setBounds(10, 113, 88, 28);
		contentPane.add(lblEmail);
		
		JLabel lblSinThoi = new JLabel("Đơn giá:");
		lblSinThoi.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblSinThoi.setBounds(10, 152, 76, 28);
		contentPane.add(lblSinThoi);
		
		JLabel lblaCh = new JLabel("Số lượng:");
		lblaCh.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblaCh.setBounds(10, 192, 61, 28);
		contentPane.add(lblaCh);
		
		txtNhaXuatBan = new JTextField();
		txtNhaXuatBan.setColumns(10);
		txtNhaXuatBan.setBounds(108, 113, 286, 20);
		contentPane.add(txtNhaXuatBan);
		
		txtDonGia = new JTextField();
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(108, 152, 286, 20);
		contentPane.add(txtDonGia);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(108, 192, 286, 20);
		contentPane.add(txtSoLuong);
		
		JButton btnChinhSua = new JButton("Chỉnh sửa");
		btnChinhSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer maSach = Integer.parseInt(labelMaSach.getText());
				String tenSach = txtTenSach.getText();
				String tacGia = txtTacGia.getText();
				String nhaXuatBan = txtNhaXuatBan.getText();
				Double donGia = Double.parseDouble(txtDonGia.getText());
				Integer soLuong = Integer.parseInt(txtSoLuong.getText());
				String theLoai = txtTheLoai.getText();
				Integer maThuThu = Integer.parseInt(txtMaThuThu.getText());
				Book books = new Book(maSach, tenSach, tacGia, nhaXuatBan, donGia, soLuong, theLoai, maThuThu);
				try {
					if (JOptionPane.showConfirmDialog(btnChinhSua, "Bạn có chắc chắn muốn chỉnh sửa không") != 0) return;
					BookBo.updateBook(books);
					ArrayList<Book> lst = BookBo.getAllBook();
					parent.loadTable(lst);
				} catch (Exception e2) {
					e2.getStackTrace();
				}
			}
		});
		btnChinhSua.setBounds(188, 314, 104, 23);
		contentPane.add(btnChinhSua);
		
		JLabel lblThLoi = new JLabel("Thể loại:");
		lblThLoi.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblThLoi.setBounds(10, 231, 61, 28);
		contentPane.add(lblThLoi);
		
		JLabel lblMThTh = new JLabel("Mã thủ thư:");
		lblMThTh.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblMThTh.setBounds(10, 275, 61, 28);
		contentPane.add(lblMThTh);
		
		txtTheLoai = new JTextField();
		txtTheLoai.setColumns(10);
		txtTheLoai.setBounds(108, 236, 286, 20);
		contentPane.add(txtTheLoai);
		
		txtMaThuThu = new JTextField();
		txtMaThuThu.setColumns(10);
		txtMaThuThu.setBounds(108, 275, 286, 20);
		contentPane.add(txtMaThuThu);
		
		labelMaSach = new JLabel("");
		labelMaSach.setBounds(107, 11, 287, 20);
		contentPane.add(labelMaSach);
		
		labelMaSach.setText(Integer.valueOf(books.getMaSach()).toString());
		txtTenSach.setText(books.getTenSach());
		txtTacGia.setText(books.getTacGia());
		txtNhaXuatBan.setText(books.getNhaXuatBan());
		txtDonGia.setText(Double.valueOf(books.getDonGia()).toString());
		txtSoLuong.setText(Integer.valueOf(books.getSoLuong()).toString());
		txtTheLoai.setText(books.getTheLoai());
		txtMaThuThu.setText(Integer.valueOf(books.getMaThuThu()).toString());
	}
}
