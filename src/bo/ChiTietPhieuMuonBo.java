package bo;
import java.util.ArrayList;

import bean.Book;
import bean.ChiTietPhieuMuon;
import bean.PhieuMuon;
import dao.ChiTietPhieuMuonDao;

public class ChiTietPhieuMuonBo {
	static public ArrayList<ChiTietPhieuMuon> getCTPMByMaPhieuMuon(PhieuMuon maPM) {
		return ChiTietPhieuMuonDao.getCTPMByMaPhieuMuon(maPM);
	}
	static public boolean checkDupicate(Book b, ArrayList<ChiTietPhieuMuon> lst) {
		for (ChiTietPhieuMuon l : lst) {
			if (l.getMaSach() == b.getMaSach() && l.getSoLuong() > 0) {
				return true;
			}
		}
		return false;
	}
	static public int updateChiTietPhieuMuon(PhieuMuon pm, Book b, int sl) {
		return ChiTietPhieuMuonDao.updateChiTietPhieuMuon(pm, b, sl);
	}
	static public ChiTietPhieuMuon getCTPMByMaPhieuMuonandMaSach(PhieuMuon pm, Book b) {
		return ChiTietPhieuMuonDao.getCTPMByMaPhieuMuonandMaSach(pm, b);
	}
	static public int insertChiTietPhieuMuon(PhieuMuon pm, Book b) {
		return ChiTietPhieuMuonDao.insertChiTietPhieuMuon(pm, b);
	}
	static public int getPosInArraybyId(PhieuMuon pm, Book b, ArrayList<ChiTietPhieuMuon> lst) {
		int pos = -1;
		for (int i = 0; i < lst.size(); i++) {
			ChiTietPhieuMuon ct = lst.get(i);
			if (ct.getMaPhieuMuon() == pm.getMaPhieuMuon() && ct.getMaSach() == b.getMaSach()) {
				pos = i;
				break;
			}
		}
		return pos;
	}
}
