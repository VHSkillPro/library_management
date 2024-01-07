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
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.UIManager;

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
	private JLabel labelMaSach;
	
	private Book books;
	private PanelBook parent;
	
	/**
	 * Create the frame.
	 */
	public FormUpdate(int maSach, PanelBook parent) {
		this.parent = parent;
		this.books = BookBo.findByBookId(maSach); 
		createContents();
	}
	
	private void createContents() {
		setResizable(false);
		setTitle("Chỉnh sửa thông tin - Phần mềm quản lý thư viện");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã sách:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel.setBounds(20, 80, 80, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblHTn = new JLabel("Tên sách");
		lblHTn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblHTn.setBounds(20, 110, 61, 16);
		contentPane.add(lblHTn);
		
		JLabel lblNgySinh = new JLabel("Tác giả");
		lblNgySinh.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNgySinh.setBounds(20, 170, 61, 16);
		contentPane.add(lblNgySinh);
		
		txtTenSach = new JTextField();
		txtTenSach.setColumns(10);
		txtTenSach.setBounds(20, 130, 350, 25);
		contentPane.add(txtTenSach);
		
		txtTacGia = new JTextField();
		txtTacGia.setColumns(10);
		txtTacGia.setBounds(20, 190, 350, 25);
		contentPane.add(txtTacGia);
		
		JLabel lblEmail = new JLabel("Nhà xuất bản");
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblEmail.setBounds(20, 230, 88, 16);
		contentPane.add(lblEmail);
		
		JLabel lblSinThoi = new JLabel("Đơn giá");
		lblSinThoi.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblSinThoi.setBounds(200, 290, 76, 16);
		contentPane.add(lblSinThoi);
		
		JLabel lblaCh = new JLabel("Số lượng");
		lblaCh.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblaCh.setBounds(20, 290, 61, 16);
		contentPane.add(lblaCh);
		
		txtNhaXuatBan = new JTextField();
		txtNhaXuatBan.setColumns(10);
		txtNhaXuatBan.setBounds(20, 250, 350, 25);
		contentPane.add(txtNhaXuatBan);
		
		txtDonGia = new JTextField();
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(200, 310, 170, 25);
		contentPane.add(txtDonGia);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(20, 310, 170, 25);
		contentPane.add(txtSoLuong);
		
		JButton btnChinhSua = new JButton("Chỉnh sửa");
		btnChinhSua.setIcon(new ImageIcon(FormUpdate.class.getResource("/icons/edit.png")));
		btnChinhSua.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnChinhSua.setBounds(130, 415, 120, 30);
		contentPane.add(btnChinhSua);
		
		btnChinhSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer maSach = Integer.parseInt(labelMaSach.getText());
				String tenSach = txtTenSach.getText();
				String tacGia = txtTacGia.getText();
				String nhaXuatBan = txtNhaXuatBan.getText();
				Double donGia = Double.parseDouble(txtDonGia.getText());
				Integer soLuong = Integer.parseInt(txtSoLuong.getText());
				String theLoai = txtTheLoai.getText();
				Integer maThuThu = books.getMaThuThu();
				
				Book book = new Book(maSach, tenSach, tacGia, nhaXuatBan, donGia, soLuong, theLoai, maThuThu);
				try {
					if (JOptionPane.showConfirmDialog(btnChinhSua, "Bạn có chắc chắn muốn chỉnh sửa không") != 0) return;
					BookBo.updateBook(book);
					ArrayList<Book> lst = BookBo.getAllBook();
					parent.loadTable(lst);
				} catch (Exception e2) {
					e2.getStackTrace();
				}
			}
		});
		
		JLabel lblThLoi = new JLabel("Thể loại");
		lblThLoi.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblThLoi.setBounds(20, 350, 61, 16);
		contentPane.add(lblThLoi);
		
		txtTheLoai = new JTextField();
		txtTheLoai.setColumns(10);
		txtTheLoai.setBounds(20, 370, 350, 25);
		contentPane.add(txtTheLoai);
		
		labelMaSach = new JLabel("");
		labelMaSach.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelMaSach.setBounds(100, 80, 270, 16);
		contentPane.add(labelMaSach);
		
		labelMaSach.setText(Integer.valueOf(books.getMaSach()).toString());
		txtTenSach.setText(books.getTenSach());
		txtTacGia.setText(books.getTacGia());
		txtNhaXuatBan.setText(books.getNhaXuatBan());
		txtDonGia.setText(Double.valueOf(books.getDonGia()).toString());
		txtSoLuong.setText(Integer.valueOf(books.getSoLuong()).toString());
		txtTheLoai.setText(books.getTheLoai());
		
		JLabel lblThngTinSch = new JLabel("Thông tin sách");
		lblThngTinSch.setHorizontalAlignment(SwingConstants.CENTER);
		lblThngTinSch.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblThngTinSch.setBounds(110, 15, 180, 30);
		contentPane.add(lblThngTinSch);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(UIManager.getColor("Button.shadow"));
		separator.setBackground(Color.WHITE);
		separator.setBounds(20, 60, 350, 2);
		contentPane.add(separator);
	}
}
