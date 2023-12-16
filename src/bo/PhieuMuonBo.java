package bo;
import java.util.ArrayList;

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
	
	
}
