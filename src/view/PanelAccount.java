package view;

import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import bean.Account;
import bo.AccountBo;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class PanelAccount extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableListAccount;

	private FormFindAccount formFind;
	private PanelAccount thisPanel = this;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	/**
	 * Create the panel.
	 */
	public PanelAccount() {
		createContents();
		loadTable(AccountBo.getAllAccount());
	}

	public void loadTable(ArrayList<Account> accounts) {
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("STT");
		dtm.addColumn("Tên đăng nhập");
		dtm.addColumn("Chức vụ");
		dtm.addColumn("Ngày tạo");
		
		int cntRow = 0;
		for (Account account: accounts) {
			Object[] row = new Object[4];
			row[0] = ++cntRow;
			row[1] = account.getUsername();
			row[2] = account.getRole() == 0 ? "Độc giả" : (account.getRole() == 1 ? "Thủ thư" : "Quản trị viên");
			row[3] = sdf.format(account.getCreateTime());
			dtm.addRow(row);
		}
		
		tableListAccount.setModel(dtm);
		setJTableColumnsWidth(tableListAccount, 1015, 4, 32, 32, 32);
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
		tableListAccount.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		tableListAccount.setRowHeight(30);
		scrollPane.setViewportView(tableListAccount);
		
		JLabel labelTitle = new JLabel("Quản lý tài khoản");
		labelTitle.setFont(new Font("Segoe UI", Font.BOLD, 25));
		labelTitle.setBounds(10, 11, 400, 40);
		add(labelTitle);
		
		JButton buttonAdd = new JButton("Thêm");
		buttonAdd.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		buttonAdd.setBounds(595, 20, 100, 30);
		add(buttonAdd);
		
		JButton buttonFind = new JButton("Tìm kiếm");
		buttonFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formFind.setVisible(true);
			}
		});
		buttonFind.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		buttonFind.setBounds(705, 20, 100, 30);
		add(buttonFind);
		
		JButton btnDelete = new JButton("Xoá");
		btnDelete.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnDelete.setBounds(925, 20, 100, 30);
		add(btnDelete);
		
		JButton btnEdit = new JButton("Chỉnh sửa");
		btnEdit.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnEdit.setBounds(815, 20, 100, 30);
		add(btnEdit);
		
		formFind = new FormFindAccount(thisPanel);
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
