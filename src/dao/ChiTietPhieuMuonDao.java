package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.ChiTietPhieuMuon;

public class ChiTietPhieuMuonDao {
	static public ArrayList<ChiTietPhieuMuon> getCTPMByMaPhieuMuon(int maPM) {
		ArrayList<ChiTietPhieuMuon> lst = new ArrayList<ChiTietPhieuMuon>();
		try {
			String sql = "SELECT * FROM ChiTietPhieuMuon WHERE maPhieuMuon = ?";
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
}
