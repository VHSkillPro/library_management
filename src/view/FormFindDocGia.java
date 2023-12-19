package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import bo.DocGiaBo;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.awt.event.ActionEvent;

public class FormFindDocGia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputMaDocGia;
	private JTextField inputHoTen;
	private JComboBox<String> comboGioiTinh;
	private JDateChooser dateChooserFromNgaySinh;
	private JDateChooser dateChooserToNgaySinh;
	private JTextField inputEmail;
	private JTextField inputSoDienThoai;
	private JButton buttonFind;
	
	private PanelDocGia parent;
	private FormFindDocGia thisForm = this;
	
	/**
	 * Create the frame.
	 */
	public FormFindDocGia(PanelDocGia parent) {
		this.parent = parent;
		createContents();
		findDocGia();
	}
	
	public void findDocGia() {
		buttonFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maDocGia = inputMaDocGia.getText().strip();
				String hoTen = inputHoTen.getText().strip();
				int gioiTinh = comboGioiTinh.getSelectedIndex();
				Date fromNgaySinh = dateChooserFromNgaySinh.getDate();
				Date toNgaySinh = dateChooserToNgaySinh.getDate();
				String email = inputEmail.getText().strip();
				String soDienThoai = inputSoDienThoai.getText().strip();
				
				int _gioiTinh = -1;
				if (gioiTinh == 1) _gioiTinh = 1;
				else if (gioiTinh == 2) _gioiTinh = 0;
				
				parent.loadTable(DocGiaBo.findDocGia(maDocGia, hoTen, _gioiTinh, fromNgaySinh, toNgaySinh, email, soDienThoai));
				thisForm.dispatchEvent(new WindowEvent(thisForm, WindowEvent.WINDOW_CLOSING));
			}
		});
	}
	
	private void createContents() {
		setResizable(false);
		setTitle("Tìm kiếm độc giả - Phần mềm quản lý thư viện");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelTitle = new JLabel("Tìm kiếm độc giả");
		labelTitle.setFont(new Font("Segoe UI", Font.BOLD, 19));
		labelTitle.setBounds(121, 10, 180, 30);
		contentPane.add(labelTitle);
		
		JLabel labelMaDocGia = new JLabel("Mã độc giả");
		labelMaDocGia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		labelMaDocGia.setBounds(20, 60, 70, 16);
		contentPane.add(labelMaDocGia);
		
		inputMaDocGia = new JTextField();
		inputMaDocGia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		inputMaDocGia.setColumns(10);
		inputMaDocGia.setBounds(20, 80, 340, 25);
		contentPane.add(inputMaDocGia);
		
		inputHoTen = new JTextField();
		inputHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		inputHoTen.setColumns(10);
		inputHoTen.setBounds(20, 140, 240, 25);
		contentPane.add(inputHoTen);
		
		JLabel labelHoTen = new JLabel("Họ và tên");
		labelHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		labelHoTen.setBounds(20, 120, 70, 16);
		contentPane.add(labelHoTen);
		
		dateChooserFromNgaySinh = new JDateChooser();
		dateChooserFromNgaySinh.setDateFormatString("dd/MM/yyyy");
		dateChooserFromNgaySinh.setBounds(100, 185, 110, 25);
		contentPane.add(dateChooserFromNgaySinh);
		
		JLabel labelFromNgaySinh = new JLabel("Ngày sinh từ");
		labelFromNgaySinh.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		labelFromNgaySinh.setBounds(20, 185, 70, 25);
		contentPane.add(labelFromNgaySinh);
		
		JLabel lbln = new JLabel("đến");
		lbln.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lbln.setBounds(220, 185, 30, 25);
		contentPane.add(lbln);
		
		dateChooserToNgaySinh = new JDateChooser();
		dateChooserToNgaySinh.setDateFormatString("dd/MM/yyyy");
		dateChooserToNgaySinh.setBounds(250, 185, 110, 25);
		contentPane.add(dateChooserToNgaySinh);
		
		JLabel labelGioiTinh = new JLabel("Giới tính");
		labelGioiTinh.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		labelGioiTinh.setBounds(280, 120, 63, 16);
		contentPane.add(labelGioiTinh);
		
		comboGioiTinh = new JComboBox<String>();
		comboGioiTinh.setModel(new DefaultComboBoxModel<String>(new String[] {"Tất cả", "Nam", "Nữ"}));
		comboGioiTinh.setSelectedIndex(0);
		comboGioiTinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboGioiTinh.setBounds(280, 140, 80, 25);
		contentPane.add(comboGioiTinh);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(160, 160, 160));
		separator.setBounds(20, 47, 340, 2);
		contentPane.add(separator);
		
		JLabel labelEmail = new JLabel("Email");
		labelEmail.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		labelEmail.setBounds(20, 220, 70, 16);
		contentPane.add(labelEmail);
		
		inputEmail = new JTextField();
		inputEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		inputEmail.setColumns(10);
		inputEmail.setBounds(20, 240, 190, 25);
		contentPane.add(inputEmail);
		
		JLabel labelSoDienThoai = new JLabel("Số điện thoại");
		labelSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		labelSoDienThoai.setBounds(230, 220, 70, 16);
		contentPane.add(labelSoDienThoai);
		
		inputSoDienThoai = new JTextField();
		inputSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		inputSoDienThoai.setColumns(10);
		inputSoDienThoai.setBounds(230, 240, 130, 25);
		contentPane.add(inputSoDienThoai);
		
		buttonFind = new JButton("Tìm kiếm");
		buttonFind.setFont(new Font("Segoe UI", Font.BOLD, 14));
		buttonFind.setBounds(150, 290, 100, 30);
		contentPane.add(buttonFind);
	}
}
