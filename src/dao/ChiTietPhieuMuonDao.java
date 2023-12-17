package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.ChiTietPhieuMuon;

public class ChiTietPhieuMuonDao {
	static public ArrayList<ChiTietPhieuMuon> getCTPMByMaPhieuMuon(int maPM) {
		ArrayList<ChiTietPhieuMuon> lst = new ArrayList<ChiTietPhieuMuon>();
		try {
			String sql = "SELECT * FROM ChiTietPhieuMuon WHERE maPhieuMuon = ? AND soLuong > 0";
			PreparedStatement ps = Database.getConnection().prepareStatement(sql);
			ps.setInt(1, maPM);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int maSach = rs.getInt(2);
				int sl = rs.getInt(3);
				lst.add(new ChiTietPhieuMuon(maPM, maSach, sl));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}
	static public int updateChiTietPhieuMuon(int maPm, int maSach, int sl) {
		int res = -1;
		try {
			String sql = "UPDATE vw_ChiTietPhieuMuon SET soLuong=? WHERE maPhieuMuon=? AND maSach=?";
			PreparedStatement ps = Database.getConnection().prepareStatement(sql);
			ps.setInt(2, maPm);
			ps.setInt(3, maSach);
			ps.setInt(1, sl);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	static public ChiTietPhieuMuon getCTPMByMaPhieuMuonandMaSach(int maPm, int maSach) {
		ChiTietPhieuMuon ch = null;
		try {
			String sql = "SELECT * FROM ChiTietPhieuMuon WHERE maPhieuMuon=? AND maSach=?";
			PreparedStatement ps = Database.getConnection().prepareStatement(sql);
			ps.setInt(1, maPm);
			ps.setInt(2, maSach);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ch = new ChiTietPhieuMuon(rs.getInt(1), rs.getInt(2), rs.getInt(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ch;
	}
}
