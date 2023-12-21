package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import bean.Book;
import bean.ChiTietPhieuMuon;
import bean.DocGia;
import bean.PhieuMuon;
import bo.BookBo;
import bo.ChiTietPhieuMuonBo;
import bo.DocGiaBo;
import bo.PhieuMuonBo;
import bo.ThuThuBo;
import dao.Database;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.Color;

public class FormChinhSuaPhieuMuon extends JFrame {

	private JPanel contentPane;
	private JTable tableDSMuon;
	private JTextField txtMaTT;
	private JTextField txtTenKH;
	private JTextField txtMaKH;
	private JTextField txtTenTT;
	private JTable tableListBook;
	private ArrayList<Book> lstBook;
	private PhieuMuon phieuMuon;
	private PanelPhieuMuon parent;
	private ArrayList<ChiTietPhieuMuon> lstChiTietPhieuMuon;
	private boolean isCommit;
	JLabel lbDateOutofBound;
	private JDateChooser dateTo, dateFrom;
	private FormChinhSuaPhieuMuon thisFrm = this;
	private JComboBox cbStatus;
	/**
	 * Create the frame.
	 */
	public void content() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		setBounds(new Rectangle(0, 0, 1035, 745));
		getContentPane().setLayout(null);
		
		JLabel lbTaoHD = new JLabel("Chỉnh sửa phiếu mượn #" + phieuMuon.getMaPhieuMuon());
		lbTaoHD.setHorizontalAlignment(SwingConstants.CENTER);
		lbTaoHD.setFont(new Font("Segoe UI", Font.BOLD, 21));
		lbTaoHD.setBounds(362, 10, 302, 50);
		contentPane.add(lbTaoHD);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 71, 989, 1);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(619, 71, 12, 586);
		contentPane.add(separator_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(641, 138, 349, 519);
		contentPane.add(scrollPane);
		
		tableDSMuon = new JTable() {
			public boolean isCellEditable(int row, int column) {                
                return false;               
			};
		};
		scrollPane.setViewportView(tableDSMuon);
		
		JLabel lbllistBook_1 = new JLabel("Danh sách mượn");
		lbllistBook_1.setIcon(new ImageIcon(FormChinhSuaPhieuMuon.class.getResource("/icons/ballot.png")));
		lbllistBook_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lbllistBook_1.setBounds(641, 83, 204, 16);
		contentPane.add(lbllistBook_1);
		
		JButton btnConfirm = new JButton("Xác Nhận");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirm();
				thisFrm.dispose();
			}
		});
		btnConfirm.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnConfirm.setIcon(new ImageIcon(FormChinhSuaPhieuMuon.class.getResource("/icons/confirmation.png")));
		btnConfirm.setBounds(20, 668, 110, 30);
		contentPane.add(btnConfirm);
		
		txtMaTT = new JTextField();
		txtMaTT.setText(Integer.valueOf(phieuMuon.getMaThuThu()).toString());
		txtMaTT.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtMaTT.setEditable(false);
		txtMaTT.setColumns(10);
		txtMaTT.setBounds(343, 103, 253, 25);
		contentPane.add(txtMaTT);
		
		JLabel lblmaKhachHang = new JLabel("Mã thủ thư");
		lblmaKhachHang.setIcon(new ImageIcon(FormChinhSuaPhieuMuon.class.getResource("/icons/user.png")));
		lblmaKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblmaKhachHang.setBounds(343, 83, 118, 16);
		contentPane.add(lblmaKhachHang);
		
		JLabel lblTenKhachHang = new JLabel("Tên khách hàng");
		lblTenKhachHang.setIcon(new ImageIcon(FormChinhSuaPhieuMuon.class.getResource("/icons/id-card.png")));
		lblTenKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblTenKhachHang.setBounds(20, 152, 118, 16);
		contentPane.add(lblTenKhachHang);
		
		txtTenKH = new JTextField();
		txtTenKH.setText(DocGiaBo.getDocGiaByMaDocGia(phieuMuon.getMaDocGia()).getHoTen());
		txtTenKH.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtTenKH.setEditable(false);
		txtTenKH.setBounds(20, 172, 244, 25);
		contentPane.add(txtTenKH);
		
		JLabel lblmaKhachHang_1 = new JLabel("Mã khách hàng");
		lblmaKhachHang_1.setIcon(new ImageIcon(FormChinhSuaPhieuMuon.class.getResource("/icons/user.png")));
		lblmaKhachHang_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblmaKhachHang_1.setBounds(20, 83, 118, 16);
		contentPane.add(lblmaKhachHang_1);
		
		txtMaKH = new JTextField();
		txtMaKH.setText(Integer.valueOf(phieuMuon.getMaDocGia()).toString());
		txtMaKH.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtMaKH.setEditable(false);
		txtMaKH.setColumns(10);
		txtMaKH.setBounds(20, 103, 244, 25);
		contentPane.add(txtMaKH);
		
		txtTenTT = new JTextField();
		txtTenTT.setText(ThuThuBo.getThuThuByMaThuThu(phieuMuon.getMaThuThu()).getHoTen());
		txtTenTT.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtTenTT.setEditable(false);
		txtTenTT.setBounds(343, 172, 253, 25);
		contentPane.add(txtTenTT);
		
		JLabel lblTnThTh = new JLabel("Tên thủ thư");
		lblTnThTh.setIcon(new ImageIcon(FormChinhSuaPhieuMuon.class.getResource("/icons/id-card.png")));
		lblTnThTh.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblTnThTh.setBounds(343, 152, 118, 16);
		contentPane.add(lblTnThTh);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 345, 576, 312);
		contentPane.add(scrollPane_1);
		
		tableListBook = new JTable() {
			public boolean isCellEditable(int row, int column) {                
                return false;               
			};
		};
		scrollPane_1.setViewportView(tableListBook);
		
		JButton btnRefresh = new JButton("Làm mới");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reloadTableDsMuon();
				reloadTableListBook();
			}
		});
		btnRefresh.setIcon(new ImageIcon(FormChinhSuaPhieuMuon.class.getResource("/icons/refresh.png")));
		btnRefresh.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnRefresh.setBounds(486, 300, 110, 30);
		contentPane.add(btnRefresh);
		
		JButton btnSearch = new JButton("Tìm kiếm sách");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inp = JOptionPane.showInputDialog(btnSearch ,"Nhập mã sách cần tìm : ");
				if (inp != "" && inp != null) {
					lstBook.clear();
					Book t = BookBo.findByBookId(Integer.valueOf(inp));
					if (t != null) {
						lstBook.add(t);
					}
					loadTableSach(lstBook);
				}
			}
		});
		btnSearch.setIcon(new ImageIcon(FormChinhSuaPhieuMuon.class.getResource("/icons/search-interface-symbol.png")));
		btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnSearch.setBounds(343, 300, 135, 30);
		contentPane.add(btnSearch);
		
		JLabel lblSchTrongKho = new JLabel("Sách trong kho");
		lblSchTrongKho.setIcon(new ImageIcon(FormChinhSuaPhieuMuon.class.getResource("/icons/book.png")));
		lblSchTrongKho.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblSchTrongKho.setBounds(20, 321, 118, 16);
		contentPane.add(lblSchTrongKho);
		
		JButton btnDelBook = new JButton("Xóa sách");
		btnDelBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableDSMuon.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn sách muốn xóa !");
				} else {
					isCommit = false;
					TableModel md = tableDSMuon.getModel();
					int maSach = Integer.valueOf(md.getValueAt(row, 0).toString());
					int posSach = BookBo.findPosInArraybyId(maSach, lstBook);
					int posCTPM = ChiTietPhieuMuonBo.getPosInArraybyId(phieuMuon, BookBo.findByBookId(maSach), lstChiTietPhieuMuon);
					
					lstBook.get(posSach).setSoLuong(lstBook.get(posSach).getSoLuong() + lstChiTietPhieuMuon.get(posCTPM).getSoLuong());
					lstChiTietPhieuMuon.remove(posCTPM);

					loadTableDsMuon(lstChiTietPhieuMuon);
					loadTableSach(lstBook);
				}
			}
		});
		btnDelBook.setIcon(new ImageIcon(FormChinhSuaPhieuMuon.class.getResource("/icons/delete.png")));
		btnDelBook.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnDelBook.setBounds(880, 668, 110, 30);
		contentPane.add(btnDelBook);
		
		JButton btnAdjust = new JButton("Chỉnh số lượng");
		btnAdjust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableDSMuon.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn sách muốn chỉnh sửa !");
				} else {
					int num;
					try {
						num = Integer.valueOf(JOptionPane.showInputDialog("Nhập số lượng"));
					} catch (Exception e2) {
						num = -1;
					}
					if (num < 0) {
						JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ !");
					} else {
						isCommit = false;
						TableModel md = tableDSMuon.getModel();
						int maSach = Integer.valueOf(md.getValueAt(row, 0).toString());
						int cur = Integer.valueOf(md.getValueAt(row, 3).toString());
						int pos = BookBo.findPosInArraybyId(maSach, lstBook);
						
						int posCTPM = ChiTietPhieuMuonBo.getPosInArraybyId(phieuMuon, BookBo.findByBookId(maSach), lstChiTietPhieuMuon);
						
						Book hv = lstBook.get(pos);
						if (hv.getSoLuong() + cur >= num) {
							lstBook.get(pos).setSoLuong(hv.getSoLuong() - num + cur);
							lstChiTietPhieuMuon.get(posCTPM).setSoLuong(num);
							loadTableDsMuon(lstChiTietPhieuMuon);
							loadTableSach(lstBook);
						} else {
							JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ !");
						}
					}
				}
			}
		});
		
		btnAdjust.setIcon(new ImageIcon(FormChinhSuaPhieuMuon.class.getResource("/icons/maths.png")));
		btnAdjust.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnAdjust.setBounds(641, 668, 149, 30);
		contentPane.add(btnAdjust);
		
		JButton btnAddBook = new JButton("Thêm sách");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableListBook.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 cuốn sách !");
				} else {
					TableModel md = tableListBook.getModel();
					if (ChiTietPhieuMuonBo.checkDupicate(BookBo.findByBookId(Integer.valueOf(md.getValueAt(row, 0).toString())), lstChiTietPhieuMuon)) {
						JOptionPane.showMessageDialog(null, "Sách đã có trong giỏ !");
					} else {
						int posSach = BookBo.findPosInArraybyId(Integer.valueOf(md.getValueAt(row, 0).toString()), lstBook);
						if (lstBook.get(posSach).getSoLuong() == 0) {
							JOptionPane.showMessageDialog(null, "Không đủ số lượng !");
						} else {
							lstBook.get(posSach).setSoLuong(lstBook.get(posSach).getSoLuong() - 1);
							lstChiTietPhieuMuon.add(new ChiTietPhieuMuon(phieuMuon.getMaPhieuMuon(), lstBook.get(posSach).getMaSach(), 1));
							loadTableDsMuon(lstChiTietPhieuMuon);
							loadTableSach(lstBook);
							isCommit = false;
						}
					}
				}
			}
		});
		btnAddBook.setIcon(new ImageIcon(FormChinhSuaPhieuMuon.class.getResource("/icons/add-to-cart.png")));
		btnAddBook.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnAddBook.setBounds(478, 668, 118, 30);
		contentPane.add(btnAddBook);
		
		dateTo = new JDateChooser();
		dateTo.getCalendarButton().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lbDateOutofBound.setVisible(false);
				isCommit = false;
			}
		});
		
		dateTo.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent event) {
			}
			public void inputMethodTextChanged(InputMethodEvent event) {
				lbDateOutofBound.setVisible(false);
				isCommit = false;
			}
		});
		dateTo.setBounds(343, 239, 253, 25);
		contentPane.add(dateTo);
		dateFrom = new JDateChooser();
		dateFrom.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent event) {
			}
			public void inputMethodTextChanged(InputMethodEvent event) {
				lbDateOutofBound.setVisible(false);
				isCommit = false;
			}
		});
		dateFrom.getCalendarButton().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lbDateOutofBound.setVisible(false);
				isCommit = false;
			}
		});
		dateFrom.setBounds(20, 239, 244, 25);
		contentPane.add(dateFrom);
		dateFrom.setDateFormatString("dd/MM/yyyy");
		dateTo.setDateFormatString("dd/MM/yyyy");
		
		
		dateFrom.setDate(phieuMuon.getNgayMuon());
		if (phieuMuon.getNgayTra() != null)
			dateTo.setDate(phieuMuon.getNgayTra());
		
		JLabel lblNgyMn = new JLabel("Ngày mượn");
		lblNgyMn.setIcon(new ImageIcon(FormChinhSuaPhieuMuon.class.getResource("/icons/deadline.png")));
		lblNgyMn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNgyMn.setBounds(20, 222, 118, 16);
		contentPane.add(lblNgyMn);
		
		JLabel lblNgyTr = new JLabel("Ngày trả");
		lblNgyTr.setIcon(new ImageIcon(FormChinhSuaPhieuMuon.class.getResource("/icons/deadline.png")));
		lblNgyTr.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNgyTr.setBounds(343, 222, 74, 16);
		contentPane.add(lblNgyTr);
		
		cbStatus = new JComboBox();
		cbStatus.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				isCommit = false;
			}
		});
		cbStatus.setBounds(121, 282, 143, 22);
		contentPane.add(cbStatus);
		
		JLabel lblTrangThai = new JLabel("Trạng thái");
		lblTrangThai.setIcon(new ImageIcon(FormChinhSuaPhieuMuon.class.getResource("/icons/complete.png")));
		lblTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblTrangThai.setBounds(20, 284, 91, 16);
		contentPane.add(lblTrangThai);
		cbStatus.addItem("Chưa trả");
		cbStatus.addItem("Đã trả");
		
		cbStatus.setSelectedIndex(phieuMuon.getTrangThai() ? 1 : 0);
		
		JButton btnQuayLi = new JButton("Quay Lại");
		btnQuayLi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wantToSave();
			}
		});
		btnQuayLi.setIcon(new ImageIcon(FormChinhSuaPhieuMuon.class.getResource("/icons/return.png")));
		btnQuayLi.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnQuayLi.setBounds(140, 668, 110, 30);
		contentPane.add(btnQuayLi);
		
		lbDateOutofBound = new JLabel("Ngày trả phải lớn hơn ngày mượn");
		lbDateOutofBound.setBackground(Color.RED);
		lbDateOutofBound.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lbDateOutofBound.setBounds(423, 222, 186, 16);
		lbDateOutofBound.setForeground(Color.RED);
		lbDateOutofBound.setVisible(false);
		contentPane.add(lbDateOutofBound);
	}
	
	public boolean check_date() {
		if (dateTo.getDate() != null) {
			System.out.println(dateFrom.getDate().toString() + ";" + dateTo.getDate().toString() + ";" + dateTo.getDate().after(dateFrom.getDate()));
			return dateTo.getDate().after(dateFrom.getDate());
		}
		return true;
	}
	public void loadTableSach(ArrayList<Book> lst) {
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("Mã sách");
		dtm.addColumn("Tên sách");
		dtm.addColumn("Tác giả");
		dtm.addColumn("Đơn giá");
		dtm.addColumn("Số lượng");
		dtm.addColumn("Thể loại");
		Object[] o = new Object[6];
		for (Book ch : lst) {
			o[0] = ch.getMaSach();
			o[1] = ch.getTenSach();
			o[2] = ch.getTacGia();
			o[3] = ch.getDonGia();
			o[4] = ch.getSoLuong();
			o[5] = ch.getTheLoai();
			dtm.addRow(o);
		}
		tableListBook.setModel(dtm);
		tableListBook.setRowHeight(30);
		setJTableColumnsWidth(tableListBook, 443, 5, 38.5, 35.5, 13, 5, 13);
	}
	
	public void loadTableDsMuon(ArrayList<ChiTietPhieuMuon> lst) {
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("Mã sách");
		dtm.addColumn("Tên sách");
		dtm.addColumn("Giá/Cuốn");
		dtm.addColumn("Số lượng");
		dtm.addColumn("Thành tiền");
		Object[] o = new Object[5];
		for (ChiTietPhieuMuon ch : lst) {
			o[0] = ch.getMaSach();
			Book t = BookBo.findByBookId(ch.getMaSach());
			o[1] = t.getTenSach();
			o[2] = t.getDonGia();
			o[3] = ch.getSoLuong();
			o[4] = ch.getSoLuong() * t.getDonGia();
			dtm.addRow(o);
		}
		tableDSMuon.setModel(dtm);
		tableDSMuon.setRowHeight(30);
	}
	
	public void updatePhieuMuon() {
		String s = cbStatus.getSelectedItem().toString();
		if (s.equals("Chưa trả"))
			phieuMuon.setTrangThai(false);
		else 
			phieuMuon.setTrangThai(true);
		
		phieuMuon.setNgayMuon(dateFrom.getDate());
		Date dto = dateTo.getDate();
		if (dto != null) {
			phieuMuon.setNgayTra(dto);
		}
		PhieuMuonBo.updatePhieuMuon(phieuMuon);
	}
	
	public void updateChiTietPhieuMuon() {
		for (ChiTietPhieuMuon ch : lstChiTietPhieuMuon) {
			
			ChiTietPhieuMuonBo.updateChiTietPhieuMuon(phieuMuon, BookBo.findByBookId(ch.getMaSach()), ch.getSoLuong());
		}
	}
	private void confirm() {
		updatePhieuMuon();
		updateChiTietPhieuMuon();
		parent.loadTableListPhieuMuon();
	}
	
	public void reloadTableDsMuon() {
		lstChiTietPhieuMuon = ChiTietPhieuMuonBo.getCTPMByMaPhieuMuon(phieuMuon);
		loadTableDsMuon(lstChiTietPhieuMuon);
	}
	public void reloadTableListBook() {
		lstBook = BookBo.getAllBook();
		loadTableSach(lstBook);
	}
	
	public void wantToSave() {
		if (!isCommit) {
			int op = JOptionPane.showConfirmDialog(null, "Xác nhận lưu ?");
			if (op == 0) {
				if (check_date()) {
					confirm();
				} else {
					lbDateOutofBound.setVisible(true);
				}
			} 
			if (op != 2 && check_date()) {
				thisFrm.dispose();
			}
			
		} else {
			thisFrm.dispose();
		}
	}
	public FormChinhSuaPhieuMuon(PhieuMuon pm, PanelPhieuMuon pr) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				wantToSave();
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.parent = pr;
		this.phieuMuon = pm;
		content();

		reloadTableDsMuon();
		reloadTableListBook();
		isCommit = true;
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
