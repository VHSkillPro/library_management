package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import bean.PhieuMuon;

public class PhieuMuonDao {
	static public ArrayList<PhieuMuon> getAllPhieuMuon() {
		ArrayList<PhieuMuon> listPhieuMuon = new ArrayList<PhieuMuon>();
		
		try {
			String sql = "select * from PhieuMuon";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int maPhieuMuon = rs.getInt("maPhieuMuon");
				Date ngayMuon = rs.getDate("ngayMuon"), ngayTra = rs.getDate("ngayTra");
				boolean trangThai = rs.getBoolean("trangThai");
				int maDocGia = rs.getInt("maDocGia");
				int maThuThu = rs.getInt("maThuThu");
				listPhieuMuon.add(new PhieuMuon(maPhieuMuon, ngayMuon, ngayTra, trangThai, maDocGia, maThuThu));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listPhieuMuon;
	}
	static public int updatePhieuMuon(PhieuMuon pm) {
		int rr = 0;
		try {
			String sql = "UPDATE PhieuMuon\r\n"
					+ "SET	ngayMuon = ?, ngayTra = ?, trangThai = ?\r\n"
					+ "WHERE maPhieuMuon = ?";
			PreparedStatement ps = Database.getConnection().prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(pm.getNgayMuon().getTime()));
			if (pm.getNgayTra() != null)
				ps.setDate(2, new java.sql.Date(pm.getNgayTra().getTime()));
			else 
				ps.setDate(2, null);
			ps.setBoolean(3, pm.getTrangThai());
			ps.setInt(4, pm.getMaPhieuMuon());
			
			 rr = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rr;
	}
	static public ArrayList<PhieuMuon> getPhieuMuonByMaDocGia(int ma) {
		ArrayList<PhieuMuon> listPhieuMuon = new ArrayList<PhieuMuon>();
		try {
			String sql = "select * from PhieuMuon where maDocGia=?";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int maPhieuMuon = rs.getInt("maPhieuMuon");
				Date ngayMuon = rs.getDate("ngayMuon"), ngayTra = rs.getDate("ngayTra");
				boolean trangThai = rs.getBoolean("trangThai");
				int maDocGia = rs.getInt("maDocGia");
				int maThuThu = rs.getInt("maThuThu");
				listPhieuMuon.add(new PhieuMuon(maPhieuMuon, ngayMuon, ngayTra, trangThai, maDocGia, maThuThu));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPhieuMuon;
	}

	static public PhieuMuon getPhieuMuonByMaPhieuMuon(int ma) {
		PhieuMuon res = null;
		try {
			String sql = "select * from dbo.PhieuMuon where maPhieuMuon=?";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			stmt.setInt(1, ma);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int maPhieuMuon = rs.getInt("maPhieuMuon");
				Date ngayMuon = rs.getDate("ngayMuon"), ngayTra = rs.getDate("ngayTra");
				boolean trangThai = rs.getBoolean("trangThai");
				int maDocGia = rs.getInt("maDocGia");
				int maThuThu = rs.getInt("maThuThu");
				res = new PhieuMuon(maPhieuMuon, ngayMuon, ngayTra, trangThai, maDocGia, maThuThu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	static public Boolean insertPhieuMuon(PhieuMuon phieuMuon) {
		try {
			String sql = "insert into PhieuMuon(maDocGia, maThuThu, trangThai) values(?, ?, ?)";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			stmt.setInt(1, phieuMuon.getMaDocGia());
			stmt.setInt(2, phieuMuon.getMaThuThu());
			stmt.setBoolean(3, phieuMuon.getTrangThai());
			int cl = stmt.executeUpdate();
			return cl > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	static public Boolean deletePhieuMuon(PhieuMuon phieuMuon) {
		try {
			String sql = "DELETE FROM PhieuMuon WHERE maPhieuMuon = ?";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			stmt.setInt(1, phieuMuon.getMaPhieuMuon());
			int cl = stmt.executeUpdate();
			return cl > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	static public PhieuMuon getLastestInsert() {
		PhieuMuon pm = null;
		try {
			String sql = "SELECT TOP 1 * FROM PhieuMuon ORDER BY maPhieuMuon DESC";
			PreparedStatement ps = Database.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int maPhieuMuon = rs.getInt("maPhieuMuon");
				Date ngayMuon = rs.getDate("ngayMuon"), ngayTra = rs.getDate("ngayTra");
				boolean trangThai = rs.getBoolean("trangThai");
				int maDocGia = rs.getInt("maDocGia");
				int maThuThu = rs.getInt("maThuThu");
				pm = new PhieuMuon(maPhieuMuon, ngayMuon, ngayTra, trangThai, maDocGia, maThuThu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}
	
	static public ArrayList<PhieuMuon> findPhieuMuon(String maPm, int trangThai, Date from, Date to, String maTT, String maKH) {
		ArrayList<PhieuMuon> pm = new ArrayList<PhieuMuon>();
		try {
			String sql = "EXEC proc_findPhieuMuon @maPhieuMuon=?,@trangThai=?,@from=?,@to=?,@maThuThu=?, @maDocGia=?";
			
			PreparedStatement ps = Database.getConnection().prepareStatement(sql);
			ps.setString(1, maPm);
			ps.setInt(2, trangThai);
			ps.setDate(3, from == null ? null : new java.sql.Date(from.getTime()));
			ps.setDate(4, to == null ? null : new java.sql.Date(to.getTime()));
			ps.setString(5, maTT);
			ps.setString(6, maKH);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int maPhieuMuon = rs.getInt(1);
				Date ngayMuon = rs.getDate(2), ngayTra = rs.getDate(3);
				boolean _trangThai = rs.getBoolean(4);
				int maDocGia = rs.getInt(5);
				int maThuThu = rs.getInt(6);
				pm.add(new PhieuMuon(maPhieuMuon, ngayMuon, ngayTra, _trangThai, maDocGia, maThuThu));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return pm;
	}
}
