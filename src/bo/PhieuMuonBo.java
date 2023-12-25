package bo;
import java.util.ArrayList;
import java.util.Date;

import bean.PhieuMuon;
import dao.PhieuMuonDao;

public class PhieuMuonBo {
	static public ArrayList<PhieuMuon> getAllPhieuMuon() {
		return PhieuMuonDao.getAllPhieuMuon();
	}
	
	static public ArrayList<PhieuMuon> getPhieuMuonByMaDocGia(int ma) {
		return PhieuMuonDao.getPhieuMuonByMaDocGia(ma);
	}
	
	static public PhieuMuon getPhieuMuonByMaPhieuMuon(int ma) {
		return PhieuMuonDao.getPhieuMuonByMaPhieuMuon(ma);
	}
	
	static public boolean insertPhieuMuon(PhieuMuon pm) {
		return PhieuMuonDao.insertPhieuMuon(pm);
	}
	static public PhieuMuon getLastestInsert() {
		return PhieuMuonDao.getLastestInsert();
	}
	static public Boolean deletePhieuMuon(PhieuMuon phieuMuon) {
		return PhieuMuonDao.deletePhieuMuon(phieuMuon);
	}
	static public int updatePhieuMuon(PhieuMuon pm) {
		return PhieuMuonDao.updatePhieuMuon(pm);
	}
	static public ArrayList<PhieuMuon> findPhieuMuon(String maPm, int trangThai, Date from, Date to, String maTT, String maKH) {
		return PhieuMuonDao.findPhieuMuon(maPm, trangThai, from, to, maTT, maKH);
	}
}
