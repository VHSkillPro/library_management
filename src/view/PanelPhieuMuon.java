package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import bean.PhieuMuon;
import bo.DocGiaBo;
import bo.PhieuMuonBo;
import bo.ThuThuBo;

import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class PanelPhieuMuon extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableListPhieuMuon;
	private FormChiTietPhieuMuon frmChiTietPhieuMuon;
	private FormTaoPhieuMuon frmTaoPM;
	private PanelPhieuMuon thisPanel = this;
	private FormFindPhieuMuon frmFindPM;
	
	/**
	 * Create the panel.
	 */
	//main
	public PanelPhieuMuon() {
		createContents();
		loadTableListPhieuMuon(PhieuMuonBo.getAllPhieuMuon());
	}
	
	void loadTableListPhieuMuon(ArrayList<PhieuMuon> PhieuMuons) {
		DefaultTableModel tb = new DefaultTableModel();
		tb.addColumn("Mã phiếu mượn");
		tb.addColumn("Ngày mượn");
		tb.addColumn("Ngày trả");
		tb.addColumn("Trạng thái");
		tb.addColumn("Tên độc giả");
		tb.addColumn("Tên thủ thư");
		
		Object[] oj = new Object[6];
		for (PhieuMuon pm : PhieuMuons) {
			oj[0] = pm.getMaPhieuMuon();
			oj[1] = pm.getNgayMuon();
			oj[2] = pm.getNgayTra();
			oj[3] = pm.getTrangThai() ? "Đã trả" : "Chưa trả";
			oj[4] = DocGiaBo.getDocGiaByMaDocGia(pm.getMaDocGia()).getHoTen();
			oj[5] = ThuThuBo.getThuThuByMaThuThu(pm.getMaThuThu()).getHoTen();
			tb.addRow(oj);
		}
		
		tableListPhieuMuon.setModel(tb);
		setJTableColumnsWidth(tableListPhieuMuon, 1015, 15, 20, 20, 15, 20, 20);
	}
	
	private void createContents() {
		setBounds(new Rectangle(0, 0, 1035, 680));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 62, 1015, 608);
		add(scrollPane);
		
		tableListPhieuMuon = new JTable() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {                
                return false;               
			};
		};
		tableListPhieuMuon.setRowHeight(30);
		DefaultTableCellRenderer rd = new DefaultTableCellRenderer();
		rd.setHorizontalTextPosition(DefaultTableCellRenderer.CENTER);
		tableListPhieuMuon.setDefaultRenderer(Object.class, rd);
		
		tableListPhieuMuon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
					e.consume();
					int row = tableListPhieuMuon.getSelectedRow();
					Integer ma = Integer.parseInt(tableListPhieuMuon.getValueAt(row, 0).toString());
					PhieuMuon pm = PhieuMuonBo.getPhieuMuonByMaPhieuMuon(ma);
					Integer maKH = pm.getMaDocGia();
					Integer maTT = pm.getMaThuThu();
					
					frmChiTietPhieuMuon = new FormChiTietPhieuMuon(ma, maKH, maTT);
					frmChiTietPhieuMuon.setVisible(true);
				}
			}
		});
		tableListPhieuMuon.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setColumnHeaderView(tableListPhieuMuon);
		scrollPane.setViewportView(tableListPhieuMuon);
		
		JLabel labelTitle = new JLabel("QUẢN LÝ PHIẾU MƯỢN");
		labelTitle.setFont(new Font("Segoe UI", Font.BOLD, 25));
		labelTitle.setBounds(50, 15, 300, 40);
		add(labelTitle);
		
		JButton buttonAdd = new JButton("Thêm");
		buttonAdd.setIcon(new ImageIcon(PanelPhieuMuon.class.getResource("/icons/plus-symbol-button.png")));
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmTaoPM = new FormTaoPhieuMuon(thisPanel);
				frmTaoPM.setVisible(true);
			}
		});
		
		buttonAdd.setFont(new Font("Segoe UI", Font.BOLD, 12));
		buttonAdd.setBounds(585, 20, 100, 30);
		add(buttonAdd);
		
		JButton btnDelete = new JButton("Xoá");
		btnDelete.setIcon(new ImageIcon(PanelPhieuMuon.class.getResource("/icons/trash.png")));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableListPhieuMuon.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu mượn muốn xóa !", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				} else {
					if(JOptionPane.showConfirmDialog(btnDelete, "Xác nhận xóa !") == 0) {
						TableModel md = tableListPhieuMuon.getModel();
						PhieuMuon pm = PhieuMuonBo.getPhieuMuonByMaPhieuMuon(Integer.valueOf(md.getValueAt(row, 0).toString()));
						PhieuMuonBo.deletePhieuMuon(pm);
						loadTableListPhieuMuon(PhieuMuonBo.getAllPhieuMuon());
					}
				}
			}
		});
		btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnDelete.setBounds(935, 20, 90, 30);
		add(btnDelete);
		
		JButton btnEdit = new JButton("Chỉnh sửa");
		btnEdit.setIcon(new ImageIcon(PanelPhieuMuon.class.getResource("/icons/edit.png")));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableListPhieuMuon.getSelectedRow();
				TableModel md = tableListPhieuMuon.getModel();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu mượn muốn chỉnh sửa !");
				} else {
					PhieuMuon pm = PhieuMuonBo.getPhieuMuonByMaPhieuMuon(Integer.parseInt(md.getValueAt(row, 0).toString()));
					new FormChinhSuaPhieuMuon(pm, thisPanel).setVisible(true);
				}
			}
		});
		btnEdit.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnEdit.setBounds(815, 20, 110, 30);
		add(btnEdit);
		
		JButton btnSearch = new JButton("Tìm kiếm");
		btnSearch.setIcon(new ImageIcon(PanelPhieuMuon.class.getResource("/icons/search.png")));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmFindPM = new FormFindPhieuMuon(thisPanel);
				frmFindPM.setVisible(true);
			}
		});
		btnSearch.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSearch.setBounds(695, 20, 110, 30);
		add(btnSearch);
		
		JButton buttonReload = new JButton("Làm mới");
		buttonReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTableListPhieuMuon(PhieuMuonBo.getAllPhieuMuon());
			}
		});
		buttonReload.setIcon(new ImageIcon(PanelPhieuMuon.class.getResource("/icons/refresh.png")));
		buttonReload.setFont(new Font("Segoe UI", Font.BOLD, 12));
		buttonReload.setBounds(465, 20, 110, 30);
		add(buttonReload);
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
