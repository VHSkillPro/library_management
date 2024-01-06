package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.Book;
import bo.BookBo;
import bo.ThuThuBo;
import utils.Validate;
import utils.ValidateElement;
import utils.ValidateForm;

import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import java.awt.Color;

public class FormAddBook extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4239099567974259157L;
	
	private JPanel contentPane;
	private JTextField txtmaSach;
	private JTextField txttenSach;
	private JTextField txttacGia;
	private JTextField txtNXB;
	private JTextField txtdonGia;
	private JTextField txtsoLuong;
	private JTextField txttheLoai;
	
	private ArrayList<JLabel> labelErrors = new ArrayList<JLabel>();
	private ArrayList<String> labelMessage = new ArrayList<String>();
	private ArrayList<ValidateForm<String>> validateForm = new ArrayList<ValidateForm<String>>();

	private JLabel labelErrorMaSach;
	private JLabel labelErrorTenSach;
	private JLabel labelErrorTacGia;
	private JLabel labelErrorNXB;
	private JLabel labelErrorDonGia;
	private JLabel labelErrorSoLuong;
	private JLabel labelErrorTheLoai;

	private PanelBook parent;
	
	/**
	 * Create the frame.
	 */
	public FormAddBook(PanelBook parent) {
		this.parent = parent;
		
		labelErrors.add(labelErrorMaSach);
		labelErrors.add(labelErrorTenSach);
		labelErrors.add(labelErrorTacGia);
		labelErrors.add(labelErrorNXB);
		labelErrors.add(labelErrorDonGia);
		labelErrors.add(labelErrorSoLuong);
		labelErrors.add(labelErrorTheLoai);
		
		for (int i = 0; i < 7; i++) {
			labelMessage.add(null);
			validateForm.add(new ValidateForm<String>());
		}
		
		validateForm.get(0).addElement(new ValidateElement<String>("Vui lòng nhập mã sách", str -> Validate.isExist(str)));
		validateForm.get(1).addElement(new ValidateElement<String>("Vui lòng nhập tên sách", str -> Validate.isExist(str)));
		validateForm.get(2).addElement(new ValidateElement<String>("Vui lòng nhập tác giả", str -> Validate.isExist(str)));
		validateForm.get(3).addElement(new ValidateElement<String>("Vui lòng nhập nhà xuất bản", str -> Validate.isExist(str)));
		validateForm.get(4).addElement(new ValidateElement<String>("Vui lòng nhập đơn giá", str -> Validate.isExist(str)));
		validateForm.get(4).addElement(new ValidateElement<String>("Đơn giá phải lớn hơn 0", str -> Integer.parseInt(str) < 0));
		validateForm.get(5).addElement(new ValidateElement<String>("Vui lòng nhập số lượng", str -> Validate.isExist(str)));
		validateForm.get(5).addElement(new ValidateElement<String>("Số lượng phải lớn hơn 0", str -> Integer.parseInt(str) < 0));
		validateForm.get(6).addElement(new ValidateElement<String>("Vui lòng nhập thể loại", str -> Validate.isExist(str)));
		
		createContents();
	}
	
	private void createContents() {
		setTitle("Thêm sách - Phần mềm quản lý thư viện");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã sách");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel.setBounds(20, 80, 100, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblTnSch = new JLabel("Tên sách");
		lblTnSch.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblTnSch.setBounds(20, 140, 80, 16);
		contentPane.add(lblTnSch);
		
		JLabel lblTcGi = new JLabel("Tác giả");
		lblTcGi.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblTcGi.setBounds(20, 200, 80, 16);
		contentPane.add(lblTcGi);
		
		JLabel lblNhXutBn = new JLabel("Nhà Xuất Bản");
		lblNhXutBn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNhXutBn.setBounds(20, 260, 80, 16);
		contentPane.add(lblNhXutBn);
		
		JLabel lblnGi = new JLabel("Đơn giá");
		lblnGi.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblnGi.setBounds(175, 320, 80, 16);
		contentPane.add(lblnGi);
		
		JLabel lblSLng = new JLabel("Số lượng");
		lblSLng.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblSLng.setBounds(20, 320, 80, 16);
		contentPane.add(lblSLng);
		
		JLabel lblThLoi = new JLabel("Thể loại");
		lblThLoi.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblThLoi.setBounds(20, 380, 80, 16);
		contentPane.add(lblThLoi);
		
		txtmaSach = new JTextField();
		txtmaSach.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtmaSach.setBounds(20, 100, 300, 25);
		contentPane.add(txtmaSach);
		txtmaSach.setColumns(10);
		
		txttenSach = new JTextField();
		txttenSach.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txttenSach.setColumns(10);
		txttenSach.setBounds(20, 160, 300, 25);
		contentPane.add(txttenSach);
		
		txttacGia = new JTextField();
		txttacGia.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txttacGia.setColumns(10);
		txttacGia.setBounds(20, 220, 300, 25);
		contentPane.add(txttacGia);
		
		txtNXB = new JTextField();
		txtNXB.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtNXB.setColumns(10);
		txtNXB.setBounds(20, 280, 300, 25);
		contentPane.add(txtNXB);
		
		txtdonGia = new JTextField();
		txtdonGia.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtdonGia.setColumns(10);
		txtdonGia.setBounds(175, 340, 145, 25);
		contentPane.add(txtdonGia);
		
		txtsoLuong = new JTextField();
		txtsoLuong.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtsoLuong.setColumns(10);
		txtsoLuong.setBounds(20, 340, 145, 25);
		contentPane.add(txtsoLuong);
		
		txttheLoai = new JTextField();
		txttheLoai.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txttheLoai.setColumns(10);
		txttheLoai.setBounds(20, 400, 300, 25);
		contentPane.add(txttheLoai);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon(FormAddBook.class.getResource("/icons/plus-symbol-button.png")));
		btnThem.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnThem.setBounds(120, 445, 100, 30);
		contentPane.add(btnThem);
		
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer maSach = Integer.parseInt(txtmaSach.getText());
				String tenSach = txttenSach.getText();
				String tacGia = txttacGia.getText();
				String nhaXuatBan = txtNXB.getText();
				Double donGia = Double.parseDouble(txtdonGia.getText());
				Integer soLuong = Integer.parseInt(txtsoLuong.getText());
				String theLoai = txttheLoai.getText();
				Integer maThuThu = ThuThuBo.getThuThuByUsername(FormHome.account.getUsername()).getMaThuThu();
				
				labelMessage.set(0, validateForm.get(0).validate(String.valueOf(maSach)));
				labelMessage.set(1, validateForm.get(1).validate(tenSach));
				labelMessage.set(2, validateForm.get(2).validate(tacGia));
				labelMessage.set(3, validateForm.get(3).validate(nhaXuatBan));
				labelMessage.set(4, validateForm.get(4).validate(String.valueOf(donGia)));
				labelMessage.set(5, validateForm.get(5).validate(String.valueOf(soLuong)));
				labelMessage.set(6, validateForm.get(6).validate(theLoai));
				
				boolean haveError = false;
				for (int i = 0; i < 7; i++) {
					if (labelMessage.get(i) != null) {
						haveError = true;
						labelErrors.get(i).setText(labelMessage.get(i));
						labelErrors.get(i).setVisible(true);
					}
					else {
						labelErrors.get(i).setVisible(false);
					}
				}
				
				if (!haveError) {
					Book books = new Book(maSach, tenSach, tacGia, nhaXuatBan, donGia, soLuong, theLoai, maThuThu);
					BookBo.insertBook(books);
					try {
						ArrayList<Book> lst = BookBo.getAllBook();
						parent.loadTable(lst);
					} catch (Exception e2) {
						e2.getStackTrace();
					}
				}
			}
		});
		
		JLabel lblThmSch = new JLabel("Thêm sách");
		lblThmSch.setHorizontalAlignment(SwingConstants.CENTER);
		lblThmSch.setFont(new Font("Segoe UI", Font.BOLD, 19));
		lblThmSch.setBounds(120, 15, 110, 30);
		contentPane.add(lblThmSch);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(UIManager.getColor("Button.shadow"));
		separator.setBounds(20, 60, 300, 2);
		contentPane.add(separator);
		
		labelErrorMaSach = new JLabel("");
		labelErrorMaSach.setHorizontalAlignment(SwingConstants.TRAILING);
		labelErrorMaSach.setForeground(Color.RED);
		labelErrorMaSach.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		labelErrorMaSach.setBounds(120, 80, 200, 16);
		contentPane.add(labelErrorMaSach);
		
		labelErrorTenSach = new JLabel("");
		labelErrorTenSach.setHorizontalAlignment(SwingConstants.TRAILING);
		labelErrorTenSach.setForeground(Color.RED);
		labelErrorTenSach.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		labelErrorTenSach.setBounds(120, 142, 200, 16);
		contentPane.add(labelErrorTenSach);
		
		labelErrorTacGia = new JLabel("");
		labelErrorTacGia.setHorizontalAlignment(SwingConstants.TRAILING);
		labelErrorTacGia.setForeground(Color.RED);
		labelErrorTacGia.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		labelErrorTacGia.setBounds(120, 202, 200, 16);
		contentPane.add(labelErrorTacGia);
		
		labelErrorNXB = new JLabel("");
		labelErrorNXB.setHorizontalAlignment(SwingConstants.TRAILING);
		labelErrorNXB.setForeground(Color.RED);
		labelErrorNXB.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		labelErrorNXB.setBounds(120, 262, 200, 16);
		contentPane.add(labelErrorNXB);
		
		labelErrorDonGia = new JLabel("");
		labelErrorDonGia.setHorizontalAlignment(SwingConstants.TRAILING);
		labelErrorDonGia.setForeground(Color.RED);
		labelErrorDonGia.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		labelErrorDonGia.setBounds(230, 322, 90, 16);
		contentPane.add(labelErrorDonGia);
		
		labelErrorSoLuong = new JLabel("");
		labelErrorSoLuong.setHorizontalAlignment(SwingConstants.TRAILING);
		labelErrorSoLuong.setForeground(Color.RED);
		labelErrorSoLuong.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		labelErrorSoLuong.setBounds(75, 320, 90, 16);
		contentPane.add(labelErrorSoLuong);
		
		labelErrorTheLoai = new JLabel("");
		labelErrorTheLoai.setHorizontalAlignment(SwingConstants.TRAILING);
		labelErrorTheLoai.setForeground(Color.RED);
		labelErrorTheLoai.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		labelErrorTheLoai.setBounds(120, 382, 200, 16);
		contentPane.add(labelErrorTheLoai);
	}
}
