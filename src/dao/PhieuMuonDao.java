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
				int trangThai = rs.getInt("trangThai");
				int maDocGia = rs.getInt("maDocGia");
				int maThuThu = rs.getInt("maThuThu");
				listPhieuMuon.add(new PhieuMuon(maPhieuMuon, ngayMuon, ngayTra, trangThai, maDocGia, maThuThu));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listPhieuMuon;
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
				int trangThai = rs.getInt("trangThai");
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
				int trangThai = rs.getInt("trangThai");
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
			stmt.setInt(3, phieuMuon.getTrangThai());
			int cl = stmt.executeUpdate();
			return cl > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	static public Boolean deletePhieuMuon(PhieuMuon phieuMuon) {
		try {
			String sql = "DELETE FROM vw_PhieuMuon WHERE maPhieuMuon = ?";
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
			String sql = "SELECT TOP 1 * FROM vw_PhieuMuon ORDER BY maPhieuMuon DESC";
			PreparedStatement ps = Database.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int maPhieuMuon = rs.getInt("maPhieuMuon");
				Date ngayMuon = rs.getDate("ngayMuon"), ngayTra = rs.getDate("ngayTra");
				int trangThai = rs.getInt("trangThai");
				int maDocGia = rs.getInt("maDocGia");
				int maThuThu = rs.getInt("maThuThu");
				pm = new PhieuMuon(maPhieuMuon, ngayMuon, ngayTra, trangThai, maDocGia, maThuThu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}
}
