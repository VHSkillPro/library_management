package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import bean.DocGia;

public class DocGiaDao {
	static public ArrayList<DocGia> getAllDocGia() {
		ArrayList<DocGia> listDocGia = new ArrayList<DocGia>();
		
		try {
			String sql = "select * from DocGia";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int maDocGia = rs.getInt("maDocGia");
				String hoTen = rs.getString("hoTen");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				Date ngaySinh = new Date(rs.getDate("ngaySinh").getTime());
				String email = rs.getString("email");
				String soDienThoai = rs.getString("soDienThoai");
				String diaChi = rs.getString("diaChi");
				String username = rs.getString("username");
				listDocGia.add(new DocGia(maDocGia, hoTen, gioiTinh, ngaySinh, email, soDienThoai, diaChi, username));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listDocGia;
	}

	static public DocGia getDocGiaByUsername(String username) {
		DocGia docGia = null;
		
		try {
			String sql = "select * from DocGia where username = ?";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				int maDocGia = rs.getInt("maDocGia");
				String hoTen = rs.getString("hoTen");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				Date ngaySinh = new Date(rs.getDate("ngaySinh").getTime());
				String email = rs.getString("email");
				String soDienThoai = rs.getString("soDienThoai");
				String diaChi = rs.getString("diaChi");
				docGia = new DocGia(maDocGia, hoTen, gioiTinh, ngaySinh, email, soDienThoai, diaChi, username);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return docGia;
	}
	
	static public DocGia getDocGiaByMaDocGia(int maDocGia) {
		DocGia docGia = null;
		
		try {
			String sql = "select * from DocGia where maDocGia = ?";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			stmt.setInt(1, maDocGia);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				String hoTen = rs.getString("hoTen");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				Date ngaySinh = new Date(rs.getDate("ngaySinh").getTime());
				String email = rs.getString("email");
				String soDienThoai = rs.getString("soDienThoai");
				String diaChi = rs.getString("diaChi");
				String username = rs.getString("username");
				docGia = new DocGia(maDocGia, hoTen, gioiTinh, ngaySinh, email, soDienThoai, diaChi, username);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return docGia;
	}
	
	static public ArrayList<DocGia> findDocGia(String maDocGia, String hoTen, int gioiTinh, Date fromDate, Date toDate, String email, String soDienThoai) {
		ArrayList<DocGia> listDocGia = new ArrayList<DocGia>();
		
		try {
			String sql = "exec proc_FindDocGia\r\n"
					+ "	@maDocGia = ?,\r\n"
					+ "	@hoTen = ?,\r\n"
					+ "	@gioiTinh = ?,\r\n"
					+ "	@fromDate = ?,\r\n"
					+ "	@toDate = ?,\r\n"
					+ "	@email = ?,\r\n"
					+ "	@soDienThoai = ?";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			stmt.setString(1, maDocGia);
			stmt.setString(2, hoTen);
			stmt.setInt(3, gioiTinh);
			stmt.setDate(4, fromDate == null ? null : new java.sql.Date(fromDate.getTime()));
			stmt.setDate(5, toDate == null ? null : new java.sql.Date(toDate.getTime()));
			stmt.setString(6, email);
			stmt.setString(7, soDienThoai);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int _maDocGia = rs.getInt("maDocGia");
				String _hoTen = rs.getString("hoTen");
				boolean _gioiTinh = rs.getBoolean("gioiTinh");
				Date _ngaySinh = new Date(rs.getDate("ngaySinh").getTime());
				String _email = rs.getString("email");
				String _soDienThoai = rs.getString("soDienThoai");
				String _diaChi = rs.getString("diaChi");
				String _username = rs.getString("username");
				listDocGia.add(new DocGia(_maDocGia, _hoTen, _gioiTinh, _ngaySinh, _email, _soDienThoai, _diaChi, _username));
			}
			
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listDocGia;
	}
	
	static public Boolean insertDocGia(DocGia docGia) {
		try {
			String sql = "insert into DocGia(hoTen, gioiTinh, ngaySinh, email, soDienThoai, diaChi, username)\n"
					   + "values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			stmt.setString(1, docGia.getHoTen());
			stmt.setBoolean(2, docGia.getGioiTinh());
			stmt.setDate(3, new java.sql.Date(docGia.getNgaySinh().getTime()));
			stmt.setString(4, docGia.getEmail());
			stmt.setString(5, docGia.getSoDienThoai());
			stmt.setString(6, docGia.getDiaChi());
			stmt.setString(7, docGia.getUsername());
			
			stmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	static public Boolean updateDocGia(int maDocGia, String hoTen, boolean gioiTinh, Date ngaySinh, String email, String soDienThoai, String diaChi) {
		try {
			String sql = "update DocGia\r\n"
					+ "set hoTen = ?,\r\n"
					+ "	gioiTinh = ?,\r\n"
					+ "	ngaySinh = ?,\r\n"
					+ "	email = ?,\r\n"
					+ "	soDienThoai = ?,\r\n"
					+ "	diaChi = ?\r\n"
					+ "where maDocGia = ?";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			stmt.setString(1, hoTen);
			stmt.setBoolean(2, gioiTinh);
			stmt.setDate(3, new java.sql.Date(ngaySinh.getTime()));
			stmt.setString(4, email);
			stmt.setString(5, soDienThoai);
			stmt.setString(6, diaChi);
			stmt.setInt(7, maDocGia);
			
			stmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	static public Boolean deleteDocGia(int maDocGia) {
		try {
			String sql = "delete from DocGia\n"
					   + "where maDocGia = ?";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			stmt.setInt(1, maDocGia);
			
			stmt.executeUpdate();
			stmt.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
