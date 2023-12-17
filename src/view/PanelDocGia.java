package view;

import java.awt.Font;
import java.awt.Rectangle;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import bean.DocGia;
import bo.DocGiaBo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelDocGia extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableListDocGia;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private JButton buttonReload;
	private JButton btnDelete;
	private JButton buttonFind;
	private JButton buttonAdd;

	private FormEditDocGia formEdit;
	private PanelDocGia thisPanel = this;
	private FormFindDocGia formFind;
	
	/**
	 * Create the panel.
	 */
	public PanelDocGia() {
		createContents();
		findDocGia();
		clickRow();
		reloadTable();
		deleteRow();
		loadTable(DocGiaBo.getAllDocGia());
	}
	
	public void findDocGia() {
		buttonFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formFind.setVisible(true);
			}
		});
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
	
	public void deleteRow() {
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tableListDocGia.getSelectedRow();
				
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn dữ liệu cần xoá", "Thông báo", JOptionPane.ERROR_MESSAGE);
				}
				else {
					int maDocGia = (int) tableListDocGia.getValueAt(tableListDocGia.getSelectedRow(), 0);
					if (JOptionPane.showConfirmDialog(null, "Bạn có muốn xoá ?") == 0) {
						if (DocGiaBo.deleteDocGia(maDocGia)) {
							JOptionPane.showMessageDialog(null, "Xoá thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
							loadTable(DocGiaBo.getAllDocGia());
						}
						else {
							JOptionPane.showMessageDialog(null, "Xoá thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
	}
	
	public void reloadTable() {
		buttonReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable(DocGiaBo.getAllDocGia());
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
		
		JLabel labelTitle = new JLabel("QUẢN LÝ ĐỘC GIẢ");
		labelTitle.setFont(new Font("Segoe UI", Font.BOLD, 25));
		labelTitle.setBounds(50, 15, 250, 40);
		add(labelTitle);
		
		buttonAdd = new JButton("Thêm");
		buttonAdd.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		buttonAdd.setBounds(705, 20, 100, 30);
		add(buttonAdd);
		
		buttonFind = new JButton("Tìm kiếm");
		buttonFind.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		buttonFind.setBounds(815, 20, 100, 30);
		add(buttonFind);
		
		btnDelete = new JButton("Xoá");
		btnDelete.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnDelete.setBounds(925, 20, 100, 30);
		add(btnDelete);
		
		buttonReload = new JButton("Làm mới");
		buttonReload.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		buttonReload.setBounds(595, 20, 100, 30);
		add(buttonReload);
		
		formFind = new FormFindDocGia(thisPanel);
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
