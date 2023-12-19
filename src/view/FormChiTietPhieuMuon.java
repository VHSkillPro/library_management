package view;
import java.awt.EventQueue;
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
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

public class FormChiTietPhieuMuon extends JFrame {

	private JPanel contentPane;
	private JFrame parent;
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
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public void content() {
		setTitle("Chi Tiết Phiếu Mượn");
		setFont(new Font("Segoe UI", Font.PLAIN, 12));
		setResizable(false);
		setBounds(100, 100, 830, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelPhieuMuon = new JLabel("Phiếu mượn #" + phieuMuon.getMaPhieuMuon());
		labelPhieuMuon.setFont(new Font("Segoe UI", Font.BOLD, 21));
		labelPhieuMuon.setBounds(66, 10, 204, 50);
		contentPane.add(labelPhieuMuon);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 70, 784, 2);
		contentPane.add(separator);
		
		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtMaKhachHang.setEditable(false);
		txtMaKhachHang.setColumns(10);
		txtMaKhachHang.setText(Integer.valueOf(docGia.getMaDocGia()).toString());
		txtMaKhachHang.setBounds(20, 123, 233, 25);
		txtMaKhachHang.getCaret().setVisible(false);
		contentPane.add(txtMaKhachHang);
		
		JLabel lblmaKhachHang = new JLabel("Mã khách hàng");
		lblmaKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblmaKhachHang.setBounds(20, 103, 118, 16);
		lblmaKhachHang.setIcon(new ImageIcon(FormChiTietPhieuMuon.class.getResource("/icons/user.png")));
		contentPane.add(lblmaKhachHang);
		
		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtTenKhachHang.setEditable(false);
//		txtTenKhachHang.setColumns(10);
		txtTenKhachHang.getCaret().setVisible(false);
		txtTenKhachHang.setText(docGia.getHoTen());
		txtTenKhachHang.setBounds(20, 179, 233, 25);
		contentPane.add(txtTenKhachHang);
		
		JLabel lblTenKhachHang = new JLabel("Tên khách hàng");
		lblTenKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblTenKhachHang.setIcon(new ImageIcon(FormChiTietPhieuMuon.class.getResource("/icons/id-card.png")));
		lblTenKhachHang.setBounds(20, 159, 118, 16);
		contentPane.add(lblTenKhachHang);
		
		JLabel lblPhone = new JLabel("Số điện thoại");
		lblPhone.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblPhone.setBounds(20, 215, 118, 16);
		lblPhone.setIcon(new ImageIcon(FormChiTietPhieuMuon.class.getResource("/icons/phone-call.png")));
		contentPane.add(lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtPhone.setEditable(false);
		txtPhone.setBounds(20, 235, 233, 25);
		txtPhone.setText(docGia.getSoDienThoai());
		txtPhone.getCaret().setVisible(false);
		contentPane.add(txtPhone);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(263, 71, 9, 422);
		contentPane.add(separator_1);
		
		JLabel lbllistBook = new JLabel("Danh sách mượn");
		lbllistBook.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lbllistBook.setBounds(284, 103, 204, 16);
		lbllistBook.setIcon(new ImageIcon(FormChiTietPhieuMuon.class.getResource("/icons/ballot.png")));
		contentPane.add(lbllistBook);
		
		JButton btnInHoaDon = new JButton("In phiếu mượn");
		btnInHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnInHoaDon.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnInHoaDon.setBounds(43, 396, 176, 33);
		btnInHoaDon.setIcon(new ImageIcon(FormChiTietPhieuMuon.class.getResource("/icons/printer.png")));
		contentPane.add(btnInHoaDon);
		
		JLabel lblNguoiTao = new JLabel("Người tạo : " + thuThu.getHoTen() + " #" + thuThu.getMaThuThu());
		lblNguoiTao.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
		lblNguoiTao.setBounds(476, 11, 244, 50);
		contentPane.add(lblNguoiTao);
		
		JLabel lblThoiGian = new JLabel("Thời hạn mượn");
		lblThoiGian.setIcon(new ImageIcon("C:\\Users\\doan2\\eclipse-workspace\\library_management\\src\\icons\\deadline.png"));
		lblThoiGian.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblThoiGian.setBounds(20, 271, 118, 16);
		contentPane.add(lblThoiGian);
		
		txtThoiHan = new JTextField();
		txtThoiHan.setText((String) null);
		txtThoiHan.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtThoiHan.setEditable(false);
		txtThoiHan.setBounds(20, 291, 233, 25);
		String ngayTra = "?";
		try {
			ngayTra = phieuMuon.getNgayTra().toString();
		} catch (Exception e) {
			
		}
		txtThoiHan.setText(phieuMuon.getNgayMuon().toString() + " - " + ngayTra);
		contentPane.add(txtThoiHan);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(282, 123, 522, 251);
		contentPane.add(scrollPane);
		
		table = new JTable() {
			public boolean isCellEditable(int row, int column) {                
                return false;               
			};
		};
		scrollPane.setRowHeaderView(table);
		scrollPane.setViewportView(table);
		
		txtTrangThai = new JTextField();
		txtTrangThai.setText(phieuMuon.getTrangThai() ? "Đã trả" : "Chưa trả");
		txtTrangThai.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtTrangThai.setEditable(false);
		txtTrangThai.setBounds(20, 347, 233, 25);
		contentPane.add(txtTrangThai);
		JLabel lblTrngThi = new JLabel("Trạng Thái");
		lblTrngThi.setIcon(new ImageIcon(FormChiTietPhieuMuon.class.getResource("/icons/complete.png")));
		lblTrngThi.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblTrngThi.setBounds(20, 327, 118, 16);
		contentPane.add(lblTrngThi);
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
		setJTableColumnsWidth(table, 522 , 10, 40, 20, 30);
	}
	
	public FormChiTietPhieuMuon(Integer ma, Integer maKH, Integer maTT) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.docGia = DocGiaBo.getDocGiaByMaDocGia(maKH);
		this.thuThu = ThuThuBo.getThuThuByMaThuThu(maTT);
		this.phieuMuon = PhieuMuonBo.getPhieuMuonByMaPhieuMuon(ma);
		content();
		lst = ChiTietPhieuMuonBo.getCTPMByMaPhieuMuon(phieuMuon);
		loadTable(lst);
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
