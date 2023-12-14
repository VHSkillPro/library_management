package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import bean.Account;

public class AccountDao {
	
	static public Account getAccountByUsername(String username) {
		Account account = null;
		
		try {
			String sql = "select * from TaiKhoan where username = ?";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				String password = rs.getString("password");
				int role = rs.getInt("role");
				Date createTime = new Date(rs.getDate("datetime").getTime());
				account = new Account(username, password, role, createTime);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return account;
	}
	
	static public ArrayList<Account> getAllAccount() {
		ArrayList<Account> accounts = new ArrayList<Account>();
		
		try {
			String sql = "select * from TaiKhoan";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				int role = rs.getInt("role");
				Date createTime = new Date(rs.getDate("datetime").getTime());
				accounts.add(new Account(username, password, role, createTime));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return accounts;
	}
	
	static public Boolean insertAccount(Account account) {
		try {
			String sql = "insert into TaiKhoan (username, password, role, createTime) "
					   + "values (?, ?, ?, ?)";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			stmt.setString(1, account.getUsername());
			stmt.setString(2, account.getPassword());
			stmt.setInt(3, account.getRole());
			stmt.setDate(4, new java.sql.Date(account.getCreateTime().getTime()));
			
			if (stmt.execute()) {
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	static public Boolean updatePassword(String username, String password) {
		try {
			String sql = "update TaiKhoan "
					   + "set password = ? "
					   + "where username = ?";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			stmt.setString(1, password);
			stmt.setString(2, username);
			
			if (stmt.execute()) {
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	static public Boolean deleteAccount(String username) {
		try {
			String sql = "delete from TaiKhoan "
					   + "where username = ?";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			stmt.setString(1, username);
			
			if (stmt.execute()) {
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
