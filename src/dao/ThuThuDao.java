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
				Date ngaySinh = new java.sql.Date(rs.getDate("ngaySinh").getTime());
				String email = rs.getString("email");
				String soDienThoai = rs.getString("soDienThoai");
				String diaChi = rs.getString("diaChi");
				String username = rs.getString("username");
				listThuThu.add(new ThuThu(maThuThu, hoTen, gioiTinh, ngaySinh, email, soDienThoai, diaChi, username));
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
				Date ngaySinh = new java.sql.Date(rs.getDate("ngaySinh").getTime());
				String email = rs.getString("email");
				String soDienThoai = rs.getString("soDienThoai");
				String diaChi = rs.getString("diaChi");
				ThuThu = new ThuThu(maThuThu, hoTen, gioiTinh, ngaySinh, email, soDienThoai, diaChi, username);
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
				Date ngaySinh = new java.sql.Date(rs.getDate("ngaySinh").getTime());
				String email = rs.getString("email");
				String soDienThoai = rs.getString("soDienThoai");
				String diaChi = rs.getString("diaChi");
				String username = rs.getString("username");
				
				ThuThu = new ThuThu(maThuThu, hoTen, gioiTinh, ngaySinh, email, soDienThoai, diaChi, username);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ThuThu;
	}
	
	static public ArrayList<ThuThu> findThuThu(String maThuThu, String hoTen, int gioiTinh, Date fromDate, Date toDate, String email, String soDienThoai, int chucVu) {
		ArrayList<ThuThu> listThuThu = new ArrayList<ThuThu>();
		
		try {
			String sql = "exec proc_Find_Thu_Thu\r\n"
					+ "	@maThuThu = ?,\r\n"
					+ "	@hoTen = ?,\r\n"
					+ "	@gioiTinh = ?,\r\n"
					+ "	@fromDate = ?,\r\n"
					+ "	@toDate = ?,\r\n"
					+ "	@email = ?,\r\n"
					+ "	@soDienThoai = ?,\r\n"
					+ " @chucVu = ?";
			
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			stmt.setString(1, maThuThu);
			stmt.setString(2, hoTen);
			stmt.setInt(3, gioiTinh);
			stmt.setDate(4, fromDate == null ? null : new java.sql.Date(fromDate.getTime()));
			stmt.setDate(5, toDate == null ? null : new java.sql.Date(toDate.getTime()));
			stmt.setString(6, email);
			stmt.setString(7, soDienThoai);
			stmt.setInt(8, chucVu);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int _maThuThu = rs.getInt("maThuThu");
				String _hoTen = rs.getString("hoTen");
				boolean _gioiTinh = rs.getBoolean("gioiTinh");
				Date _ngaySinh = new java.sql.Date(rs.getDate("ngaySinh").getTime());
				String _email = rs.getString("email");
				String _soDienThoai = rs.getString("soDienThoai");
				String _diaChi = rs.getString("diaChi");
				String _username = rs.getString("username");
				listThuThu.add(new ThuThu(_maThuThu, _hoTen, _gioiTinh, _ngaySinh, _email, _soDienThoai, _diaChi, _username));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listThuThu;
	}
	
	static public Boolean insertThuThu(ThuThu thuThu) {
		try {
			String sql = "insert into ThuThu(hoTen, gioiTinh, ngaySinh, email, soDienThoai, diaChi, username)\n"
					   + "values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			stmt.setString(1, thuThu.getHoTen());
			stmt.setBoolean(2, thuThu.getGioiTinh());
			stmt.setDate(3, new java.sql.Date(thuThu.getNgaySinh().getTime()));
			stmt.setString(4, thuThu.getEmail());
			stmt.setString(5, thuThu.getSoDienThoai());
			stmt.setString(6, thuThu.getDiaChi());
			stmt.setString(7, thuThu.getUsername());
			
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
