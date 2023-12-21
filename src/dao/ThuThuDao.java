package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import bean.ThuThu;

public class ThuThuDao {
	static public ArrayList<ThuThu> getAllThuThu() {
		ArrayList<ThuThu> listThuThu = new ArrayList<ThuThu>();
		
		try {
			String sql = "select * from ThuThu";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int maThuThu = rs.getInt("maThuThu");
				String hoTen = rs.getString("hoTen");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				String email = rs.getString("email");
				String soDienThoai = rs.getString("soDienThoai");
				String diaChi = rs.getString("diaChi");
				String username = rs.getString("username");
				listThuThu.add(new ThuThu(maThuThu, hoTen, gioiTinh, email, soDienThoai, diaChi, username));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listThuThu;
	}

	static public ThuThu getThuThuByUsername(String username) {
		ThuThu ThuThu = null;
		
		try {
			String sql = "select * from ThuThu where username = ?";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				int maThuThu = rs.getInt("maThuThu");
				String hoTen = rs.getString("hoTen");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				String email = rs.getString("email");
				String soDienThoai = rs.getString("soDienThoai");
				String diaChi = rs.getString("diaChi");
				ThuThu = new ThuThu(maThuThu, hoTen, gioiTinh, email, soDienThoai, diaChi, username);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ThuThu;
	}
	
	static public ThuThu getThuThuByMaThuThu(int maThuThu) {
		ThuThu ThuThu = null;
		
		try {
			String sql = "SELECT * FROM dbo.ThuThu WHERE maThuThu = ?";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			stmt.setInt(1, maThuThu);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				String hoTen = rs.getString("hoTen");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				String email = rs.getString("email");
				String soDienThoai = rs.getString("soDienThoai");
				String diaChi = rs.getString("diaChi");
				String username = rs.getString("username");
				
				ThuThu = new ThuThu(maThuThu, hoTen, gioiTinh, email, soDienThoai, diaChi, username);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ThuThu;
	}
	
	static public Boolean insertThuThu(ThuThu thuThu) {
		try {
			String sql = "insert into ThuThu(hoTen, gioiTinh, email, soDienThoai, diaChi, username)\n"
					   + "values (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			stmt.setString(1, thuThu.getHoTen());
			stmt.setBoolean(2, thuThu.getGioiTinh());
			stmt.setString(3, thuThu.getEmail());
			stmt.setString(4, thuThu.getSoDienThoai());
			stmt.setString(5, thuThu.getDiaChi());
			stmt.setString(6, thuThu.getUsername());
			
			stmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	static public Boolean deleteThuThu(int maThuThu) {
		try {
			String sql = "delete from ThuThu\n"
					   + "where maThuThu = ?";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			stmt.setInt(1, maThuThu);
			
			stmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
