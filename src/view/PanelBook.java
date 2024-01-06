package view;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

import bean.Book;
import bo.BookBo;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class PanelBook extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableListBook;
	private FormAddBook formAddBook = new FormAddBook(this);
	private FormSearch formSearch = new FormSearch(this);
	private PanelBook panelBook = this;
	private FormUpdate formUpdate;
	/**
	 * Create the panel.
	 */
	
	void loadTable(ArrayList<Book> lst) throws Exception {
		DefaultTableModel MH = new DefaultTableModel();
		MH.addColumn("Mã sách");
		MH.addColumn("Tên sách");
		MH.addColumn("Tác giả");
		MH.addColumn("Nhà xuất bản");
		MH.addColumn("Đơn giá");
		MH.addColumn("Số lượng");
		MH.addColumn("Thể loại");
		MH.addColumn("Mã thủ thư");
		for (Book x : lst) {
			Object[] t = new Object[8];
			t[0] = x.getMaSach();
			t[1] = x.getTenSach();
			t[2] = x.getTacGia();
			t[3] = x.getNhaXuatBan();
			t[4] = x.getDonGia();
			t[5] = x.getSoLuong();
			t[6] = x.getTheLoai();
			t[7] = x.getMaThuThu();
			MH.addRow(t);
		}
		tableListBook.setModel(MH);
	}
	
	public PanelBook() {
		createContents();
		try {
			ArrayList<Book> lst = BookBo.getAllBook();
			loadTable(lst);
		} catch (Exception e) {
			e.getStackTrace();
		}
		
	}
	private void createContents() {
		setBounds(new Rectangle(0, 0, 1035, 680));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				int row = tableListBook.getSelectedRow();
//				Book books;
			}
		});
		scrollPane.setBounds(10, 62, 1015, 608);
		add(scrollPane);
		
		tableListBook  = new JTable() {
			private static final long serialVersionUID = 1L;
			public boolean editCellAt(int row, int colum, java.util.EventObject e) {
				return false;
			}
		};
		tableListBook.setRowHeight(30);
		
		
		tableListBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int maSach = (int)tableListBook.getValueAt(tableListBook.getSelectedRow(), 0);
					formUpdate = new FormUpdate(maSach, panelBook);
					formUpdate.setVisible(true);
					
				}
			}
		});
		scrollPane.setViewportView(tableListBook);
//		scrollPane.setColumnHeaderView(tableListBook);
		
		JLabel labelTitle = new JLabel("QUẢN LÝ SÁCH");
		labelTitle.setFont(new Font("Segoe UI", Font.BOLD, 25));
		labelTitle.setBounds(50, 15, 220, 40);
		add(labelTitle);
		
		JButton buttonAdd = new JButton("Thêm");
		buttonAdd.setIcon(new ImageIcon(PanelBook.class.getResource("/icons/plus-symbol-button.png")));
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 formAddBook.setVisible(true);
			}
		});
		buttonAdd.setFont(new Font("Segoe UI", Font.BOLD, 12));
		buttonAdd.setBounds(705, 20, 100, 30);
		add(buttonAdd);
		
		JButton buttonFind = new JButton("Tìm kiếm");
		buttonFind.setIcon(new ImageIcon(PanelBook.class.getResource("/icons/search.png")));
		buttonFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formSearch.setVisible(true);
 			}
		});
		buttonFind.setFont(new Font("Segoe UI", Font.BOLD, 12));
		buttonFind.setBounds(815, 20, 110, 30);
		add(buttonFind);
		
		JButton btnDelete = new JButton("Xoá");
		btnDelete.setIcon(new ImageIcon(PanelBook.class.getResource("/icons/trash.png")));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int maSach = (int)tableListBook.getValueAt(tableListBook.getSelectedRow(), 0);
				Book books = BookBo.findByBookId(maSach);
				BookBo.deleteBook(books);
				try {
					ArrayList<Book> lst = BookBo.getAllBook();
					loadTable(lst);
				} catch (Exception e2) {
					e2.getStackTrace();
				}
			}
		});
		btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnDelete.setBounds(935, 20, 90, 30);
		add(btnDelete);
		
		JButton btnListbook = new JButton("Làm mới");
		btnListbook.setIcon(new ImageIcon(PanelBook.class.getResource("/icons/refresh.png")));
		btnListbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ArrayList<Book> lst = BookBo.getAllBook();
					loadTable(lst);
				} catch (Exception e2) {
					e2.getStackTrace();
				}
			}
		});
		
		
		btnListbook.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnListbook.setBounds(585, 20, 110, 30);
		add(btnListbook);
	}
}
