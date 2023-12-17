package view;

import java.awt.Font;
import java.awt.Rectangle;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import bean.DocGia;
import bo.DocGiaBo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelDocGia extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableListDocGia;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private PanelDocGia thisPanel = this;
	private FormEditDocGia formEdit;

	
	/**
	 * Create the panel.
	 */
	public PanelDocGia() {
		createContents();
		clickRow();
		loadTable(DocGiaBo.getAllDocGia());
	}
	
	private void clickRow() {
		tableListDocGia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int maDocGia = (int) tableListDocGia.getValueAt(tableListDocGia.getSelectedRow(), 0);
					formEdit = new FormEditDocGia(maDocGia, thisPanel);
					formEdit.setVisible(true);
				}
			}
		});
	}
	
	public void loadTable(ArrayList<DocGia> listDocGia) {
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("Mã độc giả");
		dtm.addColumn("Họ và tên");
		dtm.addColumn("Giới tính");
		dtm.addColumn("Ngày sinh");
		
		for (DocGia docGia: listDocGia) {
			Object[] row = new Object[4];
			row[0] = docGia.getMaDocGia();
			row[1] = docGia.getHoTen();
			row[2] = docGia.getGioiTinh() ? "Nam" : "Nữ";
			row[3] = sdf.format(docGia.getNgaySinh());
			dtm.addRow(row);
		}
		
		tableListDocGia.setModel(dtm);
		setJTableColumnsWidth(tableListDocGia, 1015, 10, 50, 10, 30);
	}
	
	private void createContents() {
		setBounds(new Rectangle(0, 0, 1035, 680));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 62, 1015, 608);
		add(scrollPane);
		
		tableListDocGia = new JTable() {
			private static final long serialVersionUID = 1L;
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
	            return false;
	         }
		};
		tableListDocGia.setRowHeight(30);
		scrollPane.setViewportView(tableListDocGia);
		
		JLabel labelTitle = new JLabel("Quản lý độc giả");
		labelTitle.setFont(new Font("Segoe UI", Font.BOLD, 25));
		labelTitle.setBounds(10, 11, 400, 40);
		add(labelTitle);
		
		JButton buttonAdd = new JButton("Thêm");
		buttonAdd.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		buttonAdd.setBounds(705, 20, 100, 30);
		add(buttonAdd);
		
		JButton buttonFind = new JButton("Tìm kiếm");
		buttonFind.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		buttonFind.setBounds(815, 20, 100, 30);
		add(buttonFind);
		
		JButton btnDelete = new JButton("Xoá");
		btnDelete.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnDelete.setBounds(925, 20, 100, 30);
		add(btnDelete);
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
