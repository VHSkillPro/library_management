package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.Book;
import bo.BookBo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class FormSearch extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaSach;
	private JTextField txtTenSach;
	private JTextField txtTacGia;
	private JTextField txtNhaXuatBan;
	private JTextField txtTheLoai;
	private PanelBook parent;
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FormSearch frame = new FormSearch();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public FormSearch(PanelBook parent) {
		this.parent = parent;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 389, 263);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã sách:");
		lblNewLabel.setBounds(21, 11, 75, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblTnSch = new JLabel("Tên sách:");
		lblTnSch.setBounds(21, 42, 75, 20);
		contentPane.add(lblTnSch);
		
		JLabel lblTcGi = new JLabel("Tác giả:");
		lblTcGi.setBounds(21, 80, 75, 20);
		contentPane.add(lblTcGi);
		
		JLabel lblNhXutBn = new JLabel("Nhà xuất bản:");
		lblNhXutBn.setBounds(21, 121, 82, 20);
		contentPane.add(lblNhXutBn);
		
		JLabel lblThLoi = new JLabel("Thể loại:");
		lblThLoi.setBounds(21, 162, 75, 20);
		contentPane.add(lblThLoi);
		
		txtMaSach = new JTextField();
		txtMaSach.setBounds(114, 11, 228, 20);
		contentPane.add(txtMaSach);
		txtMaSach.setColumns(10);
		
		txtTenSach = new JTextField();
		txtTenSach.setColumns(10);
		txtTenSach.setBounds(114, 42, 228, 20);
		contentPane.add(txtTenSach);
		
		txtTacGia = new JTextField();
		txtTacGia.setColumns(10);
		txtTacGia.setBounds(114, 80, 228, 20);
		contentPane.add(txtTacGia);
		
		txtNhaXuatBan = new JTextField();
		txtNhaXuatBan.setColumns(10);
		txtNhaXuatBan.setBounds(114, 121, 228, 20);
		contentPane.add(txtNhaXuatBan);
		
		txtTheLoai = new JTextField();
		txtTheLoai.setColumns(10);
		txtTheLoai.setBounds(114, 162, 228, 20);
		contentPane.add(txtTheLoai);
		
		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maSach = txtMaSach.getText();
				String tenSach = txtTenSach.getText();
				String tacGia = txtTacGia.getText();
				String NXB = txtNhaXuatBan.getText();
				String theLoai = txtTheLoai.getText();
				ArrayList<Book> lst = new ArrayList<Book>();
				Boolean ok = false;
				if (!maSach.isEmpty()) {
					Integer masach = Integer.parseInt(maSach);
					Book books = BookBo.findByBookId(masach);
					if (books != null) lst.add(books);
				}
				else if (!tenSach.isEmpty()) {
					lst = BookBo.findbyBookName(tenSach);
				}
				else if (!tacGia.isEmpty()) {
					lst = BookBo.findBookbyAuthor(tacGia);
				}
				else if (!NXB.isEmpty()) {
					lst = BookBo.findBookbyNXB(NXB);
				}
				else if (!theLoai.isEmpty()) {
					lst = BookBo.findBookbyType(theLoai);
				}
				System.out.print(lst.size());
				if (lst.size() == 0) {
					JOptionPane.showMessageDialog(btnTimKiem, "Không tìm thấy sách");
					return;
				}
				try {
					parent.loadTable(lst);
				} catch (Exception e2) {
					e2.getStackTrace();
				}
			}
		});
		btnTimKiem.setBounds(181, 193, 89, 23);
		contentPane.add(btnTimKiem);
	}
}
