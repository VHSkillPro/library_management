package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Book;
import bean.ChiTietPhieuMuon;
import bean.PhieuMuon;

public class ChiTietPhieuMuonDao {
	static public ArrayList<ChiTietPhieuMuon> getCTPMByMaPhieuMuon(PhieuMuon pm) {
		ArrayList<ChiTietPhieuMuon> lst = new ArrayList<ChiTietPhieuMuon>();
		try {
			String sql = "SELECT * FROM ChiTietPhieuMuon WHERE maPhieuMuon = ?";
			PreparedStatement ps = Database.getConnection().prepareStatement(sql);
			ps.setInt(1, pm.getMaPhieuMuon());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int maSach = rs.getInt(2);
				int sl = rs.getInt(3);
				lst.add(new ChiTietPhieuMuon(pm.getMaPhieuMuon(), maSach, sl));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}
	static public int updateChiTietPhieuMuon(PhieuMuon pm, Book b, int sl) {
		int res = -1;
		try {
			String sql = "UPDATE vw_ChiTietPhieuMuon SET soLuong=? WHERE maPhieuMuon=? AND maSach=?";
			PreparedStatement ps = Database.getConnection().prepareStatement(sql);
			ps.setInt(2, pm.getMaPhieuMuon());
			ps.setInt(3, b.getMaSach());
			ps.setInt(1, sl);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	static public ChiTietPhieuMuon getCTPMByMaPhieuMuonandMaSach(PhieuMuon pm, Book b) {
		ChiTietPhieuMuon ch = null;
		try {
			String sql = "SELECT * FROM ChiTietPhieuMuon WHERE maPhieuMuon=? AND maSach=?";
			PreparedStatement ps = Database.getConnection().prepareStatement(sql);
			ps.setInt(1, pm.getMaPhieuMuon());
			ps.setInt(2, b.getMaSach());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ch = new ChiTietPhieuMuon(rs.getInt(1), rs.getInt(2), rs.getInt(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ch;
	}
	static public int insertChiTietPhieuMuon(PhieuMuon pm, Book b) {
		int res = 0;
		try {
			String sql = "INSERT INTO ChiTietPhieuMuon(maPhieuMuon, maSach) VALUES (?, ?)";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			stmt.setInt(1, pm.getMaPhieuMuon());
			stmt.setInt(2, b.getMaSach());
			res = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}
