package view;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import bean.ChiTietPhieuMuon;
import bean.DocGia;
import bean.PhieuMuon;
import bean.ThuThu;
import bo.BookBo;
import bo.ChiTietPhieuMuonBo;
import bo.DocGiaBo;
import bo.PhieuMuonBo;
import bo.ThuThuBo;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class FormChiTietPhieuMuon extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private DocGia docGia;
	private ThuThu thuThu;
	private PhieuMuon phieuMuon;
	private JTextField txtMaKhachHang;
	private JTextField txtTenKhachHang;
	private JTextField txtPhone;
	private JTextField txtThoiHan;
	private ArrayList<ChiTietPhieuMuon> lst;
	private JTable table;
	private JTextField txtTrangThai;
	private JTextField inputNguoiTao;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * Create the frame.
	 */
	public FormChiTietPhieuMuon(Integer ma, Integer maKH, Integer maTT) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.docGia = DocGiaBo.getDocGiaByMaDocGia(maKH);
		this.thuThu = ThuThuBo.getThuThuByMaThuThu(maTT);
		this.phieuMuon = PhieuMuonBo.getPhieuMuonByMaPhieuMuon(ma);
		content();
		lst = ChiTietPhieuMuonBo.getCTPMByMaPhieuMuon(phieuMuon);
		loadTable(lst);
	}
	
	public void content() {
		setTitle("Chi tiết phiếu mượn - Phần mềm quản lý thư viện");
		setFont(new Font("Segoe UI", Font.PLAIN, 12));
		setResizable(false);
		setBounds(100, 100, 800, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelPhieuMuon = new JLabel("Chi tiết phiếu mượn #" + String.valueOf(phieuMuon.getMaPhieuMuon()));
		labelPhieuMuon.setHorizontalAlignment(SwingConstants.CENTER);
		labelPhieuMuon.setFont(new Font("Segoe UI", Font.BOLD, 20));
		labelPhieuMuon.setBounds(275, 15, 250, 30);
		contentPane.add(labelPhieuMuon);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(160, 160, 160));
		separator.setBounds(20, 60, 750, 2);
		contentPane.add(separator);
		
		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtMaKhachHang.setEditable(false);
		txtMaKhachHang.setColumns(10);
		txtMaKhachHang.setText(Integer.valueOf(docGia.getMaDocGia()).toString());
		txtMaKhachHang.setBounds(20, 100, 200, 25);
		txtMaKhachHang.getCaret().setVisible(false);
		contentPane.add(txtMaKhachHang);
		
		JLabel lblmaKhachHang = new JLabel("Mã khách hàng");
		lblmaKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblmaKhachHang.setBounds(20, 80, 118, 16);
		lblmaKhachHang.setIcon(new ImageIcon(FormChiTietPhieuMuon.class.getResource("/icons/user.png")));
		contentPane.add(lblmaKhachHang);
		
		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtTenKhachHang.setEditable(false);
		txtTenKhachHang.getCaret().setVisible(false);
		txtTenKhachHang.setText(docGia.getHoTen());
		txtTenKhachHang.setBounds(20, 160, 200, 25);
		contentPane.add(txtTenKhachHang);
		
		JLabel lblTenKhachHang = new JLabel("Tên khách hàng");
		lblTenKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblTenKhachHang.setIcon(new ImageIcon(FormChiTietPhieuMuon.class.getResource("/icons/id-card.png")));
		lblTenKhachHang.setBounds(20, 140, 118, 16);
		contentPane.add(lblTenKhachHang);
		
		JLabel lblPhone = new JLabel("Số điện thoại");
		lblPhone.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblPhone.setBounds(20, 200, 118, 16);
		lblPhone.setIcon(new ImageIcon(FormChiTietPhieuMuon.class.getResource("/icons/phone-call.png")));
		contentPane.add(lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtPhone.setEditable(false);
		txtPhone.setBounds(20, 220, 200, 25);
		txtPhone.setText(docGia.getSoDienThoai());
		txtPhone.getCaret().setVisible(false);
		contentPane.add(txtPhone);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(160, 160, 160));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(240, 60, 2, 420);
		contentPane.add(separator_1);
		
		JLabel lbllistBook = new JLabel("Danh sách mượn");
		lbllistBook.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lbllistBook.setBounds(260, 80, 150, 16);
		lbllistBook.setIcon(new ImageIcon(FormChiTietPhieuMuon.class.getResource("/icons/ballot.png")));
		contentPane.add(lbllistBook);
		
		JButton btnInHoaDon = new JButton("In phiếu mượn");
		btnInHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnInHoaDon.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnInHoaDon.setBounds(45, 440, 160, 35);
		btnInHoaDon.setIcon(new ImageIcon(FormChiTietPhieuMuon.class.getResource("/icons/printer.png")));
		contentPane.add(btnInHoaDon);
		
		JLabel lblThoiGian = new JLabel("Thời hạn mượn");
		lblThoiGian.setIcon(new ImageIcon(FormChiTietPhieuMuon.class.getResource("/icons/deadline.png")));
		lblThoiGian.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblThoiGian.setBounds(20, 260, 120, 16);
		contentPane.add(lblThoiGian);
		
		txtThoiHan = new JTextField();
		txtThoiHan.setText((String) null);
		txtThoiHan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtThoiHan.setEditable(false);
		txtThoiHan.setBounds(20, 280, 200, 25);
		String ngayTra = "?";
		if (phieuMuon.getNgayTra() != null) {
			ngayTra = sdf.format(phieuMuon.getNgayTra());
		}
		txtThoiHan.setText(sdf.format(phieuMuon.getNgayMuon()) + " - " + ngayTra);
		contentPane.add(txtThoiHan);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(260, 100, 510, 380);
		contentPane.add(scrollPane);
		
		table = new JTable() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {                
                return false;               
			};
		};
		table.setRowHeight(30);
		scrollPane.setRowHeaderView(table);
		scrollPane.setViewportView(table);
		
		txtTrangThai = new JTextField();
		txtTrangThai.setText(phieuMuon.getTrangThai() ? "Đã trả" : "Chưa trả");
		txtTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtTrangThai.setEditable(false);
		txtTrangThai.setBounds(20, 340, 200, 25);
		contentPane.add(txtTrangThai);
		JLabel lblTrngThi = new JLabel("Trạng Thái");
		lblTrngThi.setIcon(new ImageIcon(FormChiTietPhieuMuon.class.getResource("/icons/check-list.png")));
		lblTrngThi.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblTrngThi.setBounds(20, 320, 118, 16);
		contentPane.add(lblTrngThi);
		
		JLabel labelNguoiTao = new JLabel("Người tạo");
		labelNguoiTao.setIcon(new ImageIcon(FormChiTietPhieuMuon.class.getResource("/icons/user.png")));
		labelNguoiTao.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelNguoiTao.setBounds(20, 380, 118, 16);
		contentPane.add(labelNguoiTao);
		
		inputNguoiTao = new JTextField();
		inputNguoiTao.setText(thuThu.getHoTen());
		inputNguoiTao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		inputNguoiTao.setEditable(false);
		inputNguoiTao.setBounds(20, 400, 200, 25);
		contentPane.add(inputNguoiTao);
	}
	
	public void loadTable(ArrayList<ChiTietPhieuMuon> lst) {
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("Mã sách");
		dtm.addColumn("Tên sách");
		dtm.addColumn("Số lượng");
		dtm.addColumn("Giá/Cuốn");
		
		Object[] o = new Object[4];
		for (ChiTietPhieuMuon ch : lst) {
			o[0] = ch.getMaSach();
			o[1] = BookBo.findByBookId(ch.getMaSach()).getTenSach();
			o[2] = ch.getSoLuong();
			o[3] = BookBo.findByBookId(ch.getMaSach()).getDonGia();
			dtm.addRow(o);
		}
		
		table.setModel(dtm);
		setJTableColumnsWidth(table, 510, 15, 50, 15, 20);
	}
	
	public void setJTableColumnsWidth(JTable table, int tablePreferredWidth, double... percentages) {
	    double total = 0;
	    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
	        total += percentages[i];
	    }
	 
	    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
	        TableColumn column = table.getColumnModel().getColumn(i);
	        column.setPreferredWidth((int)(tablePreferredWidth * (percentages[i] / total)));
	    }
	}
}
