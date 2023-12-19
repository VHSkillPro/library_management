package bo;
import java.util.ArrayList;

import bean.ChiTietPhieuMuon;
import dao.ChiTietPhieuMuonDao;

public class ChiTietPhieuMuonBo {
	static public ArrayList<ChiTietPhieuMuon> getCTPMByMaPhieuMuon(int maPM) {
		return ChiTietPhieuMuonDao.getCTPMByMaPhieuMuon(maPM);
	}
	static public boolean checkDupicate(int ma, ArrayList<ChiTietPhieuMuon> lst) {
		for (ChiTietPhieuMuon l : lst) {
			if (l.getMaSach() == ma && l.getSoLuong() > 0) {
				return true;
			}
		}
		return false;
	}
	static public int updateChiTietPhieuMuon(int maPm, int maSach, int sl) {
		return ChiTietPhieuMuonDao.updateChiTietPhieuMuon(maPm, maSach, sl);
	}
	static public ChiTietPhieuMuon getCTPMByMaPhieuMuonandMaSach(int maPm, int maSach) {
		return ChiTietPhieuMuonDao.getCTPMByMaPhieuMuonandMaSach(maPm, maSach);
	}
	static public int getPosInArraybyId(int maPm, int maSach, ArrayList<ChiTietPhieuMuon> lst) {
		int pos = -1;
		for (int i = 0; i < lst.size(); i++) {
			ChiTietPhieuMuon ct = lst.get(i);
			if (ct.getMaPhieuMuon() == maPm && ct.getMaSach() == maSach) {
				pos = i;
				break;
			}
		}
		return pos;
	}
}
