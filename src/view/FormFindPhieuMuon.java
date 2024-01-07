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
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

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
		setBounds(100, 100, 400, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelTitle = new JLabel("Tìm kiếm phiếu mượn");
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
		labelTitle.setBounds(90, 15, 220, 30);
		contentPane.add(labelTitle);
		
		JLabel labelMaPhieuMuon = new JLabel("Mã phiếu mượn");
		labelMaPhieuMuon.setIcon(new ImageIcon(FormFindPhieuMuon.class.getResource("/icons/id-card.png")));
		labelMaPhieuMuon.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelMaPhieuMuon.setBounds(20, 80, 120, 16);
		contentPane.add(labelMaPhieuMuon);
		
		inputMaPhieuMuon = new JTextField();
		inputMaPhieuMuon.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		inputMaPhieuMuon.setColumns(10);
		inputMaPhieuMuon.setBounds(20, 100, 240, 25);
		contentPane.add(inputMaPhieuMuon);
		
		dateChooserdateFrom = new JDateChooser();
		dateChooserdateFrom.setDateFormatString("dd/MM/yyyy");
		dateChooserdateFrom.setBounds(20, 160, 155, 25);
		contentPane.add(dateChooserdateFrom);
		
		JLabel labeldateFrom = new JLabel("Thời gian mượn");
		labeldateFrom.setIcon(new ImageIcon(FormFindPhieuMuon.class.getResource("/icons/deadline.png")));
		labeldateFrom.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labeldateFrom.setBounds(20, 140, 120, 16);
		contentPane.add(labeldateFrom);
		
		JLabel lbln = new JLabel("đến");
		lbln.setHorizontalAlignment(SwingConstants.CENTER);
		lbln.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lbln.setBounds(180, 160, 30, 25);
		contentPane.add(lbln);
		
		dateChooserdateTo = new JDateChooser();
		dateChooserdateTo.setDateFormatString("dd/MM/yyyy");
		dateChooserdateTo.setBounds(215, 160, 155, 25);
		contentPane.add(dateChooserdateTo);
		
		JLabel labelTrangThai = new JLabel("Trạng thái");
		labelTrangThai.setIcon(new ImageIcon(FormFindPhieuMuon.class.getResource("/icons/check-list.png")));
		labelTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelTrangThai.setBounds(270, 80, 100, 16);
		contentPane.add(labelTrangThai);
		
		comboTrangThai = new JComboBox<String>();
		comboTrangThai.setModel(new DefaultComboBoxModel<String>(new String[] {"Tất cả", "Chưa trả", "Đã trả"}));
		comboTrangThai.setSelectedIndex(0);
		comboTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboTrangThai.setBounds(270, 100, 100, 25);
		contentPane.add(comboTrangThai);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(160, 160, 160));
		separator.setBounds(20, 60, 350, 2);
		contentPane.add(separator);
		
		JLabel labelMaThuThu = new JLabel("Mã thủ thư");
		labelMaThuThu.setIcon(new ImageIcon(FormFindPhieuMuon.class.getResource("/icons/user.png")));
		labelMaThuThu.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelMaThuThu.setBounds(20, 200, 100, 16);
		contentPane.add(labelMaThuThu);
		
		inputMaThuThu = new JTextField();
		inputMaThuThu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		inputMaThuThu.setColumns(10);
		inputMaThuThu.setBounds(20, 220, 170, 25);
		contentPane.add(inputMaThuThu);
		
		JLabel labelMaDocGia = new JLabel("Mã đọc giả");
		labelMaDocGia.setIcon(new ImageIcon(FormFindPhieuMuon.class.getResource("/icons/user.png")));
		labelMaDocGia.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelMaDocGia.setBounds(200, 200, 100, 16);
		contentPane.add(labelMaDocGia);
		
		inputMaDocGia = new JTextField();
		inputMaDocGia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		inputMaDocGia.setColumns(10);
		inputMaDocGia.setBounds(200, 220, 170, 25);
		contentPane.add(inputMaDocGia);
		
		buttonFind = new JButton("Tìm kiếm");
		buttonFind.setIcon(new ImageIcon(FormFindPhieuMuon.class.getResource("/icons/search.png")));
		buttonFind.setFont(new Font("Segoe UI", Font.BOLD, 16));
		buttonFind.setBounds(130, 260, 140, 35);
		contentPane.add(buttonFind);
	}
	
	public boolean check_date() {
		if (dateChooserdateFrom.getDate() != null) {
			return dateChooserdateTo.getDate().after(dateChooserdateFrom.getDate());
		}
		return true;
	}
}
