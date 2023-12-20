package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import bo.PhieuMuonBo;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.awt.event.ActionEvent;

public class FormFindPhieuMuon extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputMaPhieuMuon;
	private JComboBox<String> comboTrangThai;
	private JDateChooser dateChooserdateFrom;
	private JDateChooser dateChooserdateTo;
	private JTextField inputMaThuThu;
	private JTextField inputMaDocGia;
	private JButton buttonFind;
	
	private PanelPhieuMuon parent;
	private FormFindPhieuMuon thisForm = this;
	
	/**
	 * Create the frame.
	 */
	public FormFindPhieuMuon(PanelPhieuMuon parent) {
		this.parent = parent;
		createContents();
		findDocGia();
	}
	
	public void findDocGia() {
		buttonFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String MaPhieuMuon = inputMaPhieuMuon.getText().strip();
				int TrangThai = comboTrangThai.getSelectedIndex();
				Date dateFrom = dateChooserdateFrom.getDate();
				Date dateTo = dateChooserdateTo.getDate();
				String MaThuThu = inputMaThuThu.getText().strip();
				String MaDocGia = inputMaDocGia.getText().strip();
				
				parent.loadTableListPhieuMuon(PhieuMuonBo.findPhieuMuon(MaPhieuMuon, TrangThai - 1, dateFrom, dateTo, MaThuThu, MaDocGia));
				thisForm.dispatchEvent(new WindowEvent(thisForm, WindowEvent.WINDOW_CLOSING));
			}
		});
	}
	
	private void createContents() {
		setResizable(false);
		setTitle("Tìm kiếm phiếu mượn - Phần mềm quản lý thư viện");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelTitle = new JLabel("Tìm kiếm phiếu mượn");
		labelTitle.setFont(new Font("Segoe UI", Font.BOLD, 19));
		labelTitle.setBounds(90, 10, 211, 30);
		contentPane.add(labelTitle);
		
		JLabel labelMaPhieuMuon = new JLabel("Mã phiếu mượn");
		labelMaPhieuMuon.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		labelMaPhieuMuon.setBounds(20, 60, 100, 16);
		contentPane.add(labelMaPhieuMuon);
		
		inputMaPhieuMuon = new JTextField();
		inputMaPhieuMuon.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		inputMaPhieuMuon.setColumns(10);
		inputMaPhieuMuon.setBounds(20, 80, 152, 25);
		contentPane.add(inputMaPhieuMuon);
		
		dateChooserdateFrom = new JDateChooser();
		dateChooserdateFrom.setDateFormatString("dd/MM/yyyy");
		dateChooserdateFrom.setBounds(20, 140, 152, 25);
		contentPane.add(dateChooserdateFrom);
		
		JLabel labeldateFrom = new JLabel("Thời gian mượn");
		labeldateFrom.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		labeldateFrom.setBounds(20, 116, 84, 25);
		contentPane.add(labeldateFrom);
		
		JLabel lbln = new JLabel("đến");
		lbln.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lbln.setBounds(182, 140, 30, 25);
		contentPane.add(lbln);
		
		dateChooserdateTo = new JDateChooser();
		dateChooserdateTo.setDateFormatString("dd/MM/yyyy");
		dateChooserdateTo.setBounds(216, 140, 144, 25);
		contentPane.add(dateChooserdateTo);
		
		JLabel labelTrangThai = new JLabel("Trạng thái");
		labelTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		labelTrangThai.setBounds(210, 60, 134, 16);
		contentPane.add(labelTrangThai);
		
		comboTrangThai = new JComboBox<String>();
		comboTrangThai.setModel(new DefaultComboBoxModel<String>(new String[] {"Tất cả", "Chưa trả", "Đã trả"}));
		comboTrangThai.setSelectedIndex(0);
		comboTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboTrangThai.setBounds(210, 80, 150, 25);
		contentPane.add(comboTrangThai);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(160, 160, 160));
		separator.setBounds(20, 47, 340, 2);
		contentPane.add(separator);
		
		JLabel labelMaThuThu = new JLabel("Mã thủ thư");
		labelMaThuThu.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		labelMaThuThu.setBounds(20, 181, 70, 16);
		contentPane.add(labelMaThuThu);
		
		inputMaThuThu = new JTextField();
		inputMaThuThu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		inputMaThuThu.setColumns(10);
		inputMaThuThu.setBounds(20, 201, 152, 25);
		contentPane.add(inputMaThuThu);
		
		JLabel labelMaDocGia = new JLabel("Mã đọc giả");
		labelMaDocGia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		labelMaDocGia.setBounds(216, 181, 70, 16);
		contentPane.add(labelMaDocGia);
		
		inputMaDocGia = new JTextField();
		inputMaDocGia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		inputMaDocGia.setColumns(10);
		inputMaDocGia.setBounds(216, 201, 144, 25);
		contentPane.add(inputMaDocGia);
		
		buttonFind = new JButton("Tìm kiếm");
		buttonFind.setFont(new Font("Segoe UI", Font.BOLD, 14));
		buttonFind.setBounds(134, 255, 116, 30);
		contentPane.add(buttonFind);
	}
	
	public boolean check_date() {
		if (dateChooserdateFrom.getDate() != null) {
			return dateChooserdateTo.getDate().after(dateChooserdateFrom.getDate());
		}
		return true;
	}
}
