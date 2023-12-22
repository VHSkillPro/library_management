package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

import bo.ThuThuBo;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.UIManager;

public class FormFindThuThu extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JButton buttonFind;
	private JTextField inputMaThuThu;
	private JTextField inputHoTen;
	private JTextField inputEmail;
	private JTextField inputSoDienThoai;
	private JComboBox<String> comboChucVu;
	private JComboBox<String> comboGioiTinh;
	private JDateChooser dateChooserFromNgaySinh;
	private JDateChooser dateChooserToNgaySinh;
	
	private PanelThuThu parent;
	private FormFindThuThu thisForm = this;
	
	/**
	 * Create the frame.
	 */
	public FormFindThuThu(PanelThuThu parent) {
		this.parent = parent;
		createContents();
		eventFindThuThu();
	}
	
	public void eventFindThuThu() {
		buttonFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maThuThu = inputMaThuThu.getText().strip();
				String hoTen = inputHoTen.getText().strip();
				int gioiTinh = comboGioiTinh.getSelectedIndex() - 1;
				if (gioiTinh >= 0) gioiTinh = 1 - gioiTinh; 
				Date fromNgaySinh = dateChooserFromNgaySinh.getDate();
				Date toNgaySinh = dateChooserToNgaySinh.getDate();
				String email = inputEmail.getText().strip();
				String soDienThoai = inputSoDienThoai.getText().strip();
				Integer chucVu = comboChucVu.getSelectedIndex();
				
				parent.loadTable(ThuThuBo.findThuThu(maThuThu, hoTen, gioiTinh, fromNgaySinh, toNgaySinh, email, soDienThoai, chucVu));
				thisForm.dispatchEvent(new WindowEvent(thisForm, WindowEvent.WINDOW_CLOSING));
			}
		});
	}
	
	private void createContents() {
		setResizable(false);
		setTitle("Tìm kiếm tài khoản - Phần mềm quản lý thư viện");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelTitle = new JLabel("Tìm kiếm thủ thư");
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setFont(new Font("Segoe UI", Font.BOLD, 19));
		labelTitle.setBounds(110, 15, 180, 30);
		contentPane.add(labelTitle);
		
		buttonFind = new JButton("Tìm kiếm");
		buttonFind.setIcon(new ImageIcon(FormFindThuThu.class.getResource("/icons/search.png")));
		buttonFind.setFont(new Font("Segoe UI", Font.BOLD, 14));
		buttonFind.setBounds(140, 325, 120, 30);
		contentPane.add(buttonFind);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(UIManager.getColor("Button.shadow"));
		separator.setBounds(20, 60, 340, 2);
		contentPane.add(separator);
		
		JLabel labelMaThuThu = new JLabel("Mã thủ thư");
		labelMaThuThu.setIcon(new ImageIcon(FormFindThuThu.class.getResource("/icons/user.png")));
		labelMaThuThu.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelMaThuThu.setBounds(20, 80, 100, 16);
		contentPane.add(labelMaThuThu);
		
		inputMaThuThu = new JTextField();
		inputMaThuThu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		inputMaThuThu.setColumns(10);
		inputMaThuThu.setBounds(20, 100, 200, 25);
		contentPane.add(inputMaThuThu);
		
		JLabel labelHoTen = new JLabel("Họ và tên");
		labelHoTen.setIcon(new ImageIcon(FormFindThuThu.class.getResource("/icons/id-card.png")));
		labelHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelHoTen.setBounds(20, 140, 100, 16);
		contentPane.add(labelHoTen);
		
		inputHoTen = new JTextField();
		inputHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		inputHoTen.setColumns(10);
		inputHoTen.setBounds(20, 160, 250, 25);
		contentPane.add(inputHoTen);
		
		JLabel labelGioiTinh = new JLabel("Giới tính");
		labelGioiTinh.setIcon(new ImageIcon(FormFindThuThu.class.getResource("/icons/gender.png")));
		labelGioiTinh.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelGioiTinh.setBounds(280, 140, 80, 16);
		contentPane.add(labelGioiTinh);
		
		comboGioiTinh = new JComboBox<String>();
		comboGioiTinh.setModel(new DefaultComboBoxModel<String>(new String[] {"Tất cả", "Nam", "Nữ"}));
		comboGioiTinh.setSelectedIndex(0);
		comboGioiTinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboGioiTinh.setBounds(280, 160, 80, 25);
		contentPane.add(comboGioiTinh);
		
		JLabel labelFromNgaySinh = new JLabel("Ngày sinh");
		labelFromNgaySinh.setIcon(new ImageIcon(FormFindThuThu.class.getResource("/icons/date-of-birth.png")));
		labelFromNgaySinh.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelFromNgaySinh.setBounds(20, 200, 100, 16);
		contentPane.add(labelFromNgaySinh);
		
		JLabel lblT = new JLabel("Từ");
		lblT.setHorizontalAlignment(SwingConstants.LEFT);
		lblT.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblT.setBounds(20, 220, 30, 25);
		contentPane.add(lblT);
		
		dateChooserFromNgaySinh = new JDateChooser();
		dateChooserFromNgaySinh.setDateFormatString("dd/MM/yyyy");
		dateChooserFromNgaySinh.setBounds(50, 220, 140, 25);
		contentPane.add(dateChooserFromNgaySinh);
		
		dateChooserToNgaySinh = new JDateChooser();
		dateChooserToNgaySinh.setDateFormatString("dd/MM/yyyy");
		dateChooserToNgaySinh.setBounds(220, 220, 140, 25);
		contentPane.add(dateChooserToNgaySinh);
		
		JLabel lbln = new JLabel("đến");
		lbln.setHorizontalAlignment(SwingConstants.CENTER);
		lbln.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lbln.setBounds(190, 220, 30, 25);
		contentPane.add(lbln);
		
		JLabel labelEmail = new JLabel("Email");
		labelEmail.setIcon(new ImageIcon(FormFindThuThu.class.getResource("/icons/mail.png")));
		labelEmail.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelEmail.setBounds(20, 260, 100, 16);
		contentPane.add(labelEmail);
		
		inputEmail = new JTextField();
		inputEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		inputEmail.setColumns(10);
		inputEmail.setBounds(20, 280, 190, 25);
		contentPane.add(inputEmail);
		
		JLabel labelSoDienThoai = new JLabel("Số điện thoại");
		labelSoDienThoai.setIcon(new ImageIcon(FormFindThuThu.class.getResource("/icons/phone-call.png")));
		labelSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelSoDienThoai.setBounds(220, 260, 100, 16);
		contentPane.add(labelSoDienThoai);
		
		inputSoDienThoai = new JTextField();
		inputSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		inputSoDienThoai.setColumns(10);
		inputSoDienThoai.setBounds(220, 280, 140, 25);
		contentPane.add(inputSoDienThoai);
		
		JLabel labelChucVu = new JLabel("Chức vụ");
		labelChucVu.setIcon(new ImageIcon(FormFindThuThu.class.getResource("/icons/user-management.png")));
		labelChucVu.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelChucVu.setBounds(230, 80, 80, 16);
		contentPane.add(labelChucVu);
		
		comboChucVu = new JComboBox<String>();
		comboChucVu.setModel(new DefaultComboBoxModel<String>(new String[] {"Tất cả", "Thủ thư", "Quản trị viên"}));
		comboChucVu.setSelectedIndex(0);
		comboChucVu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboChucVu.setBounds(230, 100, 130, 25);
		contentPane.add(comboChucVu);
	}
}
