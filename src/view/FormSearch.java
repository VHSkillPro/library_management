package view;

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
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.ImageIcon;

public class FormSearch extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtMaSach;
	private JTextField txtTenSach;
	private JTextField txtTacGia;
	private JTextField txtNhaXuatBan;
	private JTextField txtTheLoai;
	private JLabel lblTmKimSch;
	private JSeparator separator;
	
	private PanelBook parent;
	
	/**
	 * Create the frame.
	 */
	public FormSearch(PanelBook parent) {
		this.parent = parent;
		
		createContents();
	}
	
	private void createContents() {
		setResizable(false);
		setTitle("Tìm kiếm sách - Phần mềm quản lý thư viện");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
				setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã sách");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel.setBounds(20, 80, 75, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblTnSch = new JLabel("Tên sách");
		lblTnSch.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblTnSch.setBounds(20, 140, 75, 20);
		contentPane.add(lblTnSch);
		
		JLabel lblTcGi = new JLabel("Tác giả");
		lblTcGi.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblTcGi.setBounds(20, 200, 75, 20);
		contentPane.add(lblTcGi);
		
		JLabel lblNhXutBn = new JLabel("Nhà xuất bản");
		lblNhXutBn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNhXutBn.setBounds(20, 260, 82, 20);
		contentPane.add(lblNhXutBn);
		
		JLabel lblThLoi = new JLabel("Thể loại");
		lblThLoi.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblThLoi.setBounds(20, 320, 75, 20);
		contentPane.add(lblThLoi);
		
		txtMaSach = new JTextField();
		txtMaSach.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtMaSach.setBounds(20, 100, 300, 25);
		contentPane.add(txtMaSach);
		txtMaSach.setColumns(10);
		
		txtTenSach = new JTextField();
		txtTenSach.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtTenSach.setColumns(10);
		txtTenSach.setBounds(20, 160, 300, 25);
		contentPane.add(txtTenSach);
		
		txtTacGia = new JTextField();
		txtTacGia.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtTacGia.setColumns(10);
		txtTacGia.setBounds(20, 220, 300, 25);
		contentPane.add(txtTacGia);
		
		txtNhaXuatBan = new JTextField();
		txtNhaXuatBan.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtNhaXuatBan.setColumns(10);
		txtNhaXuatBan.setBounds(20, 280, 300, 25);
		contentPane.add(txtNhaXuatBan);
		
		txtTheLoai = new JTextField();
		txtTheLoai.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtTheLoai.setColumns(10);
		txtTheLoai.setBounds(20, 340, 300, 25);
		contentPane.add(txtTheLoai);
		
		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setIcon(new ImageIcon(FormSearch.class.getResource("/icons/search.png")));
		btnTimKiem.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnTimKiem.setBounds(115, 385, 120, 30);
		contentPane.add(btnTimKiem);
		
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maSach = txtMaSach.getText();
				String tenSach = txtTenSach.getText();
				String tacGia = txtTacGia.getText();
				String NXB = txtNhaXuatBan.getText();
				String theLoai = txtTheLoai.getText();
				ArrayList<Book> lst = BookBo.SearchBook(maSach, tenSach, tacGia, NXB, theLoai);
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
		
		lblTmKimSch = new JLabel("Tìm kiếm sách");
		lblTmKimSch.setHorizontalAlignment(SwingConstants.CENTER);
		lblTmKimSch.setFont(new Font("Segoe UI", Font.BOLD, 19));
		lblTmKimSch.setBounds(100, 15, 150, 30);
		contentPane.add(lblTmKimSch);
		
		separator = new JSeparator();
		separator.setForeground(UIManager.getColor("Button.shadow"));
		separator.setBounds(20, 60, 300, 2);
		contentPane.add(separator);
	}
}
