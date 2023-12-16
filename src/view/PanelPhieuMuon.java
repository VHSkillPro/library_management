package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import bean.PhieuMuon;
import bo.PhieuMuonBo;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelPhieuMuon extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableListPhieuMuon;
	private ArrayList<PhieuMuon> listPhieuMuon = new ArrayList<PhieuMuon>();
	private PhieuMuonBo phieuMuonBo = new PhieuMuonBo();
	private FormChiTietPhieuMuon frmChiTietPhieuMuon;
	/**
	 * Create the panel.
	 */
	//main
	public PanelPhieuMuon() {
		createContents();
		listPhieuMuon = phieuMuonBo.getAllPhieuMuon();
		loadTableListPhieuMuon(listPhieuMuon);
	}
	
	void loadTableListPhieuMuon(ArrayList<PhieuMuon> PhieuMuons) {
		DefaultTableModel tb = new DefaultTableModel();
		tb.addColumn("Mã phiếu mượn");
		tb.addColumn("Ngày mượn");
		tb.addColumn("Ngày trả");
		tb.addColumn("Trạng thái");
		tb.addColumn("Mã đọc giả");
		tb.addColumn("Mã thủ thư");
		Object[] oj = new Object[6];
		for (PhieuMuon pm : PhieuMuons) {
			oj[0] = pm.getMaPhieuMuon();
			oj[1] = pm.getNgayMuon();
			oj[2] = pm.getNgayTra();
			oj[3] = pm.getTrangThai();
			oj[4] = pm.getMaDocGia();
			oj[5] = pm.getMaThuThu();
			tb.addRow(oj);
		}
		tableListPhieuMuon.setModel(tb);
		setJTableColumnsWidth(tableListPhieuMuon, 1015, 13, 23.5, 23.5, 13.3, 13.3, 13.3);
		
	}
	private void createContents() {
		setBounds(new Rectangle(0, 0, 1035, 680));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 62, 1015, 608);
		add(scrollPane);
		
		tableListPhieuMuon = new JTable() {
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
				int row = tableListPhieuMuon.getSelectedRow();
				TableModel md = tableListPhieuMuon.getModel();
				Integer ma = Integer.parseInt(md.getValueAt(row, 0).toString());
				frmChiTietPhieuMuon = new FormChiTietPhieuMuon(ma);
				frmChiTietPhieuMuon.setVisible(true);
			}
		});
		tableListPhieuMuon.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setColumnHeaderView(tableListPhieuMuon);
		scrollPane.setViewportView(tableListPhieuMuon);
		
		JLabel labelTitle = new JLabel("Quản lý phiếu mượn");
		labelTitle.setFont(new Font("Segoe UI", Font.BOLD, 25));
		labelTitle.setBounds(10, 11, 400, 40);
		add(labelTitle);
		
		JButton buttonAdd = new JButton("Tạo phiếu mượn");
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		buttonAdd.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		buttonAdd.setBounds(551, 21, 141, 30);
		add(buttonAdd);
		
		JButton buttonFind = new JButton("Tìm kiếm");
		buttonFind.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		buttonFind.setBounds(705, 21, 100, 30);
		add(buttonFind);
		
		JButton btnDelete = new JButton("Xoá");
		btnDelete.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnDelete.setBounds(925, 20, 100, 30);
		add(btnDelete);
		
		JButton btnEdit = new JButton("Chỉnh sửa");
		btnEdit.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnEdit.setBounds(815, 21, 100, 30);
		add(btnEdit);
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
