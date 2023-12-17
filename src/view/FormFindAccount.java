package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

import bo.AccountBo;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.awt.event.ActionEvent;

public class FormFindAccount extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputUsername;
	private JComboBox<String> comboRole;
	private JDateChooser dateChooserFromDate;
	private JDateChooser dateChooserToDate;
	private JButton buttonFind;

	private PanelAccount parent;
	private FormFindAccount thisForm = this;
	
	/**
	 * Create the frame.
	 */
	public FormFindAccount(PanelAccount parent) {
		this.parent = parent;
		createContents();
		FindAccount();
	}
	
	public void FindAccount() {
		buttonFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = inputUsername.getText().strip();
				Integer role = comboRole.getSelectedIndex() - 1;
				Date fromDate = dateChooserFromDate.getDate();
				Date toDate = dateChooserToDate.getDate();
				parent.loadTable(AccountBo.findAccount(username, role, fromDate, toDate));
				thisForm.dispatchEvent(new WindowEvent(thisForm, WindowEvent.WINDOW_CLOSING));
			}
		});
	}
	
	private void createContents() {
		setResizable(false);
		setTitle("Tìm kiếm tài khoản - Phần mềm quản lý thư viện");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelTitle = new JLabel("Tìm kiếm tài khoản");
		labelTitle.setFont(new Font("Segoe UI", Font.BOLD, 19));
		labelTitle.setBounds(100, 15, 180, 30);
		contentPane.add(labelTitle);
		
		JLabel labelUsername = new JLabel("Tên tài khoản");
		labelUsername.setIcon(new ImageIcon(FormFindAccount.class.getResource("/icons/user.png")));
		labelUsername.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		labelUsername.setBounds(20, 60, 100, 16);
		contentPane.add(labelUsername);
		
		inputUsername = new JTextField();
		labelUsername.setLabelFor(inputUsername);
		inputUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		inputUsername.setBounds(20, 80, 200, 25);
		contentPane.add(inputUsername);
		inputUsername.setColumns(10);
		
		JLabel labelRole = new JLabel("Chức vụ");
		labelRole.setIcon(new ImageIcon(FormFindAccount.class.getResource("/icons/user-management.png")));
		labelRole.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		labelRole.setBounds(230, 60, 70, 16);
		contentPane.add(labelRole);
		
		comboRole = new JComboBox<String>();
		comboRole.setModel(new DefaultComboBoxModel<String>(new String[] {"Tất cả", "Độc giả", "Thủ thư", "Quản trị viên"}));
		comboRole.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		labelRole.setLabelFor(comboRole);
		comboRole.setBounds(230, 80, 135, 25);
		contentPane.add(comboRole);
		
		dateChooserFromDate = new JDateChooser();
		dateChooserFromDate.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		dateChooserFromDate.setBounds(20, 140, 165, 25);
		contentPane.add(dateChooserFromDate);
		
		JLabel labelFromDate = new JLabel("Từ ngày");
		labelFromDate.setIcon(new ImageIcon(FormFindAccount.class.getResource("/icons/date-of-birth.png")));
		labelFromDate.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		labelFromDate.setBounds(20, 120, 70, 16);
		contentPane.add(labelFromDate);
		
		JLabel labelToDate = new JLabel("Đến ngày");
		labelToDate.setIcon(new ImageIcon(FormFindAccount.class.getResource("/icons/date-of-birth.png")));
		labelToDate.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		labelToDate.setBounds(200, 120, 82, 16);
		contentPane.add(labelToDate);
		
		dateChooserToDate = new JDateChooser();
		dateChooserToDate.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		dateChooserToDate.setBounds(200, 140, 165, 25);
		contentPane.add(dateChooserToDate);
		
		buttonFind = new JButton("Tìm kiếm");
		buttonFind.setIcon(new ImageIcon(FormFindAccount.class.getResource("/icons/search.png")));
		buttonFind.setFont(new Font("Segoe UI", Font.BOLD, 15));
		buttonFind.setBounds(130, 190, 130, 30);
		contentPane.add(buttonFind);
	}
}
