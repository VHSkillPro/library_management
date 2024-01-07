package view;

import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import bean.ThuThu;
import bo.AccountBo;
import bo.ThuThuBo;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;

public class PanelThuThu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableListAccount;
	private JButton buttonAdd;
	private JButton buttonFind;
	private JButton buttonChangePassword;
	private JButton btnDelete;
	
	private PanelThuThu thisPanel = this;
	private FormAddThuThu formAdd = new FormAddThuThu(thisPanel);
	private FormFindThuThu formFind = new FormFindThuThu(thisPanel);
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * Create the panel.
	 */
	public PanelThuThu() {
		createContents();
		loadTable(ThuThuBo.getAllThuThu());
	}

	public void loadTable(ArrayList<ThuThu> listThuthu) {
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("Mã độc giả");
		dtm.addColumn("Họ và tên");
		dtm.addColumn("Giới tính");
		dtm.addColumn("Ngày sinh");
		dtm.addColumn("Số điện thoại");
		dtm.addColumn("Chức vụ");
		dtm.addColumn("Tên đăng nhập");
		
		for (ThuThu thuThu: listThuthu) {
			Object[] row = new Object[7];
			row[0] = thuThu.getMaThuThu();
			row[1] = thuThu.getHoTen();
			row[2] = thuThu.getGioiTinh() == true ? "Nam" : "Nữ";
			row[3] = sdf.format(thuThu.getNgaySinh());
			row[4] = thuThu.getSoDienThoai();
			row[5] = AccountBo.getAccountByUsername(thuThu.getUsername()).getRole() == 1 ? "Thủ thư" : "Quản trị viên";
			row[6] = thuThu.getUsername();
			dtm.addRow(row);
		}
		
		tableListAccount.setModel(dtm);
		setJTableColumnsWidth(tableListAccount, 1015, 7, 33, 10, 10, 15, 15, 10);
	}
	
	private void createContents() {
		setBounds(new Rectangle(0, 0, 1035, 680));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 62, 1015, 608);
		add(scrollPane);
		
		tableListAccount = new JTable() {
			private static final long serialVersionUID = 1L;
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
	            return false;
	         }
		};
		tableListAccount.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tableListAccount.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		tableListAccount.setRowHeight(30);
		scrollPane.setViewportView(tableListAccount);
		
		JLabel labelTitle = new JLabel("QUẢN LÝ THỦ THƯ");
		labelTitle.setFont(new Font("Segoe UI", Font.BOLD, 25));
		labelTitle.setBounds(50, 15, 250, 40);
		add(labelTitle);
		
		JButton buttonReload = new JButton("Làm mới");
		buttonReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable(ThuThuBo.getAllThuThu());
			}
		});
		buttonReload.setIcon(new ImageIcon(PanelThuThu.class.getResource("/icons/refresh.png")));
		buttonReload.setFont(new Font("Segoe UI", Font.BOLD, 12));
		buttonReload.setBounds(445, 20, 110, 30);
		add(buttonReload);
		
		buttonAdd = new JButton("Thêm");
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formAdd.setVisible(true);
			}
		});
		buttonAdd.setIcon(new ImageIcon(PanelThuThu.class.getResource("/icons/plus-symbol-button.png")));
		buttonAdd.setFont(new Font("Segoe UI", Font.BOLD, 12));
		buttonAdd.setBounds(565, 20, 100, 30);
		add(buttonAdd);
		
		buttonFind = new JButton("Tìm kiếm");
		buttonFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formFind.setVisible(true);
			}
		});
		buttonFind.setIcon(new ImageIcon(PanelThuThu.class.getResource("/icons/search.png")));
		buttonFind.setFont(new Font("Segoe UI", Font.BOLD, 12));
		buttonFind.setBounds(675, 20, 110, 30);
		add(buttonFind);
		
		buttonChangePassword = new JButton("Đổi mật khẩu");
		buttonChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tableListAccount.getSelectedRow();
				
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản cần đổi mật khẩu", "Thông báo", JOptionPane.ERROR_MESSAGE);
				}
				else {
					String username = (String) tableListAccount.getValueAt(tableListAccount.getSelectedRow(), 6);
					(new FormChangePasswordThuThu(username)).setVisible(true);
				}
				
			}
		});
		buttonChangePassword.setIcon(new ImageIcon(PanelThuThu.class.getResource("/icons/edit.png")));
		buttonChangePassword.setFont(new Font("Segoe UI", Font.BOLD, 12));
		buttonChangePassword.setBounds(795, 20, 130, 30);
		add(buttonChangePassword);
		
		btnDelete = new JButton("Xoá");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tableListAccount.getSelectedRow();
				
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn dữ liệu cần xoá", "Thông báo", JOptionPane.ERROR_MESSAGE);
				}
				else {
					int maThuThu = (int) tableListAccount.getValueAt(tableListAccount.getSelectedRow(), 0);
					if (JOptionPane.showConfirmDialog(null, "Bạn có muốn xoá ?") == 0) {
						if (ThuThuBo.deleteThuThu(maThuThu)) {
							JOptionPane.showMessageDialog(null, "Xoá thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
							loadTable(ThuThuBo.getAllThuThu());
						}
						else {
							JOptionPane.showMessageDialog(null, "Xoá thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		btnDelete.setIcon(new ImageIcon(PanelThuThu.class.getResource("/icons/trash.png")));
		btnDelete.setSelectedIcon(null);
		btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnDelete.setBounds(935, 20, 90, 30);
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
