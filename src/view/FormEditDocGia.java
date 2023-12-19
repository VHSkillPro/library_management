package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.DocGia;
import bo.DocGiaBo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.awt.event.ActionEvent;

public class FormEditDocGia extends JFrame {

	private static final long serialVersionUID = 1L;
	private DocGia docGia;
	private JPanel contentPane;

	private PanelDocGia parent;
	private JTextField inputHoTen;
	private JTextField inputEmail;
	private JTextField inputSoDienThoai;
	private JTextField inputDiaChi;
	private JComboBox<String> comboGioiTinh;
	private JDateChooser dateChooserNgaySinh;
	
	private boolean enableEditor = false;
	private JButton buttonEditEnable;
	private JButton buttonExist;
	
	private FormEditDocGia thisForm = this;
	
	/**
	 * Create the frame.
	 */
	public FormEditDocGia(int maDocGia, PanelDocGia parent) {
		this.parent = parent;
		this.docGia = DocGiaBo.getDocGiaByMaDocGia(maDocGia);
		createContents();
		changeModeEditor();
	}
	
	public void changeModeEditor() {
		buttonEditEnable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (enableEditor == false) {
					enableEditor = true;
					inputHoTen.setEditable(true);
					comboGioiTinh.setEnabled(true);
					dateChooserNgaySinh.setEnabled(true);
					inputEmail.setEditable(true);
					inputSoDienThoai.setEditable(true);
					inputDiaChi.setEditable(true);
					buttonExist.setEnabled(false);
					buttonEditEnable.setText("Lưu");
				}
				else {
					int decision = JOptionPane.showConfirmDialog(null, "Bạn có muốn lưu ?");
			
					if (decision == 0) {
						String hoTen = inputHoTen.getText();
						boolean gioiTinh = comboGioiTinh.getSelectedIndex() == 0 ? true : false;
						Date ngaySinh = dateChooserNgaySinh.getDate();
						String email = inputEmail.getText();
						String soDienThoai = inputSoDienThoai.getText();
						String diaChi = inputDiaChi.getText();
						
						if (DocGiaBo.updateDocGia(docGia.getMaDocGia(), hoTen, gioiTinh, ngaySinh, email, soDienThoai, diaChi)) {
							JOptionPane.showMessageDialog(null, "Lưu thành công");
							parent.loadTable(DocGiaBo.getAllDocGia());
						}
						else {
							JOptionPane.showMessageDialog(null, "Lưu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
						}
					}
					
					if (decision != 2) {
						enableEditor = false;
						inputHoTen.setEditable(false);
						comboGioiTinh.setEnabled(false);
						dateChooserNgaySinh.setEnabled(false);
						inputEmail.setEditable(false);
						inputSoDienThoai.setEditable(false);
						inputDiaChi.setEditable(false);
						buttonExist.setEnabled(true);
						buttonEditEnable.setText("Chỉnh sửa");
					}
					
					docGia = DocGiaBo.getDocGiaByMaDocGia(docGia.getMaDocGia());
					inputHoTen.setText(docGia.getHoTen());
					comboGioiTinh.setSelectedIndex(docGia.getGioiTinh() ? 0 : 1);
					dateChooserNgaySinh.setDate(docGia.getNgaySinh());
					inputEmail.setText(docGia.getEmail());
					inputSoDienThoai.setText(docGia.getSoDienThoai());
					inputDiaChi.setText(docGia.getDiaChi());
				}
			}
		});
	}
	
	private void createContents() {
		setTitle("Thông tin độc giả - Phần mềm quản lý thư viện");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 410);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelTitle = new JLabel("Thông tin độc giả");
		labelTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
		labelTitle.setBounds(150, 15, 180, 30);
		contentPane.add(labelTitle);
		
		JLabel labelMaDocGia = new JLabel("Mã độc giả:");
		labelMaDocGia.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMaDocGia.setFont(new Font("Segoe UI", Font.BOLD, 14));
		labelMaDocGia.setBounds(20, 70, 90, 25);
		contentPane.add(labelMaDocGia);
		
		JLabel labelMaDocGiaValue = new JLabel(String.valueOf(docGia.getMaDocGia()));
		labelMaDocGiaValue.setHorizontalAlignment(SwingConstants.LEFT);
		labelMaDocGiaValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		labelMaDocGiaValue.setBounds(120, 70, 90, 25);
		contentPane.add(labelMaDocGiaValue);
		
		JLabel labelFullname = new JLabel("Họ và tên:");
		labelFullname.setHorizontalAlignment(SwingConstants.RIGHT);
		labelFullname.setFont(new Font("Segoe UI", Font.BOLD, 14));
		labelFullname.setBounds(20, 110, 90, 25);
		contentPane.add(labelFullname);
		
		inputHoTen = new JTextField();
		inputHoTen.setEditable(false);
		inputHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		inputHoTen.setBounds(120, 110, 340, 25);
		inputHoTen.setText(docGia.getHoTen());
		contentPane.add(inputHoTen);
		inputHoTen.setColumns(10);
		
		JLabel labelGender = new JLabel("Giới tính:");
		labelGender.setHorizontalAlignment(SwingConstants.RIGHT);
		labelGender.setFont(new Font("Segoe UI", Font.BOLD, 14));
		labelGender.setBounds(294, 150, 80, 25);
		contentPane.add(labelGender);
		
		comboGioiTinh = new JComboBox<String>();
		comboGioiTinh.setEnabled(false);
		comboGioiTinh.setModel(new DefaultComboBoxModel<String>(new String[] {"Nam", "Nữ"}));
		comboGioiTinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboGioiTinh.setBounds(384, 150, 76, 25);
		comboGioiTinh.setSelectedIndex(docGia.getGioiTinh() ? 0 : 1);
		contentPane.add(comboGioiTinh);
		
		JLabel labelBirthdate = new JLabel("Ngày sinh:");
		labelBirthdate.setHorizontalAlignment(SwingConstants.RIGHT);
		labelBirthdate.setFont(new Font("Segoe UI", Font.BOLD, 14));
		labelBirthdate.setBounds(20, 150, 90, 25);
		contentPane.add(labelBirthdate);
		
		dateChooserNgaySinh = new JDateChooser(docGia.getNgaySinh(), "dd/MM/yyyy");
		dateChooserNgaySinh.setEnabled(false);
		dateChooserNgaySinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		dateChooserNgaySinh.setBounds(120, 150, 180, 25);
		contentPane.add(dateChooserNgaySinh);
		
		JLabel labelEmail = new JLabel("Email:");
		labelEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		labelEmail.setFont(new Font("Segoe UI", Font.BOLD, 14));
		labelEmail.setBounds(20, 190, 90, 25);
		contentPane.add(labelEmail);
		
		inputEmail = new JTextField();
		inputEmail.setEditable(false);
		inputEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		inputEmail.setColumns(10);
		inputEmail.setBounds(120, 190, 340, 25);
		inputEmail.setText(docGia.getEmail());
		contentPane.add(inputEmail);
		
		JLabel labelSoDienThoai = new JLabel("Số điện thoại:");
		labelSoDienThoai.setHorizontalAlignment(SwingConstants.RIGHT);
		labelSoDienThoai.setFont(new Font("Segoe UI", Font.BOLD, 14));
		labelSoDienThoai.setBounds(10, 230, 100, 25);
		contentPane.add(labelSoDienThoai);
		
		inputSoDienThoai = new JTextField();
		inputSoDienThoai.setEditable(false);
		inputSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		inputSoDienThoai.setColumns(10);
		inputSoDienThoai.setBounds(120, 230, 340, 25);
		inputSoDienThoai.setText(docGia.getSoDienThoai());
		contentPane.add(inputSoDienThoai);
		
		JLabel labelDiaChi = new JLabel("Địa chỉ:");
		labelDiaChi.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDiaChi.setFont(new Font("Segoe UI", Font.BOLD, 14));
		labelDiaChi.setBounds(20, 270, 90, 25);
		contentPane.add(labelDiaChi);
		
		inputDiaChi = new JTextField();
		inputDiaChi.setEditable(false);
		inputDiaChi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		inputDiaChi.setColumns(10);
		inputDiaChi.setBounds(120, 270, 340, 25);
		inputDiaChi.setText(docGia.getDiaChi());
		contentPane.add(inputDiaChi);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(160, 160, 160));
		separator.setBackground(new Color(255, 255, 255));
		separator.setBounds(20, 61, 440, 2);
		contentPane.add(separator);
		
		buttonEditEnable = new JButton("Chỉnh sửa");
		buttonEditEnable.setFont(new Font("Segoe UI", Font.BOLD, 14));
		buttonEditEnable.setBounds(240, 320, 120, 30);
		contentPane.add(buttonEditEnable);
		
		buttonExist = new JButton("Quay lại");
		buttonExist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisForm.dispatchEvent(new WindowEvent(thisForm, WindowEvent.WINDOW_CLOSING));
			}
		});
		buttonExist.setFont(new Font("Segoe UI", Font.BOLD, 14));
		buttonExist.setBounds(130, 320, 100, 30);
		contentPane.add(buttonExist);
	}
}
